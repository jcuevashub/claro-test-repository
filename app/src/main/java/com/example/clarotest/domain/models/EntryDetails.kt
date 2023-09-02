package com.example.clarotest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EntryDetails(
    val API: String,
    val Description: String,
    val Auth: String,
    val HTTPS: Boolean,
    val Cors: String,
    val Link: String,
    val Category: String,
): Parcelable