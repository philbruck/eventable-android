package com.eventable

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_data3.*

class Data3Fragment : Fragment(R.layout.fragment_data3){

    private val args : Data3FragmentArgs by navArgs() //philbruck: autogenerire Klasse wegen dem safeArgs-Plug-In

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val one: String = args.eventId
        val two: String = args.userId
    }

}