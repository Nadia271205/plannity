package com.proyek.planity.homeNav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyek.planity.R
import com.proyek.planity.Task
import com.proyek.planity.TaskAdapter


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskList = listOf(
            Task("Judul Tugas 1", "Lorem ipsum dolor sit amet","06.00"),
            Task("Judul Tugas 2", "Lorem ipsum dolor sit amet", "09.00"),
            Task("Judul Tugas 3", "Lorem ipsum dolor sit amet", "10.15"),
            Task("Judul Tugas 4", "Lorem ipsum dolor sit amet", "11.00"),
            Task("Judul Tugas 5", "Lorem ipsum dolor sit amet", "14.35"),
            Task("Judul Tugas 6", "Lorem ipsum dolor sit amet", "15.00")
        )

        val rvTasks = view.findViewById<RecyclerView>(R.id.rvTasks)
        rvTasks.layoutManager = LinearLayoutManager(context)
        rvTasks.adapter = TaskAdapter(taskList)
    }
}