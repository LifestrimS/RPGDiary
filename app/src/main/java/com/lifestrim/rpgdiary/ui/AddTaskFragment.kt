package com.lifestrim.rpgdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lifestrim.rpgdiary.R
import kotlinx.android.synthetic.main.fragment_add_task.view.*

/**
 * Fragment for add new tasks
 */

class AddTaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        view.bt_save.setOnClickListener {
            it.findNavController().navigate(R.id.action_addTaskFragment_to_mainFragment)
        }

        return view
    }

}