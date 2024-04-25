package com.example.jsonread

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL
import java.util.*
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(),CoroutineScope {
    private lateinit var appBarConfiguration:AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    class ActivityMainBinding {

    }

    val TAG = MainActivity::class.java.simpleName
    val job = Job() + Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch{
            val json = URL("https://api.jsonserve.com/KizxuV").readText()
            Log.w(TAG,json)
            val singers = Gson().fromJson(json, Singers::class.java)
            for( w in singers.singers){
                Log.d(TAG,"onCreate: ${w.name},${w.agency},${w.year_of_debut}")
            }
//            parseJson(json)
        }

    }


    private fun parseJson(json: String) {
        val jsonObject = JSONObject(json)
        val array = jsonObject.getJSONArray("singers")
        for (i in 0 until array.length() - 1) {
            val w = array.getJSONObject(i)
            val name = w.getString("name")
            val agency = w.getString("agency")
            Log.d(TAG, "onCreate:$name : $agency")
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job
}

