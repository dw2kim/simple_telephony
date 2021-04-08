import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:simple_telephony/simple_telephony.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool _isVoiceCapable = false;

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    bool isVoiceCapable = false;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      isVoiceCapable = await SimpleTelephony.isVoiceCapable ?? false;
    } on PlatformException {
      print('Failed to get isVoiceCapable.');
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _isVoiceCapable = isVoiceCapable;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('_isVoiceCapable: $_isVoiceCapable\n'),
        ),
      ),
    );
  }
}
