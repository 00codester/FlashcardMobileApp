package com.example.flashcardv2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flashcard (
    val front: String,
    val back: String
) : Parcelable

