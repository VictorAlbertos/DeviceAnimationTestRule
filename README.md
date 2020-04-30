# DeviceAnimationTestRule

Running instrumentation tests on Android using **Espresso** requires disabling **animations**. DeviceAnimationTestRule is a **JUnit rule which disables device animations** prior to running any test, and enable them after every test has been executed.

This solution is just a wrapper around the **solution proposed by [artem-zinnatullin](https://github.com/artem-zinnatullin)** in his [blog entry](https://artemzin.com/blog/easiest-way-to-give-set_animation_scale-permission-for-your-ui-tests-on-android/). 


## SetUp

Add to top level *gradle.build* file

```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Add to app module *gradle.build* file
```gradle
dependencies {
    androidTestImplementation 'com.github.shumidub:DeviceAnimationTestRule:0.0.4'
}
```

## Usage

Add to Android manifest the next permission:

```xml
<uses-permission android:name="android.permission.SET_ANIMATION_SCALE"/>
```


Declare `DeviceAnimationTestRule` as an static field annotated with `@ClassRule` to your suit: 

Java:
```java
@ClassRule static public DeviceAnimationTestRule deviceAnimationTestRule = new DeviceAnimationTestRule();
```
or Kotlin:
```kotlin
 companion object {
    @ClassRule
    @JvmField
    val deviceAnimationTestRule = DeviceAnimationTestRule()
}
```
