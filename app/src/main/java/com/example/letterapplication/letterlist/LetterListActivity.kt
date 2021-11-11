package com.example.letterapplication.letterlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.letterapplication.R
import com.example.letterapplication.databinding.LetterListLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LetterListActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var binding: LetterListLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LetterListLayoutBinding.inflate(layoutInflater)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.floatingActionButton.visibility = View.VISIBLE
        binding.floatingActionButton.setOnClickListener {
            navController.navigate(R.id.action_letterListFragment_to_addLetterFragment)
            it.visibility = View.GONE
        }
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        NavigationUI.setupActionBarWithNavController(this, navController)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onSupportNavigateUp(): Boolean {
        binding.floatingActionButton.visibility = View.VISIBLE
        return navController.navigateUp()
    }
}