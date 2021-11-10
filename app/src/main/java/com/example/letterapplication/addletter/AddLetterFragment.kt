package com.example.letterapplication.addletter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.letterapplication.R
import com.example.letterapplication.addletter.TimePickerFragment.Companion.HOUR_OF_DAY
import com.example.letterapplication.addletter.TimePickerFragment.Companion.MINUTE
import com.example.letterapplication.database.DatabaseLetterModel
import com.example.letterapplication.databinding.FragmentAddLetterBinding
import com.example.letterapplication.viewmodel.LetterViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddLetterFragment : Fragment(), FragmentResultListener {

    val letterViewModel: LetterViewModel by activityViewModels<LetterViewModel>()
    private lateinit var binding: FragmentAddLetterBinding
    private var calendar: Calendar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddLetterBinding.inflate(inflater)
        binding.button.setOnClickListener {
            showTimePickerDialog()
        }
        letterViewModel.nextId.observe(viewLifecycleOwner, {
            calendar?.let {cal ->
                setAlarm(cal, it)
            }
        })

        return binding.root
    }

    private fun showTimePickerDialog() {
        parentFragmentManager.setFragmentResultListener("timer", this, this)
        TimePickerFragment().show(parentFragmentManager, "timePicker")
    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        val hourOfDay = result.getInt(HOUR_OF_DAY)
        val minute = result.getInt(MINUTE)
        val calNow = Calendar.getInstance()
        val calSet = calNow.clone() as Calendar
        calSet.apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

        }
        calendar = calSet
        letterViewModel.saveLetters(DatabaseLetterModel(isLocked = true, title = binding.letterTitleEditText.text.toString(), description = binding.letterDescriptionEditText.text.toString(), timeStamp = calendar?.timeInMillis?:0))
    }

    private fun setAlarm(calendar: Calendar, nextId: Long) {
        val intent = Intent(requireActivity(), AlarmReceiver::class.java).apply {
            putExtra(NEXT_ID, nextId)
        }
        val pendingIntent = PendingIntent.getBroadcast(requireActivity(), nextId.toInt(), intent, 0)
        val alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        findNavController().navigate(R.id.action_addLetterFragment_to_letterListFragment)
    }

    companion object {
        const val NEXT_ID = "nextId"
    }
}
