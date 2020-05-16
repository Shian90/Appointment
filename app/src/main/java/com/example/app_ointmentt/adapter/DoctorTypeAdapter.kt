package com.example.app_ointmentt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.R
import com.example.app_ointmentt.dataset.doctor
import com.example.app_ointmentt.models.DoctorType
import com.example.app_ointmentt.ui.fragment.PatientRequestAppointment
import kotlinx.android.synthetic.main.cardview_notification.view.txt
import kotlinx.android.synthetic.main.cardview_recycler_expanded_doctor_list.view.*

class DoctorTypeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
    private var items: List<DoctorType> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.cardview_recycler_expanded_doctor_list, parent, false)
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
        fun bind(doctorType: DoctorType){
            doctorTypeTitle.setText(doctorType.title)
            isExpanded.visibility = if (doctorType.expanded) View.VISIBLE else View.GONE
            var doctors = doctor.members.filter {
                it.doctorType == doctorType.title
            }
            var numberOfDoctors = doctors.size
            doctor1.visibility = View.GONE
            doctor2.visibility = View.GONE
            doctor3.visibility = View.GONE
            doctor4.visibility = View.GONE
            doctor5.visibility = View.GONE
            if(numberOfDoctors >= 1){
                doctor1.visibility = View.VISIBLE
                doctorName1.text = doctors[0].name
                doctor1.setOnClickListener{
                    val fragment = PatientRequestAppointment()
                    val activity  = it.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().replace(
                        R.id.mainPatient,
                        fragment,
                        fragment.javaClass.simpleName)
                        .commit()
                }
            }
            if(numberOfDoctors >= 2){
                doctor2.visibility = View.VISIBLE
                doctorName2.text = doctors[1].name
                doctor2.setOnClickListener{
                    val fragment = PatientRequestAppointment()
                    val activity  = it.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().replace(
                        R.id.mainPatient,
                        fragment,
                        fragment.javaClass.simpleName)
                        .commit()
                }
            }
            if(numberOfDoctors >= 3){
                doctor3.visibility = View.VISIBLE
                doctorName3.text = doctors[2].name
                doctor3.setOnClickListener{
                    val fragment = PatientRequestAppointment()
                    val activity  = it.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().replace(
                        R.id.mainPatient,
                        fragment,
                        fragment.javaClass.simpleName)
                        .commit()
                }
            }
            if(numberOfDoctors >= 4){
                doctor4.visibility = View.VISIBLE
                doctorName4.text = doctors[3].name
                doctor4.setOnClickListener{
                    val fragment = PatientRequestAppointment()
                    val activity  = it.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().replace(
                        R.id.mainPatient,
                        fragment,
                        fragment.javaClass.simpleName)
                        .commit()
                }
            }
            if(numberOfDoctors >= 5){
                doctor5.visibility = View.VISIBLE
                doctorName5.text = doctors[4].name
                doctor5.setOnClickListener{
                    val fragment = PatientRequestAppointment()
                    val activity  = it.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction().replace(
                        R.id.mainPatient,
                        fragment,
                        fragment.javaClass.simpleName)
                        .commit()
                }
            }
        }



    }
}