package com.proyek.planity.homeNav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyek.planity.R
import com.proyek.planity.TaskAdapter
import com.proyek.planity.TaskManager

class HistoryFragment : Fragment() {

    private lateinit var historyAdapter: TaskAdapter

    private val updateListener: () -> Unit = {
        updateUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val rvHistory = view.findViewById<RecyclerView>(R.id.rvHistory)
        rvHistory.layoutManager = LinearLayoutManager(context)

        historyAdapter = TaskAdapter(
            emptyList(),
            onCheckChange = { task ->
                TaskManager.updateTaskStatus(task, false)
            },
            onDeleteClick = { task -> // menghapus tugas
                TaskManager.deleteTask(task)
            },
            onUpdateClick = { task ->
            }
        )

        rvHistory.adapter = historyAdapter

        updateUI()


    }

    override fun onResume() {
        super.onResume()
        TaskManager.addListener(updateListener)
    }

    override fun onPause() {
        super.onPause()
        TaskManager.removeListener(updateListener)
    }

    private fun updateUI() {
        val completedTasks = TaskManager.getCompletedTasks()
        historyAdapter.updateData(completedTasks)
    }
}