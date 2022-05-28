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
import com.edstem.mockup.data.HouseholdMembers
import com.edstem.mockup.data.ReminderItems
import com.edstem.mockup.databinding.FragmentHomeBinding
import com.edstem.mockup.enums.ReminderType

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
        showHouseMembers()
        showEventReminders()
    }

    /*todo: show the other list when the wedding button is tapped*/
    private fun showEventReminders() {
        val reminders = listOf(
            ReminderItems("Rachel Thomas", "May 28 2022, Saturday", null, ReminderType.BIRTHDAY),
            ReminderItems("Aby Thomas", "May 31 2022, Tuesday", null, ReminderType.BIRTHDAY),
            ReminderItems("Gaby Thomas", "Jun 1 2022, Wednesday", null, ReminderType.BIRTHDAY),
            ReminderItems("Aby Thomas", "Jun 2 2022, Thursday", null, ReminderType.WEDDING),
        )

        reminderAdapter = EventReminderAdapter(requireContext())
        reminderAdapter.setData(reminders, ReminderType.BIRTHDAY)

        with(binding.birthdaysRv) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = reminderAdapter
        }
    }

    /**
     * function to get the members of the current user's household, and displays them
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