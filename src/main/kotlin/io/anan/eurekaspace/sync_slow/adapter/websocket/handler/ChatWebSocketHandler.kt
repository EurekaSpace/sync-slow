package io.anan.eurekaspace.sync_slow.adapter.websocket.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.sun.management.OperatingSystemMXBean
import io.anan.eurekaspace.sync_slow.domain.model.ChatMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.lang.management.ManagementFactory
import java.util.concurrent.CopyOnWriteArrayList

@Component
class ChatWebSocketHandler(
        private val objectMapper: ObjectMapper
) : TextWebSocketHandler() {
    val log = LoggerFactory.getLogger(this::class.java)

    // 연결된 세션 관리
    private val sessions = CopyOnWriteArrayList<WebSocketSession>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
        println("Session connected: ${session.id}")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        println("Received message: ${message.payload}")
        val roomId = session.uri?.path?.split("/")?.last() ?: throw IllegalArgumentException("Invalid URI")

        val chatMessage = ChatMessage(
                roomId = roomId,
                senderId = "user_001",
                content = message.payload
        )

        broadcastMessage(
                objectMapper.writeValueAsString(chatMessage)
        )
    }

    fun broadcastMessage(message: String) {
        for (session in sessions) {
            if (session.isOpen) {
                synchronized(session) {
                    try {
                        session.sendMessage(TextMessage(message))
                    } catch (e: Exception) {
                        log.error("send message failed by exception >>>> ${e.message}")
                    }
                }
            }
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session)
    }
}