package com.example.app_ointmentt.databasing

import android.content.Context
import com.example.app_ointmentt.models.Patient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientDB(val context: Context) {

    /***Create interfaces***/

    //Get Patient By Id interfaces
    lateinit var mGetPatientByIdSuccessListener: GetPatientByIdSuccessListener
    lateinit var mGetPatientByIdFailureListener: GetPatientByIdFailureListener

    /***Calling API through functions***/
    fun getPatientById(id: String)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("patientId", id)
        val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.getPatientById(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                mGetPatientByIdFailureListener.getPatientByIdFailure()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
                    val jsonRes = JSONObject(response.body()!!.string())
                    val patient = Patient().fromJSON(jsonRes.getJSONObject("userObj"))
                    mGetPatientByIdSuccessListener.getPatientByIdSuccess(patient)
                }
                else
                    mGetPatientByIdFailureListener.getPatientByIdFailure()
            }
        })
    }

    /***Interfaces***/
    interface GetPatientByIdSuccessListener
    {
        fun getPatientByIdSuccess(patient: Patient)
    }

    interface GetPatientByIdFailureListener
    {
        fun getPatientByIdFailure()
    }

    /***Interface setters***/
    fun setGetPatientByIdSuccessListener(int: GetPatientByIdSuccessListener)
    {
        this.mGetPatientByIdSuccessListener = int
    }
    fun setGetPatientByIdFailureListener(int: GetPatientByIdFailureListener)
    {
        this.mGetPatientByIdFailureListener = int
    }

}