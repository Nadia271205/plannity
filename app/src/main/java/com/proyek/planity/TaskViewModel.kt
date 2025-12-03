package com.proyek.planity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val _taskList = MutableLiveData<MutableList<Task>>()
    val taskList: LiveData<MutableList<Task>> get() = _taskList

    init {
        _taskList.value = mutableListOf(
            Task(title = "Menegerjakan Tugas Android", description = "Segera Dikerjakan mas", time = "10:00"),
            Task(title = "Beli makanan kucing", description = "merk bolt", time = "01.00"),
            Task(title = "Lari Pagi", description = "Keliling Madiun", time = "01.00", isCompleted = true),
        )
    }

    fun addTask(task: Task) {
        val currentList = _taskList.value ?: mutableListOf()
        currentList.add(0, task)
        _taskList.value = currentList
    }

    fun updateTaskStatus(task: Task, isDone: Boolean) {
        val currentList = _taskList.value ?: return

        val index = currentList.indexOfFirst { it.id == task.id  }

        if (index != -1) {
            currentList[index].isCompleted = isDone
            _taskList.value = currentList
        }
    }

    fun deleteTask(task: Task) {
        val currentList = _taskList.value ?: return
        currentList.remove(task)
        _taskList.value = currentList
    }
}