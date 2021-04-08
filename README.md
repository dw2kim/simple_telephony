# Simple Telephony

## Intro
Detects whether the device support phone calls


## Usage
Here is an example of making a `GET` to an endpoint that returns a list.

All you need is a model with a `fromJson` constructor. This ensures you app only interacts with strongly typed models, not raw JSON lists and maps. [Json_serializable](https://pub.dev/packages/json_serializable) is a commonly used dart package that provides this functionality.
```dart
    bool isVoiceCapable = false;

    try {
      isVoiceCapable = await SimpleTelephony.isVoiceCapable ?? false;
    } on PlatformException {
      print('Failed to get isVoiceCapable.');
    }
```
