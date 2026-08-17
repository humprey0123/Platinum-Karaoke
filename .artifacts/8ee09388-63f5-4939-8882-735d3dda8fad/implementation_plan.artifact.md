# Implementation Plan - Fix Radio Button Grouping

The user is experiencing an issue where the `RadioButton`s (Safe Mode, Normal Output, High Output) do not work as a single group (i.e., selecting one doesn't deselect the others). This is because they are nested inside intermediate `LinearLayout`s, which prevents the parent `RadioGroup` from managing them automatically.

## User Review Required

> [!IMPORTANT]
> The `RadioGroup` component only manages `RadioButton`s that are its **direct children**. Since your layout requires nested rows, I will implement the grouping logic manually in the `SettingsEqualizerFragment` class. This is consistent with how other selections are handled in this fragment.

## Proposed Changes

### Logic
#### [MODIFY] [SettingsEqualizerFragment.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/SettingsEqualizerFragment.kt)
- Add a list of the `RadioButton` IDs: `radioSafeMode`, `radioNormalOutput`, and `radioHighOutput`.
- Implement a `setupAudioOutputGroup` method to handle clicks on these buttons.
- When one button is clicked, it will set its `isChecked` state to `true` and all others to `false`.
- Call this new method in `onViewCreated`.

## Verification Plan

### Manual Verification
- Deploy the app and navigate to the Equalizer settings.
- Verify that clicking `Normal Output` deselects `Safe Mode`.
- Verify that only one radio button can be checked at a time.
