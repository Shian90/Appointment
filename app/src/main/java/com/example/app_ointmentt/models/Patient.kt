package com.example.app_ointmentt.models

import org.json.JSONObject

class Patient
{
    lateinit var id: String
    lateinit var name: String
    lateinit var email: String
    lateinit var pass: String
    lateinit var phone: String
    lateinit var dob: String
    lateinit var gender: String
    lateinit var blood: String
    lateinit var address: String
    lateinit var past: String

    fun fromJSON(res: JSONObject) : Patient
    {
        val patient = Patient()

        if ( !res.isNull("id"))
            patient.id = res.get("id").toString()

        if ( !res.isNull("name"))
            patient.name = res.get("name").toString()

        if ( !res.isNull("email"))
            patient.email = res.get("email").toString()

        if ( !res.isNull("pass"))
            patient.pass = res.get("pass").toString()

        if ( !res.isNull("phone"))
            patient.phone = res.get("phone").toString()

        if ( !res.isNull("dob"))
            patient.dob = res.get("dob").toString()

        if ( !res.isNull("gender"))
            patient.gender = res.get("gender").toString()

        if ( !res.isNull("blood"))
            patient.blood = res.get("blood").toString()

        if ( !res.isNull("address"))
            patient.address = res.get("address").toString()

        if ( !res.isNull("past"))
            patient.past = res.get("past").toString()

        return patient
    }

    fun isDOBInitialized() = ::dob.isInitialized
    fun isGenderInitialized() = ::gender.isInitialized
    fun isPhoneInitialized() = ::phone.isInitialized
    fun isBloodInitialized() = ::blood.isInitialized
    fun isAddressInitialized() = ::address.isInitialized
    fun isPastInitialized() = ::past.isInitialized
}