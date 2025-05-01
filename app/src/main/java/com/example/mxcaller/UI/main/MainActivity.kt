package com.example.mxcaller.UI.main

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mxcaller.databinding.ActivityMainBinding
import com.example.mxcaller.R
import com.example.mxcaller.utils.PermissionUtils
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = MainAdapter()
    private lateinit var pagerAdapter: MainPagerAdapter

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                viewModel.fetchCallLogs(this)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)

        pagerAdapter = MainPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Calls"
                1 -> "Contacts"
                2 -> "Messages"
                else -> ""
            }
        }.attach()



        viewModel = ViewModelProvider(this)[MainViewModel::class.java]



        if (PermissionUtils.hasCallLogPermission(this)) {
            viewModel.fetchCallLogs(this)
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_CALL_LOG)
        }

        viewModel.callLogs.observe(this) { logs ->
            adapter.submitList(logs ?: emptyList())
            Log.d("MainActivity", "Fetched call logs: ${logs.size}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }
}