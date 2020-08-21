package com.lifestrim.rpgdiary.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lifestrim.rpgdiary.R
import com.lifestrim.rpgdiary.data.task.EntityTask
import com.lifestrim.rpgdiary.data.task.TaskAdapter
import com.lifestrim.rpgdiary.data.task.TaskViewModel
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.*


/**
 * Main fragment for displaying the main information of the hero and current tasks
 */
class MainFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        setupRecyclerView(view)
        setupObservers()
        return view
    }

    private fun setupRecyclerView(view: View) {
        taskAdapter = TaskAdapter()
        view.rv_task.layoutManager = LinearLayoutManager(activity)
        view.rv_task.adapter = taskAdapter
    }

    private fun setupObservers() {
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        for (n in 0..10) {
            val testTasks: EntityTask = EntityTask(n.toLong(), "Title", "Description")
            taskViewModel.insertTask(testTasks)
        }

        taskViewModel.allTasks.observe(viewLifecycleOwner, Observer { tasks ->
            tasks?.let {
                taskAdapter.setItems(it)
            }
        })
    }

}