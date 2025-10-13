# ComposeBoilerplate

A modern Android application boilerplate built with **Jetpack Compose** and **Clean Architecture**. This template provides a solid foundation for building scalable Android applications with industry best practices.

## 🚀 Features

### 🏗️ Architecture
- **Clean Architecture** with separation of concerns
- **MVI Pattern** with StateFlow and Events
- **Multi-module structure** for better organization
- **Dependency Injection** with Hilt

### 🎨 UI/UX
- **Jetpack Compose** for modern declarative UI
- **Material Design 3** components
- **Dark/Light theme** support
- **Custom loading animations** (5 different loaders)
- **Shimmer effects** for better UX
- **Bottom navigation** with type-safe navigation

### 🔧 Development Tools
- **Screen Generator Script** for rapid development
- **Build variants** (dev, qa, staging, prod)
- **Type-safe navigation** with Navigation Compose
- **Comprehensive testing** setup

### 🌐 Network & Data
- **Ktor Client** for API communication
- **Safe API calls** with custom result handling
- **DataStore** for preferences management
- **Firebase Authentication** integration

### 📱 Sample Features
- **Authentication** (Email/Password, Google, Facebook)
- **Movie browsing** with categories and details
- **Settings** with theme and language preferences
- **Profile management**

## 📁 Project Structure

```
ComposeBoilerplate/
├── app/                          # Main application module
│   ├── presentation/            # UI layer (Screens, ViewModels, Components)
│   │   ├── authentication/     # Auth-related screens
│   │   ├── movie/              # Movie browsing features
│   │   ├── settings/           # App settings
│   │   ├── navigation/         # Navigation setup
│   │   └── base/               # Base classes
│   └── ui/                     # UI components and theme
├── core/                        # Core utilities and managers
│   ├── network/                # API client and safe requests
│   ├── models/                 # Common data models
│   └── manager/                # DataStore and Auth managers
├── data/                       # Data layer (Repositories, Data sources)
├── domain/                     # Business logic (Use cases, Entities)
└── shared/                     # Shared KMP code (if applicable)
```

## 🛠️ Tech Stack

| Category | Technology               |
|----------|--------------------------|
| **UI Framework** | Jetpack Compose          |
| **Architecture** | Clean Architecture + MVI |
| **DI** | Koin                     |
| **Navigation** | Navigation Compose 3     |
| **Networking** | Ktor Client + OkHttp     |
| **Local Storage** | DataStore                |
| **Authentication** | Firebase Auth            |
| **Image Loading** | Coil                     |
| **Logging** | Timber                   |
| **Testing** | JUnit, Espresso          |

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or newer
- JDK 11 or higher
- Android SDK API 24+

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/ComposeBoilerplate.git
   cd ComposeBoilerplate
   ```

2. **Configure environment variables**
   Create a `local.properties` file in the root directory:
   ```properties
   # API Configuration
   api.base.url.dev=https://api.example.com/dev/
   api.key.dev=your_dev_api_key
   api.base.url.qa=https://api.example.com/qa/
   api.key.qa=your_qa_api_key
   api.base.url.staging=https://api.example.com/staging/
   api.key.staging=your_staging_api_key
   api.base.url.prod=https://api.example.com/prod/
   api.key.prod=your_prod_api_key

   # Google Services
   google.web.client.id=your_google_client_id
   ```

3. **Add Firebase configuration**
   - Place your `google-services.json` file in the `app/` directory
   - Follow [Firebase setup guide](https://firebase.google.com/docs/android/setup) if needed

4. **Build and run**
   ```bash
   ./gradlew assembleDevDebug
   ```

## 🔧 Build Variants

The project includes multiple build variants for different environments:

- **Dev** - Development environment with debug logging
- **QA** - Quality assurance environment
- **Staging** - Pre-production environment
- **Prod** - Production environment

Each variant uses different API endpoints and configurations defined in `local.properties`.

## 📱 Screen Generator

This boilerplate includes a powerful screen generator script that creates complete screen implementations with:

- Composable screen with preview
- ViewModel with state management
- Events and States classes
- Navigation integration

### Usage
```bash
./generate_screen.sh
```

Follow the prompts to enter your screen name, and the script will generate all necessary files and update navigation.

### Generated Structure
```
presentation/yourscreen/
├── YourScreenScreen.kt        # Composable UI
├── YourScreenViewModel.kt     # State management
├── events/
│   └── YourScreenEvents.kt    # User actions
└── states/
    └── YourScreenStates.kt    # UI state
```

## 🎨 Theming

The app supports Material Design 3 with:
- Dynamic color schemes
- Dark/Light mode toggle
- Custom color palettes
- Consistent typography

Theme configuration in `ui/theme/`:
- `Color.kt` - Color definitions
- `Theme.kt` - Theme setup
- `Type.kt` - Typography system

## 🔄 State Management

The project uses a consistent state management pattern:

### ViewModel Structure
```kotlin
@HiltViewModel
class YourViewModel @Inject constructor() : BaseViewModel() {
    val states = MutableStateFlow(YourStates())

    fun onEvent(event: YourEvents) {
        when (event) {
            is YourEvents.SomeAction -> handleAction()
        }
    }
}
```

### State Classes
```kotlin
data class YourStates(
    val isLoading: Boolean = false,
    val message: Event<String>? = null,
    val data: List<Item> = emptyList()
)

sealed interface YourEvents {
    data object LoadData : YourEvents
    data class UpdateItem(val item: Item) : YourEvents
}
```

## 🧪 Testing

Run tests with:
```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest

# All tests
./gradlew check
```

## 📖 Using This Template

### 1. 📦 Change Package Name

**Step-by-step guide to rename package from `com.fahimdev.composeboilerplate` to your package:**

#### Method 1: Using Android Studio (Recommended)
1. **Open Android Studio** and load the project
2. **Switch to Project view** (not Android view) in the Project panel
3. **Navigate to** `app/src/main/java/com/fahimdev/composeboilerplate`
4. **Right-click on the package** → **Refactor** → **Rename**
5. **Choose "Rename Package"** and enter your new package name (e.g., `com.yourcompany.yourapp`)
6. **Click "Refactor"** and review all occurrences
7. **Update the following files manually:**

#### Method 2: Manual Steps
1. **Update `app/build.gradle.kts`:**
   ```kotlin
   android {
       namespace = "com.yourcompany.yourapp"  // Change this

       defaultConfig {
           applicationId = "com.yourcompany.yourapp"  // Change this
       }
   }
   ```

2. **Update `settings.gradle.kts`:**
   ```kotlin
   rootProject.name = "YourAppName"  // Change this
   ```

3. **Rename package directories:**
   - Navigate to `app/src/main/java/`
   - Rename `com/fahimdev/composeboilerplate` to `com/yourcompany/yourapp`
   - Do the same for `androidTest` and `test` directories

4. **Update all Kotlin files:**
   ```kotlin
   // Change this in every .kt file
   package com.fahimdev.composeboilerplate.xxx
   // To this
   package com.yourcompany.yourapp.xxx
   ```

5. **Update imports throughout the codebase:**
   ```kotlin
   // Find and replace all imports
   import com.fahimdev.composeboilerplate.
   // With
   import com.yourcompany.yourapp.
   ```

6. **Update AndroidManifest.xml** (if needed):
   ```xml
   <manifest xmlns:android="http://schemas.android.com/apk/res/android"
       package="com.yourcompany.yourapp">
   ```

### 2. 🏷️ Change Project and App Name

#### Update Project Name
1. **In `settings.gradle.kts`:**
   ```kotlin
   rootProject.name = "YourAwesomeApp"
   ```

2. **Rename root directory** from `ComposeBoilerplate` to `YourAwesomeApp`

#### Update App Display Name
1. **In `app/src/main/res/values/strings.xml`:**
   ```xml
   <resources>
       <string name="app_name">Your Awesome App</string>
   </resources>
   ```

2. **Update theme name in `Theme.kt`:**
   ```kotlin
   @Composable
   fun YourAwesomeAppTheme(  // Rename from ComposeBoilerplateTheme
       darkTheme: Boolean = isSystemInDarkTheme(),
       content: @Composable () -> Unit
   ) {
   ```

3. **Update references to theme in all files:**
   - Replace `ComposeBoilerplateTheme` with `YourAwesomeAppTheme`
   - Use Find & Replace across the entire project

#### Update Screen Generator Script
1. **In `generate_screen.sh`, line 23:**
   ```bash
   PACKAGE_NAME="com.yourcompany.yourapp.presentation.${SCREEN_NAME_LOWER}"
   ```

2. **Update base path in line 26:**
   ```bash
   BASE_PATH="app/src/main/java/com/yourcompany/yourapp/presentation"
   ```

### 3. 🔄 Quick Rename Checklist

Use this checklist to ensure you've renamed everything:

- [ ] `app/build.gradle.kts` - namespace and applicationId
- [ ] `settings.gradle.kts` - rootProject.name
- [ ] Package directories renamed
- [ ] All `.kt` files package declarations
- [ ] All import statements
- [ ] `strings.xml` - app_name
- [ ] Theme name in `Theme.kt`
- [ ] All theme references in composables
- [ ] `generate_screen.sh` script paths
- [ ] Root directory name
- [ ] README.md references (optional)

### 4. 🧹 Clean and Rebuild

After renaming:
```bash
# Clean the project
./gradlew clean

# Rebuild
./gradlew build

# Or in Android Studio: Build → Clean Project → Rebuild Project
```

### 5. ✅ Verify Changes

1. **Check that the app builds successfully**
2. **Run the app** and verify it launches
3. **Test navigation** between screens
4. **Verify screen generator** creates files with correct package names

### 3. Configure APIs
- Replace movie API with your actual API
- Update data models in `core/models/`
- Modify repository implementations in `data/`

### 4. Customize Features
- Remove unused screens (movie, authentication)
- Add your domain-specific screens using the generator
- Update navigation flow in `NavigationRoot.kt`

### 5. Branding
- Update color scheme in `Color.kt`
- Customize typography in `Type.kt`
- Replace logos and branding assets

## 📁 Key Files to Modify

| File | Purpose | Action |
|------|---------|--------|
| `app/build.gradle.kts` | App configuration | Update package name, versions |
| `local.properties` | Environment config | Add your API keys |
| `ui/theme/Color.kt` | App colors | Customize color palette |
| `core/network/ApiClient.kt` | API configuration | Update base URLs |
| `presentation/navigation/Screen.kt` | App screens | Add/remove screens |

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📋 TODO / Future Enhancements

### 🔄 Pagination
- [x] ~~Implement Paging 3 library integration~~ ✅ Completed
- [x] ~~Add infinite scrolling for movie lists~~ ✅ Completed
- [ ] Create reusable pagination components
- [ ] Add pull-to-refresh functionality
- [ ] Implement offline-first pagination with Room database

### 🌍 Localization (i18n)
- [ ] Set up string resources for multiple languages
- [ ] Add language selection in settings
- [ ] Implement RTL (Right-to-Left) layout support
- [ ] Create localization testing framework
- [ ] Add date/time formatting for different locales
- [ ] Support for currency and number formatting

### 🚀 CI/CD Pipeline
- [ ] **GitHub Actions Workflow**
  - [ ] Automated testing on pull requests
  - [ ] Code quality checks (detekt, ktlint)
  - [ ] Build verification for all variants
  - [ ] Automated APK generation
- [ ] **Release Automation**
  - [ ] Semantic versioning
  - [ ] Automated changelog generation
  - [ ] Play Store deployment pipeline
  - [ ] Beta testing distribution
- [ ] **Quality Assurance**
  - [ ] Unit test coverage reporting
  - [ ] UI test automation
  - [ ] Performance benchmarking
  - [ ] Security scanning

### 🏗️ Architecture Improvements
- [ ] **Dependency Injection Migration**
  - [ ] Migrate from Hilt to Koin for multiplatform support
  - [ ] Update all modules and ViewModels to use Koin
  - [ ] Create Koin modules for better organization
  - [ ] Add Koin testing support
  - [ ] Performance comparison between Hilt and Koin

### 💾 Local Database Implementation
- [ ] **Room Database Integration**
  - [ ] Set up Room database with entities and DAOs
  - [ ] Implement offline-first data caching
  - [ ] Add database migrations support
  - [ ] Create repository pattern with Room
  - [ ] Implement data synchronization between remote and local
  - [ ] Add database encryption for sensitive data
  - [ ] Performance optimization for large datasets

### 🔧 Additional Features
- [ ] **Performance Optimization**
  - [ ] Image caching and optimization
  - [ ] Memory leak detection
  - [ ] App startup time optimization
  - [ ] Battery usage optimization
- [ ] **Developer Experience**
  - [ ] Better error handling and logging
  - [ ] Crash reporting integration (Firebase Crashlytics)
  - [ ] Analytics integration
  - [ ] Feature flags implementation
- [ ] **Security Enhancements**
  - [ ] Certificate pinning
  - [ ] Proguard/R8 optimization
  - [ ] Biometric authentication
  - [ ] Data encryption at rest

### 🧪 Testing Improvements
- [ ] Increase test coverage to 80%+
- [ ] Add integration tests
- [ ] Implement screenshot testing
- [ ] Add accessibility testing
- [ ] Performance testing framework

## 🙏 Acknowledgments

- [Jetpack Compose](https://developer.android.com/jetpack/compose) team
- [Material Design](https://material.io/) team
- Android development community

---

**Happy Coding! 🚀**

For questions or support, please open an issue in this repository.