# NAB Assignment
An Android Mobile project for Weather Forecast 
 
## Here are some matches of the requirements:
- Use Kotlin as Programming language
- MVVM as design app's architecture
- Use LiveData to notify data changes from ViewModel to Fragment
- UnitTest: just being able to test response success and error for WeatherViewModel class
- Secure sensitive info (release keys and ApiKey) inside "keystore.properties" file which not submitted to git, but this way just ideally for ApiKey, it should be something else that is more convenience for developer working 
- Avoid decompile APK from enable ProGuard to ugly code after build
- Check Root device by using Google RootTools

## Missing from this assignment:
- Not yet manage caching mechanism & lifecycle
- Not yet support the disability to read out the text using VoiceOver controls.
- Not yet implement database and entity relationship because out of time. Ideally, it should be done with Room and data from api should be called to store at Repository class. 

## Out of this project scope:
- Dagger: dagger is not required but it quit modern nowadays and I would like to make this project as a base project that can build another ones, then Dagger is good to add here. 

# Libraries
* [Foundation][jetpack] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][appcompat] - Degrade gracefully on older versions of Android.
  * [Android KTX][ktx] - Write more concise, idiomatic Kotlin code.
* [Architecture][arch] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Lifecycles][lifecycle] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][livedata] - Build data objects that notify views when the underlying database changes.
  * [Navigation][navigation] - Handle everything needed for in-app navigation.
  * [ViewModel][viewmodel] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* Third party and miscellaneous libraries
  * [Dagger 2][dagger2] for dependency injection
  * [Retrofit][retrofit] for REST api communication
  * [Kotlin Coroutines][coroutines] for managing background threads with simplified code and reducing needs for callbacks
  * [RootTools][roottools] for root device checking
  * [Robolectric][robolectric] for unit testing
  * [Mockk][mockk] for mocking in tests
  * [MockWebServer][mockwebserver]

[jetpack]: https://developer.android.com/jetpack/components
[appcompat]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[ktx]: https://developer.android.com/kotlin/ktx
[arch]: https://developer.android.com/jetpack/arch/
[lifecycle]: https://developer.android.com/topic/libraries/architecture/lifecycle
[livedata]: https://developer.android.com/topic/libraries/architecture/livedata
[navigation]: https://developer.android.com/topic/libraries/architecture/navigation/
[viewmodel]: https://developer.android.com/topic/libraries/architecture/viewmodel
[glide]: https://bumptech.github.io/glide/
[coroutines]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[roottools]: https://code.google.com/archive/p/roottools/wikis/Usage.wiki

[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[arch]: https://developer.android.com/arch
[espresso]: https://google.github.io/android-testing-support-library/docs/espresso/
[dagger2]: https://google.github.io/dagger
[retrofit]: http://square.github.io/retrofit
[mockk]: https://github.com/mockk/mockk
[robolectric]: https://github.com/robolectric/robolectric
[coroutines]: https://kotlinlang.org/docs/reference/coroutines-overview.html


# Build and Run
*This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio is recommended.*

1. Download the project code, preferably using `git clone`.
2. Run project using command line or Android Studio

## 1. Android Studio
### Setup
To setup Android Studio, check [this](https://developer.android.com/studio/install)
### Import project
In Android Studio, select `File | Open...` and point to the `./build.gradle` file
### Build and run
To build and run app, check [this](https://developer.android.com/studio/run)

## 2. Command Line
To build and run app using command line, check [this](https://developer.android.com/studio/build/building-cmdline)

# Test
Make sure that you have installed **JDK 11** or above.

All Unit Tests are in `src/test/java`

## Run test using Android Studio
Follow this [guide](https://developer.android.com/training/testing/unit-testing/local-unit-tests#run)

**Note:** If the tests failed with message regarding Robolectric and Java 9, follow this [answer](https://stackoverflow.com/a/59649406) to config your test run configurations properly.

## Run test using Gradle on the command line.
Make sure that JAVA_HOME is set (for general instructions, follow [this](https://docs.oracle.com/cd/E19509-01/820-3208/inst_cli_jdk_javahome_t/)).
Then you can use the following command line:

    ./gradlew test

If all the unit tests have been successful you will get a `BUILD SUCCESSFUL` message.
