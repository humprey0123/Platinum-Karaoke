# Fix Unresolved reference 'onBackPressedDispatcher'

The issue is caused by a missing import for the `addCallback` extension function in `MainActivity.kt`. In Kotlin, `onBackPressedDispatcher.addCallback(owner) { ... }` is an extension function provided by `androidx.activity:activity-ktx`. Without the import, the compiler tries to use the member function `addCallback(owner, callback)`, which expects an `OnBackPressedCallback` object instead of a lambda, leading to resolution errors.

## Proposed Changes

### [app]

#### [MODIFY] [MainActivity.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/MainActivity.kt)
- Add `import androidx.activity.addCallback` to enable the trailing lambda syntax for back press handling.
- Remove the unused and incorrect `import java.sql.Savepoint`.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:compileDebugKotlin` to verify that the code now compiles without errors.
