# pod-test-04-6
# Sample code trying to add AEPCore dependency via cocoapods.

Currently running into cinterop issues due to a forward enum declaration in the generated AEPCore_Swift.h

initial setup with cocoa pods.
See error for :shared:cinteropAEPCore tasks

Exception in thread "main" kotlin.NotImplementedError: An operation is not implemented: support enum forward declarations: enum AEPLogLevel

Issue is in this file:
shared/build/cocoapods/synthetic/IOS/build/Pods.build/Release-iphoneos/AEPCore.build/Objects-normal/arm64/AEPCore-Swift.h

AEPLogLevel is defined in AEPServices package.

Possible fixes:
1. Create a header file AEPLogLevelFix with the enum definition and add it to the shared module build.gradle.kts pod declaration

2. Pull the AEPCore repo from here https://github.com/adobe/aepsdk-core-ios.git
    Update the header that's causing an issue, generate the xcframework and manually create cinterop tasks for the library
