package com.codelab.basiclayouts.models

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class ConnViewModel(string1: String): ViewModel() {

//    var myd = context.getSharedPreferences("DataBase",Context.MODE_PRIVATE)

    //val code = string1
    val code = "darkfromdawn.com"
    //val url_source = "http://$code/rest/"
    val url_source = "http://$code/rest/"

    suspend fun readLoginRequest():String{
        val url = URL( url_source + "searchSql.php")
        var content: String
        var check: Int
        withContext(Dispatchers.IO){
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.connectTimeout = 1000
            conn.doInput = true
            check = conn.responseCode
            conn.inputStream.bufferedReader().use {
                content = it.readText()
            }
        }
        val result = if (check == 200) content else "Not working"
        return result
    }

    suspend fun checkid(string1: String, string2: String):String{
//        var myd = context.getSharedPreferences("DataBase",Context.MODE_PRIVATE)
        val url = URL(url_source + "checkidSql.php")
        var content: String
        val column1: String = string1
        val column2: String = string2
        var check: Int

        Log.d("InViewmodel", url.toString())

        withContext(Dispatchers.IO){
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            val postData = "uname=" + column1 + "&" + "status=" + column2

            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.connectTimeout = 1000
            conn.doInput = true
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
//            conn.setRequestProperty("Content-Length", postData.length.toString())

            conn.outputStream.bufferedWriter().use {
                it.write(postData)
            }

            check = conn.responseCode
            conn.inputStream.bufferedReader().use {
                content = it.readText()
            }

            Log.d("InViewmodel", content)
        }

        val result = if (check == 200) content else "Not working"
        return result
    }

    suspend fun deletid(string1: String):Int{
        val url = URL(url_source + "deletSql.php")
        val column1: String = string1
        var content: String
        var check: Int

        withContext(Dispatchers.IO){
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            val postData = "user_id=" + column1

            conn.requestMethod = "POST"
            conn.connectTimeout = 1000
            conn.doOutput = true
            conn.doInput = true
//            check = conn.responseCode
            conn.outputStream.bufferedWriter().use {
                it.write(postData)
            }
            check = conn.responseCode
            conn.inputStream.bufferedReader().use {
                content = it.readText()
            }
            Log.d("InViewmodel", content)
        }
//        Log.d("Mylog3", check.toString())
//        val result = if (check == 200) content else "Not working"
//        return check.toString()
        if (content.toInt() == 0)
            check = 0

        return check
    }

    suspend fun updateid(string1: String, string2: String, string3: String):String{
        val url = URL(url_source + "updateSql.php")
        val column1: String = string1
        val column2: String = string2
        val column3: String = string3
        var content: String
        var check: Int

        withContext(Dispatchers.IO){
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            val postData = "user_id=" + column1 + "&" + "uname=" + column2 + "&" + "content=" + column3

            conn.requestMethod = "POST"
            conn.connectTimeout = 1000
            conn.doOutput = true
            conn.doInput = true
            conn.outputStream.bufferedWriter().use {
                it.write(postData)
            }
            check = conn.responseCode
            conn.inputStream.bufferedReader().use {
                content = it.readText()
            }
            Log.d("InViewmodel", content)
        }
        if (content == "0")
            check = 0
        else
            check = 200
//        Log.d("Mylog3", check.toString())
//        val result = if (check == 200) content else "Not working"
        return check.toString()
    }

    suspend fun insertid(string1: String, string2: String, string3: String, string4: String):String{
        val url = URL(url_source + "insertSql.php")
        val column1: String = string1
        val column2: String = string2
        val column3: String = string3
        val column4: String = string4
        var content: String
        var check: Int

        withContext(Dispatchers.IO){
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            val postData = "uname=" + column1 + "&" + "status=" +
                    column2 + "&" + "time=" + column3 + "&" + "content=" + column4

            conn.requestMethod = "POST"
            conn.connectTimeout = 1000
            conn.doOutput = true
            conn.doInput = true
            conn.outputStream.bufferedWriter().use {
                it.write(postData)
            }
            check = conn.responseCode
            conn.inputStream.bufferedReader().use {
                content = it.readText()
            }
            Log.d("InViewmodel", content)
        }
        if (content == "ERROR: Could not able to execute")
            check = 0
        else
            check = 200
//        Log.d("Mylog3", check.toString())
//        val result = if (check == 200) content else "Not working"
        return check.toString()
    }
}

