package com.proyek.planity.homeNav

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.proyek.planity.R
import com.proyek.planity.Task
import java.util.Calendar
import java.util.Locale

class AddNewFragment : Fragment() {
    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        // btnBack dihapus dari sini
        val etTaskTime = view.findViewById<EditText>(R.id.etTaskTime)
        val btnSave = view.findViewById<Button>(R.id.btnSaveTask)
        val etTitle = view.findViewById<EditText>(R.id.etTaskTitle)
        val etDesc = view.findViewById<EditText>(R.id.etTaskDesc)

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
                val newTask = Task(title, desc, time)
                viewModel.addTask(newTask)

                Toast.makeText(context, "Task Saved", Toast.LENGTH_SHORT).show()
                // Jika ingin tetap di halaman ini setelah save, hapus baris di bawah ini:
                // parentFragmentManager.popBackStack()

                // Kosongkan input field setelah save (opsional)
                etTitle.text.clear()
                etDesc.text.clear()
                etTaskTime.text.clear()
            } else {
                Toast.makeText(context, "Please enter a title", Toast.LENGTH_SHORT).show()
            }
        }
    }
}