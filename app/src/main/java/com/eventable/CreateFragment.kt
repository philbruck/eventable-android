package com.eventable

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.eventable.model.Event
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_invitation.*
import java.util.*
import kotlin.random.Random.Default.nextInt

class CreateFragment : Fragment(R.layout.fragment_create) {


    private lateinit var eventInfos: Array<String>
    val eventId = (100000..999999).random()
    val alreadyCreated = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventInfos = Array(5) {"init"}


        val headline = headlineHolderET
        val place = placeHolderET
        val time = timeHolderET
        val date = dateHolderET


        //val user_id = "42" // philbrcuk hier muss noch dei echte User id hinzu
        //Augustin: Könnt ich anstelle von SafeArgs einfach Intent mitgeben?


        createBtn.setOnClickListener {

            if (headlineHolderET.length() == 0 || placeHolderET.length() == 0 || timeHolderET.length() == 0 || dateHolderET.length() == 0) {
                Toast.makeText(activity, "Bitte alle Felder ausfüllen", Toast.LENGTH_LONG).show()
            } else {
                    eventInfos[0] = headlineHolderET.text.toString()
                    eventInfos[1] = placeHolderET.text.toString()
                    eventInfos[2] = dateHolderET.text.toString()
                    eventInfos[3] = timeHolderET.text.toString()
                    eventInfos[4] = descriptionET.text.toString()

                    Log.i("EventInfos", eventInfos.toString())

                val action_CreateToData4 = CreateFragmentDirections.actionCreateFragmentToData4Fragment(eventInfos,
                    eventId.toString(), alreadyCreated
                )

                findNavController().navigate(action_CreateToData4)


            }


        }

    }
}