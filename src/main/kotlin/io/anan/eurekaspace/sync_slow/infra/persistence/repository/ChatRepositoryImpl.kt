package io.anan.eurekaspace.sync_slow.infra.persistence.repository

import io.anan.eurekaspace.sync_slow.domain.model.ChatMessage
import io.anan.eurekaspace.sync_slow.domain.repository.ChatRepository
import io.anan.eurekaspace.sync_slow.infra.persistence.mapper.toEntity
import io.anan.eurekaspace.sync_slow.infra.persistence.mapper.toModel
import org.springframework.stereotype.Repository

@Repository
class ChatRepositoryImpl(
    private val chatMessageMongoRepository: ChatMessageMongoRepository
): ChatRepository {
    override fun saveMessage(message: ChatMessage): ChatMessage {
        val savedEntity = chatMessageMongoRepository.insert(message.toEntity())
        return savedEntity.toModel()
    }
}
