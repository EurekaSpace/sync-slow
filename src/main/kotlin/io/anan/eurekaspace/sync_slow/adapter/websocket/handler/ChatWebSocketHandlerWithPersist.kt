package io.anan.eurekaspace.sync_slow.adapter.websocket.handler

import com.fasterxml.jackson.databind.ObjectMapper
import io.anan.eurekaspace.sync_slow.application.usecase.SendChatMessageUseCase
import io.anan.eurekaspace.sync_slow.domain.model.ChatMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.CopyOnWriteArrayList

@Component
class ChatWebSocketHandlerWithPersist(
        private val sendChatMessageUseCase: SendChatMessageUseCase,
        private val objectMapper: ObjectMapper
) : TextWebSocketHandler() {

    // 연결된 세션 관리
    private val sessions = CopyOnWriteArrayList<WebSocketSession>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
        println("Session connected: ${session.id}")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("Received message: ${message.payload}")
        val roomId = session.uri?.path?.split("/")?.last() ?: throw IllegalArgumentException("Invalid URI")

        // 메시지 전송
        val savedChatMessage = sendChatMessageUseCase.executeWithoudKafka(
                ChatMessage(
                        roomId = roomId,
                        senderId = "user_001",
                        content = message.payload
                )
        )

        broadcastMessage(
                objectMapper.writeValueAsString(savedChatMessage)
        )
    }

    // Kafka에서 받은 메시지 브로드캐스트
    fun broadcastMessage(message: String) {
        for (session in sessions) {
            if (session.isOpen) {
                session.sendMessage(TextMessage(message))
            }
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session)
        println("Session disconnected: ${session.id}")
    }
}