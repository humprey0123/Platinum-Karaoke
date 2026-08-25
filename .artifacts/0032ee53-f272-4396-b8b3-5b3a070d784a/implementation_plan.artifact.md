# Implementation Plan - Add Left Vertical Line to Equalizer

The goal is to enhance the `VerticalSeekBar` by adding a vertical line on the left side of each slider, complementing the existing tick marks on the right.

## Proposed Changes

### Custom View Enhancements

#### [MODIFY] [VerticalSeekBar.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/VerticalSeekBar.kt)
- Update `drawTickMarks` to also draw a continuous vertical line on the left side of the track.
- This line will span from the first tick mark's Y position to the last tick mark's Y position.
- Adjust horizontal offsets to ensure balance:
    - Center: Track (under thumb)
    - Right: Tick marks (stripes)
    - Left: New vertical line

## Verification Plan

### Manual Verification
- Deploy to emulator.
- Navigate to Equalizer settings.
- Verify:
    - A vertical line appears on the left of each slider.
    - Tick marks remain on the right.
    - The thumb moves between the two lines.
    - The UI looks balanced and matches the desired "equalizer" aesthetic.
