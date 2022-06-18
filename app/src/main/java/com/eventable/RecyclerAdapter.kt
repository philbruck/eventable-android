package com.eventable

import android.content.ContentValues.TAG
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var navController: NavController



    val testvar_1 = (0..10).random()
    val testvar_2 = (0..10).random()
    val testvar_3 = (0..10).random()

    var auth = FirebaseAuth.getInstance()
    var user = auth.currentUser
    var uid = user?.uid

    var db = FirebaseFirestore.getInstance()
    var i = 0


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

}