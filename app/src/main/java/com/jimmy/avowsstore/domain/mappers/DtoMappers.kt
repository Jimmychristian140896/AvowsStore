package com.jimmy.avowsstore.domain.mappers

import com.jimmy.avowsstore.data.local.entity.TransactionProductEntity
import com.jimmy.avowsstore.data.remote.dto.CartDto
import com.jimmy.avowsstore.data.remote.dto.LoginDto
import com.jimmy.avowsstore.data.remote.dto.ProductDto
import com.jimmy.avowsstore.data.remote.dto.UserDto
import com.jimmy.avowsstore.domain.model.Cart
import com.jimmy.avowsstore.domain.model.CartProduct
import com.jimmy.avowsstore.domain.model.Login
import com.jimmy.avowsstore.domain.model.Product
import com.jimmy.avowsstore.domain.model.Transaction
import com.jimmy.avowsstore.domain.model.TransactionProduct
import com.jimmy.avowsstore.domain.model.User

fun LoginDto.toLogin(
    userId: Int,
    username: String
) = Login(
    token = token,
    userId = userId,
    username = username
)

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

fun ProductDto.toCartProduct(
    quantity: Int
) = CartProduct(
    id = id,
    quantity = quantity,
    name = title,
    price = price,
    imageUrl = image,
)

fun ProductDto.toTransactionProduct(
    quantity: Int
) = TransactionProduct(
    id = id,
    quantity = quantity,
    name = title,
    price = price,
    imageUrl = image,
)

fun CartDto.toCart(
    productAll: List<ProductDto>
): Cart {
    val productCarts = products.mapNotNull { product ->
        productAll.find { it.id == product.productId }?.toCartProduct(product.quantity)
    }
    return Cart(
        date = date,
        id = id,
        products = productCarts
    )
}

fun CartDto.toTransaction(
    productAll: List<ProductDto>
): Transaction {
    val productCarts = products.mapNotNull { product ->
        productAll.find { it.id == product.productId }?.toTransactionProduct(product.quantity)
    }
    return Transaction(
        date = date,
        id = id.toString(),
        products = productCarts
    )
}

fun CartProduct.toTransactionProductEntity(
    transactionId: String
) = TransactionProductEntity(
    id = id,
    quantity = quantity,
    name = name,
    price = price,
    imageUrl = imageUrl,
    transactionId = transactionId
)