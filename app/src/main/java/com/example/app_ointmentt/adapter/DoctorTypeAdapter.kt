package com.example.app_ointmentt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.DoctorDB
import com.example.app_ointmentt.models.RatedDoctor
import com.example.app_ointmentt.models.old.DoctorType
import com.example.app_ointmentt.ui.patient.fragment.PatientRequestAppointment
import com.example.app_ointmentt.ui.patient.fragment.SeeAllDoctorsFragment
import com.example.app_ointmentt.utils.changeFragmentFromFragment
import kotlinx.android.synthetic.main.cardview_notification.view.txt
import kotlinx.android.synthetic.main.cardview_recycler_expanded_doctor_list.view.*


class DoctorTypeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var context: Context? = null
    companion object {
        fun newInstance(context: Context): DoctorTypeAdapter {
            val adapter =
                DoctorTypeAdapter()
            adapter.context = context
            return adapter
        }
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

    private var items: List<DoctorType> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.cardview_recycler_expanded_doctor_list, parent, false)
        return DoctorTypeViewHolder(view,context!!).listen{ pos, type ->
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

    class DoctorTypeViewHolder constructor(itemView: View, var context: Context): RecyclerView.ViewHolder(itemView),
        DoctorDB.GetTopDoctorsSuccessListener,
        DoctorDB.GetTopDoctorsFailureListener{
        private val doctorTypeTitle: TextView = itemView.txt
        private val isExpanded = itemView.expandableLayout
        var doctor1 = itemView.doctorList1
        var doctor2 = itemView.doctorList2
        var doctor3 = itemView.doctorList3
        var doctor4 = itemView.doctorList4
        var doctor5 = itemView.doctorList5
        var doctorName1 = itemView.doctorListName1
        var doctorName2 = itemView.doctorListName2
        var doctorName3 = itemView.doctorListName3
        var doctorName4 = itemView.doctorListName4
        var doctorName5 = itemView.doctorListName5
        var seeAllBtn = itemView.seeAllDoctors
        lateinit var doctors: ArrayList<RatedDoctor>
        fun bind(doctorType: DoctorType){
            val ddb = context.let { DoctorDB(it) }
            ddb.setGetTopDoctorsSuccessListener(this)
            ddb.setGetTopDoctorsFailureListener(this)
            val queryOpts = mutableMapOf<String, String>()
            doctorType.title?.let { queryOpts.put("specialty", it) }
            doctorTypeTitle.text = doctorType.title
            isExpanded.visibility = if (doctorType.expanded) View.VISIBLE else View.GONE
            ddb.getTopDoctors(queryOpts)

            seeAllBtn.setOnClickListener{
                changeFragmentFromFragment(
                    fragment = SeeAllDoctorsFragment.newInstance(doctorTypeTitle.text.toString()),
                    context = it.context,
                    root = R.id.mainPatient,
                    addToBackStack = true)
            }

        }

        override fun getTopDoctorsSuccess(ratedDoctorArray: ArrayList<RatedDoctor>) {
            doctors = ratedDoctorArray
            val numberOfDoctors = doctors.size
            val doctorsArray = arrayOf(doctor1,doctor2,doctor3,doctor4,doctor5)
            val doctorNames  = arrayOf(doctorName1,doctorName2,doctorName3,doctorName4,doctorName5)
            for(index:Int in doctorsArray.indices){
                if(index > numberOfDoctors -1 ){
                    doctorsArray[index].visibility = View.GONE
                }
                else{
                    doctorsArray[index].visibility = View.VISIBLE
                    doctorNames[index].text = doctors[index].doctor.name
                    doctorsArray[index].setOnClickListener{
                        changeFragmentFromFragment(
                            fragment = PatientRequestAppointment.newInstance(doctorName = doctors[index].doctor.name,
                                doctorId = doctors[index].doctor.id,
                                doctorSpecialty = doctors[index].doctor.specialty,
                                doctorAddress = doctors[index].doctor.address),
                            context = it.context,
                            root = R.id.mainPatient,
                            addToBackStack = true)
                    }
                }
            }
        }

        override fun getTopDoctorsFailure(message: String) {
           Toast.makeText(context,"Wait for doctor to be fetched",Toast.LENGTH_SHORT).show()
        }

    }

}