# "BforBankTest" Android App

This application has been developed as part of a technical test for a job opportunity at BforBank.

Here is the skill test statement:

Nous transmettre un repository git sur lequel le projet sera hébergé. Il consistera en une
application Android utilisant une API publique pour en afficher les données reçues.

Les points indispensables au projet :
- utiliser kotlin
- mettre en place une injection de dépendance
- avoir une donnée à envoyer à l’api (comme le texte d’un champ de recherche ou
  une géolocalisation)
- afficher des informations reçues de l’api (texte, image, points sur une carte, ...)
- les appels API doivent utiliser une pagination

Les points d’attention :
- l’architecture de votre application
- l’utilisation de coroutines
- les librairies jetpack utilisées
- l’écriture de tests

### State of the application

First of all, I would like to point out that the application is incomplete in its current state. 
I did not manage to write all the tests I wanted (missing UI and integration tests) nor to implement caching data from the api. 
Enough of what is missing, let's talk about what has been done so far.

The application in its current state can display the list of Pokemon fetched from the pokeapi API. 
In addition to the list feature, it is possible to click on a Pokemon name and fetch its details through another endpoint.

An handmade pagination system as been implemented to avoid using JetPack Pager3 lib. 
Pager3 lib is part of androidx Jetpack and enforce the use of PagingData structure to hold the data and the page info from data to presentation layer.
For this reason, I preferred to implement an handmade pagination system in order to avoid android code into the domain layer.
As an improvement, I would like to check if it is possible to use it without referencing those PagingData android classes in the domain layer.

### Technical choices

I choose to use Gradle KTS with build convention structure to manage the project configuration, while it is a powerful tool that allow autocompletion, it also provide all the features of Kotlin language

I used Hilt to manage the dependency injection. This library has its pros (simplicity, compile time dependency resolution, reduce verbosity) and of course its cons (tie to android)

I used Kotlin Flow to communicate the data between the different layers because it is a native Kotlin API and offers better integration with "reactive" operators.
It also avoid to have android objects like LiveData in the domain and make testing easier.

I used Jetpack Compose declarative UI framework coupled with the navigation library to navigate inside features screen

An handmade paging system as been implemented to avoid using JetPack Pager3 lib.

Retrofit and OkHttp make it really easy and scalable to interact with REST APIs.

I chose Coil to download the pokemon images as it is reliable and reduces the complexity of such operations.

Finally, I like using JUnit5 and Mockk as testing libraries. JUnit5 offers a lot of functions (@Nested test classes, customizable @ParameterizedTest...) and Mockk gracefully replaces Mockito for Kotlin projects.

### Architectural decisions

For the presentation part, I choose to implement MVI pattern, that explicitly define Event and State of the UI with a viewModel to coordinate them.
Then I tried to implement the Clean Architecture following the SOLID principles.
It makes the applications scalable in different way. For instance, there is no business logic within the presentation layer and the code is, as much as possible, decoupled from the Android framework (which makes it more easily testable).

Here I choose to split my folder structure by feature, and then in subfolder by layer (domain, data, ui) in order to be more explicit.
There are also following reasons that convince me to drive my architecture by feature: 

- Higher Modularity
  Package-by-feature has packages with high cohesion, high modularity, and low coupling between packages.

- Easier Code Navigation
  Maintenance programmers need to do a lot less searching for items, since all items needed for a given task are usually in the same directory. Some tools that encourage package-by-layer use package naming conventions to ease the problem of tedious code navigation. However, package-by-feature transcends the need for such conventions in the first place, by greatly reducing the need to navigate between directories.

- Higher Level of Abstraction
  Staying at a high level of abstraction is one of programming's guiding principles of lasting value. It makes it easier to think about a problem, and emphasizes fundamental services over implementation details. As a direct benefit of being at a high level of abstraction, the application becomes more self-documenting: the overall size of the application is communicated by the number of packages, and the basic features are communicated by the package names. The fundamental flaw with package-by-layer style, on the other hand, is that it puts implementation details ahead of high level abstractions - which is backwards.
  
- Separates Both Features and Layers
  The package-by-feature style still honors the idea of separating layers, but that separation is implemented using separate classes. The package-by-layer style, on the other hand, implements that separation using both separate classes and separate packages, which doesn't seem necessary or desirable.

- Minimizes Scope
  Minimizing scope is another guiding principle of lasting value. Here, package-by-feature allows some classes to decrease their scope from public to package-private. This is a significant change, and will help to minimize ripple effects. The package-by-layer style, on the other hand, effectively abandons package-private scope, and forces you to implement nearly all items as public. This is a fundamental flaw, since it doesn't allow you to minimize ripple effects by keeping secrets.

- Better Growth Style
  In the package-by-feature style, the number of classes within each package remains limited to the items related to a specific feature. If a package becomes too large, it may be refactored in a natural way into two or more packages. The package-by-layer style, on the other hand, is monolithic. As an application grows in size, the number of packages remains roughly the same, while the number of classes in each package will increase without bound.

### Environment

To allow more flexibility over testing and development, I added 2 build variants mock and prod:
- "mock" variant will only use mock predefined data for network and local storage
- "prod" variant will use regular networking and caching library

### Quality measurements

In order to implement quality standards, I added to the project some Gradle task for:
- Linter and code smell checker, using KtLint and DeteKt (./gradlew check and ./gradlew detekt)

### Real work environment and improvements reflexion

I tried to split my commits so they are reviewable but it is possible to split in even smaller chunks of code.
In a real work environment, we probably would have groomed the work to split it into small subtasks with some acceptance criteria for each.

There are some improvements that I would like to add later: 
- The application could have a cache first strategy so it would show cached data until a refresh has been manually triggered by the user.
  Using this caching strategy would allow to have most of the feature working in an offline mode and to enforce a lower data consumption.
- Implementing Pager3 library for paging data, if it respect clean architecture principle
- Current LazyColumn implementation has a scroll state issue when updating its data, see https://issuetracker.google.com/issues/214253526
- Replace Hilt by a Kotlin dependency injection to be able to take advantage of Kotlin only module for feature data & domain and core gateway modules
- Replacing feature sub package ui, data & domain to be gradle modules
- Another good approach would be to add dynamic feature modules for managing custom deliveries in order to improve scalability and performance. (I mean to not load useless feature)

### Source of inspiration

- Multiple way of defining Clean Architecture Layers: https://proandroiddev.com/multiple-ways-of-defining-clean-architecture-layers-bbb70afa5d4a
- Dynamic feature delivery: https://developer.android.com/guide/playcore/feature-delivery
- Uncle bob screaming architecture: https://levelup.gitconnected.com/what-is-screaming-architecture-f7c327af9bb2
- Modular patterns: https://hackernoon.com/applying-clean-architecture-on-web-application-with-modular-pattern-7b11f1b89011
- Package by features with bounded contexts: https://reflectoring.io/java-components-clean-boundaries/ 
- 5 most populars package structures: https://www.techyourchance.com/popular-package-structures/ 

### Source code description

```yaml
- /app
    # Main activity
    - MainActivity.kt
    # Application class
    - App.kt
    # Inter-Feature navigation
    - AppNavHost.kt
- /build-logic
    # Configuration for Android Library module
    - AndroidLibraryConventionPlugin
    # Configuration for Android Library module with compose
    - AndroidLibraryComposeConventionPlugin
    # Configuration for Android Library module with Hilt
    - AndroidHiltConventionPlugin
    # Configuration for Feature module
    - AndroidFeatureConventionPlugin
    # Configuration for Detekt for all module
    - AndroidDetektConventionPlugin
    # Configuration for Application module
    - AndroidApplicationConventionPlugin
    # Configuration for Application module with compose
    - AndroidApplicationComposeConventionPlugin
# All the common, global and shared material and configuration
- /core
    # Common UI component and design system
    - /ui
    # Common Kotlin tools like Flow, coroutine or Result extension
    - /common
        # Abstraction interface (should be a Kotlin only module)
        - /gateway
        # Implementation (should be a Android library module)
        - /implementation
    # Shared environment tool as Debug mode, Flavor mode
    - /environment
        # Abstraction interface (should be a Kotlin only module)
        - /gateway
        # Implementation (should be a Android library module)
        - /implementation
    # Shared network configuration for Retrofit and Moshi
    - /network
# Features
- /feature
    # Feature package corresponding to PokemonList feature
    - /pokemonlist
        # Directory responsible for containing everything related to external services like databases, remote services, device apis, data providers
        - /data
        # The enterprise business rules that contains entities, failures, value objects and repositories abstractions
        - /domain
        # Directory responsible for containing activities, fragments, views, viewmodels
        - /ui
        # Feature package corresponding to PokemonList feature
    - /pokemondetail
        # Directory responsible for containing everything related to external services like databases, remote services, device apis, data providers
        - /data
        # The enterprise business rules that contains entities, failures, value objects and repositories abstractions
        - /domain
        # Directory responsible for containing activities, fragments, views, viewmodels
        - /ui
```