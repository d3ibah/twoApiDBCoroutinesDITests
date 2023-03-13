package com.twcc.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//TODO: Remove it
@Parcelize
data class User(val id: String, val name: String, val api: String, val image: String) : Parcelable
