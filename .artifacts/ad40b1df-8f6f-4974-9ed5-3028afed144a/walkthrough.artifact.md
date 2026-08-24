# Walkthrough - Single-Tap Synchronization for Lyric Settings

I have updated the `SettingsLyricColorFragment` and `SettingsLyricFontFragment` to ensure that their dual-column `RadioGroup` layouts behave as a single selection group.

## Changes

### 1. Settings Lyric Color
- **Single-Tap Logic:** Replaced `OnCheckedChangeListener` with a custom `OnClickListener` for all 10 color buttons.
- **Cross-Group Clearing:** Clicking any button in Group 1 now immediately clears Group 2, and vice-versa.
- **Auto-Mode Switch:** Clicking any color button automatically switches the top mode to "Select One" and updates the "Selected" label with the color name.
- **Default State:** Updated to correctly show "Navy Black" as selected on startup.

### 2. Settings Lyric Font
- **Single-Tap Logic:** Implemented the same shared `OnClickListener` pattern for the 10 font buttons.
- **Cross-Group Clearing:** Selecting a font in either column clears the other column instantly.
- **Auto-Mode Switch:** Selecting a font automatically triggers the "Select One" mode and updates the "Selected" label.
- **Default State:** Set "Agenta Chubby Demo" as the default selection.

## Verification Results

### Manual Verification
> [!NOTE]
> These changes eliminate the "dead tap" where the first click only cleared the previous selection. Now, every click results in a selection change and a UI update.

- [x] Lyric Color: Tapping "Sky-Navy" clears "Navy Black" and updates the sample text label.
- [x] Lyric Font: Tapping "Albha" clears "Agenta Chubby" and updates the sample text label.
- [x] Both: Tapping "Default" or "Random All" at the top clears all radio buttons in both groups.
