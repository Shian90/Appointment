package com.example.app_ointmentt.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class PatientClass(val usernamePatient: String, val emailPatient: String, val passwordPatient: String, val dobPatient: String, val contactPatient: String, val bloodgroupPatient: String, val genderPatient: String, val addressDistrictPatient: String, val addressAreaPatient: String) : Parcelable {
    constructor() : this("","","","","","","","","")
}