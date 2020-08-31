package com.lifestrim.rpgdiary.util

import android.content.Context
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.lifestrim.rpgdiary.R

class ShowToast() {
    fun showToast(context: Context, resId: Int) {
        val toast = Toast.makeText(context, resId, Toast.LENGTH_LONG)
        val toastLayout = toast.view as LinearLayout?
        val toastTV = toastLayout!!.getChildAt(0) as TextView
        toastTV.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            context.resources.getDimension(R.dimen.text_size)
        )
        toast.show()
    }
}