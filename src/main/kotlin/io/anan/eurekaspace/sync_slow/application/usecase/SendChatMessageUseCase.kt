package io.anan.eurekaspace.sync_slow.application.usecase

import io.anan.eurekaspace.sync_slow.domain.model.ChatMessage
import io.anan.eurekaspace.sync_slow.domain.port.ChatMessageMQPort
import io.anan.eurekaspace.sync_slow.domain.repository.ChatRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SendChatMessageUseCase(
        private val chatMessageMQPort: ChatMessageMQPort,
        private val chatRepository: ChatRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun execute(message: ChatMessage) {
        val savedMessage = chatRepository.saveMessage(message)
        log.info("saved message >>>  $savedMessage")
        chatMessageMQPort.publishMessage(savedMessage)
    }
}
