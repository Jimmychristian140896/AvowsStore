package com.jimmy.avowsstore.domain.mappers

import com.jimmy.avowsstore.data.remote.dto.UserDto
import com.jimmy.avowsstore.domain.model.User

fun UserDto.toUser() = User(
    username = username,
    password = password
)
fun User.toUserDto() = UserDto(
    username = username,
    password = password
)