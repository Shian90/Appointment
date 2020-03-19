package com.example.app_ointmentt

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class DoctorClass(val usernameDoctor: String, val emailDoctor: String, val passwordDoctor: String, val dobDoctor: String, val contactDoctor: String, val certificateNumber: String, val doctorType: String, val fieldOfSpeciality: String, val chamberLocation: String, val bloodgroupDoctor: String, val genderDoctor: String, val addressDistrictDoctor: String, val addressAreaDoctor: String) : Parcelable {
    constructor():this("","","","","","","","","","","","","")
}