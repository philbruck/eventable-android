package com.eventable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var testvar = arrayOf("M-test1", "B-test2", "F-test3", "O-test4", "C-test5", "P-test6", "D-test7", "W-test8", "Q-test9", "A-test10", "F-test11", "Ö-test12", "V-test13", "X-test14")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_cardlayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) { //philbruck: der Binder matcht die Daten auf die XML Elemente
        holder.itemTitle.text = testvar[position]
    }

    override fun getItemCount(): Int { //philbruck: wie bei Java, man muss sagn wie viele Emelent man hat
        return testvar.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){ //philbruck: innere Klasse der die verbindung zu den MXL Elemeten regelt

        var itemTitle : TextView = itemView.findViewById(R.id.testTextView1)

        init {
            itemView.setOnClickListener {
                val pos: Int = bindingAdapterPosition

                //var variable_1 = HomeFragmentDirections.actionHomeFragmentToData1Fragment()
                //findNavController().navigate(variable_1)


            }
        }
    }

}