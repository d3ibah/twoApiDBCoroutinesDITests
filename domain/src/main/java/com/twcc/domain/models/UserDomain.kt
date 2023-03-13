package com.twcc.domain.models

import java.io.Serializable

data class UserDomain(
    val id: String,
    val name: String,
    val api: String,
    val image: String
) : Serializable
