package com.lifestrim.rpgdiary.data.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lifestrim.rpgdiary.R
import kotlinx.android.synthetic.main.rv_item.view.*
import java.util.*


class TaskAdapter: RecyclerView.Adapter<TaskViewHolder>() {
//class TaskAdapter : ListAdapter<EntityTask, TaskViewHolder>() {

    private var tasks = emptyList<EntityTask>()

    fun setItems(tasksList: List<EntityTask>) {
        this.tasks = tasksList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}

class TaskViewHolder(private val view: View) :RecyclerView.ViewHolder(view) {
    private lateinit var task: EntityTask

    fun bind(item: EntityTask) {
        this.task = item

        val calendar: Calendar = Calendar.getInstance()
        val date = "${calendar.get(Calendar.DAY_OF_MONTH)}.${calendar.get(Calendar.MONTH)+1}"

        view.tv_task_title.text = task.taskTitle
        view.tv_task_time.text = date
    }

}
