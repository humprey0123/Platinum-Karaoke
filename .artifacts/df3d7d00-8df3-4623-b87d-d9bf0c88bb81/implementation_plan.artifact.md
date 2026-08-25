# Justify Space Around in Settings Fragment

The user wants to distribute the items in `fragment_settings_default.xml` with "space around" (even vertical distribution). The current implementation uses a `LinearLayout` which does not support this natively without using spacers or weights on items that might distort their size.

## User Review Required

> [!IMPORTANT]
> I will be using `ConstraintLayout` to achieve the vertical distribution (chains). This is a more modern and flexible approach than `LinearLayout`.
> I will also fix the duplicate `android:id="@+id/phone_auth"` issue by assigning unique IDs to each setting item.

## Proposed Changes

### Layout Optimization

#### [MODIFY] [fragment_settings_default.xml](file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/fragment_settings_default.xml)
- Change the inner container to `androidx.constraintlayout.widget.ConstraintLayout`.
- Assign unique IDs to the `TextView` items.
- Create a vertical chain using constraints and `app:layout_constraintVertical_chainStyle="spread"`.
- Set horizontal constraints to maintain the current "centered with margin" look.

## Verification Plan

### Automated Tests
- I will use `render_compose_preview` (if possible, but this is XML) or just verify the XML structure. Since it's XML, I'll rely on the structure and properties being correct for `ConstraintLayout`.
- I'll check for any lint errors in the updated XML.

### Manual Verification
- The user can preview the layout in Android Studio to confirm the items are evenly spaced.
