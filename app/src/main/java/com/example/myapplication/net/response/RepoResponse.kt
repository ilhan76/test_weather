package com.example.myapplication.net.response

data class RepoResponse<T>(
    val content: T?,
    val message: String?
)