package com.example.myapplication.net.response

data class RepositoryResponse<T>(
    val content: T?,
    val message: String?
)