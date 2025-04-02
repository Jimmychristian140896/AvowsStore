package com.jimmy.avowsstore.domain.mappers

import com.jimmy.avowsstore.data.local.entity.UserEntity
import com.jimmy.avowsstore.domain.model.User

fun UserEntity.toUser() = User(
    username = username,
    password = password
)
fun User.toUserEntity() = UserEntity(
    username = username,
    password = password
)