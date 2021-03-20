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

In Kotlin, coroutines are the way to handle long-running tasks elegantly and efficiently instead of callbacks. Kotlin coroutines let you convert callback-based code to sequential code. Code written sequentially is typically easier to read and maintain. Unlike callbacks, coroutines can safely use valuable language features such as exceptions. And most importantly coroutines have a higher degree of maintainability and flexibility. In the end, coroutines and callbacks perform the same functionality: they are both ways of handling potentially long-running asynchronous tasks within an app.

![952b19bd8601a7a5.png](https://developer.android.com/codelabs/kotlin-android-training-coroutines-and-room/img/952b19bd8601a7a5.png)

Coroutines have the following properties:

- Coroutines are asynchronous and non-blocking.
- Coroutines use *suspend* functions to make asynchronous code sequential.

#### Coroutines are asynchronous.

A coroutine runs independently from the main execution steps of your program. This could be in parallel or on a separate processor. It could also be that while the rest of the app is waiting for input, you sneak in a bit of processing. One of the important aspects of asynchronous programming is you cannot expect the result to be immediately available, until you explicitly wait for it.

For example, let's say you have a question that requires research, and you politely request to ask a colleague to find the answer. Your colleague then starts to work on it by themselves. You can continue to do other unrelated work that doesn't depend on the answer until your colleague returns with an answer. In this example, your colleague is doing the work asynchronously "on a separate thread".

#### Coroutines are non-blocking.

*Non-blocking* means that a coroutine does not block, or interfere with the progress of the main or UI thread. So with coroutines, users can have the smoothest possible experience, because the UI interaction, which is run on the main thread, always has priority.

#### Coroutines use suspend functions to make asynchronous code sequential.

The keyword `suspend` is Kotlin's way of marking a function, or function type, as being available to coroutines. When a coroutine calls a function marked with `suspend`, instead of blocking until the function returns like a normal function call, the coroutine suspends execution until the result is ready. Then the coroutine resumes where it left off, with the result.

While the coroutine is suspended and waiting for a result, it unblocks the thread that it's running on. That way, other functions or coroutines can run.

The `suspend` keyword doesn't specify the thread that the code runs on. A suspend function may run on a background thread, or on the main thread.

![ce77d98e12909f3e.png](https://developer.android.com/codelabs/kotlin-android-training-coroutines-and-room/img/ce77d98e12909f3e.png)

To use coroutines in Kotlin, you need three things:

- A job
- A dispatcher
- A scope

**Job**: Basically, a job is anything that can be canceled. Every coroutine has a job, and you can use the job to cancel the coroutine. Jobs can be arranged into parent-child hierarchies. Canceling a parent job immediately cancels all the job's children, which is a lot more convenient than canceling each coroutine manually.

**Dispatcher:** The dispatcher sends off coroutines to run on various threads. For example, `Dispatchers.Main` runs tasks on the main thread, and `Dispatchers.IO` offloads blocking I/O tasks to a shared pool of threads.

**Scope:** A coroutine's *scope* defines the context in which the coroutine runs. A scope combines information about a coroutine's job and dispatchers. Scopes keep track of coroutines. When you launch a coroutine, it's "in a scope," which means that you've indicated which scope will keep track of the coroutine.

#### Kotlin coroutines with Architecture components

A [`CoroutineScope`](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/) keeps track of all your coroutines and helps you to manage when your coroutines should run. It can also cancel all of the coroutines started within it. Each asynchronous operation or coroutine runs within a particular `CoroutineScope`.

[Architecture components](https://developer.android.com/topic/libraries/architecture?authuser=1) provide first-class support for coroutines for logical scopes in your app. Architecture components define the following built-in scopes that you can use in your app. The built-in coroutine scopes are in the [KTX extensions](https://developer.android.com/kotlin/ktx?authuser=1) for each corresponding Architecture component. Be sure to add the appropriate dependencies when using these scopes.

- [`ViewModelScope`](https://developer.android.com/topic/libraries/architecture/coroutines?authuser=1#viewmodelscope)
- [`LifecycleScope`](https://developer.android.com/topic/libraries/architecture/coroutines?authuser=1#lifecyclescope)
- [`liveData`](https://developer.android.com/topic/libraries/architecture/coroutines?authuser=1#livedata)

[`ViewModelScope`](https://developer.android.com/topic/libraries/architecture/coroutines?authuser=1#viewmodelscope): A `ViewModelScope` is defined for each [`ViewModel`](https://developer.android.com/topic/libraries/architecture/viewmodel?authuser=1) in your app. Any coroutine launched in this scope is automatically canceled if the `ViewModel` is cleared. In this codelab you will use `ViewModelScope` to initiate the database operations.

#### Room and Dispatcher

When using the Room library to perform a database operation, Room uses a `Dispatchers.IO` to perform the database operations in a background thread. You don't have to explicitly specify any `Dispatchers`. Room does this for you.

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

Displaying a list or grid of data is one of the most common UI tasks in Android. Lists vary from simple to very complex. A list of text views might show simple data, such as a shopping list. A complex list, such as an annotated list of vacation destinations, might show the user many details inside a scrolling grid with headers.

To support all these use cases, Android provides the `RecyclerView` widget.

![643a2240444361ad.png](https://developer.android.com/codelabs/kotlin-android-training-recyclerview-fundamentals/img/643a2240444361ad.png)

The greatest benefit of `RecyclerView` is that it is very efficient for large lists:

- By default, `RecyclerView` only does work to process or draw items that are currently visible on the screen. For example, if your list has a thousand elements but only 10 elements are visible, `RecyclerView` does only enough work to draw 10 items on the screen. When the user scrolls, `RecyclerView` figures out what new items should be on the screen and does just enough work to display those items.
- When an item scrolls off the screen, the item's views are recycled. That means the item is filled with new content as it scrolls onto the screen. This `RecyclerView` behavior saves a lot of processing time and helps lists scroll smoothly.
- When an item changes, instead of redrawing the entire list, `RecyclerView` can update that one item. This is a huge efficiency gain when displaying long lists of complex items!

In the sequence shown below, you can see that one view has been filled with data, `ABC`. After that view scrolls off the screen, `RecyclerView` reuses the view for new data, `XYZ`.

![dcf4599789b9c2a1.png](https://developer.android.com/codelabs/kotlin-android-training-recyclerview-fundamentals/img/dcf4599789b9c2a1.png)

#### The adapter pattern

If you ever travel between countries that use different electric sockets, you probably know how you can plug your devices into foreign outlets by using an adapter. The adapter lets you convert one type of plug to another, which is really converting one interface into another.

The [adapter pattern](https://en.wikipedia.org/wiki/Adapter_pattern) in software engineering uses a similar concept. This pattern allows the API of one class to be used as another API. `RecyclerView` uses an adapter to transform app data into something the `RecyclerView` can display, without changing how the app stores and processes the data. For the sleep-tracker app, you build an adapter that adapts data from the `Room` database into something that `RecyclerView` knows how to display, without changing the `ViewModel`.

#### Implementing a RecyclerView

![4e9c18b463f00bf7.png](https://developer.android.com/codelabs/kotlin-android-training-recyclerview-fundamentals/img/4e9c18b463f00bf7.png)

To display your data in a `RecyclerView`, you need the following parts:

- Data to display.
- A [`RecyclerView`](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView) instance defined in your layout file, to act as the container for the views.
- A layout for one item of data. If all the list items look the same, you can use the same layout for all of them, but that is not mandatory. The item layout has to be created separately from the fragment's layout, so that one item view at a time can be created and filled with data.
- A layout manager. The layout manager handles the organization (the layout) of UI components in a view.
- A view holder. The view holder extends the `ViewHolder` class. It contains the view information for displaying one item from the item's layout. View holders also add information that `RecyclerView` uses to efficiently move views around the screen.
- An adapter. The adapter connects your data to the `RecyclerView`. It adapts the data so that it can be displayed in a `ViewHolder`. A `RecyclerView` uses the adapter to figure out how to display the data on the screen.

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

Another common use case is needing to show a lot of data to the user, which you can do using `GridLayout`. The `GridLayoutManager` for `RecyclerView` lays out the data as a scrollable grid, as shown below.

![fcf0fc4b78f8650.png](https://developer.android.com/codelabs/kotlin-android-training-grid-layout/img/fcf0fc4b78f8650.png)

From a design perspective, `GridLayout` is best for lists that can be represented as icons or images, such as lists within a photo browsing app. In the sleep-tracker app, you could show each night of sleep as a grid of large icons. This design would give the user an overview of their sleep quality at a glance.

#### How GridLayout lays out items

`GridLayout` arranges items in a grid of rows and columns. Assuming vertical scrolling, by default, each item in a row takes up one "span." Sometimes an item can occupy multiple spans. In the case below, one span is equivalent to the width of one column.

In the first two examples shown below, each row is made up of three spans. By default, the `GridLayoutManager` lays out each item in one span until the span count, which you specify. When it reaches the span count, it wraps to the next line.

By default, each item takes up one span, but you can make an item wider by specifying how many spans it is to occupy.

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

This codelab teaches the general principle of including items that use different layouts in a `RecyclerView`. One common example is having headers in your list or grid. A list can have a single header to describe the item content. A list can also have multiple headers to group and separate items in a single list.

`RecyclerView` doesn't know anything about your data or what type of layout each item has. The `LayoutManager` arranges the items on the screen, but the adapter adapts the data to be displayed and passes view holders to the `RecyclerView`. So you will add the code to create headers in the adapter.

#### Two ways of adding headers

In `RecyclerView`, every item in the list corresponds to an index number starting from 0. For example:

**[Actual Data] -> [Adapter Views]**

[0: SleepNight] -> [0: SleepNight]

[1: SleepNight] -> [1: SleepNight]

[2: SleepNight] -> [2: SleepNight]

One way to add headers to a list is to modify your adapter to use a different `ViewHolder` by checking indexes where your header needs to be shown. The `Adapter` will be responsible for keeping track of the header. For example, to show a header at the top of the table, you need to return a different `ViewHolder` for the header while laying out the zero-indexed item. Then all the other items would be mapped with the header offset, as shown below.

**[Actual Data] -> [Adapter Views]**

**[0: Header]**

[0: SleepNight] -> [1: SleepNight]

[1: SleepNight] -> [2: SleepNight]

[2: SleepNight] -> [3: SleepNight.

Another way to add headers is to modify the backing dataset for your data grid. Since all the data that needs to be displayed is stored in a list, you can modify the list to include items to represent a header. This is a bit simpler to understand, but it requires you to think about how you design your objects, so you can combine the different item types into a single list. Implemented this way, the adapter will display the items passed to it. So the item at position 0 is a header, and the item at position 1 is a `SleepNight`, which maps directly to what's on the screen.

**[Actual Data] -> [Adapter Views]**

**[0: Header] -> [0: Header]**

[1: SleepNight] -> [1: SleepNight]

[2: SleepNight] -> [2: SleepNight]

[3: SleepNight] -> [3: SleepNight]

Each methodology has benefits and drawbacks. Changing the dataset doesn't introduce much change to the rest of the adapter code, and you can add header logic by manipulating the list of data. On the other hand, using a different `ViewHolder` by checking indexes for headers gives more freedom on the layout of the header. It also lets the adapter handle how data is adapted to the view without modifying the backing data.

In this codelab, you update your `RecyclerView` to display a header at the start of the list. In this case, your app will use a different `ViewHolder` for the header than for data items. The app will check the index of the list to determine which `ViewHolder` to use.

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