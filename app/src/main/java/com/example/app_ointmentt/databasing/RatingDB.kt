package com.example.app_ointmentt.databasing

import android.content.Context
import android.preference.PreferenceManager
import com.example.app_ointmentt.models.Rating
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RatingDB(val context: Context) {

    /***Create interfaces***/
    //Get ratings by id interface
    lateinit var mGetRatingsByIdSuccessListener: GetRatingsByIdSuccessListener
    lateinit var mGetRatingsByIdFailureListener: GetRatingsByIdFailureListener

    lateinit var mUpdateRatingByIdSuccessListener: UpdateRatingByIdSuccessListener
    lateinit var mUpdateRatingByIdFailureListener: UpdateRatingByIdFailureListener


    /***Calling API through functions***/
    fun getRatingsById(id: String)
    {
        val paramsJSON = JSONObject()
        paramsJSON.put("doctorId", id)
        val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

        val call = APIObject.api.getRatingsById(params)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                mGetRatingsByIdFailureListener.getRatingsByIDFailure()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if ( response.isSuccessful )
                {
                    val jsonRes = JSONObject(response.body()!!.string())
                    val rating = Rating().fromJSON(jsonRes.getJSONArray("docRating").getJSONObject(0))

                    mGetRatingsByIdSuccessListener.getRatingsByIDSuccess(rating)
                }
                else
                    mGetRatingsByIdFailureListener.getRatingsByIDFailure()
            }
        })
    }

    fun updateRatingById(updOpts: Map<String, String>)
    {
        val sh = PreferenceManager.getDefaultSharedPreferences(context)
        val jwt = sh.getString("jwt", "NONE FOUND").toString()
        val uid = sh.getString("uid", "NONE FOUND").toString()

        if ( jwt == "NONE FOUND" || uid == "NONE FOUND" )
        {
            //don't go any further
            mUpdateRatingByIdFailureListener.updateRatingByIdFailure()
        }
        else
        {
            val paramsJSON = JSONObject()
            paramsJSON.put("doctorId", updOpts["doctorId"].toString())
            paramsJSON.put("rating",updOpts["rating"].toString())


            val params = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), paramsJSON.toString())

            val headerJwt = "Bearer $jwt"
            val call = APIObject.api.editRatingsById(headerJwt, params)

            call.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                    mUpdateRatingByIdFailureListener.updateRatingByIdFailure()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {

                        mUpdateRatingByIdSuccessListener.updateRatingByIdSuccess()
                    } else {

                        mUpdateRatingByIdFailureListener.updateRatingByIdFailure()
                    }
                }
            })
        }
    }


    /*** interfaces ***/
    interface GetRatingsByIdSuccessListener
    {
        fun getRatingsByIDSuccess(rating: Rating)
    }

    interface GetRatingsByIdFailureListener
    {
        fun getRatingsByIDFailure()
    }

    interface UpdateRatingByIdSuccessListener
    {
        fun updateRatingByIdSuccess()
    }

    interface UpdateRatingByIdFailureListener
    {
        fun updateRatingByIdFailure()
    }


    /***interface setters***/
    fun setGetRatingsByIDSuccessListener(int: GetRatingsByIdSuccessListener)
    {
        this.mGetRatingsByIdSuccessListener = int
    }

    fun setGetRatingsByIDFailureListener(int: GetRatingsByIdFailureListener)
    {
        this.mGetRatingsByIdFailureListener = int
    }

    fun setUpdateRatingByIdSuccessListener(anInt: UpdateRatingByIdSuccessListener)
    {
        this.mUpdateRatingByIdSuccessListener = anInt
    }

    fun setUpdateRatingByIdFailureListener(anInt: UpdateRatingByIdFailureListener)
    {
        this.mUpdateRatingByIdFailureListener = anInt
    }
}