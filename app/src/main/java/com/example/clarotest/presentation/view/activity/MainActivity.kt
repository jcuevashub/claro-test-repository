package com.example.clarotest.presentation.view.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.clarotest.R
import com.example.clarotest.databinding.ActivityMainBinding
import com.example.clarotest.presentation.view.fragment.details.DetailsFragment
import com.example.clarotest.presentation.view.fragment.home.HomeFragment
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
                    navigationController(binding)
                } else {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout1, HomeFragment())
                        .commit()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout2, DetailsFragment())
                        .commit()
                }

            } false -> {
                navigationController(binding)
            }
        }

    }

    private fun navigationController(binding: ActivityMainBinding) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar?.setupWithNavController(navController, appBarConfiguration)
    }
}