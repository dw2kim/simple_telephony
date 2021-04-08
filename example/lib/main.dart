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

    try {
      isVoiceCapable = await SimpleTelephony.isVoiceCapable ?? false;
    } on PlatformException {
      print('Failed to get isVoiceCapable.');
    }

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
