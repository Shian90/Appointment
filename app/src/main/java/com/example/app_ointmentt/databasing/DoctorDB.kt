package com.example.app_ointmentt.databasing


import android.content.Context
import android.preference.PreferenceManager
import com.example.app_ointmentt.models.Doctor
import com.example.app_ointmentt.models.Rating

import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoctorDB(val context: Context) {

    //interface variables
    //Find Doctor By Id
    lateinit var mGetDoctorByIdSuccessListener: GetDoctorByIdSuccessListener
    lateinit var mGetDoctorByIdFailureListener: GetDoctorByIdFailureListener

    //Find Doctors
    lateinit var mGetDoctorsSuccessListener: GetDoctorsSuccessListener
    lateinit var mGetDoctorsFailureListener: GetDoctorsFailureListener

    //Find Top Doctors
    lateinit var mGetTopDoctorsSuccessListener: GetTopDoctorsSuccessListener
    lateinit var mGetTopDoctorsFailureListener: GetTopDoctorsFailureListener

    //Update profile for doctors
    lateinit var mUpdateProfileDoctorSuccessListener: UpdateDoctorProfileSuccessListener
    lateinit var mUpdateProfileDoctorFailureListener: UpdateDoctorProfileFailureListener

    //Delete doctor profile
    lateinit var mDeleteProfileDoctorSuccessListener: DeleteDoctorProfileSuccessListener
    lateinit var mDeleteProfileDoctorFailureListener: DeleteDoctorProfileFailureListener

    //functions

    /*******Find Doctor By Id ********/
    fun getDoctorByID(id: String)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("doctorId", id)
        val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.getDoctorById(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                mGetDoctorByIdFailureListener.getDoctorByIDFailure()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
                    val jsonRes = JSONObject(response.body()!!.string())
                    val doctor = Doctor().fromJSON(jsonRes.getJSONObject("userObj"))
                    mGetDoctorByIdSuccessListener.getDoctorByIDSuccess(doctor)
                }
                else
                    mGetDoctorByIdFailureListener.getDoctorByIDFailure()
            }
        })
    }

    /**********Find the Doctors in general*********/
    fun getDoctors(queryOpts: Map<String, String>)
    {
        val paramsJSON = JSONObject()

        //Check queryOpts
        if ( queryOpts.containsKey("name") )
            paramsJSON.put("name", queryOpts["name"].toString())
        if ( queryOpts.containsKey("gender") )
            paramsJSON.put("gender", queryOpts["gender"].toString())
        if ( queryOpts.containsKey("address") )
            paramsJSON.put("address", queryOpts["address"].toString())
        if ( queryOpts.containsKey("specialty") )
            paramsJSON.put("specialty", queryOpts["specialty"].toString())
        if ( queryOpts.containsKey("limit") )
            paramsJSON.put("limit", queryOpts["limit"].toString())

        val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.getDoctors(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val message = "Failed to retrieve from database"
                mGetDoctorsFailureListener.getDoctorsFailure(message)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
                    val jsonRes = JSONObject(response.body()!!.string())
                    val doctors = jsonRes.getJSONArray("user")

                    if(doctors.length() != 0) {
                        for (i in 0 until doctors.length()) {
                            val doctorJsonObject = doctors.getJSONObject(i)
                            val doctor = Doctor().fromJSON(doctorJsonObject)
                            mGetDoctorsSuccessListener.getDoctorsSuccess(doctor)
                        }
                    }
                    else{
                        val message = "No doctor found"
                        mGetDoctorsFailureListener.getDoctorsFailure(message)
                    }
                }
                else{
                    val message = "Failed to retrieve response as success"
                    mGetDoctorsFailureListener.getDoctorsFailure(message)
                }

            }
        })
    }

    /************Find top Doctors in particular specialty********/
    fun getTopDoctors(queryOpts: Map<String, String>)
    {
        val paramsJSON = JSONObject()

        //Check queryOpts
        if ( queryOpts.containsKey("gender") )
            paramsJSON.put("gender", queryOpts["gender"].toString())
        if ( queryOpts.containsKey("address") )
            paramsJSON.put("address", queryOpts["address"].toString())
        if ( queryOpts.containsKey("specialty") )
            paramsJSON.put("specialty", queryOpts["specialty"].toString())
        if ( queryOpts.containsKey("limit") )
            paramsJSON.put("limit", queryOpts["limit"].toString())

        val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.getTopDoctors(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val message = "Failed to retrieve from database"
                mGetDoctorsFailureListener.getDoctorsFailure(message)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
                    val jsonRes = JSONObject(response.body()!!.string())
                    val doctors = jsonRes.getJSONArray("users")

                    if(doctors.length() != 0) {
                        for (i in 0 until doctors.length()) {
                            val doctorJsonObject = doctors.getJSONObject(i)
                            val ratingJsonObject = doctorJsonObject.getJSONObject("rating")
                            val rating = Rating().fromJSON(ratingJsonObject)
                            val doctor = Doctor().fromJSON(doctorJsonObject)
                            mGetTopDoctorsSuccessListener.getTopDoctorsSuccess(doctor, rating)
                        }
                    }
                    else{
                        val message = "No doctor found"
                        mGetTopDoctorsFailureListener.getTopDoctorsFailure(message)
                    }
                }
                else{
                    val message = "Failed to retrieve response as success"
                    mGetTopDoctorsFailureListener.getTopDoctorsFailure(message)
                }

            }
        })
    }

    fun updateDoctorProfile(updOpts: Map<String, String>)
    {
        val sh = PreferenceManager.getDefaultSharedPreferences(context)
        val jwt = sh.getString("jwt", "NONE FOUND").toString()
        val uid = sh.getString("uid", "NONE FOUND").toString()
        if ( jwt == "NONE FOUND" || uid == "NONE FOUND" )
        {
            //don't go any further
            mUpdateProfileDoctorFailureListener.updateDoctorProfileFailure()
        }
        else
        {
            val paramsJSON = JSONObject()
            paramsJSON.put("id", uid)

            //manually check the updOpts map; could possibly be done with an existing converter but I cannot be bothered at this point
            if ( updOpts.containsKey("name") )
                paramsJSON.put("name", updOpts["name"].toString())
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
            if ( updOpts.containsKey("specialty") )
                paramsJSON.put("specialty", updOpts["specialty"].toString())
            if ( updOpts.containsKey("bmdc") )
                paramsJSON.put("bmdc", updOpts["bmdc"].toString())

            val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

            val headerJwt = "Bearer $jwt"
            val call = APIObject.api.updateProfileById(headerJwt, params)

            call.enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    mUpdateProfileDoctorFailureListener.updateDoctorProfileFailure()
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if ( response.isSuccessful )
                    {
                        mUpdateProfileDoctorSuccessListener.updateDoctorProfileSuccess()
                    }
                    else
                    {
                        mUpdateProfileDoctorFailureListener.updateDoctorProfileFailure()
                    }
                }
            })
        }
    }

    /***** Delete doctor profile *****/
    fun deleteDoctorById()
    {
        val sh = PreferenceManager.getDefaultSharedPreferences(context)
        val jwt = sh.getString("jwt", "NONE FOUND").toString()
        val uid = sh.getString("uid", "NONE FOUND").toString()
        if ( jwt == "NONE FOUND" || uid == "NONE FOUND" )
        {
            //don't go any further
            mDeleteProfileDoctorFailureListener.deleteDoctorProfileFailure()
        }
        else {
            val paramsJSON = JSONObject()
            paramsJSON.put("doctorId", uid)
            val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

            val headerJwt = "Bearer $jwt"
            val call = APIObject.api.deleteDoctorById(headerJwt, params)

            call.enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    mDeleteProfileDoctorFailureListener.deleteDoctorProfileFailure()
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if ( response.isSuccessful )
                    {
                        mDeleteProfileDoctorSuccessListener.deleteDoctorProfileSuccess()
                    }
                    else
                    {
                        mDeleteProfileDoctorFailureListener.deleteDoctorProfileFailure()
                    }
                }
            })
        }
    }

    /***************interfaces**************/
    //Find Doctor By Id
    interface GetDoctorByIdSuccessListener
    {
        fun getDoctorByIDSuccess(doctor: Doctor)
    }

    interface GetDoctorByIdFailureListener
    {
        fun getDoctorByIDFailure()
    }

    //Find doctors
    interface GetDoctorsSuccessListener
    {
        fun getDoctorsSuccess(doctor: Doctor)
    }

    interface GetDoctorsFailureListener
    {
        fun getDoctorsFailure(message: String)
    }

    //Find Top Doctors
    interface GetTopDoctorsSuccessListener
    {
        fun getTopDoctorsSuccess(doctor: Doctor, rating: Rating)
    }

    interface GetTopDoctorsFailureListener
    {
        fun getTopDoctorsFailure(message: String)
    }

    //Update doctors
    interface UpdateDoctorProfileSuccessListener
    {
        fun updateDoctorProfileSuccess()
    }

    interface UpdateDoctorProfileFailureListener
    {
        fun updateDoctorProfileFailure()
    }

    //Delete doctor profile
    interface DeleteDoctorProfileSuccessListener
    {
        fun deleteDoctorProfileSuccess()
    }

    interface DeleteDoctorProfileFailureListener
    {
        fun deleteDoctorProfileFailure()
    }

    /***************interface setters************/

    //Find Doctor By Id
    fun setGetDoctorByIDSuccessListener(int: GetDoctorByIdSuccessListener)
    {
        this.mGetDoctorByIdSuccessListener = int
    }

    fun setGetDoctorByIDFailureListener(int:  GetDoctorByIdFailureListener)
    {
        this.mGetDoctorByIdFailureListener = int
    }

    //Find doctors
    fun setGetDoctorsSuccessListener(int: GetDoctorsSuccessListener)
    {
        this.mGetDoctorsSuccessListener = int
    }

    fun setGetDoctorsFailureListener(int: GetDoctorsFailureListener)
    {
        this.mGetDoctorsFailureListener = int
    }

    //Find top doctors in particular specialty
    fun setGetTopDoctorsSuccessListener(int: GetTopDoctorsSuccessListener)
    {
        this.mGetTopDoctorsSuccessListener = int
    }

    fun setGetTopDoctorsFailureListener(int: GetTopDoctorsFailureListener)
    {
        this.mGetTopDoctorsFailureListener = int
    }

    //Update doctor profile
    fun setUpdateDoctorProfileSuccessListener(int: UpdateDoctorProfileSuccessListener)
    {
        this.mUpdateProfileDoctorSuccessListener = int
    }

    fun setUpdateDoctorProfileFailureListener(int: UpdateDoctorProfileFailureListener)
    {
        this.mUpdateProfileDoctorFailureListener = int
    }

    //Delete doctor profile
    fun setDeleteDoctorProfileSuccessListener(int: DeleteDoctorProfileSuccessListener)
    {
        this.mDeleteProfileDoctorSuccessListener = int
    }

    fun setDeleteDoctorProfileFailureListener(int: DeleteDoctorProfileFailureListener)
    {
        this.mDeleteProfileDoctorFailureListener = int
    }
}