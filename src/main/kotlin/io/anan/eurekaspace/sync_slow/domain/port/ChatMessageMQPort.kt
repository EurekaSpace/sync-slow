package io.anan.eurekaspace.sync_slow.domain.port

import io.anan.eurekaspace.sync_slow.domain.model.ChatMessage

interface ChatMessageMQPort {
    fun publishMessage(chatMessage: ChatMessage)
}
