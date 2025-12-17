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

        fun bind(task: Task) { // menerima objek task yang berisi data tugas
            tvTitle.text = task.title //judul ditampilkan
            tvDeskripsi.text = task.description //deskripsi ditampilkan
            tvTime.text = task.time //menampilkan waktu
            if(task.time.isNullOrEmpty()) {
                tvTime.visibility = View.GONE //waktu disembunyikan
            } else {
                tvTime.text = task.time
                tvTime.visibility = View.VISIBLE //waktu ditampilkan
            }

            cbTask.setOnCheckedChangeListener(null) // menghapus listener sebelumnya
            cbTask.isChecked = task.isCompleted // mengecek apakah tugas sudah selesai


            cbTask.setOnClickListener {
                onCheckChange(task)  //mengambil fungsi di atas
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false) //memanggil nilai layout inflater dari tampilan xml item task
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) { //menampilkan data ke tampilan
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size //mengembalikan jumlah item dalam daftar tugas
    }

    fun updateData(newTasks: List<Task>) {
        taskList = newTasks //mengupdate data tugas
        notifyDataSetChanged()
    }


}