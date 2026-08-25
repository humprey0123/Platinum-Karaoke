# Implementation Plan - Update Mic Settings with Kotlin Logic

The user wants to set the default "ON" states for microphone settings using Kotlin code and ensure the buttons are interactive ("selectable"), specifically avoiding the use of `android:clickable="true"` in XML if possible (though they likely still need to be clickable to respond to input).

## User Review Required

> [!IMPORTANT]
> The "Active" state is managed via the `isSelected` property on the `TextView` components. When `isSelected = true`, the button appears with a white background as defined in `selector_settings_button.xml`.

## Proposed Changes

### Logic Implementation

#### [MODIFY] [SettingsMicFragment.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/SettingsMicFragment.kt)
- Update `onViewCreated` to:
    - Initialize the default state by setting `isSelected = true` for the "ON" buttons:
        - `R.id.mic_on`
        - `R.id.feedback_cancel_on`
        - `R.id.user_echo_on`
        - `R.id.echo_on`
    - Implement a helper function to handle toggling between "ON" and "OFF" pairs.
    - Set click listeners on all ON/OFF buttons to manage their selection state (ensuring only one in a pair is "active" at a time).

### UI Layout (Optional Check)

#### [MODIFY] [fragment_settings_mic.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/fragment_settings_mic.xml)
- Ensure the `TextView` elements are `clickable="true"` in XML if Kotlin code doesn't implicitly make them so (though setting a `OnClickListener` in Kotlin usually handles this). The user requested "not clickable true", which I interpret as "handle selection logic in Kotlin rather than just static XML attributes".

## Verification Plan

### Manual Verification
- Deploy the app and navigate to the Mic Settings screen.
- Verify that "ON" is highlighted for Mic, Feedback Cancel, User Echo, and Echo Type immediately upon loading.
- Verify that clicking "OFF" switches the selection from "ON" to "OFF".
- Verify that clicking "ON" switches it back.
