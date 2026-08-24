# Implementation Plan - Synchronize RadioGroups and Fix "Two Taps" Interaction

The goal is to make the `RadioGroup`s in both `SettingsLyricColorFragment` and `SettingsLyricFontFragment` behave as a single logical unit. This ensures that selecting any option immediately clears selections in other groups and updates the UI (sample text, mode selection) in a single interaction.

## User Review Required

> [!IMPORTANT]
> I am replacing the `RadioGroup.OnCheckedChangeListener` with individual `OnClickListener`s for each `RadioButton`. This approach is more robust for multi-group synchronization and avoids common "two-tap" issues where the first interaction only clears the previous selection without selecting the new one.

> [!TIP]
> I will also add automatic updates to the sample text (`StrokeTextView`) and the "Selected" label, so the user sees the effect of their choice immediately upon clicking.

## Proposed Changes

### Settings Lyric Font

#### [MODIFY] [SettingsLyricFontFragment.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/SettingsLyricFontFragment.kt)
- Replace the synchronization logic with a shared `OnClickListener` for all 10 font radio buttons.
- Update `lyric_font_sample` typeface and `lyric_font_selected` text when a button is clicked.
- Automatically trigger `selectMode(modeSelectOne, ...)` on click.

### Settings Lyric Color

#### [MODIFY] [SettingsLyricColorFragment.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/SettingsLyricColorFragment.kt)
- Replace the synchronization logic with a shared `OnClickListener` for all 10 color radio buttons.
- Update `lyric_color_selected` text and automatically trigger `selectMode(modeSelectOne, ...)` on click.
- (Optional) Prepare a mapping for color names to values if the user provides them, or simply update the text for now.

## Verification Plan

### Manual Verification
1.  Open **Lyric Font Settings**.
2.  Click a font in the first column; verify it is selected and the second column is cleared.
3.  Click a font in the second column; verify it is selected and the first column is cleared.
4.  Verify that the "Mode" at the top switches to "Select One" automatically.
5.  Verify the sample text updates (if fonts are applied to buttons).
6.  Repeat the same steps for **Lyric Color Settings**.
7.  Verify D-pad navigation (if applicable) only requires one "Enter" press to change the selection and update the UI.
