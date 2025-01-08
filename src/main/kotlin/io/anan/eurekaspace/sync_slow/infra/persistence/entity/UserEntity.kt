package io.anan.eurekaspace.sync_slow.infra.persistence.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class UserEntity(
        val id: String? = null,
        val username: String,
)
