package com.example.letterapplication.letterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.letterapplication.R
import com.example.letterapplication.databinding.FragmentLetterListBinding
import com.example.letterapplication.letterlist.adaper.LettersListAdapter
import com.example.letterapplication.letterlist.adaper.OnItemClickListener
import com.example.letterapplication.model.LetterUIModel
import com.example.letterapplication.viewmodel.LetterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LetterListFragment : Fragment() {

    val letterViewModel: LetterViewModel by activityViewModels<LetterViewModel>()
    private lateinit var letterListBinding: FragmentLetterListBinding
    var observer: Observer<List<LetterUIModel>> = Observer {
        val adapter = LettersListAdapter(it, OnItemClickListener{title, description ->
            val action = LetterListFragmentDirections.actionLetterListFragmentToLetterDetailFragment(title, description)
            findNavController().navigate(action)
        })
        letterListBinding.letterRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        letterListBinding.letterRecyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        letterListBinding = FragmentLetterListBinding.inflate(inflater)
        letterViewModel.letters.observe(viewLifecycleOwner, observer)
        letterViewModel.updatedLetters.observe(viewLifecycleOwner, observer)
        return letterListBinding.root
    }
}