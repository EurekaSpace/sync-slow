package io.anan.eurekaspace.sync_slow.infra.persistence.mapper

import io.anan.eurekaspace.sync_slow.domain.model.ChatMessage
import io.anan.eurekaspace.sync_slow.infra.persistence.entity.ChatMessageEntity

fun ChatMessage.toEntity() = ChatMessageEntity(
    id = id,
    roomId = roomId,
    senderId = senderId,
    content = content,
    timestamp = timestamp
)
fun ChatMessageEntity.toModel() = ChatMessage(
    id = id,
    roomId = roomId,
    senderId = senderId,
    content = content,
    timestamp = timestamp
)
