package com.example.app_ointmentt.databasing

import android.content.Context
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

    //Delete Slot By Id interfaces
    lateinit var mDeleteSlotByIdFailureListener: deleteSlotByIdFailureListener
    lateinit var mDeleteSlotByIdSuccessListener: deleteSlotByIdSuccessListener

    /***Calling API through functions***/
    fun createSlot(date: String, startTime: String, endTime: String, numSlots: Int, status: Int)
    {
        val slotArray = arrayListOf<Slot>()

        val sh = PreferenceManager.getDefaultSharedPreferences(context)
        val jwt = sh.getString("jwt", "NONE FOUND").toString()
        val uid = sh.getString("uid", "NONE FOUND").toString()
        if ( jwt == "NONE FOUND" || uid == "NONE FOUND" )
        {
            //don't go any further
            Log.d("CREATESLOTS", "$jwt $uid")
            mCreateSlotFailureListener.createSlotFailure()
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
                    mCreateSlotFailureListener.createSlotFailure()
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
                mGetSlotByIDFailureListener.getSlotByIdFailureListener()
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
                    mGetSlotByIDFailureListener.getSlotByIdFailureListener()
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
                mViewAllSlotsByDoctorFailureListener.viewAllSlotsByDoctorFailureListener()
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
                        mViewAllSlotsByDoctorFailureListener.viewAllSlotsByDoctorFailureListener()
                    }

                }
                else
                {
                    mViewAllSlotsByDoctorFailureListener.viewAllSlotsByDoctorFailureListener()
                }
            }
        })
    }

    fun deleteSlotById(slotId: String)
    {
        val sh = PreferenceManager.getDefaultSharedPreferences(context)
        val jwt = sh.getString("jwt", "NONE FOUND").toString()
        val uid = sh.getString("uid", "NONE FOUND").toString()
        if ( jwt == "NONE FOUND" || uid == "NONE FOUND" )
        {
            //don't go any further
            Log.d("DELETEAPI", "$jwt ")
            mDeleteSlotByIdFailureListener.deleteSlotByIdFailure()
        }
        else {
            Log.d("DELETEAPI", "$jwt ")
            val paramsJSON = JSONObject()
            paramsJSON.put("slotId", slotId)
            val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

            val headerJwt = "Bearer $jwt"
            val call = APIObject.api.deleteSlotById(headerJwt, params)
            call.enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    mDeleteSlotByIdFailureListener.deleteSlotByIdFailure()
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if ( response.isSuccessful )
                    {
                        Log.d("Response", "$response ")
                        mDeleteSlotByIdSuccessListener.deleteSlotByIdSuccess()
                    }
                    else
                    {
                        mDeleteSlotByIdFailureListener.deleteSlotByIdFailure()
                    }
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
        fun createSlotFailure()
    }

    interface getSlotByIdSuccessListener
    {
        fun getSlotByIdSuccessListener(slot: Slot)
    }

    interface getSlotByIdFailureListener
    {
        fun getSlotByIdFailureListener()
    }

    interface viewAllSlotsByDoctorSuccessListener
    {
        fun viewAllSlotsByDoctorSuccessListener(slotsArray: ArrayList<Slot>)
    }

    interface viewAllSlotsByDoctorFailureListener
    {
        fun viewAllSlotsByDoctorFailureListener()
    }

    interface deleteSlotByIdSuccessListener
    {
        fun deleteSlotByIdSuccess()
    }

    interface deleteSlotByIdFailureListener
    {
        fun deleteSlotByIdFailure()
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

    fun setDeleteSlotByIdSuccessListener(int: deleteSlotByIdSuccessListener)
    {
        this.mDeleteSlotByIdSuccessListener = int
    }

    fun setDeleteSlotByIdFailureListener(int: deleteSlotByIdFailureListener)
    {
        this.mDeleteSlotByIdFailureListener = int
    }

}