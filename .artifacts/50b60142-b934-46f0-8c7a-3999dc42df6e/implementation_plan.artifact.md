# Implementation Plan - Fix VerticalSeekBar Thumb Positioning

The user reports that the thumb doesn't reach the top of the seekbar (staying below the half). This is likely caused by the underlying `SeekBar` using the view's actual width (which is small) for thumb positioning instead of the rotated length. We will fix this by ensuring the `SeekBar`'s internal state is updated correctly and the coordinate mapping is accurate.

## Proposed Changes

### [Component Name] VerticalSeekBar

#### [MODIFY] [VerticalSeekBar.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/VerticalSeekBar.kt)
- Update `onTouchEvent` to use the same `startY` and `endY` range as `drawTickMarks`. This ensures that the progress 0 and `max` perfectly align with the visual tick marks.
- Add logic to ensure the `SeekBar`'s internal thumb position is refreshed during interaction.
- Set `thumbOffset = 0` to allow the thumb to reach the very top and bottom of the track.

#### [MODIFY] [fragment_settings_equalizer.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/fragment_settings_equalizer.xml)
- Add `android:thumbOffset="0dp"` to all `VerticalSeekBar` instances.

## Verification Plan

### Automated Tests
- Verify the build passes.

### Manual Verification
- Deploy and verify that the thumb now moves all the way from the bottom (-10 dB) to the top (+10 dB).
- Verify that at 0 dB, the thumb is exactly in the middle of the track and aligned with the center tick mark.
