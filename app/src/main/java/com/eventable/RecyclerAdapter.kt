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
class RecyclerAdapter(val events: List<Event>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var navController: NavController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_cardlayout, parent, false)
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
            itemView.invitationHolderTV.text = event.votes.toString()


            //Augustin: Mögliche Lösung um unterschiedliche Anzahl von Arrays auszugeben. Kann auch jeweils in andere Felder gespeichert werden
/*            var countquestions = event.questions?.size?.toInt()
            when(countquestions) {
                0 -> Log.e(TAG, "Keine Fragen vorhanden")
                1 -> itemView.confirmedHolderTV.text = event.questions?.get(0)
                2 -> itemView.confirmedHolderTV.text  = "${event.questions?.get(0)} ${event.questions?.get(1)}"
                3 -> itemView.confirmedHolderTV.text = "${event.questions?.get(0)} ${event.questions?.get(1)} ${event.questions?.get(2)}"
            }*/
        }


        var event_id = "test"

        init {
            itemView.setOnClickListener {
                val pos: Int =
                    bindingAdapterPosition //philbruck: wenn man die akteulle Position wissen will

                val event_id = pos.toString() //philbruck: hier muss noch die event-Id gespeciht werden damit die im nächsten Schritt ausgelesen wird

                navController = findNavController(itemView)

                val action_HomeToData1 =
                    HomeFragmentDirections.actionHomeFragmentToData1Fragment(event_id)

                navController.navigate(action_HomeToData1) //philbruck: geht auch "// navController.navigate(R.id.action.....)"


            }
        }
    }
}


