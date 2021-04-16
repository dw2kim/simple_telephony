# Simple Telephony

## Intro
Detects whether the device support phone calls


## Usage
Here is an example of detecting whether the device support phone calls
```dart
    bool isVoiceCapable = false;

    try {
      isVoiceCapable = await SimpleTelephony.isVoiceCapable ?? false;
    } on PlatformException {
      print('Failed to get isVoiceCapable.');
    }
```
