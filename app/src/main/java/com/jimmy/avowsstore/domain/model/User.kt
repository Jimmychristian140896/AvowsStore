package com.jimmy.avowsstore.domain.model

import com.jimmy.avowsstore.data.remote.dto.UserDto.Address
import com.jimmy.avowsstore.data.remote.dto.UserDto.Geolocation
import com.jimmy.avowsstore.data.remote.dto.UserDto.Name
import kotlinx.serialization.SerialName

data class User(
    val email: String,
    val id: Int,
    val firstname: String,
    val lastname: String,
    val password: String,
    val phone: String,
    val username: String,
    val city: String,
    val number: Int,
    val street: String,
    val zipcode: String,
    val lat: String,
    val long: String
)
