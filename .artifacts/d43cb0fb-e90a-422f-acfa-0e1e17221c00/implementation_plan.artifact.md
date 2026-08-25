# Fix UI Glitch when Navigating from Search to Home

The user reports a "glimpse of karaoke page" when navigating back from the Search screen to the Home screen. This is caused by non-atomic fragment transactions in `MainActivity.kt`. Specifically, `showHome()` calls `hideSearch()` (which commits one transaction to remove the search overlay) and then commits another transaction to replace the main content with `HomeFragment`. During the interval between these two commits, the background `KaraokeFragment` (placed there by `showSearch`) becomes visible.

## Proposed Changes

### [MainActivity](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/MainActivity.kt)

- Refactor `hideSearch` to accept an optional `FragmentTransaction`. If a transaction is provided, it will add the removal operation to that transaction instead of creating and committing a new one.
- Update `showHome` to perform both `hideSearch` and `replace(main_content, HomeFragment)` within a single atomic transaction.
- Update `onBackPressedDispatcher` to ensure that when navigating back to Home from Search, it uses the atomic transition.

#### [MODIFY] [MainActivity.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/MainActivity.kt)

- Update `hideSearch(transaction: FragmentTransaction? = null)`
- Update `showHome()` to use a single transaction.

## Verification Plan

### Manual Verification
- Deploy the app to a device/emulator.
- Navigate to the Search screen (by clicking the Search nav item or a category on Home).
- Observe that `KaraokeFragment` is in the background (hidden by `SearchFragment` overlay).
- Press the Back button or click the Home nav item.
- Verify that the transition to `HomeFragment` is seamless and no glimpse of `KaraokeFragment` is visible.
