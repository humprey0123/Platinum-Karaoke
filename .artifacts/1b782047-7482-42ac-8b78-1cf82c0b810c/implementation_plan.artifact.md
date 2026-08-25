# Implementation Plan - Remove Focusable In Touch Mode

In addition to the `OnTouchListener` pattern already implemented, this plan aims to remove `android:focusableInTouchMode="true"` from styles, layouts, and code. This ensures that the first tap on a touch device (like a tablet) triggers the click event immediately, rather than just focusing the element.

## User Review Required

> [!IMPORTANT]
> This change will remove the "two-tap" requirement for elements that were previously configured to gain focus first in touch mode. The `OnTouchListener` implemented previously serves as an additional safeguard, but removing these focus settings is the standard way to achieve single-tap behavior.

## Proposed Changes

### Styles

#### [MODIFY] [styles.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/values/styles.xml)
- Change `android:focusableInTouchMode` to `false` (or remove if preferred, but setting to `false` is explicit) for:
    - `NavItem`
    - `CategoryTabs`
    - `ChoiceCategory`
    - `SettingNav`
    - `SettingEqualizer`
    - `SoundSettings`

### Layouts

#### [MODIFY] [component_navbar.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/component_navbar.xml)
- Remove `android:focusableInTouchMode="true"` from all navigation items.

#### [MODIFY] [fragment_search.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/fragment_search.xml)
- Remove `android:focusableInTouchMode="true"` from `songRecycler`.

#### [MODIFY] [fragment_settings_authentication.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/fragment_settings_authentication.xml)
- Remove `android:focusableInTouchMode="true"` from USB and Phone authentication buttons.

#### [MODIFY] [fragment_settings_default.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/fragment_settings_default.xml)
- Remove `android:focusableInTouchMode="true"` from all settings options.

#### [MODIFY] [item_setting_slider.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/item_setting_slider.xml)
- Remove `android:focusableInTouchMode="true"` from the `SeekBar`.

#### [MODIFY] [item_song.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/item_song.xml)
- Remove `android:focusableInTouchMode="true"` from the root `LinearLayout`.

#### [MODIFY] [popup_settings.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/popup_settings.xml)
- Remove `android:focusableInTouchMode="true"` from `btn_settings`.

### Kotlin Code

#### [MODIFY] [CardPresenter.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/CardPresenter.kt)
- Change `cardView.isFocusableInTouchMode = true` to `false`.

## Verification Plan

### Manual Verification
- Deploy to a tablet or touch-enabled emulator.
- Verify that a single tap triggers the click action for all updated elements.
- Verify that D-pad/keyboard navigation still works (elements should still be `focusable="true"`).
