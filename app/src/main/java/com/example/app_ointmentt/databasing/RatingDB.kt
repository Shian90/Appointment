package com.example.app_ointmentt.databasing

import com.example.app_ointmentt.models.Rating
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RatingDB {

    /***Create interfaces***/
    //Get ratings by id interface
    lateinit var mGetRatingsByIdSuccessListener: GetRatingsByIdSuccessListener
    lateinit var mGetRatingsByIdFailureListener: GetRatingsByIdFailureListener


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


    /*** interfaces ***/
    interface GetRatingsByIdSuccessListener
    {
        fun getRatingsByIDSuccess(rating: Rating)
    }

    interface GetRatingsByIdFailureListener
    {
        fun getRatingsByIDFailure()
    }


    /***************interface setters************/
    fun setGetRatingsByIDSuccessListener(int: GetRatingsByIdSuccessListener)
    {
        this.mGetRatingsByIdSuccessListener = int
    }

    fun setGetRatingsByIDFailureListener(int: GetRatingsByIdFailureListener)
    {
        this.mGetRatingsByIdFailureListener = int
    }
}