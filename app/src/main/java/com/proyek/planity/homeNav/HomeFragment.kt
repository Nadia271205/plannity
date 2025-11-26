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
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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


        val tvDate = view.findViewById<TextView>(R.id.tvDate)

        val today = Date()

        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        val dateString = formatter.format(today)

        tvDate.text = dateString
        // ------------------------------

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