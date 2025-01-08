package io.anan.eurekaspace.sync_slow.domain.model

import java.time.LocalDateTime

data class ChatRoom(
        val id: String? = null,
        val name: String? = null,
        val userIds: List<String>,
        val messageIds: List<String> = emptyList(),
        val createdAt: LocalDateTime = LocalDateTime.now()
)