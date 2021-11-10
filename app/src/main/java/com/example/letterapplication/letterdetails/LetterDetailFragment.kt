package com.example.letterapplication.letterdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.letterapplication.databinding.FragmentLetterDetailBinding

private const val TITLE = "param1"
private const val DESCRIPTION = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LetterDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LetterDetailFragment : Fragment() {

    private lateinit var binding: FragmentLetterDetailBinding
    val args: LetterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLetterDetailBinding.inflate(inflater)
        binding.titleTextView.text = args.title
        binding.descriptionTextView.text = args.description
        return binding.root
    }
}