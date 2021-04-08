package com.daewonkim.simple_telephony

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry


/** SimpleTelephonyPlugin */
class SimpleTelephonyPlugin: FlutterPlugin,
        PluginRegistry.RequestPermissionsResultListener, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity

  private var channel: MethodChannel? = null
  private val channelID = "com.daewonkim/simple_telephony"
  private var handler: MethodCallHandlerImpl? = null

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    setupChannel(flutterPluginBinding)
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    teardownChannel()
  }

  private fun setupChannel(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, channelID)
    handler = MethodCallHandlerImpl(flutterPluginBinding.applicationContext, null)
    channel?.setMethodCallHandler(handler)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    handler?.setActivity(binding.activity)
  }

  override fun onDetachedFromActivityForConfigChanges() {
    handler?.setActivity(null)
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    onAttachedToActivity(binding)
    binding.addRequestPermissionsResultListener(this)
  }

  override fun onDetachedFromActivity() {
    onDetachedFromActivity()
  }

  private fun teardownChannel() {
    channel?.setMethodCallHandler(null)
    channel = null
    handler = null
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?): Boolean {
    TODO("Not yet implemented")
  }
}
