package com.edstem.mockup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edstem.mockup.adapters.EventReminderAdapter
import com.edstem.mockup.adapters.HouseholdsAdapter
import com.edstem.mockup.adapters.layoutManagers.FadeInLayoutManager
import com.edstem.mockup.data.HouseholdMembers
import com.edstem.mockup.data.ReminderItems
import com.edstem.mockup.databinding.FragmentHomeBinding
import com.edstem.mockup.enums.ReminderType
import java.text.SimpleDateFormat
import java.util.*

/**
 * The my profile page
 */
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var familyAdapter: HouseholdsAdapter
    private lateinit var reminderAdapter: EventReminderAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        bindListeners()
        showHouseMembers()
        showEventReminders()
    }

    private fun bindListeners() {
        with(binding) {

            birthdayBtn.setOnClickListener {
                birthdayBtn.alpha               = 1F
                anniversaryBtn.alpha            = 0.5F
                birthdayBtnAccent.visibility    = View.VISIBLE
                anniversaryBtnAccent.visibility = View.GONE
                reminderAdapter.setShowType(ReminderType.BIRTHDAY)
            }

            anniversaryBtn.setOnClickListener {
                anniversaryBtn.alpha            = 1F
                birthdayBtn.alpha               = 0.5F
                birthdayBtnAccent.visibility    = View.GONE
                anniversaryBtnAccent.visibility = View.VISIBLE
                reminderAdapter.setShowType(ReminderType.WEDDING)
            }
        }
    }

    /**
     * Populates the [EventReminderAdapter] with dummy data where the 2nd element will always be
     * the current date so that the birthday icon will be shown.
     */
    private fun showEventReminders() {
        val today = Calendar.getInstance().time

        val reminders = listOf(
            ReminderItems("Rachel Thomas", "May 28 2022, Saturday", null, ReminderType.BIRTHDAY),
            ReminderItems("Aby Thomas", SimpleDateFormat("MMM dd yyyy, EEEE", Locale.getDefault()).format(today), null, ReminderType.BIRTHDAY),
            ReminderItems("Gaby Thomas", "Jun 1 2022, Wednesday", null, ReminderType.BIRTHDAY),
            ReminderItems("Aby Thomas", "Jun 2 2022, Thursday", null, ReminderType.WEDDING),
        )

        reminderAdapter = EventReminderAdapter(requireContext())
        reminderAdapter.setData(reminders, ReminderType.BIRTHDAY)

        with(binding.birthdaysRv) {
            layoutManager = FadeInLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = reminderAdapter
        }
    }

    /**
     * Populates the [HouseholdsAdapter] with mock members of the current user's household.
     */
    private fun showHouseMembers(){
        val membersList = listOf(
            HouseholdMembers("Rachel Thomas", "Wife", null),
            HouseholdMembers("Aby Thomas", "Sister", null),
            HouseholdMembers("Gaby Thomas", "Brother", null)
        )

        familyAdapter = HouseholdsAdapter(requireContext())
        familyAdapter.setData(membersList)

        with(binding.householdsRv) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = familyAdapter
        }
    }
}