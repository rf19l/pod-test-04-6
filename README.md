# Sample project importing IOS Adobe AEP dependencies via cocopods gradle dsl.
If the depenedencies are successfully imported we can reference the iOS dependencies in shared/iosMain. We can see if the dependencies were successfully transformed to kotlin if a KLIB was generated in shared/build/classes/iosArm64/main/cinterop for the given dependency.
    
    ex: See iosMain/Platform.kt 'import cocoapods.AEPRulesEngine'

# Setup
1. Install cocoapods on your machine 'brew install cocoapods'
2. Add Kotlin Multiplatform Mobile Pluging to Android Studio
3. (Optional) Add Kdoctor to your machine 'brew install kdoctor'
4. in the terminal type 'kdoctor' to verify your environment is setup for KMM correctly

# Sample code trying to add AEPCore dependency via cocoapods.
Currently running into cinterop issues due to a forward enum declaration in the generated AEPCore_Swift.h
Import the project and run gradly sync to reproduce. 

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
    https://kotlinlang.org/docs/multiplatform-mobile-ios-dependencies.html#add-a-framework-without-cocoapods
