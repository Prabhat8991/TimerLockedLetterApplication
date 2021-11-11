package com.example.letterapplication.letterlist

import android.app.UiModeManager
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.example.letterapplication.R
import com.example.letterapplication.databinding.LetterListLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.prefs.PreferenceChangeEvent
import java.util.prefs.PreferenceChangeListener

@AndroidEntryPoint
class LetterListActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

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
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.settings_menu -> {
                navController.navigate(R.id.action_letterListFragment_to_preferenceFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        binding.floatingActionButton.visibility = View.VISIBLE
        return navController.navigateUp()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when(key) {
            "darkmode" -> {
                val isDarkModeEnabled = sharedPreferences?.getBoolean("darkmode", false)?: false
                if(isDarkModeEnabled) {
                    (getSystemService(UI_MODE_SERVICE) as UiModeManager).nightMode = UiModeManager.MODE_NIGHT_YES
                } else {
                    (getSystemService(UI_MODE_SERVICE) as UiModeManager).nightMode = UiModeManager.MODE_NIGHT_NO
                }
            }
        }
    }
}