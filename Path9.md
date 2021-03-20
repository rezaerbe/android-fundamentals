# [Android Kotlin Fundamentals](https://developer.android.com/codelabs/kotlin-android-training-welcome)

Welcome to the Android Kotlin Fundamentals course, created by the Google Developers Training team. This course provides a series of codelabs that lead you through the fundamentals of building Android apps using Kotlin. In this course, you learn basic Android Kotlin programming concepts and build various apps.

## **Repository and WorkManager**

This pathway teaches you how to add a repository to abstract the data layer and provide a clean API to the rest of your Android Kotlin app. You also learn how to use `WorkManager` to schedule background tasks in an efficient and optimized way.

### [Repository](https://developer.android.com/codelabs/kotlin-android-training-repository)

In this codelab, you improve the user experience for an app by using offline caching. Many apps rely on data from the network. If your app fetches data from the server on every launch, the user might see a loading screen, and this might be a bad user experience. Users might uninstall your app.

When users launch an app, they expect the app to show data quickly. You can achieve this goal by implementing offline caching. *Offline caching* means that your app saves data fetched from the network on the device's local storage, for faster access.

Many users have intermittent access to the internet. By implementing offline caching, you add offline support to your app, helping these users to use your app while they are offline.

After an app fetches data from the network, the app can [cache](https://searchstorage.techtarget.com/definition/cache) the data by storing the data in a device's storage. You cache data so that you can access it later when the device is offline, or if you want to access the same data again.

The following table shows several ways to implement network caching in Android. In this codelab, you use `Room`, because it's the recommended way to store structured data on a device file system.

| **Caching technique**                                        | **Uses**                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [`Retrofit`](http://square.github.io/retrofit/) is a networking library used to implement a type-safe REST client for Android. You can configure Retrofit to store a copy of every network result locally. | Good solution for simple requests and responses, infrequent network calls, or small datasets. |
| You can use `SharedPreferences` to store key-value pairs.    | Good solution for a small number of keys and simple values. You can't use this technique to store large amounts of structured data. |
| You can access the app's internal storage directory and save data files in it. Your app's package name specifies the app's internal storage directory, which is in a special location in the Android file system. This directory is private to your app, and it is cleared when your app is uninstalled. | Good solution if you have specific needs that a file system can solve—for example, if you need to save media files or data files and you have to manage the files yourself. You can't use this technique to store complex and structured data. |
| You can cache data using [`Room`](https://developer.android.com/topic/libraries/architecture/room), which is an SQLite object-mapping library that provides an abstraction layer over SQLite. | Recommended solution for complex and structured data, because the best way to store structured data on a device's file system is in a local SQLite database. |

#### The repository pattern

The *repository pattern* is a design pattern that isolates data sources from the rest of the app.

A *repository* mediates between data sources (such as persistent models, web services, and caches) and the rest of the app. The diagram below shows how app components such as activities that use `LiveData` might interact with data sources by way of a repository.

![69021c8142d29198.png](https://developer.android.com/codelabs/kotlin-android-training-repository/img/69021c8142d29198.png)

To implement a repository, you use a *repository class*, such as the `VideosRepository` class that you create in the next task. The repository class isolates the data sources from the rest of the app and provides a clean API for data access to the rest of the app. Using a repository class is a recommended best practice for code separation and architecture.

#### Advantages of using a repository

A repository module handles data operations and allows you to use multiple backends. In a typical real-world app, the repository implements the logic for deciding whether to fetch data from a network or use results that are cached in a local database. This helps make your code modular and testable. You can easily mock up the repository and test the rest of the code.

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

[`WorkManager`](https://developer.android.com/arch/work) is one of the [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) and part of [Android Jetpack](http://d.android.com/jetpack). `WorkManager` is for background work that's deferrable and requires guaranteed execution:

- *Deferrable* means that the work is not required to run immediately. For example, sending analytical data to the server or syncing the database in the background is work that can be deferred.
- *Guaranteed execution* means that the task will run even if the app exits or the device restarts.

![img](https://developer.android.com/codelabs/kotlin-android-training-work-manager/img/4b9d878415255637.png)

While `WorkManager` runs background work, it takes care of compatibility issues and best practices for battery and system health. `WorkManager` offers compatibility back to API level 14. `WorkManager` chooses an appropriate way to schedule a background task, depending on the device API level. It might use [`JobScheduler`](https://developer.android.com/reference/android/app/job/JobScheduler) (on API 23 and higher) or a combination of [`AlarmManager`](https://developer.android.com/reference/android/app/AlarmManager) and [`BroadcastReceiver`](https://developer.android.com/reference/android/app/BroadcastReceiver).![img](https://developer.android.com/codelabs/kotlin-android-training-work-manager/img/e04f53ac665e07c9.png)

`WorkManager` also lets you set criteria on when the background task runs. For example, you might want the task to run only when the battery status, network status, or charge state meet certain criteria. You learn how to set constraints later in this codelab.

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