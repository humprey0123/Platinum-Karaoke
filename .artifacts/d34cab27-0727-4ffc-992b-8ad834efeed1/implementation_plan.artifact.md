# Implementation Plan - Navigation Selection State

The goal is to implement a visual selection state for the navigation items (`navHome`, `navSearch`, and `navSettings`) in `MainActivity`. This will provide visual feedback to the user about which section of the app is currently active.

## User Review Required

> [!IMPORTANT]
> The `navSettings` item currently opens a `SettingsPopup` rather than a full-screen Fragment in the main navigation flow. However, I will still implement the selection logic for it as requested. If `navSettings` should only be "selected" when the `SettingsFragment` is shown (currently handled by `showSettings()`), please let me know.

## Proposed Changes

### [MainActivity](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/MainActivity.kt)

#### [MODIFY] [MainActivity.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/MainActivity.kt)
- Add a helper function `updateNavSelection(selectedView: View?)` to manage the `isSelected` state of navigation items.
- Update `showHome()`, `showSearch()`, and the `navSettings` click listener to call `updateNavSelection`.

### [Resources](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/)

#### [MODIFY] [selector_icon_tint.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/color/selector_icon_tint.xml)
- Add `android:state_selected="true"` to ensure the settings icon changes color when selected.

## Verification Plan

### Manual Verification
1.  Launch the app.
2.  Observe that "Home" is selected by default.
3.  Click "Search" and verify that "Search" becomes highlighted and "Home" loses its highlight.
4.  Click "Settings" and verify the settings icon updates its state.
5.  Press back to return to Home and verify the selection state reverts to "Home".
