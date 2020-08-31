package com.lifestrim.rpgdiary.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.lifestrim.rpgdiary.R
import com.lifestrim.rpgdiary.data.task.EntityTask
import com.lifestrim.rpgdiary.data.task.TaskViewModel
import com.lifestrim.rpgdiary.util.NotificationUtils
import com.lifestrim.rpgdiary.util.ShowToast
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_add_task.view.*
import java.util.*

/**
 * Fragment for add new tasks
 */

class AddTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    private var notificationYear: Int
    private var notificationMonth: Int
    private var notificationDay: Int
    private var notificationHour: Int
    private var notificationMinute: Int

    init {
        val calendar = Calendar.getInstance()
        notificationYear = calendar.get(Calendar.YEAR)
        notificationMonth = calendar.get(Calendar.MONTH)
        notificationDay = calendar.get(Calendar.DAY_OF_MONTH)
        notificationHour = calendar.get(Calendar.HOUR_OF_DAY)
        notificationMinute = calendar.get(Calendar.MINUTE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        view.bt_save.setOnClickListener {
            addTask(view)
        }

        var dateString = "$notificationDay.${notificationMonth + 1}.$notificationYear"
        var timeString = "$notificationHour:$notificationMinute"

        view.notificationDateSet.text = dateString
        view.notificationTimeSet.text = timeString

        view.notificationCheck.setOnClickListener {
            checkBoxClick(view)
        }

        view.notificationDateSet.setOnClickListener {
            val dpd = DatePickerDialog(
                it.context,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    dateString = "$dayOfMonth.${monthOfYear + 1}.$year"
                    it.notificationDateSet.text = dateString
                },
                notificationYear,
                notificationMonth,
                notificationDay
            )

            dpd.show()
        }

        view.notificationTimeSet.setOnClickListener {
            val tmd = TimePickerDialog(
                it.context, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    timeString = "$hourOfDay:$minute"
                    it.notificationTimeSet.text = timeString
                },
                notificationHour,
                notificationMinute,
                true
            )
            tmd.show()
        }

        return view
    }

    private fun checkBoxClick(view: View) {
        if (view.notificationCheck.isChecked) {
            view.notificationDate.visibility = View.VISIBLE
            view.notificationDateSet.visibility = View.VISIBLE
            view.notificationTime.visibility = View.VISIBLE
            view.notificationTimeSet.visibility = View.VISIBLE
        } else {
            view.notificationDate.visibility = View.GONE
            view.notificationDateSet.visibility = View.GONE
            view.notificationTime.visibility = View.GONE
            view.notificationTimeSet.visibility = View.GONE
        }
    }

    private fun addTask(view: View) {
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        if (et_title.text.isEmpty() ||
            et_description.text.isEmpty()
        ) {
            ShowToast().showToast(view.context, R.string.need_fill_all_fields)

        } else {
            val task = EntityTask(
                taskTitle = view.et_title.text.toString(),
                taskDescription = view.et_description.text.toString(),
                taskCreatedTime = Calendar.getInstance().time
            )
            taskViewModel.insertTask(task)

            if (view.notificationCheck.isChecked) {
                setNotification(view)
            }

            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            view.findNavController().navigate(R.id.action_addTaskFragment_to_mainFragment)
        }
    }

    private fun setNotification(view: View) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(
            notificationYear,
            notificationMonth,
            notificationDay,
            notificationHour,
            notificationMinute,
            0
        )
        val dateLong = calendar.timeInMillis

        NotificationUtils().setNotification(view, dateLong, "testNotification")
    }

}
