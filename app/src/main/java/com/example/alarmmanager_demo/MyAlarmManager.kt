package com.example.alarmmanager_demo

interface MyAlarmManager {
    fun scheduleRepeatingAlarm(alarmItem: AlarmItem)
    fun cancelRepeatingAlarm(alarmItem: AlarmItem)
}