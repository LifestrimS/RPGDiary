package com.lifestrim.rpgdiary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lifestrim.rpgdiary.R
import com.lifestrim.rpgdiary.util.ShowToast
import kotlinx.android.synthetic.main.fragment_add_character.view.*


class AddCharacterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_character, container, false)

        view.bt_save.setOnClickListener {
            if (view.et_name.text.isEmpty()) {
                ShowToast().showToast(view.context, R.string.need_fill_all_fields)
            } else {
                it.findNavController().navigate(R.id.action_addCharacterFragment_to_mainFragment)
            }
        }

        return view
    }
}