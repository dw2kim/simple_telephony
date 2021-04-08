import 'dart:async';

import 'package:flutter/services.dart';

class SimpleTelephony {
  static const MethodChannel _channel =
      const MethodChannel('com.daewonkim/simple_telephony');

  static Future<bool?> get isVoiceCapable async {
    final bool? isVoiceCapable = await _channel.invokeMethod('isVoiceCapable');
    return isVoiceCapable;
  }
}
