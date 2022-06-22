package com.eventable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapterConfirmed : RecyclerView.Adapter<RecyclerAdapterConfirmed.ViewHolder>() {

    private lateinit var navController: NavController

    val testvarc_1 = (0..10).random()
    val testvarc_2 = (0..10).random()
    val testvarc_3 = (0..10).random()

    private var testvarc = arrayOf(
        "M-HALLO1-Random:" + testvarc_1.toString(),
        "B-HALLO2-Random:" + testvarc_2.toString(),
        "F-HALLO3-Random:" + testvarc_3.toString(),
        "O-HALLO4",


        )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterConfirmed.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_cardlayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: RecyclerAdapterConfirmed.ViewHolder,
        position: Int
    ) { //philbruck: der Binder matcht die Daten auf die XML Elemente
        holder.itemTitle.text = testvarc[position]
    }

    override fun getItemCount(): Int { //philbruck: wie bei Java, man muss sagn wie viele Emelent man hat
        return testvarc.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) { //philbruck: innere Klasse der die verbindung zu den MXL Elemeten regelt

        var itemTitle: TextView = itemView.findViewById(R.id.headlineTV)

        init {
            itemView.setOnClickListener {
                val pos: Int =
                    bindingAdapterPosition //philbruck: wenn man die akteulle Position wissen will

                navController = findNavController(itemView)

                val event_id = pos.toString()
                val question_id = pos.toString()

                val action_ConfirmedToData3 =
                    ConfirmedFragmentDirections.actionConfirmedFragmentToData3Fragment(event_id, question_id)
                navController.navigate(action_ConfirmedToData3) //philbruck: geht auch "// navController.navigate(R.id.action.....)"


            }
        }
    }

}