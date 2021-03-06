# [Android Kotlin Fundamentals](https://developer.android.com/codelabs/kotlin-android-training-welcome)

Welcome to the Android Kotlin Fundamentals course, created by the Google Developers Training team. This course provides a series of codelabs that lead you through the fundamentals of building Android apps using Kotlin. In this course, you learn basic Android Kotlin programming concepts and build various apps.

## **Build An Interactive App**

In this pathway, you learn how to use the Android Studio Layout Editor to create linear layouts and constraint layouts. You create apps that get and display user input, respond to user taps, and change the visibility and color of views. This pathway also teaches you how to use data binding to eliminate inefficient calls to `findViewById()`.

### [Anatomy of Basic Android Project](https://developer.android.com/codelabs/kotlin-android-training-app-anatomy)

In this codelab, you learn more about the major components of an Android app and add simple interactivity to an app with a button.

#### Activities

- `MainActivity` is a subclass of `AppCompatActivity`, which in turn is a subclass of `Activity`. An `Activity` is a core Android class that is responsible for drawing an Android app UI and receiving input events.
- All activities have an associated layout file, which is an XML file in the app's resources. The layout file is named for the activity, for example `activity_main.xml`.
- The `setContentView()` method in `MainActivity` associates the layout with the activity, and inflates that layout when the activity is created.
- Layout inflation is a process where the views defined in the XML layout files are turned into (or "inflated" into) Kotlin view objects in memory. Once layout inflation happens, the `Activity` can draw these objects to the screen and dynamically modify them.

#### Views

- All UI elements in the app layout are subclasses of the [`View`](http://developer.android.com/reference/android/view/View.html) class and are called *views*. `TextView` and `Button` are examples of views.
- `View` elements can be grouped inside a [`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html). A view group acts as a container for the views, or other view groups, within it. `LinearLayout` is an example of a view group that arranges its views linearly.

#### View attributes

- The `android:layout_width` and `android:layout_height` attributes indicate the width and height of a view. The `match_parent` value stretches the view to its parent's width or height. The `wrap_content` value shrinks the view to fit the view's contents.
- The `android:text` attribute indicates the text that a view should display (if that view displays text.) For buttons, `android:text` is the button label.
- The `android:orientation` attribute in a `LinearLayout` view group arranges the view elements it contains. A value of `horizontal` arranges views left to right. A value of `vertical` arranges the views top to bottom.
- The `android:layout_gravity` attribute determines the placement of a view and all that view's children.
- The `android:textSize` attribute defines the size of the text in a text view. Text sizes are specified in sp units (*scalable pixels*). By using sp units, you can size text independently of the device's display quality.

#### Strings

- Instead of hardcoding strings in the layout, it's a best practice to use string resources.
- String resources are contained in the `res/values/string.xml` file.
- To extract strings, use `Alt+Enter` (`Option+Enter` on a Mac). Select **Extract string resources** from the popup menu.

#### Using views

- To connect your Kotlin code to a view that you defined in the layout, you need to get a reference to the view object after the view has been inflated. Assign an ID (`android:id`) to the view in the layout, then use the [`findViewById()`](https://developer.android.com/reference/android/view/View#findViewById(int)) method to get the associated view object.
- When you create an ID for a view in the XML layout file, Android Studio creates an integer constant with that ID's name in the generated `R` class. You can then use that `R.id` reference in the `findViewById()` method.
- You can set the attributes of a view object in your Kotlin code directly by property name. For example, the text in a text view is defined by the `android:text` attribute in the XML, and it is defined by the `text` property in Kotlin.
- A *click handler* is a method that is invoked when the user clicks or taps on a UI element. To attach a click-handler method to a view such as a button, use the `setOnClickListener()` method.

#### Using toasts

A [toast](https://developer.android.com/reference/android/widget/Toast.html) is a view that shows the user a simple message in a small popup window.

To create a toast, call the [`makeText()`](https://developer.android.com/reference/android/widget/Toast.html#makeText(android.content.Context, int, int)) factory method on the [`Toast`](https://developer.android.com/reference/android/widget/Toast.html) class with three arguments:

- The [context](https://developer.android.com/reference/android/content/Context.html) of the app `Activity`
- The message to display, for example a string resource
- A duration, for example [`Toast.LENGTH_SHORT`](https://developer.android.com/reference/android/widget/Toast.html#LENGTH_SHORT)

To display the toast, call `show()`.

### [Image resources and compatibility](https://developer.android.com/codelabs/kotlin-android-training-images-compat)

In this codelab, you improve the DiceRoller app from the last codelab and learn how to add and use image resources in your app. You also learn about app compatibility with different Android versions and how the Android Jetpack can help.

App resources:

- Your app's resources can include images and icons, standard colors used in the app, strings, and XML layouts. All of those resources are stored in the `res` folder.
- The `drawable` resources folder is where you should put all the image resources for your app.

Using vector drawables in image views:

- Vector drawables are images described in XML format. Vector drawables are more flexible than bitmap images (such as PNG files) because they can be scaled to any size or resolution.
- To add a drawable to your app's layout, use an `<ImageView>` element. The source of the image is in the `android:src` attribute. To refer to the drawable resource folder, use `@drawable`, for example `"@drawable/image_name"`.
- Use the `ImageView` view in your `MainActivity` code for the image. You can use `setImageResource()` to change the view's image to a different resource. Use `R.drawable` to refer to specific drawables, for example `setImageResource(R.drawable.image_name)`.

The `lateinit` keyword:

- Minimize the calls to `findViewById()` in your code by declaring fields to hold those views, and initializing the fields in `onCreate()`. Use the `lateinit` keyword for the field to avoid needing to declare it nullable.

The `tools` namespace for design-time attributes:

- Use the `tools:src` attribute in the `<ImageView>` element in your layout to display an image in only Android Studio's preview or design editor. You can then use an empty image for `android:src` for the final app.
- Use the `tools` namespace in the Android layout file to create placeholder content or hints for layout in Android Studio. Data declared by `tools` attributes is not used in the final app.

API levels:

- Each Android OS has an official version number and name (for example Android 9.0, "Pie") and an API level (API 28). Use the API levels in your app's Gradle files to indicate the versions of Android your app supports.
- The `compileSdkVersion` parameter in the `build.gradle` file specifies the Android API level that Gradle should use to compile your app.
- The `targetSdkVersion` parameter specifies the most recent API level that you have tested your app against. In many cases this parameter has the same value as `compileSdkVersion`.
- The `minSdkVersion` parameter specifies the oldest API level your app can run on.

Android Jetpack:

- Android Jetpack is a collection of libraries, developed by Google, that offers backward-compatible classes and helpful functions for supporting older versions of Android. Jetpack replaces and expands on the set of libraries formerly known as the Android Support Library.
- Classes imported from the `androidx` package refer to the Jetpack libraries. Dependencies to Jetpack in your `build.gradle` file also start with `androidx`.

Backward compatibility for vector drawables:

- Vector drawables are only natively supported in versions of Android higher than API 21. In older versions, Gradle generates PNG images for those drawables when your app is built.
- You can specify that the Android Support Library should be used for vector drawables in older API versions with the `vectorDrawables.useSupportLibrary = true` configuration parameter in the `build.gradle` file.
- Once you've enabled the support library for vector drawables, use the `app:srcCompat` attribute in the `<ImageView>` element (instead of `android:src`) to specify the vector drawable source for that image.

The `app` namespace:

- The `app` namespace in your XML layout file is for attributes that come from either your custom code or from libraries, not from the core Android framework.

### [Learn to help yourself](https://developer.android.com/codelabs/kotlin-android-training-available-resources)

In this codelab, you learn about resources that are helpful to Kotlin Android developers, including templates, documentation, videos, and sample apps.

- Official Android developer documentation is at [developer.android.com](http://developer.android.com/index.html).
- *Material Design* is a conceptual design philosophy that outlines how apps should look and function on mobile devices. Material Design isn't just for Android apps. The Material Design guidelines are at [material.io](https://material.io/).
- Android Studio provides templates for common and recommended app and activity designs. These templates offer working code for common use cases.
- When you create a project, you can choose a template for your first activity.
- While you are developing your app, you can create activities and other app components from built-in templates.
- [Google Samples](https://github.com/googlesamples) contains code samples that you can study, copy, and incorporate into your projects.