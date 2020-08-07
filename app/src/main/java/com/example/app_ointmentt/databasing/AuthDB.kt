package com.example.app_ointmentt.databasing

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.app_ointmentt.models.Doctor
import com.example.app_ointmentt.models.Patient
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response

class AuthDB(val context: Context){

    //sharedPreference File Name
    private val sharedPrefFile = "appointmentSharedPref"
    //interface variables
    lateinit var mRegisterDoctorBasicSuccessListener: RegisterDoctorBasicSuccessListener
    lateinit var mRegisterDoctorBasicFailureListener: RegisterDoctorBasicFailureListener

    lateinit var mRegisterPatientBasicSuccessListener: RegisterPatientBasicSuccessListener
    lateinit var mRegisterPatientBasicFailureListener: RegisterPatientBasicFailureListener

    lateinit var mLoginDoctorSuccessListener: LoginDoctorSuccessListener
    lateinit var mLoginDoctorFailureListener: LoginDoctorFailureListener

    lateinit var mLoginPatientSuccessListener: LoginPatientSuccessListener
    lateinit var mLoginPatientFailureListener: LoginPatientFailureListener

    //functions
    fun registerDoctorBasic(name: String, email: String, pass: String, bmdc: String)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("name", name)
        paramsJSON.put("email", email)
        paramsJSON.put("pass", pass)
        paramsJSON.put("bmdc", bmdc)
        val params = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.registerDoctorBasic(params)

        call.enqueue(object: Callback<ResponseBody>
        {
            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                Log.d("LOLZIES", t.message.toString())
                mRegisterDoctorBasicFailureListener.registerDoctorBasicFailure()
            }

            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
//                    Log.d("LOLZIES", "Successfully received response: ${response.body()!!.string()}")
                    val jsonRes = JSONObject(response.body()!!.string())
                    val doctor = Doctor().fromJSON(jsonRes.getJSONObject("userObj"))
                    mRegisterDoctorBasicSuccessListener.registerDoctorBasicSuccess(doctor)
                }
                else
                {
                    Log.d("LOLZIES", "Failed to receive response")
                    mRegisterDoctorBasicFailureListener.registerDoctorBasicFailure()
                }
            }
        })
    }

    fun registerPatientBasic(name: String, email: String, pass: String)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("name", name)
        paramsJSON.put("email", email)
        paramsJSON.put("pass", pass)
        val params = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.registerPatientBasic(params)

        call.enqueue(object: Callback<ResponseBody>
        {
            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                Log.d("LOLZIES", t.message.toString())
                mRegisterPatientBasicFailureListener.registerPatientBasicFailure()
            }

            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
//                    Log.d("LOLZIES", "Successfully received response: ${response.body()!!.string()}")
                    val jsonRes = JSONObject(response.body()!!.string())
                    val patient = Patient().fromJSON(jsonRes.getJSONObject("userObj"))
                    mRegisterPatientBasicSuccessListener.registerPatientBasicSuccess(patient)
                }
                else
                {
                    Log.d("LOLZIES", "Failed to receive response")
                    mRegisterPatientBasicFailureListener.registerPatientBasicFailure()
                }
            }
        })
    }

    fun loginDoctor(email: String, pass: String)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("email", email)
        paramsJSON.put("pass", pass)
        val params = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.loginDoctor(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                Log.d("LOLZIES", t.message.toString())
                mLoginDoctorFailureListener.loginDoctorFailure()
            }

            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
//                    Log.d("LOLZIES", "Successfully received response: ${response.body()!!.string()}")
                    val jsonRes = JSONObject(response.body()!!.string())
                    val jwt = jsonRes.getString("jwt")
                    Log.d("LOLZIES", "jwt = $jwt")
                    val doctor = Doctor().fromJSON(jsonRes.getJSONObject("user"))
                    val uid = doctor.id
                    Log.d("LOLZIES", "uid = $uid")
                    val sharedPreferences:SharedPreferences = context.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("jwt", jwt)
                    editor.putString("uid", uid)
                    editor.putString("type", "doctor")
                    editor.apply()
                    mLoginDoctorSuccessListener.loginDoctorSuccess(doctor)
                }
                else
                {
                    Log.d("LOLZIES", "Failed to receive response")
                    mLoginDoctorFailureListener.loginDoctorFailure()
                }
            }
        })
    }

    fun loginPatient(email: String, pass: String)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("email", email)
        paramsJSON.put("pass", pass)
        val params = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.loginPatient(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                Log.d("LOLZIES", t.message.toString())
                mLoginPatientFailureListener.loginPatientFailure()
            }

            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
//                    Log.d("LOLZIES", "Successfully received response: ${response.body()!!.string()}")
                    val jsonRes = JSONObject(response.body()!!.string())
                    val jwt = jsonRes.getString("jwt")
                    Log.d("LOLZIES", "jwt = $jwt")
                    val patient = Patient().fromJSON(jsonRes.getJSONObject("user"))
                    val uid = patient.id
                    Log.d("LOLZIES", "uid = $uid")
                    val sharedPreferences:SharedPreferences = context.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("jwt", jwt)
                    editor.putString("uid", uid)
                    editor.putString("type", "patient")
                    editor.apply()
                    mLoginPatientSuccessListener.loginPatientSuccess(patient)
                }
                else
                {
                    Log.d("LOLZIES", "Failed to receive response")
                    mLoginPatientFailureListener.loginPatientFailure()
                }
            }
        })
    }

    //interfaces
    interface RegisterDoctorBasicSuccessListener
    {
        fun registerDoctorBasicSuccess(doctor: Doctor)
    }

    interface RegisterDoctorBasicFailureListener
    {
        fun registerDoctorBasicFailure()
    }

    interface RegisterPatientBasicSuccessListener
    {
        fun registerPatientBasicSuccess(patient: Patient)
    }

    interface RegisterPatientBasicFailureListener
    {
        fun registerPatientBasicFailure()
    }

    interface LoginDoctorSuccessListener
    {
        fun loginDoctorSuccess(doctor: Doctor)
    }

    interface LoginDoctorFailureListener
    {
        fun loginDoctorFailure()
    }

    interface LoginPatientSuccessListener
    {
        fun loginPatientSuccess(patient: Patient)
    }

    interface LoginPatientFailureListener
    {
        fun loginPatientFailure()
    }

    //interface setters
    fun setRegisterDoctorBasicSuccessListener(int: RegisterDoctorBasicSuccessListener)
    {
        this.mRegisterDoctorBasicSuccessListener = int
    }

    fun setRegisterDoctorBasicFailureListener(int: RegisterDoctorBasicFailureListener)
    {
        this.mRegisterDoctorBasicFailureListener = int
    }

    fun setRegisterPatientBasicSuccessListener(int: RegisterPatientBasicSuccessListener)
    {
        this.mRegisterPatientBasicSuccessListener = int
    }

    fun setRegisterPatientBasicFailureListener(int: RegisterPatientBasicFailureListener)
    {
        this.mRegisterPatientBasicFailureListener = int
    }

    fun setLoginDoctorSuccessListener(int: LoginDoctorSuccessListener)
    {
        this.mLoginDoctorSuccessListener = int
    }

    fun setLoginDoctorFailureListener(int: LoginDoctorFailureListener)
    {
        this.mLoginDoctorFailureListener = int
    }

    fun setLoginPatientSuccessListener(int: LoginPatientSuccessListener)
    {
        this.mLoginPatientSuccessListener = int
    }

    fun setLoginPatientFailureListener(int: LoginPatientFailureListener)
    {
        this.mLoginPatientFailureListener = int
    }
}