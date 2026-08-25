# Walkthrough - VerticalSeekBar Range Update

I have updated the `VerticalSeekBar` to support a range from -10 to 10, with 0 at the middle.

## Changes Made

### `VerticalSeekBar.kt`
- Updated `drawTickMarks` to show 20 intervals, creating 21 tick marks total.
- Modified tick styling: major ticks (every 5 units) are now longer (15f) than minor ticks (8f).
- The touch logic remains consistent as it relies on the `max` property, which is now set to 20.

### `SettingsEqualizerFragment.kt`
- Updated the gain calculation logic in `updateGainText`. It now simply subtracts 10 from the progress (0-20 range) to get the -10 to 10 range.
- Explicitly set `seekbar.max = 20` when setting up the equalizer bands.

### `fragment_settings_equalizer.xml`
- Updated all `VerticalSeekBar` instances to have `android:max="20"` and `android:progress="10"` (the 0 dB middle position).

## Verification Results

### Automated Tests
- Ran `gradlew app:assembleDebug` and the build passed successfully.

### Manual Verification
- The logic maps progress as follows:
    - **Top**: Progress 20 -> +10 dB
    - **Middle**: Progress 10 -> 0 dB
    - **Bottom**: Progress 0 -> -10 dB
- Tick marks are correctly distributed across the new range.
