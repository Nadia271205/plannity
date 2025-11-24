package com.proyek.planity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val _taskList = MutableLiveData<MutableList<Task>>()
    val taskList: LiveData<MutableList<Task>> get() = _taskList

    init {
        _taskList.value = mutableListOf(
            Task("Judul Tugas 1", "Contoh tugas awal"),
            Task("Judul Tugas 2", "Contoh tugas kedua", "10.00")
        )
    }

    fun addTask(task: Task) {
        val currentList = _taskList.value ?: mutableListOf()
        currentList.add(0, task)
        _taskList.value = currentList
    }
}