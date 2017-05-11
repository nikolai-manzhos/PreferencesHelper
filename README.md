# PreferencesHelper
[![Build Status](https://travis-ci.org/NikolayManzhos/PreferencesHelper.svg?branch=master)](https://travis-ci.org/NikolayManzhos/PreferencesHelper)
## Getting started
In your project `build.gradle`:
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
In app `build.gradle`:
```gradle
	dependencies {
	        compile 'com.github.nikolaymanzhos:preferenceshelper:1.0'
	}
```
Finally create instance in `Application` class:
```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new PreferencesHelper.Builder(this)
                .build();
    }
}
```
