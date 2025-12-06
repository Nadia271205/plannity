package com.proyek.planity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private var taskList: List<Task>,
                  private val onCheckChange: (Task) -> Unit,
                  private val onDeleteClick: (Task) -> Unit,
                  private val onUpdateClick: (Task) -> Unit) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvJudulTugas)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsi)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val cbTask: CheckBox = itemView.findViewById(R.id.cbTask)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
        val btnUpdate: ImageView = itemView.findViewById(R.id.btnUpdate)

        fun bind(task: Task) {
            tvTitle.text = task.title
            tvDeskripsi.text = task.description
            tvTime.text = task.time
            if(task.time.isNullOrEmpty()) {
                tvTime.visibility = View.GONE
            } else {
                tvTime.visibility = View.VISIBLE
            }

            cbTask.setOnCheckedChangeListener(null)
            cbTask.isChecked = task.isCompleted

            cbTask.setOnClickListener {
                onCheckChange(task)
            }

            btnDelete.setOnClickListener {
                onDeleteClick(task)
            }

            btnUpdate.setOnClickListener {
                onUpdateClick(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateData(newTasks: List<Task>) {
        taskList = newTasks
        notifyDataSetChanged()
    }
}