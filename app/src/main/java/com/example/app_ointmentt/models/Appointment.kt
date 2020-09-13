package com.example.app_ointmentt.models

import org.json.JSONObject

class Appointment
{
    lateinit var id: String
    lateinit var patientId: String
    lateinit var slotId: String
    lateinit var prescription: String
    lateinit var status: String
    lateinit var slot: Slot
    lateinit var patient: Patient

    fun fromJSON(res: JSONObject) : Appointment
    {
        val appointment = Appointment()

        if ( !res.isNull("id") )
            appointment.id = res.get("id").toString()

        if ( !res.isNull("patientId"))
            appointment.patientId = res.get("patientId").toString()

        if ( !res.isNull("slotId"))
            appointment.slotId = res.get("slotId").toString()

        if ( !res.isNull("prescription"))
            appointment.prescription = res.get("prescription").toString()

        if ( !res.isNull("status"))
            appointment.status = res.get("status").toString()

        if ( res.has("slot"))
            appointment.slot = Slot().fromJSON(res.getJSONObject("slot"))

        if ( res.has("patient"))
            appointment.patient = Patient().fromJSON(res.getJSONObject("patient"))

        return appointment
    }
}