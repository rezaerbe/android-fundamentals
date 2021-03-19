# [Android Kotlin Fundamentals](https://developer.android.com/codelabs/kotlin-android-training-welcome)

Welcome to the Android Kotlin Fundamentals course, created by the Google Developers Training team. This course provides a series of codelabs that lead you through the fundamentals of building Android apps using Kotlin. In this course, you learn basic Android Kotlin programming concepts and build various apps.

##  **Databases and RecyclerView**

**Part One - Room database and coroutines:**

This part of the pathway teaches you how to use the [`Room`](https://developer.android.com/topic/libraries/architecture/room) database library. `Room` takes care of many of the chores of setting up and configuring a database, and simplifies the code for interacting with the database. You learn how to use Kotlin coroutines to move database operations away from the main thread, and you learn more about using `ViewModel` and `LiveData` with app navigation.

**Part Two - RecyclerView:**

This part of the pathway teaches you how to use a `RecyclerView` to efficiently display lists and grids of items. For complex lists and grids, you learn ways to make `RecyclerView` more efficient and your code easier to maintain and extend. You learn how to make items in a `RecyclerView` clickable. You also learn how to add more than one view holder and layout to lists and grids in a `RecyclerView`, for example, to add a header in your app.

### [Create a Room Database](https://developer.android.com/codelabs/kotlin-android-training-room-database)

[`Room`](https://developer.android.com/topic/libraries/architecture/room) is a database library that's part of Android [Jetpack](https://developer.android.com/jetpack/). Under the hood, the Room `library` is an abstraction layer on top of a [SQLite](https://developer.android.com/training/data-storage/sqlite) database. SQLite uses a specialized language (SQL) to perform database operations. Instead of using SQLite directly, Room simplifies the chores of setting up, configuring, and interacting with the database. It also makes it possible for your app to interact with the database using ordinary function calls. Room also has a query syntax allowing you to search for data.

- Define your tables as data classes annotated with `@Entity`. Define properties annotated with `@ColumnInfo` as columns in the tables.
- Define a data access object (DAO) as an interface annotated with `@Dao`. The DAO maps Kotlin functions to database queries.
- Use annotations to define `@Insert`, `@Delete`, and `@Update` functions.
- Use the `@Query` annotation with an SQLite query string as a parameter for any other queries.
- Create an abstract class that has a `getInstance()` function that returns a database.
- Use instrumented tests to test that your database and DAO are working as expected. You can use the provided tests as a template.

### [Coroutines and Room](https://developer.android.com/codelabs/kotlin-android-training-coroutines-and-room)

In this codelab, you implement the user-facing portion of the TrackMySleepQuality app, using Kotlin coroutines to perform database operations away from the main thread.

- Use `ViewModel`, `ViewModelFactory`, and data binding to set up the UI architecture for the app.
- To keep the UI running smoothly, use coroutines for long-running tasks, such as all database operations.
- Coroutines are asynchronous and non-blocking. They use `suspend` functions to make asynchronous code sequential.
- When a coroutine calls a function marked with `suspend`, instead of blocking until that function returns like a normal function call, it suspends execution until the result is ready. Then it resumes where it left off with the result.
- The difference between *blocking* and *suspending* is that if a thread is blocked, no other work happens. If the thread is suspended, other work happens until the result is available.

To implement click handlers that trigger database operations, follow this pattern:

1. Launch a coroutine that runs on the main or UI thread, because the result affects the UI.
2. Call a suspend function to do the long-running work, so that you don't block the UI thread while waiting for the result.
3. The long-running work has nothing to do with the UI, so switch to the I/O context. That way, the work can run in a thread pool that's optimized and set aside for these kinds of operations.
4. Then call the long running function to do the work.

Use a `Transformations` map to create a string from a `LiveData` object every time the object changes.

### [Use LiveData to control button states]()

This codelab recaps how to use `ViewModel` and fragments together to implement navigation. Remember that the goal is to put the logic of *when* to navigate into the `ViewModel`, but define the paths in the fragments and the navigation file. To achieve this goal, you use view models, fragments, `LiveData`, and observers.

The codelab concludes by showing a clever way to track button states with minimal code, so that each button is enabled and clickable only when it makes sense for the user to tap that button.

Implementing sleep quality tracking in this app is like playing a familiar piece of music in a new key. While details change, the underlying pattern of what you did in previous codelabs in this lesson remains the same. Being aware of these patterns makes coding much faster, because you can reuse code from existing apps. Here are some of the patterns used in this course so far:

- Create a `ViewModel` and a `ViewModelFactory` and set up a data source.
- Trigger navigation. To separate concerns, put the click handler in the view model and the navigation in the fragment.
- Use encapsulation with `LiveData` to track and respond to state changes.
- Use transformations with `LiveData`.
- Create a singleton database.
- Set up coroutines for database operations.

**Triggering navigation**

You define possible navigation paths between fragments in a navigation file. There are some different ways to trigger navigation from one fragment to the next. These include:

- Define `onClick` handlers to trigger navigation to a destination fragment.
- Alternatively, to enable navigation from one fragment to the next:
- Define a `LiveData` value to record if navigation should occur.
- Attach an observer to that `LiveData` value.
- Your code then changes that value whenever navigation needs to be triggered or is complete.

**Setting the android:enabled attribute**

- The [`android:enabled`](https://developer.android.com/reference/android/widget/TextView.html#attr_android:enabled) attribute is defined in `TextView` and inherited by all subclasses, including `Button`.
- The `android:enabled` attribute determines whether or not a `View` is enabled. The meaning of "enabled" varies by subclass. For example, a non-enabled `EditText` prevents the user from editing the contained text, and a non-enabled `Button` prevents the user from tapping the button.
- The `enabled` attribute is not the same as the `visibility` attribute.
- You can use transformation maps to set the value of the `enabled` attribute of buttons based on the state of another object or variable.

Other points covered in this codelab:

- To trigger notifications to the user, you can use the same technique as you use to trigger navigation.
- You can use a [`Snackbar`](https://material.io/develop/android/components/snackbar/) to notify the user.

### [RecyclerView fundamentals](https://developer.android.com/codelabs/kotlin-android-training-recyclerview-fundamentals)

This codelab teaches you how to use a `RecyclerView` to display lists of items. Building on the concepts learned in the sleep-tracker app from the previous series of codelabs, you learn a better and more versatile way to display data. Your new app will update the sleep-tracker to use a `RecyclerView` with a recommended architecture.

- Displaying a list or grid of data is one of the most common UI tasks in Android. `RecyclerView` is designed to be efficient even when displaying extremely large lists.
- `RecyclerView` does only the work necessary to process or draw items that are currently visible on the screen.
- When an item scrolls off the screen, its views are recycled. That means the item is filled with new content that scrolls onto the screen.
- The [adapter pattern](https://en.wikipedia.org/wiki/Adapter_pattern) in software engineering helps an object work together with another API. `RecyclerView` uses an adapter to transform app data into something it can display, without the need for changing how the app stores and processes data.

To display your data in a `RecyclerView`, you need the following parts:

- **RecyclerView** To create an instance of [`RecyclerView`](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView), define a `<RecyclerView>` element in the layout file.
- **LayoutManager** A `RecyclerView` uses a `LayoutManager` to organize the layout of the items in the `RecyclerView`, such as laying them out in a grid or in a linear list.

In the `<RecyclerView>` in the layout file, set the `app:layoutManager` attribute to the layout manager (such as `LinearLayoutManager` or `GridLayoutManager`).

You can also set the `LayoutManager` for a `RecyclerView` programmatically. (This technique is covered in a later codelab.)

- **Layout for each item** Create a layout for one item of data in an XML layout file.
- **Adapter** Create an adapter that prepares the data and how it will be displayed in a `ViewHolder`. Associate the adapter with the `RecyclerView`.

When `RecyclerView` runs, it will use the adapter to figure out how to display the data on the screen.

The adapter requires you to implement the following methods: – `getItemCount()` to return the number of items. – `onCreateViewHolder()` to return the `ViewHolder` for an item in the list. – `onBindViewHolder()` to adapt the data to the views for an item in the list.

- **ViewHolder** A `ViewHolder` contains the view information for displaying one item from the item's layout.
- The `onBindViewHolder()` method in the adapter adapts the data to the views. You always override this method. Typically, `onBindViewHolder()` inflates the layout for an item, and puts the data in the views in the layout.
- Because the `RecyclerView` knows nothing about the data, the `Adapter` needs to inform the `RecyclerView` when that data changes. Use `notifyDataSetChanged()`to notify the `Adapter` that the data has changed.

### [DiffUtil and data binding with RecyclerView](https://developer.android.com/codelabs/kotlin-android-training-diffutil-databinding)

In this codelab, you build on the sleep-tracker app from the previous codelab. You learn a more effective way to update the list of sleep data. You also learn how to use data binding with `RecyclerView`. If you don't have the app from the previous codelab, you can download starter code for this codelab.

```
DiffUtil
```

- `RecyclerView` has a class called `DiffUtil` which is for calculating the differences between two lists.
- `DiffUtil` has a class called `ItemCallBack` that you extend in order to figure out the difference between two lists.
- In the `ItemCallback` class, you must override the `areItemsTheSame()` and `areContentsTheSame()` methods.

```
ListAdapter
```

- To get some list management for free, you can use the `ListAdapter` class instead of `RecyclerView.Adapter`. However, if you use `ListAdapter` you have to write your own adapter for other layouts, which is why this codelab shows you how to do it.
- To open the intention menu in Android Studio, place the cursor on any item of code and press `Alt+Enter` (`Option+Enter` on a Mac). This menu is particularly helpful for refactoring code and creating stubs for implementing methods. The menu is context-sensitive, so you need to place the cursor exactly to get the correct menu.

Data binding

- Use data binding in the item layout to bind data to the views.

Binding adapters

- You previously used `Transformations` to create strings from data. If you need to bind data of different or complex types, provide binding adapters to help data binding use them.
- To declare a binding adapter, define a method that takes an item and a view, and annotate the method with `@BindingAdapter`. In Kotlin, you can write the binding adapter as an extension function on the `View`. Pass in the name of the property that the adapter adapts. For example:

```
@BindingAdapter("sleepDurationFormatted")
```

- In the XML layout, set an `app` property with the same name as the binding adapter. Pass in a variable with the data. For example:

```
.app:sleepDurationFormatted="@{sleep}"
```

### [GridLayout with RecyclerView](https://developer.android.com/codelabs/kotlin-android-training-grid-layout)

In this codelab, you learn how to display data using a grid layout instead of a list, building on the sleep-tracker app from the previous codelab. (If you don't have the app from the previous codelab, you can download starter code for this codelab.)

- A Layout manager can be used to determine the arrangement of items in a `RecyclerView`.
- `RecyclerView` comes with built-in layout managers for common use cases such as `LinearLayout` for horizontal and vertical lists, and `GridLayout` for grids.
- For more complicated use cases, you can implement a custom `LayoutManager`.
- From a design perspective, `GridLayout` is best used for lists of items that can be represented as icons or images.
- `GridLayout` arranges items in a grid of rows and columns. Assuming vertical scrolling, each item in a row takes up what's called a "span."
- You can customize how many spans an item takes up, creating more interesting grids without the need for a custom layout manager.
- Create an item layout for one item in the grid, and the layout manager takes care of arranging the items.
- You can set the `LayoutManager` for the `RecyclerView` either in the XML layout file that contains the `<RecyclerView>` element, or you can set it programmatically.

### [Interacting with RecyclerView items](https://developer.android.com/codelabs/kotlin-android-training-interacting-with-items)

In this codelab, you add this type of interaction to your `RecyclerView`, and build on an extended version of the sleep-tracker app from the previous series of codelabs.

To make items in a `RecyclerView` respond to clicks, attach click listeners to list items in the `ViewHolder`, and handle clicks in the `ViewModel`.

To make items in a `RecyclerView` respond to clicks, you need to do the following:

- Create a listener class that takes a lambda and assigns it to an `onClick()` function.

```
class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
   fun onClick(night: SleepNight) = clickListener(night.nightId)
}
```

- Set the click listener on the view.

```
android:onClick="@{() -> clickListener.onClick(sleep)}"
```

- Pass the click listener to the adapter constructor, into the view holder, and add it to the binding object.

```
class SleepNightAdapter(val clickListener: SleepNightListener):
       ListAdapter<DataItem, RecyclerView.ViewHolder>(SleepNightDiffCallback()
holder.bind(getItem(position)!!, clickListener)
binding.clickListener = clickListener
```

- In the fragment that shows the recycler view, where you create the adapter, define a click listener by passing a lambda to the adapter.

```
val adapter = SleepNightAdapter(SleepNightListener { nightId ->
      sleepTrackerViewModel.onSleepNightClicked(nightId)
})
```

- Implement the click handler in the view model. For clicks on list items, this commonly triggers navigation to a detail fragment.

### [Headers in RecyclerView](https://developer.android.com/codelabs/kotlin-android-training-headers)

In this codelab, you learn how to add a header that spans the width of the list displayed in a `RecyclerView`. You build on the sleep-tracker app from previous codelabs.

- A *header* is generally an item that spans the width of a list and acts as a title or separator. A list can have a single header to describe the item content, or multiple headers to group items and separate items from each other.
- A `RecyclerView` can use multiple view holders to accommodate a heterogeneous set of items; for example, headers and list items.
- One way to add headers is to modify your adapter to use a different `ViewHolder` by checking indexes where your header needs to be shown. The `Adapter` is responsible for keeping track of the header.
- Another way to add headers is to modify the backing dataset (the list) for your data grid, which is what you did in this codelab.

These are the major steps for adding a header:

- Abstract the data in your list by creating a `DataItem` that can hold a header or data.
- Create a view holder with a layout for the header in the adapter.
- Update the adapter and its methods to use any kind of `RecyclerView.ViewHolder`.
- In `onCreateViewHolder()`, return the correct type of view holder for the data item.
- Update `SleepNightDiffCallback` to work with the `DataItem` class.
- Create a `addHeaderAndSubmitList()` function that uses coroutines to add the header to the dataset and then calls `submitList()`.
- Implement `GridLayoutManager.SpanSizeLookup()` to make only the header three spans wide.