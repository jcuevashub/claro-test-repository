package com.example.clarotest.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.clarotest.R
import com.example.clarotest.databinding.ActivityMainBinding
import com.example.clarotest.ui.details.DetailsFragment
import com.example.clarotest.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        when(resources.getBoolean(R.bool.isTablet)) {
            true -> {
                val orientation = resources.configuration.orientation
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {

                } else {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout1, HomeFragment())
                        .commit()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout2, DetailsFragment())
                        .commit()
                }

            } false -> {
            val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = navHostFragment.navController

                val appBarConfiguration = AppBarConfiguration(navController.graph)
                binding.toolbar?.setupWithNavController(navController, appBarConfiguration)
            }
        }

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        binding.toolbar?.setupWithNavController(navController, appBarConfiguration)

    }
}