# Implementation Plan - Text Stroke Styling

This plan outlines the steps to apply a white border (stroke) and shadow directly to the text of `lyric_color_sample`, as requested.

## User Review Required

> [!NOTE]
> I will be utilizing the existing `StrokeTextView` class in your project and updating it to be more flexible, allowing us to set the stroke color and width dynamically.

## Proposed Changes

### [Component] Custom Views & Layout

#### [MODIFY] [StrokeTextView.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/StrokeTextView.kt)
- Expose `strokeColor` and `strokeWidth` as public properties so they can be set from the Fragment.
- Use `Paint.Join.ROUND` for the stroke to ensure smooth corners on the text outline.

#### [MODIFY] [fragment_settings_lyric_color.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/fragment_settings_lyric_color.xml)
- Replace the standard `TextView` with `com.example.platinumkaraoke.StrokeTextView` for the `lyric_color_sample` ID.
- Keep the `shadowColor`, `dx`, `dy`, and `radius` properties for the text shadow.
- Set the `android:background` to `@null` or remove it if you no longer want the box border (I will remove the box border since you said "not the bg").

#### [MODIFY] [SettingsLyricColorFragment.kt](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/java/com/example/platinumkaraoke/SettingsLyricColorFragment.kt)
- In `onViewCreated`:
    - Find the `lyricColorSample` as a `StrokeTextView`.
    - Set its `strokeColor` to White (`#FFFFFF`).
    - Set its `strokeWidth` to a suitable value (e.g., `3f` for a `1px` equivalent).

## Verification Plan

### Manual Verification
- Deploy the app and navigate to the Lyric Color Settings.
- Verify that the "We live to share moments" text has a sharp white outline and a blue fill.
- Ensure the shadow is still visible underneath the stroked text.
- Confirm the background box and its border are gone.
