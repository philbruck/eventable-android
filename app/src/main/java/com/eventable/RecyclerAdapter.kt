package com.eventable

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.provider.CalendarContract
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
import org.w3c.dom.Text
import java.lang.reflect.Array


// Augustin: Diese Klasse hab ich erstellt
class RecyclerAdapter(val events: List<Event>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var navController: NavController
    private lateinit var events_id : String

    //Augustin: Speicherung von Events-Ids um Data1Fragment die richtige Event-ID mitgeben zu können
    var countEventId = mutableListOf<String>()
    var count = 0

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
            itemView.dataHolderVotes.text = event.votes.toString()
            itemView.dataHolderVotes.text = event.votesUser?.size.toString()

            countEventId.add(event.eventId)
            count++
        }

        init {
            itemView.setOnClickListener {
                val pos: Int =
                    bindingAdapterPosition //philbruck: wenn man die akteulle Position wissen will

                navController = findNavController(itemView)

                val action_HomeToData1 =
                    HomeFragmentDirections.actionHomeFragmentToData1Fragment(countEventId[pos])

                navController.navigate(action_HomeToData1) //philbruck: geht auch "// navController.navigate(R.id.action.....)"
            }
        }
    }
}


