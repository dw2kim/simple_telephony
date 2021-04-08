package com.daewonkim.simple_telephony


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import java.lang.Exception


internal class MethodCallHandlerImpl(context: Context, activity: Activity?) : MethodCallHandler {
    private var context: Context?
    private var activity: Activity?
    private var mTelephonyManager: TelephonyManager? = null
    private lateinit var func: () -> Unit?

    private val E_NO_IS_VOICE_CAPABLE = "no_is_voice_capable"

    fun setActivity(act: Activity?) {
        this.activity = act
    }

    init {
        this.activity = activity
        this.context = context

        mTelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        Handler(Looper.getMainLooper()).post {
            when (call.method) {
                "isVoiceCapable" -> {
                    isVoiceCapable(result)
                }
                else -> {
                    result.notImplemented()
                }
            }
        }
    }

    private fun isVoiceCapable(result: MethodChannel.Result) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            result.success(   mTelephonyManager!!.isVoiceCapable)
        }  else {
            result.error(E_NO_IS_VOICE_CAPABLE, "error on isVoiceCapable method", "Not support below API level 22")
        }
    }


    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?) {
        when (requestCode) {
            0 -> return if (grantResults!![0] == PackageManager.PERMISSION_GRANTED) {
                this.func()!!

            } else {
                requestForSpecificPermission(0)

            }
            1 -> return if (grantResults!![0] == PackageManager.PERMISSION_GRANTED) {
                this.func()!!

            } else {
                requestForSpecificPermission(1)

            }
        }
    }

    private fun requestForSpecificPermission(i: Int) {
        try {
            ActivityCompat.requestPermissions(this.activity!!, arrayOf(Manifest.permission.ACCESS_WIFI_STATE), i)
        } catch (e: Exception) {
            print("error!!!!!!error!!!!!!error!!!!!!error!!!!!!error!!!!!!error!!!!!!")
        }
    }
}
