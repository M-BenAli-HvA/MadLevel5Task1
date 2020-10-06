package com.example.madlevel5task1.models

import androidx.room.PrimaryKey
import java.util.*

class Note(
    var title: String,
    var date: Date,
    var text: String,
    @PrimaryKey
    var id: Long) {
}