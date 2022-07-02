package com.eventable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.eventable.model.Question
import kotlinx.android.synthetic.main.view_cardlayout_data_1_fragment.view.*


class RecyclerAdapterData1Fragment(val questionlist: MutableList<Question>) :
    RecyclerView.Adapter<RecyclerAdapterData1Fragment.ViewHolder>() {

    private lateinit var navController: NavController
    private lateinit var events_id: String

    var countEventId = mutableListOf<String>()
    var count = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterData1Fragment.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_cardlayout_data_1_fragment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = questionlist.size

    override fun onBindViewHolder(holder: RecyclerAdapterData1Fragment.ViewHolder, position: Int) {
        holder.bind(questionlist[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question) {


/*            itemView.question5Data1HolderTV.text = question.questionname
            itemView.cardLayoutLocationData1HolderTV.text = question.votesyes.toString()
            itemView.dateData3HolderTV.text = question.votesno.toString()*/

        }

    }


    /*private lateinit var navController: NavController

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterConfirmed.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_cardlayout_confirmed, parent, false)
        return ViewHolder(v)
    }

        }
    }
}