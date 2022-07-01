package com.eventable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eventable.model.Event
import kotlinx.android.synthetic.main.view_cardlayout.view.*


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
            itemView.nameTV.text = event.name
            itemView.placeHolderTV.text = event.location
            itemView.dateHolderTV.text = event.date
            itemView.timeHolderTV.text = "${event.starttime} Uhr"
            itemView.dataHolderVotes.text = event.votes?.size.toString()

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


