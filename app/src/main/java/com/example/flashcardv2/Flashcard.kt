package com.example.flashcardv2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Class that defines what a Flashcard is
@Parcelize
data class Flashcard (
    val front: String,
    val back: String
) : Parcelable

