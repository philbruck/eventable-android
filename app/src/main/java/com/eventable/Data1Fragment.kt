package com.eventable

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_data1.*

class Data1Fragment : Fragment(R.layout.fragment_data1){

    private val args : Data1FragmentArgs by navArgs() //philbruck: autogenerire Klasse wegen dem safeArgs-Plug-In

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data1_user_id_TV.text = args.userId.toString()
        data1_event_id_TV.text = args.eventId.toString()

    }

}