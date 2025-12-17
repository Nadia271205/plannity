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
import com.proyek.planity.R
import com.proyek.planity.Task
import com.proyek.planity.TaskManager  // ← TAMBAH IMPORT INI
import java.util.Calendar
import java.util.Locale

class AddNewFragment : Fragment() {
    private var currentTaskId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // menemukan semua komponen UI di layout dengan ID yang sesuai
        val etTaskTime = view.findViewById<EditText>(R.id.etTaskTime)
        val btnSave = view.findViewById<Button>(R.id.btnSaveTask)
        val etTitle = view.findViewById<EditText>(R.id.etTaskTitle)
        val etDesc = view.findViewById<EditText>(R.id.etTaskDesc)

        checkEditMode(etTitle, etDesc, etTaskTime, btnSave)

        etTaskTime.setOnClickListener {
            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                requireContext(),
                { _, selectedHour, selectedMinute ->
                    val timeString = String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        selectedHour,
                        selectedMinute
                    )
                    etTaskTime.setText(timeString)
                }, hour, minute, true
            )
            timePickerDialog.show()
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()
            val time = etTaskTime.text.toString()

            if (title.isNotEmpty()) {
                if (currentTaskId != null) {

                    TaskManager.updateTaskContent(currentTaskId!!, title, desc, time)
                    TaskManager.taskToEdit = null
                    Toast.makeText(context, "Task Updated", Toast.LENGTH_SHORT).show()
                } else {
                    val newTask = Task(title = title, description = desc, time = time)
                    TaskManager.addTask(newTask)
                    Toast.makeText(context, "Task Saved", Toast.LENGTH_SHORT).show()
                }

                etTitle.text.clear()
                etDesc.text.clear()
                etTaskTime.text.clear()
            } else {
                Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //TAMBAH method ini untuk check edit mode
    private fun checkEditMode(
        etTitle: EditText,
        etDesc: EditText,
        etTaskTime: EditText,
        btnSave: Button
    ) {
        val task = TaskManager.taskToEdit

        if (task != null) {
            // Mode EDIT
            currentTaskId = task.id
            etTitle.setText(task.title)
            etDesc.setText(task.description)
            etTaskTime.setText(task.time)
            btnSave.text = "Update Task"

            // Clear setelah diambil
            TaskManager.taskToEdit = null
        } else {
            // Mode ADD NEW
            currentTaskId = null
            etTitle.text.clear()
            etDesc.text.clear()
            etTaskTime.text.clear()
            btnSave.text = "Save Task"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        TaskManager.taskToEdit = null
    }
}