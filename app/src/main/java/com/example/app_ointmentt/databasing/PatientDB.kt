package com.example.app_ointmentt.databasing

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
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

    //Update Patient Profile interfaces
    lateinit var mUpdatePatientProfileSuccessListener: UpdatePatientProfileSuccessListener
    lateinit var mUpdatePatientProfileFailureListener: UpdatePatientProfileFailureListener

    //Delete Patient By Id interfaces
    lateinit var mDeletePatientByIdSuccessListener: DeletePatientByIdSuccessListener
    lateinit var mDeletePatientByIdFailureListener: DeletePatientByIdFailureListener

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

    fun updatePatientProfile(updOpts: Map<String, String>)
    {
        val sh = PreferenceManager.getDefaultSharedPreferences(context)
        val jwt = sh.getString("jwt", "NONE FOUND").toString()
        val uid = sh.getString("uid", "NONE FOUND").toString()
        if ( jwt == "NONE FOUND" || uid == "NONE FOUND" )
        {
            mUpdatePatientProfileFailureListener.updatePatientProfileFailure()
        }
        else
        {
            val paramsJSON = JSONObject()
            paramsJSON.put("id", uid)

            if ( updOpts.containsKey("name") )
                paramsJSON.put("name", updOpts["name"].toString())
            if ( updOpts.containsKey("email") )
                paramsJSON.put("email", updOpts["email"].toString())
            if ( updOpts.containsKey("phone") )
                paramsJSON.put("phone", updOpts["phone"].toString())
            if ( updOpts.containsKey("dob") )
                paramsJSON.put("dob", updOpts["dob"].toString())
            if ( updOpts.containsKey("gender") )
                paramsJSON.put("gender", updOpts["gender"].toString())
            if ( updOpts.containsKey("blood") )
                paramsJSON.put("blood", updOpts["blood"].toString())
            if ( updOpts.containsKey("address") )
                paramsJSON.put("address", updOpts["address"].toString())

            val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

            val headerJwt = "Bearer $jwt"
            val call = APIObject.api.updatePatientProfileById(headerJwt, params)

            call.enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    mUpdatePatientProfileFailureListener.updatePatientProfileFailure()
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if ( response.isSuccessful )
                    {
                        mUpdatePatientProfileSuccessListener.updatePatientProfileSuccess()
                    }
                    else
                    {
                        mUpdatePatientProfileFailureListener.updatePatientProfileFailure()
                    }
                }
            })
        }
    }

    fun deletePatientById()
    {
        val sh = PreferenceManager.getDefaultSharedPreferences(context)
        val jwt = sh.getString("jwt", "NONE FOUND").toString()
        val uid = sh.getString("uid", "NONE FOUND").toString()
        if ( jwt == "NONE FOUND" || uid == "NONE FOUND" )
        {
            Log.d("DELETEAPI", "$jwt $uid")
            mDeletePatientByIdFailureListener.deletePatientByIdFailure()
        }
        else {
            Log.d("DELETEAPI", "$jwt $uid")
            val paramsJSON = JSONObject()
            paramsJSON.put("patientId", uid)
            val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

            val headerJwt = "Bearer $jwt"
            val call = APIObject.api.deletePatientById(headerJwt, params)

            call.enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    mDeletePatientByIdFailureListener.deletePatientByIdFailure()
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if ( response.isSuccessful )
                    {
                        mDeletePatientByIdSuccessListener.deletePatientByIdSuccess()
                    }
                    else
                    {
                        mDeletePatientByIdFailureListener.deletePatientByIdFailure()
                    }
                }
            })
        }
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

    interface UpdatePatientProfileSuccessListener
    {
        fun updatePatientProfileSuccess()
    }

    interface UpdatePatientProfileFailureListener
    {
        fun updatePatientProfileFailure()
    }

    interface DeletePatientByIdSuccessListener
    {
        fun deletePatientByIdSuccess()
    }

    interface DeletePatientByIdFailureListener
    {
        fun deletePatientByIdFailure()
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

    fun setUpdatePatientProfileSuccessListener(int: UpdatePatientProfileSuccessListener)
    {
        this.mUpdatePatientProfileSuccessListener = int
    }

    fun setUpdatePatientProfileFailureListener(int: UpdatePatientProfileFailureListener)
    {
        this.mUpdatePatientProfileFailureListener = int
    }

    fun setDeletePatientByIdSuccessListener(int: DeletePatientByIdSuccessListener)
    {
        this.mDeletePatientByIdSuccessListener = int
    }

    fun setDeletePatientByIdFailureListener(int: DeletePatientByIdFailureListener)
    {
        this.mDeletePatientByIdFailureListener = int
    }

}