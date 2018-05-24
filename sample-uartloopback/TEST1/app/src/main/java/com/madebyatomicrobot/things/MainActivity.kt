package com.madebyatomicrobot.things

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.google.android.things.pio.PeripheralManagerService
import com.google.android.things.pio.UartDevice
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : Activity(), OnSeekBarChangeListener  {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    lateinit var redLabelView : TextView
    lateinit var greenLabelView : TextView
    lateinit var blueLabelView : TextView

    lateinit var redBarView : SeekBar
    lateinit var greenBarView : SeekBar
    lateinit var blueBarView : SeekBar

    lateinit var device : UartDevice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        redLabelView = findViewById(R.id.red_label) as TextView
        greenLabelView = findViewById(R.id.green_label) as TextView
        blueLabelView = findViewById(R.id.blue_label) as TextView

        redBarView = findViewById(R.id.red) as SeekBar
        greenBarView = findViewById(R.id.green) as SeekBar
        blueBarView = findViewById(R.id.blue) as SeekBar

        redBarView.max = 255
        greenBarView.max = 255
        blueBarView.max = 255

        redBarView.setOnSeekBarChangeListener(this)
        greenBarView.setOnSeekBarChangeListener(this)
        blueBarView.setOnSeekBarChangeListener(this)

        val manager = PeripheralManagerService()
        try {
            Log.i(TAG, "UART: " + manager.uartDeviceList)

            device = manager.openUartDevice("UART0")

            device.setBaudrate(9600)
            device.setDataSize(8)
            device.setParity(UartDevice.PARITY_NONE)
            device.setStopBits(1)

            showColor(0, 0, 0)
        } catch (ex: IOException) {
            Log.w(TAG, "Error in onCreate", ex)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            device.close()
        } catch (ex: IOException) {
            Log.w(TAG, "Error in onDestroy", ex)
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val red = redBarView.progress
        val green = greenBarView.progress
        val blue = blueBarView.progress

        showColor(red, green, blue)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        // Don't care
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        // Don't care
    }

    fun showColor(red: Int, green: Int, blue: Int) {
        redLabelView.setText("Red ($red)")
        greenLabelView.setText("Green ($green)")
        blueLabelView.setText("Blue ($blue)")

        val serialMessage = "$red $green $blue\n"
        val bytes = serialMessage.toByteArray(Charset.forName("ASCII"))
        device.write(bytes, bytes.size)
    }
}