package io.anan.eurekaspace.sync_slow.infra.messaging

import io.anan.eurekaspace.sync_slow.adapter.websocket.handler.ChatWebSocketHandler
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class ChatKafkaConsumer(
        private val handlerMap: Map<String, ChatWebSocketHandler>
) {
    @KafkaListener(topicPattern = "chat-*", groupId = "chat-group")
    fun listen(record: ConsumerRecord<String, String>, ack: Acknowledgment) {
        try {
            // 키(key)와 메시지(value) 추출
            val key = record.key() ?: throw IllegalArgumentException("Key is null!")
            val message = record.value()

            println("Received Kafka Message: $message for room: $key")

            handlerMap[key]?.broadcastMessage(message) ?: println("No handler found for room: $key")

            // 수동 ACK 처리
            ack.acknowledge()
        } catch (e: Exception) {
            println("Error processing message: ${e.message}")
            e.printStackTrace()
        }
    }
}