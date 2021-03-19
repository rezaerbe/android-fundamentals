# [Android Kotlin Fundamentals](https://developer.android.com/codelabs/kotlin-android-training-welcome)

Welcome to the Android Kotlin Fundamentals course, created by the Google Developers Training team. This course provides a series of codelabs that lead you through the fundamentals of building Android apps using Kotlin. In this course, you learn basic Android Kotlin programming concepts and build various apps.

## **Build your first app**

This pathway teaches you how to set up Android Studio to use Kotlin and how to build apps. You start with "Hello World" and move up to an app that uses image files and a click handler. You learn how Android projects are structured, how to use and modify views in your Android Kotlin app, and how to make sure your apps are backward-compatible. You also learn about API levels and the Android Jetpack libraries.

### [Install Android Studio](https://developer.android.com/codelabs/kotlin-android-training-install-studio)

In this codelab, you learn how to install Android Studio, Google's Android development environment.

### [Get started](https://developer.android.com/codelabs/kotlin-android-training-get-started)

In this codelab you create and run your first Android app, HelloWorld, on an emulator and on a physical device. You also explore what an Android project looks like.

- To install Android Studio, go to [Android Studio](https://developer.android.com/sdk/index.html) and follow the instructions to download and install it.
- To see an app's Android hierarchy in the Project pane, click the **Project** tab in the vertical tab column. Then select **Android** in the drop-down menu at the top.
- When you need to add new dependencies to your project or change dependency versions, edit the `build.gradle(Module:app)` file.
- All code and resources for an app are located within the `app` and `res` folders. The `java` folder includes activities, tests, and other components in Kotlin or Java source code (or both). The `res` folder holds resources, such as layouts, strings, and images.
- To add features, components, and permissions to your Android app, edit the `AndroidManifest.xml` file. All app components, such as additional activities, must be declared in this XML file.
- To create an Android virtual device (an emulator) to run your app, use the [AVD Manager](https://developer.android.com/tools/devices/managing-avds.html).
- To run your app on a physical Android device using Android Studio, enable USB debugging on the device. To do this, open **Settings > About phone** and tap **Build number** seven times. Then open **Settings** > **Developer options** and select **USB debugging**.