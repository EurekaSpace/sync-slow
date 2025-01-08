package io.anan.eurekaspace.sync_slow.infra.persistence.mapper

import io.anan.eurekaspace.sync_slow.domain.model.User
import io.anan.eurekaspace.sync_slow.infra.persistence.entity.UserEntity

fun User.toEntity() = UserEntity(
    id = id,
    username = username
)