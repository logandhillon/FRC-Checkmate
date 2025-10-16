# FRC Checkmate

Create simple on-field and system tests to ensure your robot performs best on the field.

## Features

- Easily create full system tests
- Integrates with Shuffleboard
- Immediate feedback for test results
- End-to-end robot tests

## Installation

### Using Gradle (build.gradle)

1. Add the JitPack maven repository

```groovy
    maven { url "https://jitpack.io"  }
```

2. Add this to the root of your `settings.gradle`:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

3. Add the dependency to your `build.gradle`

```groovy
dependencies {
    implementation 'com.github.logandhillon:frc-checkmate:v1.0.0-rc.1'
}
```

## Usage

Example TBD
