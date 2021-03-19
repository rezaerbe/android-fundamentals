# [Android Kotlin Fundamentals](https://developer.android.com/codelabs/kotlin-android-training-welcome)

Welcome to the Android Kotlin Fundamentals course, created by the Google Developers Training team. This course provides a series of codelabs that lead you through the fundamentals of building Android apps using Kotlin. In this course, you learn basic Android Kotlin programming concepts and build various apps.

## **Layouts**

In this pathway, you learn how to use the Android Studio Layout Editor to create linear layouts and constraint layouts. You create apps that get and display user input, respond to user taps, and change the visibility and color of views. This pathway also teaches you how to use data binding to eliminate inefficient calls to `findViewById()`.

### [LinearLayout using the Layout Editor](https://developer.android.com/codelabs/kotlin-android-training-linear-layout)

- A `ViewGroup` is a view that can contain other views. [`LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout) and [`ScrollView`](https://developer.android.com/reference/android/widget/ScrollView) are view groups.
- `LinearLayout` is a view group that arranges its child views horizontally or vertically.
- Use a `ScrollView` when you need to display content on the screen, such as long text or a collection of images. A scroll view can contain only one child view. If you want to scroll more than one view, then add a `ViewGroup` such as a `LinearLayout` to the `ScrollView`, and put the views to be scrolled inside that `ViewGroup`.
- The [Layout Editor](https://developer.android.com/studio/write/layout-editor) is a visual design editor inside Android Studio. You can use the Layout Editor to build your app's layout by dragging UI elements into the layout.
- A [style](https://developer.android.com/guide/topics/ui/look-and-feel/themes) is a collection of attributes that specify the appearance for a view. For example, a style can specify font color, font size, background color, padding, and margin.
- You can extract and collect all the formatting of a view into a style. To give your app a consistent look, reuse the style for other views.

### [Add user interactivity](https://developer.android.com/codelabs/kotlin-android-training-interactivity)

- The [Layout Editor](https://developer.android.com/studio/write/layout-editor) tool in Android Studio is a visual design editor. You can use the Layout Editor to build your app's layout by dragging UI elements into your layout.
- [`EditText`](https://developer.android.com/reference/android/widget/EditText) is a UI element that lets the user enter and modify text.
- A [`Button`](https://developer.android.com/reference/android/widget/Button) is a UI element that the user can tap to perform an action. A button can consist of text, an icon, or both text and an icon.

Click listeners

- You can make any `View` respond to being tapped by adding a click listener to it.
- The function that defines the click listener receives the `View` that is clicked.

You can attach a click-listener function to a `View` in either of two ways:

- In the XML layout, add the [`android:onClick`](https://developer.android.com/reference/android/R.attr.html#onClick) attribute to the `<`*`View`*`>` element.
- Programmatically, use the [`setOnClickListener(View.OnClickListener)`](https://developer.android.com/reference/android/view/View.html#setOnClickListener(android.view.View.OnClickListener)) function in the corresponding `Activity`.

### [ConstraintLayout using the Layout Editor](https://developer.android.com/codelabs/kotlin-android-training-constraint-layout)

- A [`ConstraintLayout`](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html) is a [`ViewGroup`](http://developer.android.com/reference/android/view/ViewGroup.html) that allows you to position and size the layout's child views in a flexible way.
- In a ConstraintLayout, each view's position is defined using at least one horizontal constraint, and at least one vertical constraint*.*
- A [constraint](https://developer.android.com/training/constraint-layout/#constraints-overview) connects or aligns a view to another UI element, to the parent layout, or to an invisible guideline.

Advantages of using `ConstraintLayout`:

- You can make a ConstraintLayout responsive to devices that have different screen sizes and resolutions.
- `ConstraintLayout` usually results in a flatter view hierarchy than `LinearLayout`.
- The design editor and the view inspector in Android Studio help you add and configure constraints.

Chains:

- A [chain](https://developer.android.com/training/constraint-layout/#constrain-chain) is a group of views that are linked to each other with bidirectional constraints.
- The views within a chain can be distributed either vertically or horizontally.

Design-time attributes:

- Design-time attributes are used and applied only during the layout design, not at runtime. When you run the app, design-time attributes are ignored.
- Design-time attributes are prefixed with the `tools` namespace. For example, the `tools:layout_editor_absoluteY` and `tools:text` attributes are design-time attributes.

Baseline constraints:

- A baseline constraint aligns a view's text baseline to the text baseline of another view that has text.
- Baseline constraints are helpful when views have different font sizes.

### [Data binding basics](https://developer.android.com/codelabs/kotlin-android-training-data-binding-basics)

In this codelab, you learn how to use data binding to eliminate the need for `findViewById()`. You also learn how to use data binding to access data directly from a view.

Steps to use data binding to replace calls to `findViewById()`:

1. Enable data binding in the android section of the `build.gradle` file: ``buildFeatures { dataBinding true }``

1. Use `<layout>` as the root view in your XML layout.
2. Define a binding variable: `private lateinit var binding: ActivityMainBinding`
3. Create a binding object in `MainActivity`, replacing `setContentView`: `binding = DataBindingUtil.setContentView(this, R.layout.activity_main)`
4. Replace calls to `findViewById()` with references to the view in the binding object. For example: `findViewById<Button>(R.id.done_button)` ⇒ `binding.doneButton` (In the example, the name of the view is generated camel case from the view's `id` in the XML.)

Steps for binding views to data:

1. Create a data class for your data.
2. Add a `<data>` block inside the `<layout>` tag.
3. Define a `<variable>` with a name, and a type that is the data class.

```
<data>
   <variable
       name="myName"
       type="com.example.android.aboutme.MyName" />
</data>
```

1. In `MainActivity`, create a variable with an instance of the data class. For example: `private val myName: MyName = MyName("Aleks Haecky")`
2. In the binding object, set the variable to the variable you just created: `binding.myName = myName`
3. In the XML, set the content of the view to the variable that you defined in the `<data>` block. Use dot notation to access the data inside the data class. `android:text="@={myName.name}"`