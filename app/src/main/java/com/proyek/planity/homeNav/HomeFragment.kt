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
import com.proyek.planity.Task
import com.proyek.planity.TaskAdapter


class HomeFragment : Fragment() {
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        val rvTasks = view.findViewById<RecyclerView>(R.id.rvTasks)
        rvTasks.layoutManager = LinearLayoutManager(context)

        taskAdapter = TaskAdapter(emptyList())
        rvTasks.adapter = taskAdapter

        viewModel.taskList.observe(viewLifecycleOwner) { tasks ->
            taskAdapter.updateData(tasks)
        }
    }
}