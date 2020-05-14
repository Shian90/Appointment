package com.example.app_ointmentt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.R
import com.example.app_ointmentt.dataset.doctorTypeData
import com.example.app_ointmentt.models.DoctorType
import kotlinx.android.synthetic.main.cardview_notification.view.txt
import kotlinx.android.synthetic.main.cardview_patient_homepage.view.*

class DoctorTypeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
    private var items: List<DoctorType> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.cardview_patient_homepage, parent, false)
        return DoctorTypeViewHolder(view).listen{ pos, type ->
            val item = items.get(pos)
            item.expanded = !item.expanded
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is DoctorTypeViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(titleList: List<DoctorType>){
        items = titleList

    }

    class DoctorTypeViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        private val doctorTypeTitle: TextView = itemView.txt
        private val isExpanded = itemView.expandableLayout

        fun bind(doctorType: DoctorType){
            doctorTypeTitle.setText(doctorType.title)
            isExpanded.visibility = if (doctorType.expanded) View.VISIBLE else View.GONE


        }

        fun onClickRecyclerView(){
            val doctorType = doctorTypeData.members.get(adapterPosition)
            doctorType.expanded = !doctorType.expanded
        }


    }
}