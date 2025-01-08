package io.anan.eurekaspace.sync_slow.infra.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import io.anan.eurekaspace.sync_slow.domain.model.ChatMessage
import io.anan.eurekaspace.sync_slow.domain.port.ChatMessageMQPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ChatKafkaProducer(
        private val kafkaTemplate: KafkaTemplate<String, String>,
        private val objectMapper: ObjectMapper
) : ChatMessageMQPort {

    override fun publishMessage(chatMessage: ChatMessage) {
        kafkaTemplate.send(
                "chat",
                chatMessage.roomId,
                objectMapper.writeValueAsString(chatMessage)
        )
    }
}
