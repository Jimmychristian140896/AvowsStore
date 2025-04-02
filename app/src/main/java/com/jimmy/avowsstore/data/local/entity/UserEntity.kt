package com.jimmy.avowsstore.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "users"
)
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val username: String,
    val password: String
)
