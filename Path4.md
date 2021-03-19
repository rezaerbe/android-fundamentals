# [Android Kotlin Fundamentals](https://developer.android.com/codelabs/kotlin-android-training-welcome)

Welcome to the Android Kotlin Fundamentals course, created by the Google Developers Training team. This course provides a series of codelabs that lead you through the fundamentals of building Android apps using Kotlin. In this course, you learn basic Android Kotlin programming concepts and build various apps.

## **Navigation**

In this pathway, you learn how to create useful navigation in an app. You create a fragment and add it to an app, then add navigation to the app using the Android Studio navigation graph. You add a navigation drawer and an options menu to your app, and you work with the app's back stack, changing the destination of the system Back button. Finally, you learn how to invoke an external activity from within the app.

### [Create a fragment](https://developer.android.com/codelabs/kotlin-android-training-create-and-add-fragment)

In this codelab, you learn about fragments, which represent a behavior or a portion of the user interface in an activity. You will create a fragment inside a fun starter app called AndroidTrivia. In the next codelab, you learn more about navigation and do further work on the AndroidTrivia app.

In this codelab, you added a Fragment to the AndroidTrivia app, which you will keep working on in the next two codelabs in this lesson.

- A *Fragment* is a modular section of an activity.
- A Fragment has its own lifecycle and receives its own input events.
- Use the `<fragment>` tag to define the layout for the Fragment in the XML layout file.
- Inflate the layout for a Fragment in `onCreateView()`.
- You can add or remove a Fragment while the activity is running.

### [Define navigation paths](https://developer.android.com/codelabs/kotlin-android-training-add-navigation)

In this codelab, you add navigation to that app.

#### Navigation components

To use the Android *navigation library*, you need to do some setup:

- Add dependencies for `navigation-fragment-ktx` and `navigation-ui-ktx` in the module-level `build.gradle` file.
- Add an `ext` variable for the `navigationVersion` in the project-level `build.gradle` file.

*Navigation destinations* are fragments, activities, or other app components that the user navigates to. A *navigation graph* defines the possible paths from one navigation destination to the next.

- To create a navigation graph, create a new Android resource file of type **Navigation**. This file defines the navigation flow through the app. The file is in the `res/navigation` folder, and it's typically called `navigation.xml`.
- To see the navigation graph in the Navigation Editor, open the `navigation.xml` file and click the **Design** tab.
- Use the Navigation Editor to add destinations such as fragments to the navigation graph.
- To define the path from one destination to another, use the Navigation Graph to create an action that connects the destinations. In the `navigation.xml` file, each of these connections is represented as an `action` that has an `ID`.

A *navigation host Fragment*, usually named `NavHostFragment`, acts as a host for the fragments in the navigation graph:

- As the user moves between destinations defined in the navigation graph, the `NavHostFragment` swaps the fragments in and out and manages the Fragment back stack.
- In the `activity_main.xml` layout file, the `NavHostFragment` is represented by a `fragment` element with the name `android:name="androidx.navigation.fragment.NavHostFragment"`.

To define which Fragment is displayed when the user taps a view (for example a button), set the `onClick` listener for the view:

- In the `onClick` listener, call `findNavController().navigate()` on the view.
- Specify the `ID` of the `action` that leads to the destination.

*Conditional navigation* navigates to one screen in one case, and to a different screen in another case. To create conditional navigation:

1. Use the Navigation Editor to create a connection from the starting Fragment to each of the possible destination fragments.
2. Give each connection a unique ID.
3. In the click-listener method for the `View`, add code to detect the conditions. Then call `findNavController().navigate()` on the view, passing in the ID for the appropriate action.

#### The Back button

The system's *Back button* is usually at the bottom of the device. By default, the Back button navigates the user back to the screen they viewed most recently. In some situations, you can control where the Back button takes the user:

- In the Navigation Editor, you can use the **Attributes** pane to change an action's **popUpTo** setting. This setting removes destinations from the back stack, which has the effect of determining where the Back button takes the user.
- The **popUpTo** setting appears as the `popUpTo` attribute in the `navigation.xml` file.

![1f8e86b02d795270.png](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/1f8e86b02d795270.png)

- Selecting the **popUpToInclusive** checkbox sets the `popUpToInclusive` attribute to `true`. All destinations up to and *including* this destination are removed from the back stack.
- If an action's `popUpTo` attribute is set to the app's starting destination and `popUpToInclusive` is set to `true`, the Back button takes the user all the way out of the app.

#### The Up button

Screens in an Android app can have an on-screen *Up button* that appears at the top left of the [*app bar*](https://developer.android.com/topic/libraries/architecture/navigation/navigation-ui#top_app_bar). (The app bar is sometimes called the *action bar.*) The Up button navigates "upwards" within the app's screens, based on the hierarchical relationships between screens.

The navigation controller's [`NavigationUI`](https://developer.android.com/topic/libraries/architecture/navigation/navigation-ui) library integrates with the app bar to allow the user to tap the Up button on the app bar to get back to the app's home screen from anywhere in the app.

To link the navigation controller to the app bar:

1. In `onCreate()`, call `setupActionBarWithNavController()` on the `NavigationUI` class, passing in the navigation controller:

```
val navController = this.findNavController(R.id.myNavHostFragment)
NavigationUI.setupActionBarWithNavController(this,navController)
```

1. Override the `onSupportNavigateUp()` method to call `navigateUp()` in the navigation controller:

```
override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}
```

#### The options menu

The *options menu* is a menu that the user accesses from the app bar by tapping the icon with the three vertical dots ![4cdd17fa43bfbe6.png](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/4cdd17fa43bfbe6.png). To create an options menu with a menu item that displays a Fragment, make sure the Fragment has an ID. Then define the options menu and code the `onOptionsItemSelected()` handler for the menu items.

1. Make sure the Fragment has an ID:

- Add the destination Fragment to the navigation graph and note the ID of the Fragment. (You can change the ID if you like.)

1. Define the options menu:

- Create an Android resource file of type **Menu**, typically named `options_menu.xml`. The file is stored in the **Res > Menu** folder.
- Open the `options_menu.xml` file in the design editor and drag a **Menu Item** widget from the **Palette** pane to the menu.
- For convenience, make the ID of the menu item the same as the ID of the Fragment to display when the user clicks this menu item. This step is not required, but it makes it easier to code the `onClick` behavior for the menu item.

1. Code the `onClick` handler for the menu item:

- In the Fragment or Activity that displays the options menu, in `onCreateView()`, call `setHasOptionsMenu(true)` to enable the options menu.
- Implement `onCreateOptionsMenu()` to inflate the options menu:

```
override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
}
```

- Override the `onOptionsItemSelected()` method to take the appropriate action when the menu item is clicked. The following code displays the Fragment that has the same ID as the menu item. (This code only works if the menu item and the Fragment have identical ID values.)

```
override fun onOptionsItemSelected(item: MenuItem): Boolean {
     return NavigationUI.
            onNavDestinationSelected(item,requireView().findNavController())
            || super.onOptionsItemSelected(item)
}
```

#### The navigation drawer

The *navigation drawer* is a panel that slides out from the edge of the screen. There are two ways for the user to open the navigation drawer:

- Swipe from the starting edge (usually the left) on any screen.
- Use the drawer button (three lines) ![7277f85db3a1ad13.png](https://developer.android.com/codelabs/kotlin-android-training-add-navigation/img/7277f85db3a1ad13.png) on the app bar at the top of the app.

To add a navigation drawer to your app:

1. Add dependencies to `build.gradle (app)`.
2. Make sure each destination Fragment has an ID.
3. Create the menu for the drawer.
4. Add the drawer to the layout for the Fragment.
5. Connect the drawer to the navigation controller.
6. Set up the drawer button in the app bar.

These steps are explained in more detail below.

1. Add dependencies to `build.gradle`:

- The navigation drawer is part of the Material Components for Android library. Add the Material library to the `build.gradle (app)` file:

```
dependencies {
    ...
    implementation "com.google.android.material:material:$supportlibVersion"
    ...
}
```

1. Give each destination Fragment an ID:

- If a Fragment is reachable from the navigation drawer, open it in the navigation graph to make sure that it has an ID.

1. Create the menu for the drawer:

- Create an Android resource file of type **Menu** (typically called `navdrawer_menu`) for a navigation drawer menu. This creates a new `navdrawer_menu.xml` file in the `Res > Menu` folder.
- In the design editor, add **Menu Item** widgets to the **Menu**.

1. Add the drawer to the layout for the Fragment:

- In the layout that contains the navigation host Fragment (which is typically the main layout), use `<androidx.drawerlayout.widget.DrawerLayout>` as the root view.
- Add a `<com.google.android.material.navigation.NavigationView>` view to the layout.

1. Connect the drawer to the navigation controller:

- Open the Activity that creates the navigation controller. (The main Activity is typically the one you want here.) In `onCreate()`, use `NavigationUI.setupWithNavController()`to connect the navigation drawer with the navigation controller:

```
val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
       this, R.layout.activity_main)
NavigationUI.setupWithNavController(binding.navView, navController)
```

1. Set up the drawer button in the app bar:

- In `onCreate()` in the Activity that creates the navigation controller (which is typically the main Activity), pass the drawer layout as the third parameter to `NavigationUI.setupActionBarWithNavController`:

```
val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
    this, R.layout.activity_main)

NavigationUI.setupActionBarWithNavController(
    this, navController, binding.drawerLayout)
```

- To make the Up button work with the drawer button, edit `onSupportNavigateUp()` to return `NavigationUI.navigateUp()`. Pass the navigation controller and the drawer layout to `navigateUp()`.

```
override fun onSupportNavigateUp(): Boolean {
   val navController = this.findNavController(R.id.myNavHostFragment)
   return NavigationUI.navigateUp(navController, drawerLayout)
}
```

### [Start an external Activity](https://developer.android.com/codelabs/kotlin-android-training-start-external-activity)

In this codelab, you modify the app so that the user can share their game-play results. The user can initiate an email or text, or they can copy their game-play results to the clipboard.

Safe Args:

- To help catch errors caused by missing keys or mismatched types when you pass data from one Fragment to another, use a Gradle plugin called [*Safe Args*](https://developer.android.com/topic/libraries/architecture/navigation/navigation-pass-data#Safe-args).
- For each Fragment in your app, the Safe Args plugin generates a corresponding `NavDirection` class. You add the `NavDirection` class to the Fragment code, then use the class to pass arguments between the Fragment and other fragments.
- The `NavDirection` classes represent navigation from all the app's actions.

Implicit intents:

- An [*implicit intent*](https://developer.android.com/training/basics/intents/sending) declares an action that your app wants some other app (such as a camera app or email app) to perform on its behalf.
- If several Android apps could handle an implicit intent, Android shows the user a chooser. For example, when the user taps the **share** icon in the AndroidTrivia app, the user can select which app they want to use to share their game results.
- To build an intent, you declare an action to perform, for example [`ACTION_SEND`](https://developer.android.com/reference/android/content/Intent.html#ACTION_SEND).
- Several [`Intent()`](https://developer.android.com/reference/android/content/Intent.html#public-constructors_1) constructors are available to help you build intents.

Sharing functionality:

- In the case of sharing your success with your friends, the `Intent` action would be `Intent.ACTION_SEND.`
- To add an options menu to a Fragment, set the [`setHasOptionsMenu()`](https://developer.android.com/reference/android/support/v4/app/Fragment#sethasoptionsmenu) method to `true` in the Fragment code.
- In the Fragment code, override the `onCreateOptionsMenu()` method to inflate the menu.
- Override the `onOptionsItemSelected()` to use `startActivity()` to send the `Intent` to other apps that can handle it.

When the user taps the menu item, the intent is fired, and the user sees a chooser for the `SEND` action.