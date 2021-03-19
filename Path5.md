# [Android Kotlin Fundamentals](https://developer.android.com/codelabs/kotlin-android-training-welcome)

Welcome to the Android Kotlin Fundamentals course, created by the Google Developers Training team. This course provides a series of codelabs that lead you through the fundamentals of building Android apps using Kotlin. In this course, you learn basic Android Kotlin programming concepts and build various apps.

## Activity and fragment lifecycles

In this pathway, you learn about the activity and fragment lifecycles, and you learn how to manage complex lifecycle situations. You work with a starter app that contains several bugs related to the Android lifecycle. You add logging to the app to better understand the app's lifecycle events, and you fix the bugs that the app contains and add some enhancements to the app. You also learn about Android Jetpack's lifecycle library, which can help you manage lifecycle events with code that's better organized and easier to maintain.

### [Lifecycles and logging](https://developer.android.com/codelabs/kotlin-android-training-lifecycles-logging)

In this codelab, you learn about a fundamental part of Android: the activity and fragment lifecycle. The activity lifecycle is the set of states an activity can be in during its lifetime. The lifecycle extends from when the activity is initially created to when it is destroyed and the system reclaims that activity's resources. As a user navigates between activities in your app (and into and out of your app), those activities each transition between different states in the activity lifecycle.

#### Activity lifecycle

- The *activity lifecycle* is a set of states through which an activity migrates. The activity lifecycle begins when the activity is first created and ends when the activity is destroyed.
- As the user navigates between activities and inside and outside of your app, each activity moves between states in the activity lifecycle.
- Each state in the activity lifecycle has a corresponding callback method you can override in your `Activity` class. There are seven lifecycle methods: [`onCreate()`](https://developer.android.com/reference/android/app/Activity.html#onCreate(android.os.Bundle))[`onStart()`](https://developer.android.com/reference/android/app/Activity.html#onStart())[`onPause()`](https://developer.android.com/reference/android/app/Activity.html#onPause())[`onRestart()`](https://developer.android.com/reference/android/app/Activity.html#onRestart())[`onResume()`](https://developer.android.com/reference/android/app/Activity.html#onResume())[`onStop()`](https://developer.android.com/reference/android/app/Activity.html#onStop())[`onDestroy()`](https://developer.android.com/reference/android/app/Activity.html#onDestroy())
- To add behavior that occurs when your activity transitions into a lifecycle state, override that state's callback method.
- To add skeleton override methods to your classes in Android Studio, select **Code > Override Methods** or press `Control+o`.

#### Logging with Log

- The Android logging API, and specifically the `Log` class, enables you to write short messages that are displayed in the Logcat within Android Studio.
- Use `Log.i()` to write an informational message. This method takes two arguments: the log *tag*, typically the name of the class, and the log *message*, a short string.
- Use the **Logcat** pane in Android Studio to view the system logs, including the messages you write.

#### Logging with Timber

[`Timber`](https://github.com/JakeWharton/timber) is a logging library with several advantages over the Android logging API. In particular, the `Timber` library:

- Generates the log tag for you based on the class name.
- Helps avoid showing logs in a release version of your Android app.
- Allows for integration with crash reporting libraries.

To use `Timber`, add its dependency to your Gradle file and extend the [`Application`](https://developer.android.com/reference/android/app/Application) class to initialize it:

- `Application` is a base class that contains global application state for your entire app. There is a default `Application` class that is used by Android if you don't specify one. You can create your own `Application` subclass to initialize app-wide libraries such as `Timber`.
- Add your custom `Application` class to your app by adding the `android:name` attribute to the `<application>` element in the Android manifest. Do not forget to do this!
- Use `Timber.i()` to write log messages with `Timber`. This method only takes one argument: the message to write. The log tag (the name of the class) is added for you automatically.

### [Complex Lifecycle Situations](https://developer.android.com/codelabs/kotlin-android-training-complex-lifecycle)

In this codelab, you will explore the activity lifecycle in greater detail. You will also learn about Android Jetpack's lifecycle library, which can help you manage lifecycle events with code that's better organized and easier to maintain.

#### Lifecycle tips

- If you set up or start something in a lifecycle callback, stop or remove that thing in the corresponding callback. By stopping the thing, you make sure it doesn't keep running when it's no longer needed. For example, if you set up a timer in `onStart()`, you need to pause or stop the timer in `onStop()`.
- Use `onCreate()` only to initialize the parts of your app that run once, when the app first starts. Use `onStart()` to start the parts of your app that run both when the app starts, and each time the app returns to the foreground.

#### Lifecycle library

- Use the Android lifecycle library to shift lifecycle control from the activity or fragment to the actual component that needs to be lifecycle-aware.
- Lifecycle *owners* are components that have (and thus "own") lifecycles, including `Activity` and `Fragment`. Lifecycle owners implement the `LifecycleOwner` interface.
- Lifecycle *observers* pay attention to the current lifecycle state and perform tasks when the lifecycle changes. Lifecycle observers implement the `LifecycleObserver` interface.
- `Lifecycle` objects contain the actual lifecycle states, and they trigger events when the lifecycle changes.

To create a lifecycle-aware class:

- Implement the `LifecycleObserver` interface in classes that need to be lifecycle-aware.
- Initialize a lifecycle observer class with the lifecycle object from the activity or fragment.
- In the lifecycle observer class, annotate lifecycle-aware methods with the lifecycle state change they are interested in.

For example, the `@OnLifecycleEvent(Lifecycle.Event.ON_START)`annotation indicates that the method is watching the `onStart` lifecycle event.

#### Process shutdowns and saving activity state

- Android regulates apps running in the background so that the foreground app can run without problems. This regulation includes limiting the amount of processing that apps in the background can do, and sometimes even shutting down your entire app process.
- The user cannot tell if the system has shut down an app in the background. The app still appears in the recents screen and should restart in the same state in which the user left it.
- The Android Debug Bridge (`adb`) is a command-line tool that lets you send instructions to emulators and devices attached to your computer. You can use `adb` to simulate a process shutdown in your app.
- When Android shuts down your app process, the `onDestroy()` lifecycle method is not called. The app just stops.

#### Preserving activity and fragment state

- When your app goes into the background, just after `onStop()` is called, app data is saved to a bundle. Some app data, such as the contents of an `EditText`, is automatically saved for you.
- The bundle is an instance of `Bundle`, which is a collection of keys and values. The keys are always strings.
- Use the `onSaveInstanceState()` callback to save other data to the bundle that you want to retain, even if the app was automatically shut down. To put data into the bundle, use the bundle methods that start with `put`, such as `putInt()`.
- You can get data back out of the bundle in the `onRestoreInstanceState()` method, or more commonly in `onCreate()`. The `onCreate()` method has a `savedInstanceState` parameter that holds the bundle.
- If the `savedInstanceState` variable contains `null`, the activity was started without a state bundle and there is no state data to retrieve.
- To retrieve data from the bundle with a key, use the `Bundle` methods that start with `get`, such as `getInt()`.

#### Configuration changes

- A *configuration change* happens when the state of the device changes so radically that the easiest way for the system to resolve the change is to shut down and rebuild the activity.
- The most common example of a configuration change is when the user rotates the device from portrait to landscape mode, or from landscape to portrait mode. A configuration change can also occur when the device language changes or a hardware keyboard is plugged in.
- When a configuration change occurs, Android invokes all the activity lifecycle's shutdown callbacks. Then Android restarts the activity from scratch, running all the lifecycle startup callbacks.
- When Android shuts down an app because of a configuration change, it restarts the activity with the state bundle that is available to `onCreate()`.
- As with process shutdown, save your app's state to the bundle in `onSaveInstanceState()`.