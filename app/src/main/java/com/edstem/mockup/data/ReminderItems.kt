package com.edstem.mockup.data

import com.edstem.mockup.enums.ReminderType

/**
 * Mock data holder for reminder items.
 *
 * @property name name of the person
 * @property date date of the event in "MMM dd yyyy, EEEE" format
 * @property imagePath path to profile image (unused now)
 * @property eventType the type of event that this entry represents
 */
data class ReminderItems(val name: String, val date: String, val imagePath: String?, val eventType: ReminderType)
