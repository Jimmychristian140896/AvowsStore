package com.jimmy.avowsstore.domain.mappers

import com.jimmy.avowsstore.data.remote.dto.ProductDto
import com.jimmy.avowsstore.data.remote.dto.UserDto
import com.jimmy.avowsstore.domain.model.Product
import com.jimmy.avowsstore.domain.model.User

fun UserDto.toUser() = User(
    email = email,
    id = id,
    firstname = name.firstname,
    lastname = name.lastname,
    password = password,
    phone = phone,
    username = username,
    city = address.city,
    number = address.number,
    street = address.street,
    zipcode = address.zipcode,
    lat = address.geolocation.lat,
    long = address.geolocation.long
)


fun ProductDto.toProduct() = Product(
    id = id,
    name = title,
    description = description,
    price = price,
    imageUrl = image,
    rating = rating.rate,
    ratingCount = rating.count,
    category = category,
)