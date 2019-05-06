package dev.bananaumai.practices.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

class BatteryReceiver : BroadcastReceiver() {
    private val tag = this::class.java.name

    private data class BatteryStatus(
        val charging: Boolean,
        val pluggedAC: Boolean,
        val pluggedUSB: Boolean
    )

    override fun onReceive(context: Context, intent: Intent) {
        val status: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val charging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                || status == BatteryManager.BATTERY_STATUS_FULL

        val chargePlug: Int = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
        val pluggedUSB: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
        val pluggedAC: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC

        val batteryStatus = BatteryStatus(charging, pluggedAC, pluggedUSB)

        Log.d(tag, "$batteryStatus")
    }
}