package com.example.letterapplication.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.letterapplication.R
import androidx.appcompat.app.AppCompatActivity

class PreferenceFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onDestroy() {
        (activity as AppCompatActivity?)?.supportActionBar?.show()
        super.onDestroy()
    }
}