package com.example.app_ointmentt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.R
import com.example.app_ointmentt.dataset.doctor
import com.example.app_ointmentt.models.DoctorType
import com.example.app_ointmentt.ui.fragment.PatientRequestAppointment
import com.example.app_ointmentt.ui.fragment.SeeAllDoctorsFragment
import com.example.app_ointmentt.utils.changeFragmentFromFragment
import kotlinx.android.synthetic.main.cardview_notification.view.txt
import kotlinx.android.synthetic.main.cardview_recycler_expanded_doctor_list.view.*


class DoctorTypeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
    private var items: List<DoctorType> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.cardview_recycler_expanded_doctor_list, parent, false)
        return DoctorTypeViewHolder(view).listen{ pos, type ->
            val item = items[pos]
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
        private val doctor1 = itemView.doctorList1
        private val doctor2 = itemView.doctorList2
        private val doctor3 = itemView.doctorList3
        private val doctor4 = itemView.doctorList4
        private val doctor5 = itemView.doctorList5
        private val doctorName1 = itemView.doctorListName1
        private val doctorName2 = itemView.doctorListName2
        private val doctorName3 = itemView.doctorListName3
        private val doctorName4 = itemView.doctorListName4
        private val doctorName5 = itemView.doctorListName5
        private val seeAllBtn = itemView.seeAllDoctors
        private val arrowButton = itemView.arrowButton
        fun bind(doctorType: DoctorType){
            doctorTypeTitle.text = doctorType.title
            isExpanded.visibility = if (doctorType.expanded) View.VISIBLE else View.GONE
            val doctors = doctor.members.filter {
                it.doctorType == doctorType.title
            }
            val numberOfDoctors = doctors.size
            val doctorsArray = arrayOf(doctor1,doctor2,doctor3,doctor4,doctor5)
            val doctorNames  = arrayOf(doctorName1,doctorName2,doctorName3,doctorName4,doctorName5)
            for(index:Int in doctorsArray.indices){
                if(index > numberOfDoctors -1 ){
                    doctorsArray[index].visibility = View.GONE
                }
                else{
                    doctorsArray[index].visibility = View.VISIBLE
                    doctorNames[index].text = doctors[index].name
                    doctorsArray[index].setOnClickListener{
                        changeFragmentFromFragment(
                            fragment = PatientRequestAppointment.newInstance(doctorName = doctors[index].name),
                            context = it.context,
                            root = R.id.mainPatient,
                            addToBackStack = true)
                    }
                }
            }

            seeAllBtn.setOnClickListener{
                changeFragmentFromFragment(
                    fragment = SeeAllDoctorsFragment(),
                    context = it.context,
                    root = R.id.mainPatient,
                    addToBackStack = true)
            }

        }

    }
}