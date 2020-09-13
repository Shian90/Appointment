package com.example.app_ointmentt.models

import org.json.JSONObject

class Slot
{
    lateinit var id: String
    lateinit var doctorId: String
    lateinit var dateOfSlot: String
    lateinit var startTime: String
    lateinit var endTime: String
    var status: Int? = 0
    lateinit var doctor: Doctor

    fun fromJSON(res: JSONObject) : Slot
    {
        val slot = Slot()

        if ( !res.isNull("id") )
            slot.id = res.get("id").toString()

        if ( !res.isNull("doctorId") )
            slot.doctorId = res.get("doctorId").toString()

        if ( !res.isNull("dateOfSlot") )
            slot.dateOfSlot = res.get("dateOfSlot").toString()

        if ( !res.isNull("startTime") )
            slot.startTime = res.get("startTime").toString()

        if ( !res.isNull("endTime") )
            slot.endTime = res.get("endTime").toString()

        if ( !res.isNull("status") )
            slot.status = res.get("status").toString().toInt()

        if (res.has("doctor"))
            slot.doctor = Doctor().fromJSON(res.getJSONObject("doctor"))

        return slot
    }
}

