package io.anan.eurekaspace.sync_slow.domain.repository

import io.anan.eurekaspace.sync_slow.domain.model.ChatMessage

interface ChatRepository {
    fun saveMessage(message: ChatMessage): ChatMessage
}