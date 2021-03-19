# [Android Kotlin Fundamentals](https://developer.android.com/codelabs/kotlin-android-training-welcome)

Welcome to the Android Kotlin Fundamentals course, created by the Google Developers Training team. This course provides a series of codelabs that lead you through the fundamentals of building Android apps using Kotlin. In this course, you learn basic Android Kotlin programming concepts and build various apps.

## **Repository and WorkManager**

This pathway teaches you how to add a repository to abstract the data layer and provide a clean API to the rest of your Android Kotlin app. You also learn how to use `WorkManager` to schedule background tasks in an efficient and optimized way.

### [Repository](https://developer.android.com/codelabs/kotlin-android-training-repository)

In this codelab, you improve the user experience for an app by using offline caching. Many apps rely on data from the network. If your app fetches data from the server on every launch, the user might see a loading screen, and this might be a bad user experience. Users might uninstall your app.

When users launch an app, they expect the app to show data quickly. You can achieve this goal by implementing offline caching. *Offline caching* means that your app saves data fetched from the network on the device's local storage, for faster access.

Many users have intermittent access to the internet. By implementing offline caching, you add offline support to your app, helping these users to use your app while they are offline.

- [Caching](https://searchstorage.techtarget.com/definition/cache) is a process of storing data fetched from a network on a device's storage. Caching lets your app access data when the device is offline, or if your app needs to access the same data again.
- The best way for your app to store structured data on a device's file system is to use a local SQLite database. `Room` is an SQLite object-mapping library, meaning that it provides an abstraction layer over SQLite. Using `Room` is the recommended best practice for implementing offline caching.
- A repository class isolates data sources, such as `Room` database and web services, from the rest of the app. The repository class provides a clean API for data access to the rest of the app.
- Using repositories is a recommended best practice for code separation and architecture.
- When you design an offline cache, it's a best practice to separate the app's network, domain, and database objects. This strategy is an example of [separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns).
- To add offline-support to an app, add a local database using `Room`. Implement a repository to manage and access the `Room` database. In the `ViewModel`, fetch and display the data directly from the repository instead of fetching the data from the network.
- Use a database refresh strategy to insert and update the data in the local database. In a database refresh, the local database is updated or refreshed to keep it in sync with data from the network.
- To update your app's UI data automatically when the data in the database changes, use observable queries with a return value of type [`LiveData`](https://developer.android.com/reference/androidx/lifecycle/LiveData.html) in the DAO. When the `Room` database is updated, `Room` runs the query in background to update the associated `LiveData`.

### [WorkManager](https://developer.android.com/codelabs/kotlin-android-training-work-manager)

In this codelab, you learn how to use [`WorkManager`](https://developer.android.com/topic/libraries/architecture/workmanager) to schedule a background task in an optimized and efficient way. To learn more about other available solutions for background processing in Android, see [Guide to background processing](https://developer.android.com/guide/background).

- The [`WorkManager`](https://developer.android.com/topic/libraries/architecture/workmanager.html) API makes it easy to schedule deferrable, asynchronous tasks that must be run reliably.
- Most real-world apps need to perform long-running background tasks. To schedule a background task in an optimized and efficient way, use `WorkManager`.
- The main classes in the `WorkManager` library are [`Worker`](https://developer.android.com/reference/androidx/work/Worker.html), [`WorkRequest`](https://developer.android.com/reference/androidx/work/WorkRequest.html), and [`WorkManager`](https://developer.android.com/reference/androidx/work/WorkManager.html).
- The `Worker` class represents a unit of work. To implement the background task, extend the `Worker` class and override the [`doWork()`](https://developer.android.com/reference/androidx/work/Worker.html#doWork()) method.
- The `WorkRequest` class represents a request to perform a unit of work. `WorkRequest` is the base class for specifying parameters for work that you schedule in `WorkManager`.
- There are two concrete implementations of the `WorkRequest` class: [`OneTimeWorkRequest`](https://developer.android.com/reference/androidx/work/OneTimeWorkRequest.html) for one-off tasks, and [`PeriodicWorkRequest`](https://developer.android.com/reference/androidx/work/PeriodicWorkRequest.html) for periodic work requests.
- When defining the `WorkRequest`, you can specify [`Constraints`](https://developer.android.com/reference/androidx/work/Constraints.html) indicating when the `Worker` should run. Constraints include things like whether the device is plugged in, whether the device is idle, or whether Wi-Fi is connected.
- To add constraints to the `WorkRequest`, use the set methods listed in [the `Constraints.Builder` documentation](https://developer.android.com/reference/androidx/work/Constraints.Builder#public-methods_1). For example, to indicate that the `WorkRequest` should not run if the device battery is low, use the [`setRequiresBatteryNotLow()`](https://developer.android.com/reference/androidx/work/Constraints.Builder#setRequiresBatteryNotLow(boolean)) set method.
- After you define the `WorkRequest`, hand off the task to the Android system. To do this, schedule the task using one of the `WorkManager` [`enqueue` methods](https://developer.android.com/reference/androidx/work/WorkManager.html#enqueue(androidx.work.WorkRequest)).
- The exact time that the `Worker` is executed depends on the constraints that are used in the `WorkRequest`, and on system optimizations. `WorkManager` is designed to give the best possible behavior, given these restrictions.