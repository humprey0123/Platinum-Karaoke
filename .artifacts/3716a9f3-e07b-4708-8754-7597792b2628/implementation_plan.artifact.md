# Implementation Plan - Update Settings Default IDs and Interactivity

This plan describes the steps to rename the IDs in `fragment_settings_default.xml` based on their text content, correct the text for specific items, and update `SettingsDefaultFragment.kt` to make these items interactive.

## User Review Required

> [!IMPORTANT]
> The text for items 4 and 5 will be updated as follows:
> - Item 4: "Factory Reset" -> "Clear Prosco"
> - Item 5: "Clear Prosco" -> "Country Enumeration"

## Proposed Changes

### [Layout]

#### [MODIFY] [fragment_settings_default.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/fragment_settings_default.xml)
- Update text of `setting_item_4` to "Clear Prosco".
- Update text of `setting_item_5` to "Country Enumeration".
- Rename IDs to be descriptive:
    - `setting_item_1` -> `default_clear_popular_list`
    - `setting_item_2` -> `default_factory_reset`
    - `setting_item_3` -> `default_clear_coin`
    - `setting_item_4` -> `default_clear_prosco`
    - `setting_item_5` -> `default_country_enumeration`
    - `setting_item_6` -> `default_enable_home_menu_on_boot`
- Update all `app:layout_constraint...` references to the new IDs.

### [Fragment]

#### [MODIFY] [SettingsDefaultFragment.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/SettingsDefaultFragment.kt)
- Add `findViewById` calls for each setting item.
- Implement click listeners for each item to handle interactions.

## Verification Plan

### Automated Tests
- Build the project to verify that all ID references in the XML are correct.
- Verify that the Fragment compiles with the new ID references.

### Manual Verification
- Deploy to a device/emulator.
- Navigate the settings items using a D-pad or touch.
- Verify that each item is focusable and responds to clicks.
