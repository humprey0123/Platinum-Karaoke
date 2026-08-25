# Fix Radio Button Interaction in Settings Equalizer

The user is experiencing difficulties with the radio buttons in the `SettingsEqualizerFragment`. Currently, the `RadioButton`s are nested within `LinearLayout`s inside a `RadioGroup`, which prevents the `RadioGroup` from managing their selection state automatically. Additionally, there is no code in the Fragment to handle these interactions.

## User Review Required

> [!IMPORTANT]
> The `RadioGroup` component only manages `RadioButton`s that are its **direct children**. Since yours are nested inside `LinearLayout`s, I will implement a manual selection logic in the Fragment, similar to how your equalizer presets are handled.

## Proposed Changes

### [Component Name]

#### [MODIFY] [SettingsEqualizerFragment.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/SettingsEqualizerFragment.kt)
- Implement selection logic for `radioSafeMode`, `radioNormalOutput`, and `radioHighOutput`.
- Ensure that only one radio button is checked at a time.
- (Optional) Allow clicking the entire row (LinearLayout) to select the radio button.

#### [MODIFY] [fragment_settings_equalizer.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/fragment_settings_equalizer.xml)
- Add IDs to the row containers if we want to make the whole row clickable.
- Ensure `RadioButton`s are correctly configured.

## Verification Plan

### Manual Verification
- Deploy the app and navigate to the Equalizer settings.
- Verify that clicking each `RadioButton` selects it and deselects the others.
- Verify that the selection state is visually represented (using the `radio_custom` drawable).
