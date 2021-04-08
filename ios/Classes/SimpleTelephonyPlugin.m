#import "SimpleTelephonyPlugin.h"
#import <CoreTelephony/CTTelephonyNetworkInfo.h>
#import <CoreTelephony/CTCarrier.h>

@implementation SimpleTelephonyPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"com.daewonkim/simple_telephony"
            binaryMessenger:[registrar messenger]];
    SimpleTelephonyPlugin* instance = [[SimpleTelephonyPlugin alloc] init];
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([@"isVoiceCapable" isEqualToString:call.method]) {
      CTTelephonyNetworkInfo *networkInfo = [[CTTelephonyNetworkInfo alloc]init];

      CTCarrier *carrier = networkInfo.subscriberCellularProvider;

      result(@(carrier.allowsVOIP));
  } else {
    result(FlutterMethodNotImplemented);
  }
}

@end
