package com.example.app_ointmentt.databasing


import com.example.app_ointmentt.models.Doctor
import com.example.app_ointmentt.models.Rating

import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoctorDB {

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



    //Find Top Doctors InAllCategories
    lateinit var mGetTopDoctorsInAllCategoriesSuccessListener: GetTopDoctorsInAllCategoriesSuccessListener
    lateinit var mGetTopDoctorsInAllCategoriesFailureListener: GetTopDoctorsInAllCategoriesFailureListener


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

    /*******Find the Doctors in general with email and limit******/
    fun getDoctors(limit: Int, email: String)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("email", email)
        paramsJSON.put("limit", limit)
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

    /********Find the Doctors in general with limit********/
    fun getDoctors(limit: Int)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("limit", limit)
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

    /********Find the Doctors in general with email*******/
    fun getDoctors(email: String)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("email", email)
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

    /**********Find the Doctors in general*********/
    fun getDoctors()
    {
        val paramsJSON = JSONObject()
        paramsJSON.put(null, null)
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
    fun getTopDoctors(specialty: String, limit: Int)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("specialty", specialty)
        paramsJSON.put("limit", limit)

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

    /**********Find top Doctors in all Categories without limit********/
    fun getTopDoctorsInAllCategories()
    {
        val paramsJSON = JSONObject()
        paramsJSON.put(null, null)
        val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.getTopDoctorsInAllCategories(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val message = "Failed to retrieve from database"
                mGetTopDoctorsInAllCategoriesFailureListener.getTopDoctorsInAllCategoriesFailure(message)
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
                            mGetTopDoctorsInAllCategoriesSuccessListener.getTopDoctorsInAllCategoriesSuccess(doctor, rating)
                        }
                    }
                    else{
                        val message = "No doctor found"
                        mGetTopDoctorsInAllCategoriesFailureListener.getTopDoctorsInAllCategoriesFailure(message)
                    }
                }
                else{
                    val message = "Failed to retrieve response as success"
                    mGetTopDoctorsInAllCategoriesFailureListener.getTopDoctorsInAllCategoriesFailure(message)
                }

            }
        })
    }

    /*********Find top Doctors in all Categories with limit********/
    fun getTopDoctorsInAllCategories(limit: Int)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("limit", limit)
        val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.getTopDoctorsInAllCategories(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val message = "Failed to retrieve from database"
                mGetTopDoctorsInAllCategoriesFailureListener.getTopDoctorsInAllCategoriesFailure(message)
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
                            mGetTopDoctorsInAllCategoriesSuccessListener.getTopDoctorsInAllCategoriesSuccess(doctor, rating)
                        }
                    }
                    else{
                        val message = "No doctor found"
                        mGetTopDoctorsInAllCategoriesFailureListener.getTopDoctorsInAllCategoriesFailure(message)
                    }
                }
                else{
                    val message = "Failed to retrieve response as success"
                    mGetTopDoctorsInAllCategoriesFailureListener.getTopDoctorsInAllCategoriesFailure(message)
                }

            }
        })
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

    //Find Top Doctors in particular specialty
    interface GetTopDoctorsInAllCategoriesSuccessListener
    {
        fun getTopDoctorsInAllCategoriesSuccess(doctor: Doctor, rating: Rating)
    }

    interface GetTopDoctorsInAllCategoriesFailureListener
    {
        fun getTopDoctorsInAllCategoriesFailure(message: String)
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

    //Find top doctors in all categories
    fun setGetTopDoctorsInAllCategoriesSuccessListener(int: GetTopDoctorsInAllCategoriesSuccessListener)
    {
        this.mGetTopDoctorsInAllCategoriesSuccessListener = int
    }

    fun setGetTopDoctorsInAllCategoriesFailureListener(int: GetTopDoctorsInAllCategoriesFailureListener)
    {
        this.mGetTopDoctorsInAllCategoriesFailureListener = int
    }
}