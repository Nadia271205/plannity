package com.proyek.planity.homeNav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.proyek.planity.R
import com.proyek.planity.TaskAdapter
import com.proyek.planity.TaskManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var taskAdapter: TaskAdapter

    private val updateListener: () -> Unit = {
        updateUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

// tanggal
        val tvDate = view.findViewById<TextView>(R.id.tvDate)
        val today = Date()
        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        val dateString = formatter.format(today)
        tvDate.text = dateString

        val rvTasks = view.findViewById<RecyclerView>(R.id.rvTasks)
        rvTasks.layoutManager = LinearLayoutManager(context)

        taskAdapter = TaskAdapter (
            emptyList(),
            onCheckChange = { task ->
                TaskManager.updateTaskStatus(task, true)
            },
            onDeleteClick = { task ->
                TaskManager.deleteTask(task)
            },
            onUpdateClick = { task ->
                TaskManager.taskToEdit = task
                requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                    .selectedItemId = R.id.addNewFragment
            }
        )
        rvTasks.adapter = taskAdapter

        updateUI()
    }

    override fun onResume() {
        super.onResume()
        TaskManager.addListener(updateListener)
        updateUI()
    }

    override fun onPause() {
        super.onPause()
        TaskManager.removeListener(updateListener)
    }

    private fun updateUI() {
        val pendingTasks = TaskManager.getPendingTasks()
        taskAdapter.updateData(pendingTasks)
    }
}