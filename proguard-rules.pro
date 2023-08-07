# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


#这段代码的目的是在混淆代码时保留特定包下的类及其成员，同时排除对某些包或类显示警告，
#特别是针对 com.squareup、okio 和 com.qweather.sdk 包。
#这些操作旨在确保在代码混淆后应用程序的稳定性和正确性。
#  排除okhttp
 -dontwarn com.squareup.**
 -dontwarn okio.**
 -keep public class org.codehaus.* { *; }
 -keep public class java.nio.* { *; }

#  排除QWeather
 -dontwarn com.qweather.sdk.**
 -keep class com.qweather.sdk.** { *;}