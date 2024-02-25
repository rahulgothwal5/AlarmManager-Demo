package com.example.alarmmanager_demo

const val ONE_MIN: Long = 60 * 1000

data class AlarmItem(
    var intervalMillis: Long = ONE_MIN,
    var message: String = ""
)


fun AlarmItem.timeToString(): String {
    return (intervalMillis / ONE_MIN).toString()
}

fun AlarmItem.stringToTime(str: String) {
    intervalMillis = try {
        str.toInt() * ONE_MIN
    } catch (e: NumberFormatException) {
        // Handle the case when the string cannot be converted to a Long
        // For example, return a default value
        intervalMillis
    }

}
