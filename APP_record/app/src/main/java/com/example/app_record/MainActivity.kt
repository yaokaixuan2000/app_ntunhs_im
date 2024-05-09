package com.example.app_record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View

import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_record.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: RecordAdapter
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarMain.inflateMenu(R.menu.menu)
        mAdapter = RecordAdapter(this, Bprecords(emptyList()))
        binding.recordRecycler.adapter = mAdapter
        binding.recordRecycler.layoutManager = LinearLayoutManager(this)
        val records = Bprecords(mutableListOf(
            bprecord("2024-05-09 10:00",120,80,72),
            bprecord("2024-05-09 14:00",125,82,75),
            bprecord("2024-05-10 08:00",118,78,70),
        ))
        mAdapter.updateData(records)

        val menu = binding.toolbarMain.menu
        val menuItem = menu.findItem(R.id.menu_add)
        menuItem.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_add -> {
                    val seconIntent = Intent(this,MainActivity2::class.java)
                    startActivity(seconIntent)
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

}