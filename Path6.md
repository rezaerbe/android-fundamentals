# [Android Kotlin Fundamentals](https://developer.android.com/codelabs/kotlin-android-training-welcome)

Welcome to the Android Kotlin Fundamentals course, created by the Google Developers Training team. This course provides a series of codelabs that lead you through the fundamentals of building Android apps using Kotlin. In this course, you learn basic Android Kotlin programming concepts and build various apps.

## **Architecture components**

This pathway teaches you how to use `ViewModel` and `LiveData` objects. You learn how to use `ViewModel` objects to enable data to survive configuration changes such as screen rotations. You convert an app's UI data into encapsulated `LiveData` and add observer methods that are notified when the value of the `LiveData` changes.

You also integrate `LiveData` and `ViewModel` with data binding so that the views in your layout communicate directly with `ViewModel` objects, without using the app's fragments to relay information. This technique simplifies your code and eliminates the need for click handlers in the UI controllers.

### [ViewModel](https://developer.android.com/codelabs/kotlin-android-training-view-model)

In this codelab, you learn about one of the Android Architecture Components, [`ViewModel`](https://developer.android.com/reference/android/arch/lifecycle/ViewModel):

- You use the `ViewModel` class to store and manage UI-related data in a lifecycle-conscious way. The `ViewModel` class allows data to survive device-configuration changes such as screen rotations and changes to keyboard availability.
- You use the [`ViewModelFactory`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider.Factory) class to instantiate and return the `ViewModel` object that survives configuration changes.

- The Android [app architecture](https://developer.android.com/jetpack/docs/guide) guidelines recommend separating classes that have different responsibilities.
- A *UI controller* is UI-based class like [`Activity`](https://developer.android.com/reference/android/app/Activity.html) or [`Fragment`](https://developer.android.com/reference/android/app/Fragment.html). UI controllers should only contain logic that handles UI and operating system interactions; they shouldn't contain data to be displayed in the UI. Put that data in a `ViewModel`.
- The [`ViewModel`](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html) class stores and manages UI-related data. The `ViewModel` class allows data to survive configuration changes such as screen rotations.
- `ViewModel` is one of the recommended [Android Architecture Components](https://developer.android.com/jetpack/#architecture-components).
- `ViewModelProvider.Factory` is an interface you can use to create a `ViewModel` object.

The table below compares UI controllers with the `ViewModel` instances that hold data for them:

| **UI controller**                                            | **ViewModel**                                                |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| An example of a UI controller is the `ScoreFragment` that you created in this codelab. | An example of a `ViewModel` is the `ScoreViewModel` that you created in this codelab. |
| Doesn't contain any data to be displayed in the UI.          | Contains data that the UI controller displays in the UI.     |
| Contains code for displaying data, and user-event code such as click listeners. | Contains code for data processing.                           |
| Destroyed and re-created during every configuration change.  | Destroyed only when the associated UI controller goes away permanently—for an activity, when the activity finishes, or for a fragment, when the fragment is detached. |
| Contains views.                                              | Should never contain references to activities, fragments, or views, because they don't survive configuration changes, but the `ViewModel` does. |
| Contains a reference to the associated `ViewModel`.          | Doesn't contain any reference to the associated UI controller. |

### [LiveData and LiveData observers](https://developer.android.com/codelabs/kotlin-android-training-live-data)

In this codelab, you learn how to integrate [`LiveData`](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html) with the data in the `ViewModel` classes. `LiveData`, which is one of the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture), lets you build data objects that notify views when the underlying database changes.

To use the `LiveData` class, you set up "observers" (for example, activities or fragments) that observe changes in the app's data. `LiveData` is lifecycle-aware, so it only updates app-component observers that are in an active lifecycle state.

#### LiveData

- [`LiveData`](https://developer.android.com/topic/libraries/architecture/livedata) is an observable data holder class that is lifecycle-aware, one of the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture).
- You can use `LiveData` to enable your UI to update automatically when the data updates.
- `LiveData` is observable, which means that an observer like an activity or an fragment can be notified when the data held by the `LiveData` object changes.
- `LiveData` holds data; it is a wrapper that can be used with any data.
- `LiveData` is lifecycle-aware, meaning that it only updates observers that are in an active lifecycle state such as [`STARTED`](https://developer.android.com/reference/android/arch/lifecycle/Lifecycle.State.html#STARTED) or [`RESUMED`](https://developer.android.com/reference/android/arch/lifecycle/Lifecycle.State.html#RESUMED).

#### To add LiveData

- Change the type of the data variables in `ViewModel` to `LiveData` or [`MutableLiveData`](https://developer.android.com/reference/android/arch/lifecycle/MutableLiveData).

`MutableLiveData` is a `LiveData` object whose value can be changed. `MutableLiveData` is a generic class, so you need to specify the type of data that it holds.

- To change the value of the data held by the `LiveData`, use the `setValue()` method on the `LiveData` variable.

#### To encapsulate LiveData

- The `LiveData` inside the `ViewModel` should be editable. Outside the `ViewModel`, the `LiveData` should be readable. This can be implemented using a Kotlin [backing property](https://kotlinlang.org/docs/reference/properties.html#backing-properties).
- A Kotlin backing property allows you to return something from a getter other than the exact object.
- To encapsulate the `LiveData`, use `private` `MutableLiveData` inside the `ViewModel` and return a `LiveData` backing property outside the `ViewModel`.

#### Observable LiveData

- `LiveData` follows an observer pattern. The "observable" is the `LiveData` object, and the observers are the methods in the UI controllers, like fragments. Whenever the data wrapped inside `LiveData` changes, the observer methods in the UI controllers are notified.
- To make the `LiveData` observable, attach an observer object to the `LiveData` reference in the observers (such as activities and fragments) using the [`observe()`](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html#observe(android.arch.lifecycle.LifecycleOwner, android.arch.lifecycle.Observer)) method.
- This `LiveData` observer pattern can be used to communicate from the `ViewModel` to the UI controllers.

### [Data binding with ViewModel and LiveData](https://developer.android.com/codelabs/kotlin-android-training-live-data-data-binding)

In this codelab, you continue to work with the GuessTheWord app. You bind views to the `ViewModel` classes in the app so that the views in your layout communicate directly with the `ViewModel` objects. (Up until now in your app, views have communicated *indirectly* with the `ViewModel`, by way of the app's fragments.) After you integrate data binding with the `ViewModel` objects, you no longer need click handlers in the app's fragments, so you remove them.

You also change the GuessTheWord app to use `LiveData` as the data-binding source to notify the UI about changes in the data, without using `LiveData` observer methods.

- The Data Binding Library works seamlessly with Android Architecture Components like `ViewModel` and `LiveData`.
- The layouts in your app can bind to the data in the Architecture Components, which already help you manage the UI controller's lifecycle and notify about changes in the data.

#### ViewModel data binding

- You can associate a [`ViewModel`](https://developer.android.com/reference/android/arch/lifecycle/ViewModel) with a layout by using data binding.
- `ViewModel` objects hold the UI data. By passing `ViewModel` objects into the data binding, you can automate some of the communication between the views and the `ViewModel` objects.

How to associate a `ViewModel` with a layout:

- In the layout file, add a data-binding variable of the type `ViewModel`.

```
   <data>
       <variable
           name="gameViewModel"
           type="com.example.android.guesstheword.screens.game.GameViewModel" />
   </data>
```

- In the `GameFragment` file, pass the `GameViewModel` into the data binding.

```
binding.gameViewModel = viewModel
```

#### Listener bindings

- [*Listener bindings*](https://developer.android.com/topic/libraries/data-binding/expressions#listener_bindings) are binding expressions in the layout that run when click events such as `onClick()` are triggered.
- Listener bindings are written as lambda expressions.
- Using listener bindings, you replace the click listeners in the UI controllers with listener bindings in the layout file.
- Data binding creates a listener and sets the listener on the view.

```
 android:onClick="@{() -> gameViewModel.onSkip()}"
```

#### Adding LiveData to data binding

- [`LiveData`](https://developer.android.com/reference/android/arch/lifecycle/LiveData) objects can be used as a data-binding source to automatically notify the UI about changes in the data.
- You can bind the view directly to the `LiveData` object in the `ViewModel`. When the `LiveData` in the `ViewModel` changes, the views in the layout can be automatically updated, without the observer methods in the UI controllers.

```
android:text="@{gameViewModel.word}"
```

- To make the `LiveData` data binding work, set the current activity (the UI controller) as the lifecycle owner of the `binding` variable in the UI controller.

```
binding.lifecycleOwner = this
```

#### String formatting with data binding

- Using data binding, you can format a string resource with placeholders like `%s` for strings and `%d` for integers.
- To update the `text` attribute of the view, pass in the `LiveData` object as an argument to the formatting string.

```
 android:text="@{@string/quote_format(gameViewModel.word)}"
```

### [LiveData transformations](https://developer.android.com/codelabs/kotlin-android-training-live-data-transformations)

In this codelab, you add a countdown timer in the app. You learn how to use `Transformations.map()` on the `LiveData` to transform elapsed time into a format to display on the screen.

#### Transforming LiveData

- Sometimes you want to transform the results of `LiveData`. For example, you might want to format a `Date` string as "hours:mins:seconds," or return the number of items in a list rather than returning the list itself. To perform transformations on `LiveData`, use helper methods in the [`Transformations`](https://developer.android.com/reference/androidx/lifecycle/Transformations.html) class.
- The [`Transformations.map()`](https://developer.android.com/reference/androidx/lifecycle/Transformations.html#map(androidx.lifecycle.LiveData, androidx.arch.core.util.Function)) method provides an easy way to perform data manipulations on the `LiveData` and return another `LiveData` object. The recommended practice is to put data-formatting logic that uses the `Transformations` class in the `ViewModel` along with the UI data.

#### Displaying the result of a transformation in a

**`TextView`**

- Make sure the source data is defined as `LiveData` in the `ViewModel`.
- Define a variable, for example `newResult`. Use `Transformation.map()` to perform the transformation and return the result to the variable.

```
val newResult = Transformations.map(someLiveData) { input ->
   // Do some transformation on the input live data
   // and return the new value
}
```

- Make sure the layout file that contains the `TextView` declares a `<data>` variable for the `ViewModel`.

```
<data>
   <variable
       name="MyViewModel"
       type="com.example.android.something.MyViewModel" />
</data>
```

- In the layout file, set the `text` attribute of the `TextView` to the binding of the `newResult` of the `ViewModel`. For example:

```
android:text="@{SomeViewModel.newResult}"
```

#### Formatting dates

- The [`DateUtils.formatElapsedTime()`](https://developer.android.com/reference/android/text/format/DateUtils.html#formatElapsedTime(long)) utility method takes a `long` number of milliseconds and formats the number to use a `MM:SS` string format.