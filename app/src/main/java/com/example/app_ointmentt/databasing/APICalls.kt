//This is where we're going to post to all the routes

package com.example.app_ointmentt.databasing

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface APICalls {

    //doctor routes

    @POST("doctor/findById")
    fun getDoctorById(@Body params: RequestBody): Call<ResponseBody>

    @POST("doctor/find")
    fun getDoctors(@Body params: RequestBody): Call<ResponseBody>

    @POST("doctor/findTop")
    fun getTopDoctors(@Body params: RequestBody): Call<ResponseBody>

    @POST("doctor/updateProfile")
    fun updateProfileById(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    @POST("doctor/deleteById")
    fun deleteDoctorById(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>


    //patient routes

    @POST("patient/findById")
    fun getPatientById(@Body params: RequestBody): Call<ResponseBody>

    @POST("patient/updateProfile")
    fun updatePatientProfileById(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    @POST("patient/deleteById")
    fun deletePatientById(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    //slot routes

    @POST("slot/create")
    fun createSlot(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    @POST("slot/getSlotById")
    fun getSlotById(@Body params: RequestBody): Call<ResponseBody>

    @POST("slot/viewAllSlotsByDoctorId")
    fun viewAllSlotsByDoctor(@Body params: RequestBody): Call<ResponseBody>

    @POST("slot/deleteSlotByDoctorId")
    fun deleteSlotByDoctorId(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    @POST("slot/deleteSlotId")
    fun deleteSlotId(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>


    //appointment routes

    @POST("appointment/createAppointment")
    fun createAppointment(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    @POST("appointment/viewAppointment")
    fun  viewAppointmentById(@Body params: RequestBody): Call<ResponseBody>


    @POST("appointment/viewPastAppointmentsPatient")
    fun viewPastAppointmentsPatient(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    @POST("appointment/viewPastAppointmentsDoctor")
    fun viewPastAppointmentsDoctor(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    @POST("appointment/viewUpcomingAppointmentsPatient")
    fun viewUpcomingAppointmentsPatient(@Body params: RequestBody): Call<ResponseBody>

    @POST("appointment/viewUpcomingAppointmentsDoctor")
    fun viewUpcomingAppointmentsDoctor(@Body params: RequestBody): Call<ResponseBody>

    @POST("appointment/deleteAppointment")
    fun deleteAppointmentById(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    @POST("appointment/completeAppointment")
    fun completeAppointment(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    @POST("appointment/updatePrescription")
    fun updatePrescription(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    //rating routes

    @POST("rating/getById")
    fun getRatingsById(@Body params: RequestBody): Call<ResponseBody>

    @POST("rating/updateById")
    fun editRatingsById(@Header("Authorization") jwt: String, @Body params: RequestBody): Call<ResponseBody>

    //auth routes

    @POST("auth/signup/doctor")
    fun registerDoctorBasic(@Body params: RequestBody): Call<ResponseBody>

    @POST("auth/signup/patient")
    fun registerPatientBasic(@Body params: RequestBody): Call<ResponseBody>

    @POST("auth/login/doctor")
    fun loginDoctor(@Body params: RequestBody): Call<ResponseBody>

    @POST("auth/login/patient")
    fun loginPatient(@Body params: RequestBody): Call<ResponseBody>


}