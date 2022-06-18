package com.eventable

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eventable.model.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.view_cardlayout.view.*
import kotlinx.coroutines.NonDisposableHandle.parent


// Augustin: Diese Klasse hab ich erstellt
class RecyclerAdapter(val context: HomeFragment, val events: List<Event>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_cardlayout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event) {
            itemView.headlineTV.text = event.name
            itemView.placeHolderTV.text = event.location
            itemView.dateHolderTV.text = event.date
            itemView.timeHolderTV.text = "${event.starttime} Uhr"
            itemView.confirmedHolderTV.text = event.questions?.get(2)
        }
    }
}



/*
class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {



    private lateinit var navController: NavController

    val testvar_1 = (0..10).random()
    val testvar_2 = (0..10).random()
    val testvar_3 = (0..10).random()

    private var testvar = arrayOf(
        "1 random:" + testvar_1.toString(),
        "2 random" + testvar_2.toString(),
        "3 random" + testvar_3.toString(),
        "4 Event",
        "5 Event",
        "6 Event",
        "7",
        "8",
        "9",
        "10",
        "11",
        "12"
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_cardlayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: RecyclerAdapter.ViewHolder,
        position: Int
    ) { //philbruck: der Binder matcht die Daten auf die XML Elemente
        holder.itemTitle.text = testvar[position]
    }

    override fun getItemCount(): Int { //philbruck: wie bei Java, man muss sagn wie viele Emelent man hat
        return testvar.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) { //philbruck: innere Klasse der die verbindung zu den MXL Elemeten regelt

        var itemTitle: TextView = itemView.findViewById(R.id.headlineTV)

        init {
            itemView.setOnClickListener {
                val pos: Int =
                    bindingAdapterPosition //philbruck: wenn man die akteulle Position wissen will

                val user_id = pos
                val event_id = testvar[pos]


                navController = findNavController(itemView)
                val action_HomeToData1 =
                    HomeFragmentDirections.actionHomeFragmentToData1Fragment(event_id, user_id)
                navController.navigate(action_HomeToData1) //philbruck: geht auch "// navController.navigate(R.id.action.....)"


            }
        }
    }

}*/
