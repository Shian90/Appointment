package com.example.app_ointmentt.models

import org.json.JSONObject

class Doctor
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
    lateinit var specialty: String
    lateinit var bmdc: String

    fun fromJSON(res: JSONObject) : Doctor
    {
        val doctor = Doctor()

        if ( !res.isNull("id") )
            doctor.id = res.get("id").toString()

        if ( !res.isNull("name"))
            doctor.name = res.get("name").toString()

        if ( !res.isNull("email"))
            doctor.email = res.get("email").toString()

        if ( !res.isNull("pass"))
            doctor.pass = res.get("pass").toString()

        if ( !res.isNull("phone"))
            doctor.phone = res.get("phone").toString()

        if ( !res.isNull("dob"))
            doctor.dob = res.get("dob").toString()

        if ( !res.isNull("gender"))
            doctor.gender = res.get("gender").toString()

        if ( !res.isNull("blood"))
            doctor.blood = res.get("blood").toString()

        if ( !res.isNull("address"))
            doctor.address = res.get("address").toString()

        if ( !res.isNull("specialty"))
            doctor.specialty = res.get("specialty").toString()

        if ( !res.isNull("bmdc"))
            doctor.bmdc = res.get("bmdc").toString()

        return doctor
    }
    fun isDOBInitialized() = ::dob.isInitialized
    fun isGenderInitialized() = ::gender.isInitialized
    fun isPhoneInitialized() = ::phone.isInitialized
    fun isBloodInitialized() = ::blood.isInitialized
    fun isAddressInitialized() = ::address.isInitialized
    fun isSpecialtyInitialized() = ::specialty.isInitialized
}