package com.edstem.mockup.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edstem.mockup.R
import com.edstem.mockup.adapters.viewHolders.ReminderVH
import com.edstem.mockup.data.ReminderItems
import com.edstem.mockup.databinding.BirthdayItemBinding
import com.edstem.mockup.enums.ReminderType
import com.edstem.mockup.getDrawableRes
import java.text.SimpleDateFormat
import java.util.*

class EventReminderAdapter(private val context: Context): RecyclerView.Adapter<ReminderVH>() {
    private val TAG = EventReminderAdapter::class.java.canonicalName

    private val filteredList = arrayListOf<ReminderItems>()
    private val reminderList = arrayListOf<ReminderItems>()

    private var showType = ReminderType.BIRTHDAY

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderVH {
        val view = BirthdayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReminderVH(view)
    }

    override fun onBindViewHolder(holder: ReminderVH, position: Int) {
        with(holder.binding){
            name.text = filteredList[position].name
            birthdayIcon.visibility =
                if (isEventToday(filteredList[position].date)) View.VISIBLE else View.GONE
            dateOfBirth.text = filteredList[position].date

            profilePic.setImageDrawable(when(position) {
                1 -> context.getDrawableRes(R.drawable.hm2)
                2 -> context.getDrawableRes(R.drawable.hm3)
                else -> context.getDrawableRes(R.drawable.hm1)
            })
        }
    }

    override fun getItemCount(): Int = filteredList.size

    fun setData(newMembersList: List<ReminderItems>, reminderType: ReminderType){
        reminderList.clear()
        reminderList.addAll(newMembersList)
        filteredList.clear()
        filteredList.addAll(newMembersList.filter { item -> item.eventType == reminderType || reminderType == ReminderType.ALL })
        notifyDataSetChanged()  //  Fixme: should be changed to diff util implementation
    }

    fun setShowType(newType: ReminderType){
        showType = newType
        filteredList.clear()
        filteredList.addAll(reminderList.filter { item -> item.eventType == showType || showType == ReminderType.ALL })
        notifyDataSetChanged()
    }

    private fun isEventToday(date: String): Boolean {
        //  May 30 2022, Monday
        val df = SimpleDateFormat("MMM dd yyyy, EEEE")
        val today = Calendar.getInstance().apply{
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        Log.d(TAG, "isEventToday: today = ${today.time}, other time = ${df.parse(date)}")

        return today.time == df.parse(date)
    }
}