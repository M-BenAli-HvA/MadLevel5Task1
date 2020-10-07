package com.example.madlevel5task1.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "NoteTable")
class Note(
    @ColumnInfo(name = "title")
    var title: String,
    var date: Date,
    var text: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null) {
}