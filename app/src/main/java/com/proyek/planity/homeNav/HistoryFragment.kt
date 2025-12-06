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
import com.proyek.planity.TaskViewModel

class HistoryFragment : Fragment() {

    private lateinit var historyAdapter: TaskAdapter
    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        val rvHistory = view.findViewById<RecyclerView>(R.id.rvHistory)
        rvHistory.layoutManager = LinearLayoutManager(context)

        historyAdapter = TaskAdapter(
            emptyList(),
            onCheckChange = { task ->
                viewModel.updateTaskStatus(task, false)
            },
            onDeleteClick = { task ->
                viewModel.deleteTask(task)
            },
            onUpdateClick = { task ->
            }
        )

        rvHistory.adapter = historyAdapter

        viewModel.taskList.observe(viewLifecycleOwner) { allTasks ->
            val completedTasks = allTasks.filter { it.isCompleted }
            historyAdapter.updateData(completedTasks)
        }
    }
}