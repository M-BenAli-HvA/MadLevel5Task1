package com.example.madlevel5task1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task1.models.Note
import com.example.madlevel5task1.repository.NotepadRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NoteViewModel(application: Application) :  AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val noteRepository: NotepadRepository = NotepadRepository(application.applicationContext)
    val note = noteRepository.getNotepad()

    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun updateNote(title: String, text: String) {
        val newNote = Note(id = note.value?.id,
                title = title,
                date = Date(),
                text = text
        )

        if (isNoteValid(newNote)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    noteRepository.updateNotepad(newNote)
                }
                success.value = true
            }
        }
    }

    private fun isNoteValid(note: Note): Boolean {
        return when {
            note.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }


}