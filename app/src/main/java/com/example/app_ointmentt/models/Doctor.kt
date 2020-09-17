package com.example.app_ointmentt.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import org.json.JSONObject

@Serializable
class Doctor
{
    @SerializedName("id")
    lateinit var id: String
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("email")
    lateinit var email: String
    @SerializedName("pass")
    lateinit var pass: String
    @SerializedName("phone")
    lateinit var phone: String
    @SerializedName("dob")
    lateinit var dob: String
    @SerializedName("gender")
    lateinit var gender: String
    @SerializedName("blood")
    lateinit var blood: String
    @SerializedName("address")
    lateinit var address: String
    @SerializedName("specialty")
    lateinit var specialty: String
    @SerializedName("bmdc")
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