package com.proyek.planity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private var taskList: List<Task>, private val onCheckChange: (Task) -> Unit) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitleLogin)
        val tvSubtitle: TextView = itemView.findViewById(R.id.tvSubtitle)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val cbTask: CheckBox = itemView.findViewById(R.id.cbTask)

        fun bind(task: Task) {
            tvTitle.text = task.title
            tvSubtitle.text = task.description

            if(task.time.isNullOrEmpty()) {
                tvTime.text = task.time
                tvTime.visibility = View.VISIBLE
            } else {
                tvTime.visibility = View.GONE
            }

            cbTask.setOnCheckedChangeListener(null)
            cbTask.isChecked = task.isCompleted

            cbTask.setOnClickListener {
                onCheckChange(task)
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