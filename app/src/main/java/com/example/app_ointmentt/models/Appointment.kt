package com.example.app_ointmentt.models

import org.json.JSONObject

class Appointment
{
    lateinit var id: String
    lateinit var patientId: String
    lateinit var slotId: String
    lateinit var prescription: String
    lateinit var status: String

    fun fromJSON(res: JSONObject) : Appointment
    {
        val appointment = Appointment()

        if ( res.has("id") )
            appointment.id = res.get("id").toString()

        return appointment
    }
}