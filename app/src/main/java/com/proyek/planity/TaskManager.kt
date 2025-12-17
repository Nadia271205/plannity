package com.proyek.planity

object TaskManager {
    private val taskList = mutableListOf<Task>( // membuat daftar list yang bisa dirubah
        Task(title = "Menegerjakan Tugas Android", description = "Segera Dikerjakan mas", time = "10:00"),
        Task(title = "Beli makanan kucing", description = "merek whiskas", time = "01:00"),
        Task(title = "Lari pagi", description = "Segera Dikerjakan mas", time = "06:00", isCompleted = true),
    )

    // listener untuk update UI otomatis
    private val listeners = mutableListOf<() -> Unit>()

    fun addListener(listener: () -> Unit) { // mendaftar pengamat
        listeners.add(listener)
    }

    fun removeListener(listener: () -> Unit) { // berhenti berlangganan
        listeners.remove(listener)
    }

    private fun notifyListener() {
        listeners.forEach { it.invoke() }
    }

    // Get Data
    fun getAllTasks(): List<Task> = taskList.toList()

    fun getPendingTasks(): List<Task> = taskList.filter {!it.isCompleted}

    fun getCompletedTasks(): List<Task> = taskList.filter { it.isCompleted }

    fun addTask(task: Task) {
        taskList.add(0, task)
        notifyListener()
    }

    fun updateTaskStatus(task: Task, isDone: Boolean) {
        val index = taskList.indexOfFirst { it.id == task.id }
        if (index != -1) {
            taskList[index].isCompleted = isDone
            notifyListener()
        }
    }

    fun updateTaskContent(id: String, newTitle: String, newDesc: String, newTime: String) {
        val index = taskList.indexOfFirst { it.id == id }
        if (index != -1) {
            val oldTask = taskList[index]
            val updatedTask = oldTask.copy(
                title = newTitle,
                description = newDesc,
                time = newTime
            )
            taskList[index] = updatedTask
            notifyListener()
        }
    }

    fun deleteTask(task: Task) {
        taskList.remove(task)
        notifyListener()
    }

    //
    var taskToEdit: Task? = null
}