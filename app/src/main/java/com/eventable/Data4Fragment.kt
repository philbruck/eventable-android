package com.eventable

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_data4.*

class Data4Fragment : Fragment(R.layout.fragment_data4) {

    private val args: Data4FragmentArgs by navArgs() //philbruck: autogenerire Klasse wegen dem safeArgs-Plug-In

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val one: String = args.userId

        continueBtn.setOnClickListener {

            val action_Data4ToSelf =
                Data4FragmentDirections.actionData4FragmentSelf(one)
            findNavController().navigate(action_Data4ToSelf)

        }
        finishBtn.setOnClickListener {

            val action_Data4ToHome =
                Data4FragmentDirections.actionData4FragmentToHomeFragment()
            findNavController().navigate(action_Data4ToHome)

        }
    }

}