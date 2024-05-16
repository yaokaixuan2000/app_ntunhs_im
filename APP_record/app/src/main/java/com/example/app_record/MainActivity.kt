package com.example.app_record

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_record.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: RecordAdapter
    private val TAG = "MainActivity"
    private lateinit var records: Bprecords
    private val sharedPreferences by lazy { getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    private val gson by lazy { Gson() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarMain.inflateMenu(R.menu.menu)

        mAdapter = RecordAdapter(this, Bprecords(emptyList()))
        binding.recordRecycler.adapter = mAdapter
        binding.recordRecycler.layoutManager = LinearLayoutManager(this)

        records = Bprecords(mutableListOf(
            bprecord("2024-05-09 10:00", 120, 80, 72),
            bprecord("2024-05-09 14:00", 125, 82, 75),
            bprecord("2024-05-10 08:00", 118, 78, 70),
        ))

        val userJson = sharedPreferences.getString("user_data", null)
        if (userJson != null) {
            records = gson.fromJson(userJson, object : TypeToken<Bprecords>() {}.type)
            Log.d(TAG, "Loaded records from SharedPreferences: ${records.bprecords}")
        } else {
            Log.d(TAG, "No records found in SharedPreferences")
        }

        mAdapter.updateData(records)

        binding.toolbarMain.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_add -> {
                    val secondIntent = Intent(this, MainActivity2::class.java)
                    startActivityForResult(secondIntent, REQUEST_CODE_ADD_RECORD)
                    true
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add -> {
                Log.e(TAG, "Menu_add")
                Toast.makeText(this, "Menu Item Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_RECORD && resultCode == Activity.RESULT_OK) {
            val sys = data?.getIntExtra("sys", 0) ?: 0
            val dia = data?.getIntExtra("dia", 0) ?: 0
            val hr = data?.getIntExtra("hr", 0) ?: 0
            val newRecord = bprecord(getCurrentDateTime(), sys, dia, hr)
            if (records.bprecords == null){
                records = Bprecords(mutableListOf())
            }
            val mutableList = records.bprecords.toMutableList()
            mutableList.add(newRecord)
            records = Bprecords(mutableList)
            val userJson = gson.toJson(records)
            with(sharedPreferences.edit()) {
                putString("user_data", userJson)
                apply()
            }
            mAdapter.addRecord(newRecord)
        }
    }

    private fun getCurrentDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return current.format(formatter)
    }

    companion object {
        const val REQUEST_CODE_ADD_RECORD = 1
    }
}
