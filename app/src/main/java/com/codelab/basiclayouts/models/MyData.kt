package com.codelab.basiclayouts.models

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.codelab.basiclayouts.R
import java.text.SimpleDateFormat
import java.util.*


class MyData(application: Application) : AndroidViewModel(application) {


    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    @SuppressLint("SimpleDateFormat")
    val dateTime = SimpleDateFormat("yy/M/d HH:mm:ss")
    val currentTime = dateTime.format(Date())

    val user_idLD: MutableLiveData<String> by lazy { MutableLiveData<String>(" ") }
    val user_nameLD: MutableLiveData<String> by lazy { MutableLiveData<String>(" ") }
    val user_statusLD: MutableLiveData<String> by lazy { MutableLiveData<String>(" ") }
    var user_timeLD: String = " "
    val user_contextLD: MutableLiveData<String> by lazy { MutableLiveData<String>(" ") }
    val resultsLD: MutableLiveData<String> by lazy { MutableLiveData<String>(" ") }
    val positionLD: MutableLiveData<String> by lazy { MutableLiveData<String>(" ") }



    fun setString(stringEdit1: String, stringEdit2: String){
        user_nameLD.value = stringEdit1
        user_statusLD.value = stringEdit2
    }

    fun setResult(stringEdit1: String){
        resultsLD.value = stringEdit1
    }

    fun setPosition(stringEdit1: TextFieldValue){
        positionLD.value = stringEdit1.toString()
    }

    fun getuserIDString(): MutableLiveData<String> { return user_idLD }
    fun getuserNMString(): MutableLiveData<String> { return user_nameLD }
    fun getuserSTString(): MutableLiveData<String> { return user_statusLD }
    fun getuserCTString(): MutableLiveData<String> { return user_contextLD }
    fun getresultsString(): MutableLiveData<String> { return resultsLD }
    fun getposition(): MutableLiveData<String> { return positionLD }
    fun getuserTMString(): String {
        var uers_timeLD = currentTime
        return currentTime
    }

    fun save(){
        val myd = context.getSharedPreferences("DataBase",Context.MODE_PRIVATE)

        val stringid: String ?= getuserIDString().value
        val stringnm: String ?= getuserNMString().value
        val stringst: String ?= getuserSTString().value
        val stringtm: String ?= getuserTMString()
        val stringct: String ?= getuserCTString().value
        val stringrs: String ?= getresultsString().value

        val user_id: String = "user_id"
        val user_name:String = context.resources.getString(R.string.user_name)
        val user_status:String = context.resources.getString(R.string.user_status)
        val user_time:String = context.resources.getString(R.string.user_time)
        val user_context:String = context.resources.getString(R.string.user_content)
        val user_results:String = "results"

        val editor = myd.edit()
        editor.putString(user_name, stringnm)
        editor.putString(user_status, stringst)
        editor.putString(user_time, stringtm)
        editor.putString(user_results, stringrs)

        editor.apply()
    }

    fun save_position(){
        val myd = context.getSharedPreferences("DataBase",Context.MODE_PRIVATE)
        val stringpt: String ?= getposition().value
        val editor = myd.edit()
        editor.putString("position", stringpt)
        editor.apply()
    }

    fun load(){
        val myd = context.getSharedPreferences("DataBase",Context.MODE_PRIVATE)
        val user_id:String = "user_id"
        val user_name:String = "name"
        val user_status:String = context.resources.getString(R.string.user_status)
        val user_time:String = context.resources.getString(R.string.user_time)
        val user_context:String = context.resources.getString(R.string.user_content)
        val user_results:String = "results"

        val s1: String?  = myd.getString(user_id, " ")
        val s2: String?  = myd.getString(user_name, " ")
        val s3: String? = myd.getString(user_status, " ")
        val s4: String? = myd.getString(user_time, " ")
        val s5: String? = myd.getString(user_context, " ")
        val s6: String? = myd.getString(user_results, " ")

        user_idLD.value = s1
        user_nameLD.value = s2
        user_statusLD.value = s3
        user_timeLD = s4.toString()
        user_contextLD.value = s5
        resultsLD.value = s6

    }

}