package com.example.app_ointmentt.databasing

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.preference.PreferenceManager
import com.example.app_ointmentt.models.Doctor
import com.example.app_ointmentt.models.Slot
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SlotDB(val context: Context) {

    private val sharedPrefFile = "appointmentSharedPref"

    /***Create interfaces***/

    //Create Slot interfaces
    lateinit var mCreateSlotSuccessListener: createSlotSuccessListener
    lateinit var mCreateSlotFailureListener: createSlotFailureListener

    //Get Slot By Id interfaces
    lateinit var mGetSlotByIDSuccessListener: getSlotByIdSuccessListener
    lateinit var mGetSlotByIDFailureListener: getSlotByIdFailureListener

    //View all slots By Doctor Id interfaces
    lateinit var mViewAllSlotsByDoctorSuccessListener: viewAllSlotsByDoctorSuccessListener
    lateinit var mViewAllSlotsByDoctorFailureListener: viewAllSlotsByDoctorFailureListener

    //Delete Doctor Slots interfaces
    lateinit var mDeleteDoctorSlotsFailureListener: deleteDoctorSlotsFailureListener
    lateinit var mDeleteDoctorSlotsSuccessListener: deleteDoctorSlotsSuccessListener

    //Delete Slot By Id interfaces
    lateinit var mDeleteSlotByIdFailureListener: deleteSlotByIdFailureListener
    lateinit var mDeleteSlotByIdSuccessListener: deleteSlotByIdSuccessListener


    /***Calling API through functions***/
    fun createSlot(date: String, startTime: String, endTime: String, numSlots: Int, status: Int)
    {
        val slotArray = arrayListOf<Slot>()

        val sh: SharedPreferences = context.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        val jwt = sh.getString("jwt", "NONE FOUND").toString()
        val uid = sh.getString("uid", "NONE FOUND").toString()
        if ( jwt == "NONE FOUND" || uid == "NONE FOUND" )
        {
           val message = "Please login before creating slots."
            mCreateSlotFailureListener.createSlotFailure(message)
        }
        else
        {
            val paramsJSON = JSONObject()
            paramsJSON.put("doctorId", uid)
            paramsJSON.put("dateOfSlot", date)
            paramsJSON.put("startTime", startTime)
            paramsJSON.put("endTime", endTime)
            paramsJSON.put("status", status.toString())
            paramsJSON.put("numSlots", numSlots.toString())

            val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

            val headerJwt = "Bearer $jwt"

            val call = APIObject.api.createSlot(headerJwt, params)

            call.enqueue(object: Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    val jsonRes = JSONObject(response.body()!!.string())
                    val slots = jsonRes.getJSONArray("slots")
                    Log.d("DEBUGSLOTS", "$slots")
                    for ( i in 0 until slots.length() )
                    {
                        val slot = Slot().fromJSON(slots.getJSONObject(i))
                        slotArray.add(slot)
                    }
                    mCreateSlotSuccessListener.createSlotSuccess(slotArray)
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    mCreateSlotFailureListener.createSlotFailure(t.message)
                }
            })
        }
    }

    fun getSlotById(slotId: String)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("slotId", slotId)

        Log.d("ParamsJSON", "$paramsJSON")
        val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())
        val call = APIObject.api.getSlotById(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                mGetSlotByIDFailureListener.getSlotByIdFailureListener(t.message)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
                    val jsonRes = JSONObject(response.body()!!.string())
                    val slotJSONObject = jsonRes.getJSONObject("slotRet")
                    val slot = Slot().fromJSON(slotJSONObject)
                    mGetSlotByIDSuccessListener.getSlotByIdSuccessListener(slot)
                }
                else
                {
                    mGetSlotByIDFailureListener.getSlotByIdFailureListener(response.message())
                }
            }
        })

    }

    fun viewAllUnbookedSlotsByDoctor(doctorId: String)
    {
        val slotsArray = arrayListOf<Slot>()
        val paramsJSON = JSONObject()
        paramsJSON.put("doctorId", doctorId)

        val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())
        val call = APIObject.api.viewAllUnbookedSlotsByDoctor(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                mViewAllSlotsByDoctorFailureListener.viewAllSlotsByDoctorFailureListener(t.message)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
                    val jsonRes = JSONObject(response.body()!!.string())
                    val slots = jsonRes.getJSONArray("slots")

                    if(slots.length() != 0){
                        for(i in 0 until slots.length()){
                            val slotObj = slots.getJSONObject(i)
                            val slot = Slot().fromJSON(slotObj)
                            slotsArray.add(slot)
                        }
                        mViewAllSlotsByDoctorSuccessListener.viewAllSlotsByDoctorSuccessListener(slotsArray)
                    }
                    else{
                        mViewAllSlotsByDoctorFailureListener.viewAllSlotsByDoctorFailureListener(response.message())
                    }

                }
                else
                {
                    mViewAllSlotsByDoctorFailureListener.viewAllSlotsByDoctorFailureListener(response.message())
                }
            }
        })
    }

    fun viewAllSlotsByDoctor(doctorId: String)
    {
        val slotsArray = arrayListOf<Slot>()
        val paramsJSON = JSONObject()
        paramsJSON.put("doctorId", doctorId)

        val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())
        val call = APIObject.api.viewAllSlotsByDoctor(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                mViewAllSlotsByDoctorFailureListener.viewAllSlotsByDoctorFailureListener(t.message)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
                    val jsonRes = JSONObject(response.body()!!.string())
                    val slots = jsonRes.getJSONArray("slots")

                    if(slots.length() != 0){
                        for(i in 0 until slots.length()){
                            val slotObj = slots.getJSONObject(i)
                            val slot = Slot().fromJSON(slotObj)
                            slotsArray.add(slot)
                        }
                        mViewAllSlotsByDoctorSuccessListener.viewAllSlotsByDoctorSuccessListener(slotsArray)
                    }
                    else{
                        mViewAllSlotsByDoctorFailureListener.viewAllSlotsByDoctorFailureListener(response.message())
                    }

                }
                else
                {
                    mViewAllSlotsByDoctorFailureListener.viewAllSlotsByDoctorFailureListener(response.message())
                }
            }
        })
    }

    fun deleteSlotById(slotId: String)
    {
        val sh: SharedPreferences = context.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        val jwt = sh.getString("jwt", "NONE FOUND").toString()
        val uid = sh.getString("uid", "NONE FOUND").toString()
        if ( jwt == "NONE FOUND" || uid == "NONE FOUND" )
        {
            val message = "Please login before deleting a slot."
            mDeleteSlotByIdFailureListener.deleteSlotByIdFailure(message)
        }
        else {

            val paramsJSON = JSONObject()
            paramsJSON.put("slotId", slotId)
            val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

            val headerJwt = "Bearer $jwt"
            val call = APIObject.api.deleteSlotById(headerJwt, params)
            call.enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    mDeleteSlotByIdFailureListener.deleteSlotByIdFailure(t.message)
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if ( response.isSuccessful )
                    {
                        Log.d("Response", "$response ")
                        mDeleteSlotByIdSuccessListener.deleteSlotByIdSuccess()
                    }
                    else
                    {
                        mDeleteSlotByIdFailureListener.deleteSlotByIdFailure(response.message())
                    }
                }
            })
        }
    }

    fun deleteSlotsByDoctorId()
    {
        val sh: SharedPreferences = context.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        val jwt = sh.getString("jwt", "NONE FOUND").toString()
        val uid = sh.getString("uid", "NONE FOUND").toString()
        if ( jwt == "NONE FOUND" || uid == "NONE FOUND" )
        {
           val message = "Please login before deleting the slots."
            mDeleteDoctorSlotsFailureListener.deleteDoctorSlotsFailure(message)
        }
        else
        {
            val paramsJSON = JSONObject()
            paramsJSON.put("doctorId", uid)
            val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

            val headerJwt = "Bearer $jwt"

            val call = APIObject.api.deleteSlotByDoctorId(headerJwt, params)

            call.enqueue(object: Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    mDeleteDoctorSlotsSuccessListener.deleteDoctorSlotsSuccess()
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    mDeleteDoctorSlotsFailureListener.deleteDoctorSlotsFailure(t.message)
                }
            })
        }
    }

    /***Interfaces***/
    interface createSlotSuccessListener
    {
        fun createSlotSuccess(slotArray: ArrayList<Slot>)
    }

    interface createSlotFailureListener
    {
        fun createSlotFailure(message: String?)
    }

    interface getSlotByIdSuccessListener
    {
        fun getSlotByIdSuccessListener(slot: Slot)
    }

    interface getSlotByIdFailureListener
    {
        fun getSlotByIdFailureListener(message: String?)
    }

    interface viewAllSlotsByDoctorSuccessListener
    {
        fun viewAllSlotsByDoctorSuccessListener(slotsArray: ArrayList<Slot>)
    }

    interface viewAllSlotsByDoctorFailureListener
    {
        fun viewAllSlotsByDoctorFailureListener(message: String?)
    }

    interface deleteDoctorSlotsSuccessListener
    {
        fun deleteDoctorSlotsSuccess()
    }

    interface deleteDoctorSlotsFailureListener
    {
        fun deleteDoctorSlotsFailure(message: String?)
    }

    interface deleteSlotByIdSuccessListener
    {
        fun deleteSlotByIdSuccess()
    }

    interface deleteSlotByIdFailureListener
    {
        fun deleteSlotByIdFailure(message: String?)
    }

    /***Interface setters***/
    fun setCreateSlotSuccessListener(int: createSlotSuccessListener)
    {
        this.mCreateSlotSuccessListener = int
    }

    fun setCreateSlotFailureListener(int: createSlotFailureListener)
    {
        this.mCreateSlotFailureListener = int
    }

    fun setGetSlotByIdSuccessListener(int: getSlotByIdSuccessListener)
    {
        this.mGetSlotByIDSuccessListener = int
    }

    fun setGetSlotByIdFailureListener(int: getSlotByIdFailureListener)
    {
        this.mGetSlotByIDFailureListener = int
    }

    fun setViewAllSlotsByDoctorSuccessListener(int: viewAllSlotsByDoctorSuccessListener)
    {
        this.mViewAllSlotsByDoctorSuccessListener = int
    }

    fun setViewAllSlotsByDoctorFailureListener(int: viewAllSlotsByDoctorFailureListener)
    {
        this.mViewAllSlotsByDoctorFailureListener = int
    }

    fun setDeleteDoctorSlotsSuccessListener(int: deleteDoctorSlotsSuccessListener)
    {
        this.mDeleteDoctorSlotsSuccessListener = int
    }

    fun setDeleteDoctorSlotsFailureListener(int: deleteDoctorSlotsFailureListener)
    {
        this.mDeleteDoctorSlotsFailureListener = int
    }

    fun setDeleteSlotByIdSuccessListener(int: deleteSlotByIdSuccessListener)
    {
        this.mDeleteSlotByIdSuccessListener = int
    }

    fun setDeleteSlotByIdFailureListener(int: deleteSlotByIdFailureListener)
    {
        this.mDeleteSlotByIdFailureListener = int
    }
}