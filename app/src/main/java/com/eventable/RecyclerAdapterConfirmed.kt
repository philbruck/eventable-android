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
import com.eventable.model.Event
import kotlinx.android.synthetic.main.view_cardlayout.view.*


class RecyclerAdapterConfirmed(val events: List<Event>) :
    RecyclerView.Adapter<RecyclerAdapterConfirmed.ViewHolder>() {

    private lateinit var navController: NavController
    private lateinit var events_id : String

    var countEventId = mutableListOf<String>()
    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterConfirmed.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_cardlayout_confirmed, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: RecyclerAdapterConfirmed.ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event) {
            itemView.headlineTV.text = event.name
            itemView.placeHolderTV.text = event.location
            itemView.dateHolderTV.text = event.date
            itemView.timeHolderTV.text = "${event.starttime} Uhr"

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











    /*private lateinit var navController: NavController

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
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_cardlayout_confirmed, parent, false)
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

                //*navController = findNavController(itemView)

                val action_ConfirmedToSelf =
                    ConfirmedFragmentDirections.actionConfirmedFragmentSelf()
                navController.navigate(action_ConfirmedToSelf) //philbruck: geht auch "// navController.navigate(R.id.action.....)"

            }
        }
    }
    */
     */


   }