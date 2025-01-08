package io.anan.eurekaspace.sync_slow.infra.persistence.repository

import io.anan.eurekaspace.sync_slow.infra.persistence.entity.ChatMessageEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface ChatMessageMongoRepository: MongoRepository<ChatMessageEntity, String> {
    fun insert(message: ChatMessageEntity): ChatMessageEntity
}
