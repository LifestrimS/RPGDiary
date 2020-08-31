package com.lifestrim.rpgdiary.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lifestrim.rpgdiary.R
import com.lifestrim.rpgdiary.data.task.TaskAdapter
import com.lifestrim.rpgdiary.data.task.TaskViewModel
import com.lifestrim.rpgdiary.util.ShowToast
import kotlinx.android.synthetic.main.fragment_main.view.*


/**
 * Main fragment for displaying the main information of the hero and current tasks
 */
class MainFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    private lateinit var sharedPref: SharedPreferences
    private val PREF_NAME = "character"
    private var FIRST_LOAD_FLAG_NAME = "first_load"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        sharedPref = view.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        if (!sharedPref.getBoolean(FIRST_LOAD_FLAG_NAME, false)) {
            //ShowToast().showToast(view.context, R.string.test_text)
            findNavController().navigate(R.id.action_mainFragment_to_addCharacterFragment)
            val editor: SharedPreferences.Editor = sharedPref.edit()
            editor.putBoolean(FIRST_LOAD_FLAG_NAME, true)
            editor.commit()
        }

        setupRecyclerView(view)
        setupObservers()
        setupFab(view)
        return view
    }

    private fun setupRecyclerView(view: View) {
        taskAdapter = TaskAdapter()
        view.rv_task.layoutManager = LinearLayoutManager(activity)
        view.rv_task.adapter = taskAdapter
    }

    private fun setupObservers() {
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        taskViewModel.allTasks.observe(viewLifecycleOwner, Observer { tasks ->
            tasks?.let {
                taskAdapter.setItems(it)
            }
        })
    }

    private fun setupFab(view: View) {
        view.fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_addTaskFragment)
        }
    }

}