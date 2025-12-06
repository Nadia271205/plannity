package com.proyek.planity.homeNav

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.proyek.planity.R
import com.proyek.planity.Task
import com.proyek.planity.TaskViewModel
import java.util.Calendar
import java.util.Locale

class AddNewFragment : Fragment() {
    private lateinit var viewModel: TaskViewModel
    private var currentTaskId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        val etTaskTime = view.findViewById<EditText>(R.id.etTaskTime)
        val btnSave = view.findViewById<Button>(R.id.btnSaveTask)
        val etTitle = view.findViewById<EditText>(R.id.etTaskTitle)
        val etDesc = view.findViewById<EditText>(R.id.etTaskDesc)

        viewModel.taskToEdit.observe(viewLifecycleOwner) { task ->
            if (task != null) {
                currentTaskId = task.id
                etTitle.setText(task.title)
                etDesc.setText(task.description)
                etTaskTime.setText(task.time)
                btnSave.text = "Update Task"
            } else {
                currentTaskId = null
                etTitle.text.clear()
                etDesc.text.clear()
                etTaskTime.text.clear()
                btnSave.text = "Save Task"
            }
        }

        etTaskTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                requireContext(),
                { _, selectedHour, selectedMinute ->
                    val timeString = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
                    etTaskTime.setText(timeString)
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()
            val time = etTaskTime.text.toString()

            if (title.isNotEmpty()) {
                if (currentTaskId != null) {
                    viewModel.updateTaskContent(currentTaskId!!, title, desc, time)
                    Toast.makeText(context, "Task Updated", Toast.LENGTH_SHORT).show()
                } else {
                    val newTask = Task(title = title, description = desc, time = time)
                    viewModel.addTask(newTask)
                    Toast.makeText(context, "Task Saved", Toast.LENGTH_SHORT).show()
                }

                viewModel.setTaskToEdit(null)

                etTitle.text.clear()
                etDesc.text.clear()
                etTaskTime.text.clear()
            } else {
                Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setTaskToEdit(null)
    }
}