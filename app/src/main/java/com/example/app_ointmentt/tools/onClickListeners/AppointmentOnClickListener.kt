package com.example.app_ointmentt.tools.onClickListeners

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.app_ointmentt.R
import com.example.app_ointmentt.databasing.AppointmentDB
import com.example.app_ointmentt.models.Appointment
import com.example.app_ointmentt.tools.adaptersNew.HistoryAdapter
import com.example.app_ointmentt.tools.adaptersNew.NotificationAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener

class AppointmentOnClickListener(val context: Context, val usertype: String) : OnItemClickListener, AppointmentDB.CompleteAppointmentSuccessListener, AppointmentDB.CompleteAppointmentFailureListener, AppointmentDB.UpdatePrescriptionSuccessListener, AppointmentDB.UpdatePrescriptionFailureListener{

    lateinit var dialog: AlertDialog
    lateinit var layoutInflater: LayoutInflater
    lateinit var appdb: AppointmentDB
    lateinit var app: Appointment

    override fun onItemClick(item: Item<*>, view: View) {

        if ( item is HistoryAdapter )
        {
            item as HistoryAdapter
            app = item.app
        }
        else
        {
            item as NotificationAdapter
            app = item.app
        }

        appdb = AppointmentDB(context)
        appdb.setCompleteAppointmentSuccessListener(this)
        appdb.setCompleteAppointmentFailureListener(this)
        appdb.setUpdatePrescriptionSuccessListener(this)
        appdb.setUpdatePrescriptionFailureListener(this)

        layoutInflater = LayoutInflater.from(context)
        dialog = AlertDialog.Builder(context).create()
        val dialogview = layoutInflater.inflate(R.layout.layout_appointment_details, null)

        val button = dialogview.findViewById<Button>(R.id.view_appointment_layout_button)
        val drugTextBox = dialogview.findViewById<EditText>(R.id.prescription_appointment_drugs)
        val diagnosisTextBox = dialogview.findViewById<EditText>(R.id.prescription_appointment_diagnosis)
        dialogview.findViewById<TextView>(R.id.doctor_name_appointment_details).text = app.slot.doctor.name
        dialogview.findViewById<TextView>(R.id.patient_name_appointment_details).text = app.patient.name
        dialogview.findViewById<TextView>(R.id.date_appointment_details).text = app.slot.dateOfSlot
        dialogview.findViewById<TextView>(R.id.start_time_appointment_details).text = app.slot.startTime
        dialogview.findViewById<TextView>(R.id.end_time_appointment_details).text = app.slot.endTime

        if ( usertype == "doctor" )
        {
            button.text = "Update Prescription"
            button.setOnClickListener {
                if ( drugTextBox.text.isEmpty() || diagnosisTextBox.text.isEmpty() )
                {
                    Toast.makeText(context, "Please enter a valid prescription (diagnosis, drugs) before updating it", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    val pres = "Diagnosis: " + diagnosisTextBox.text.toString() + " Drugs: " + drugTextBox.text.toString()
                    appdb.updatePrescription(app.id, pres)
                }
            }
        }
        else if ( usertype == "patient" )
        {
            button.text = "Complete appointment"
            var drugs: String
            var diagnosis: String
            if ( app.prescription == "NONE" )
            {
                drugs = "N/A"
                diagnosis = "N/A"
            }
            else
            {
                var temp = app.prescription
                diagnosis = temp.substring(app.prescription.indexOf("Diagnosis: ").plus(11), app.prescription.indexOf("Drugs: "))
                temp = app.prescription
                drugs = temp.substring(app.prescription.indexOf("Drugs: ").plus(7), app.prescription.lastIndex+1)
            }
            drugTextBox.setText(drugs)
            diagnosisTextBox.setText(diagnosis)
            drugTextBox.isFocusable = false
            drugTextBox.isEnabled = false
            diagnosisTextBox.isFocusable = false
            diagnosisTextBox.isEnabled = false
            button.setOnClickListener {
                appdb.completeAppointment(app.id)
            }
        }
        dialog.setView(dialogview)
        dialog.setCancelable(true)
        dialog.show()
    }

    override fun completeAppointmentSuccess() {
        Toast.makeText(context, "Successfully completed appointment", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

    override fun completeAppointmentFailure(message: String?) {
        Toast.makeText(context, "Failed to complete appointment. Please try again.", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

    override fun updatePrescriptionSuccess() {
        Toast.makeText(context, "Successfully updated prescription", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }

    override fun updatePrescriptionFailure(message: String?) {
        Toast.makeText(context, "Failed to update prescription. Please try again.", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }
}