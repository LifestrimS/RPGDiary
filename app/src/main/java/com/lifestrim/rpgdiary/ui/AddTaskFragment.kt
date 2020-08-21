package com.lifestrim.rpgdiary.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.lifestrim.rpgdiary.R
import com.lifestrim.rpgdiary.data.task.EntityTask
import com.lifestrim.rpgdiary.data.task.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_add_task.view.*

/**
 * Fragment for add new tasks
 */

class AddTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        view.bt_save.setOnClickListener {
            addTask(view)
        }

        return view
    }

    private fun addTask(view: View) {
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        if (et_title.text.isEmpty() ||
            et_description.text.isEmpty()
        ) {
            Toast.makeText(context, "You need fill all fields!", Toast.LENGTH_SHORT).show()
        } else {
            val task = EntityTask(
                taskTitle = view.et_title.text.toString(),
                taskDescription = view.et_description.text.toString()
            )
            taskViewModel.insertTask(task)

            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            view.findNavController().navigate(R.id.action_addTaskFragment_to_mainFragment)
        }
    }

}