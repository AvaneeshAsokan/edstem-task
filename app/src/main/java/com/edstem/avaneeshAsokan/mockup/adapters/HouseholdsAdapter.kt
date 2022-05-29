package com.edstem.avaneeshAsokan.mockup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edstem.mockup.R
import com.edstem.avaneeshAsokan.mockup.adapters.viewHolders.HouseholdVH
import com.edstem.avaneeshAsokan.mockup.data.HouseholdMembers
import com.edstem.mockup.databinding.HouseholdItemBinding
import com.edstem.avaneeshAsokan.mockup.utils.getDrawableRes

class HouseholdsAdapter(private val context: Context): RecyclerView.Adapter<HouseholdVH>() {

    private val membersList = arrayListOf<HouseholdMembers>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseholdVH {
        val view = HouseholdItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HouseholdVH(view)
    }

    override fun onBindViewHolder(holder: HouseholdVH, position: Int) {
        with(holder.binding){
            name.text = membersList[position].name
            relationship.text = membersList[position].relationship

            profilePic.setImageDrawable(
                when (position) {
                    1 -> context.getDrawableRes(R.drawable.hm2)
                    2 -> context.getDrawableRes(R.drawable.hm3)
                    else -> context.getDrawableRes(R.drawable.hm1)
                }
            )
        }
    }

    override fun getItemCount(): Int = membersList.size

    fun setData(newMembersList: List<HouseholdMembers>){
        membersList.clear()
        membersList.addAll(newMembersList)
        notifyDataSetChanged()  //  Fixme: should be changed to diff util implementation
    }
}