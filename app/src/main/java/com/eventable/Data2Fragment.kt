package com.eventable

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class Data2Fragment : Fragment(R.layout.fragment_data2){

    private val args : Data2FragmentArgs by navArgs() //philbruck: autogenerire Klasse wegen dem safeArgs-Plug-In

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val one: String = args.userId
        val two: Int = args.invitationCode
    }

}