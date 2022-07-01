package com.eventable

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_create.*

class CreateFragment : Fragment(R.layout.fragment_create) {


    private lateinit var eventInfos: Array<String>
    val eventId = (100000..999999).random()
    val alreadyCreated = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventInfos = Array(5) {"init"}


        val headline = nameHolderET
        val place = placeHolderET
        val time = timeHolderET
        val date = dateHolderET

        //val user_id = "42" // philbrcuk hier muss noch dei echte User id hinzu
        //Augustin: Könnt ich anstelle von SafeArgs einfach Intent mitgeben?


        createBtn.setOnClickListener {

            if (nameHolderET.length() == 0 || placeHolderET.length() == 0 || timeHolderET.length() == 0 || dateHolderET.length() == 0) {
                Toast.makeText(activity, "Bitte alle Felder ausfüllen", Toast.LENGTH_LONG).show()
            } else {
                    eventInfos[0] = nameHolderET.text.toString()
                    eventInfos[1] = placeHolderET.text.toString()
                    eventInfos[2] = dateHolderET.text.toString()
                    eventInfos[3] = timeHolderET.text.toString()
                    eventInfos[4] = descriptionHolderET.text.toString()

                    Log.i("EventInfos", eventInfos.toString())

                val action_CreateToData4 = CreateFragmentDirections.actionCreateFragmentToData4Fragment(eventInfos,
                    eventId.toString(), alreadyCreated
                )

                findNavController().navigate(action_CreateToData4)


            }


        }

    }
}