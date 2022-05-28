package com.edstem.mockup.data

import com.edstem.mockup.enums.ReminderType

data class ReminderItems(val name: String, val date: String, val imagePath: String?, val eventType: ReminderType)
