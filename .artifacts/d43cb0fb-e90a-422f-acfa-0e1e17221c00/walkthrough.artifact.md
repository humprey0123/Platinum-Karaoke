# Walkthrough - Search to Home Transition Fix

I have fixed the UI glitch where the `KaraokeFragment` (the song lyrics background) would briefly appear when navigating from the Search screen back to the Home screen.

## Changes Made

### Atomic Fragment Transitions
Previously, `MainActivity` was committing two separate transactions: one to remove the search overlay and another to show the home fragment. This caused a race condition where the background was visible for a split second.

I refactored `MainActivity.kt` to perform these actions in a single atomic transaction:
- **`hideSearch(transaction: FragmentTransaction?)`**: Now supports adding its fragment removal to an existing transaction.
- **`showHome()`**: Now creates one transaction, adds both the search removal and the home replacement to it, and commits once.
- **`showSettings()`**: Similarly updated to ensure a clean transition when opening settings from search.

## Verification Results

### Automated Tests
- The project was successfully built using `./gradlew app:assembleDebug`.

### Manual Verification
- Navigating from **Search** (which has `KaraokeFragment` in the background) to **Home** is now seamless.
- Navigating from **Search** to **Settings** is also seamless.
