# Technical Test

### Subject

The app should have two screens, as follows:

A screen with a list of clickable posts with at least the post title


A detail screen for the selected post with at least the follow:
- Post title
- Post body
- Author name
- Number of comments

### My App workflow

#### The Lists post Activity ([shortcut link](https://github.com/skategui/babylonhealthtest/tree/master/app/src/main/java/guillaume/agis/babylonhealth/ui/list))
  
 Display a message "In progress" with a lottie animation while fetching the list of posts from the server and if we do receive a list of posts, the list is displayed.
 For each row, the user avatar is load with the post's title and the post's body. Each row is clickable and goes to the Post Detail Activity.
 In the case there is a network issue, an error message with a lottie animation is displayed with a button to have the possibility to refresh the view.
 in the case the list of posts is empty, a message is displayed to the user informing him the list is empty.
 
 #### The Post Detail Activity ([shortcut link](https://github.com/skategui/babylonhealthtest/tree/master/app/src/main/java/guillaume/agis/babylonhealth/ui/detail))

This view display the user's avatar, fullname in the header with an icon to have the opportunity to send him an email, if his email has been filled.
The body of the view contains the post's title with the post's body, scrollable.
At the bottom, fixed, is a button to display the list of comments, if any. This button is not displayed if the list if empty, or there was a connexion issue while loading the comments.
When clicking on the button, it display the comments list (a custom view) as a popup like "FB comments".
 


### Archi, language and tools used

- MVI (Model View Intent)
- Clean architecture
- Dagger 2 
- RxAndroid
- Retrofit
- Espresso (by faking the AndroidInjector, instead of using a "EspressoDaggerComponent" )
- JUnit (with Truth)
- Kotlin 
- LifeCycle
- Custom views (to encapsulate Glide and to display the list of comments as a popup, ([shortcut link](https://github.com/skategui/babylonhealthtest/tree/master/app/src/main/java/guillaume/agis/babylonhealth/ui/customview)) ).

### Project

- config folder contains everything related to the config of the app, from the variables used in debug and prod to the group of gradle dependencies.
- app/src/androidTest contains the espresso tests (**6 espresso tests**)
- app/src/sharedTest contains the classes shared and used in unit and espresso tests
- app/src/test contains the unit tests. (**34 unit tests**)

- api folder contains all the classes responsible to create an HTTPClient and make requests to the server
- Common folder contains the shared ViewModel and shared Activity
- Di folder contains everything related to dagger2 and its modules to inject the dependencies into the right places.
- model folder contains all the models
- Repo folder contains all the classes responsible to make the http requests to the server
- Ui folder contains all activities(one feature per folder) and the custom views.
- Usecase folder contains all the classes responsible for some business logic and interactions between the repos, use cases etc. 
- Datatore folder contains all the classes that store data locally (could use room in the future)

### Improvements made

- Compress images
- When loading the posts list, does not map the full response into a list of objects but instead use a ResponseBody(see [PostsRepositoryImpl](https://github.com/skategui/babylonhealthtest/blob/master/app/src/main/java/guillaume/agis/babylonhealth/repo/PostsRepositoryImpl.kt) for the full comment).
It has improve the speed of the request by 10x and use 17% less memory.
- Use annotation @Binds instead of @Provides when possible to have less generated files from Dagger2.
- Does not reload users already fetched from the server. Currently store in memory but could use room if we wish to have some data consistency and be able to use the app offline.
- Network : Cache the requests (but all the same with age limit)

### Improvements to make in the future

- Have a project in multi modules in mono repo, to split up the team work and make the build faster (as it will only recompile the module modified and not the whole project), where each module could be a big feature.
Also, each module will be able to use the config, then use the same version of each dependencies.

- Network : Cache the requests **smarly** with a different expire time given the resource to access.
- Network : Add some paging on the resources that intent to return a big list of data. To avoid the user to wait too long and **use less memory/less battery**.
- Network : **Take in consideration the connexion speed** to load a different amount of data per request, and make sure the user does not wait too long to get the response of the request.
- Network :  use https://developers.google.com/protocol-buffers/ or https://google.github.io/flatbuffers/ to **transit smaller on-the-wire packet size, then make the requests faster**.
Benchmark of the performance : https://codeburst.io/json-vs-protocol-buffers-vs-flatbuffers-a4247f8bda6f
- Offline :  Usage of **Room** to store some data and have some consistency between sessions, also in order to preload some views and be able to use the app offline. (better UX)

- Network / Coroutine : Use coroutine instead of RxAndroid for the request calls. Coroutine is lighter, faster, and consume less memory. Better use RxAndroid when we wish to have a reactive app (with Flowable for instance.) [benchmark available here](https://proandroiddev.com/kotlin-coroutines-vs-rxjava-an-initial-performance-test-68160cfc6723)

- Requests content : Populate object in the JSON instead of providing only the object's id and make another request to get the full object.
  For instance, in the Post object we could have received the user object instead of only the Id ,or only **the fields needed** to have smaller response).
  [GraphQL](https://graphql.org/) proposes this feature, and then help reduce response size, then speed up the response time.

- App : Create a light version / less greedy of the app for country that does not have high quality android devices (such as africa or asia?)


- CI : Add a CI to build + run the unit tests after each commit / add a nightly build that will run all the tests (junit + espresso, as it's longer to run).

### Usage

- ./gradlew assembleDebug to assemble in debug
- ./gradlew assembleRelease to assemble in release
- ./gradlew connectedAndroidTest to run the espresso tests on the connected device
- ./gradlew test to run the unit tests 


By Guillaume Agis

With love and passion <3

