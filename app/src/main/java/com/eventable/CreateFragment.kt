package com.eventable

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_invitation.*

class CreateFragment : Fragment(R.layout.fragment_create) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val headline = headlineHolderET
        val place = placeHolderET
        val time = timeHolderET
        val date = dateHolderET

        val user_id = "42" // philbrcuk hier muss noch dei echte User id hinzu


        createBtn.setOnClickListener {

            if (headlineHolderET.length() == 0 || placeHolderET.length() == 0 || timeHolderET.length() == 0 || dateHolderET.length() == 0) {
                Toast.makeText(activity, "Bitte alle Felder ausfüllen", Toast.LENGTH_LONG).show()
            } else {

                val action_CreateToData4 = CreateFragmentDirections.actionCreateFragmentToData4Fragment(user_id)
                findNavController().navigate(action_CreateToData4)


            }


        }

    }
}