package com.example.letterapplication.addletter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.letterapplication.databinding.FragmentAddLetterBinding
import com.example.letterapplication.viewmodel.LetterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddLetterFragment : Fragment() {

    private val letterViewModel: LetterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentAddLetterBinding.inflate(inflater)
        binding.button.setOnClickListener {
            showTimePickerDialog()
        }
        return binding.root
    }

    private fun showTimePickerDialog() {
        TimePickerFragment().show(parentFragmentManager, "timePicker")
    }
}