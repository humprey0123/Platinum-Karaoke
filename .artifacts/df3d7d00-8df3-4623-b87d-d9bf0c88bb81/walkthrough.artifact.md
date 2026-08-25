# Walkthrough - Justify Space Around in Settings Fragment

I have updated `fragment_settings_default.xml` to distribute its items evenly using a `ConstraintLayout` vertical chain.

## Changes

### UI Layout
- **Converted** the inner `LinearLayout` to `androidx.constraintlayout.widget.ConstraintLayout`.
- **Assigned unique IDs** to each `TextView` (`setting_item_1` through `setting_item_6`) to allow for proper constraint targeting.
- **Implemented a Vertical Chain** with `app:layout_constraintVertical_chainStyle="spread"`. This ensures the items are distributed evenly across the vertical space of the parent container, effectively achieving "justify space around".
- **Bound the `app` namespace** in the root `LinearLayout` to support `ConstraintLayout` attributes.

## Verification Results

### Layout Analysis
- Verified that all `TextView` items are part of a single vertical chain.
- Confirmed that horizontal constraints are set to `parent` with `match_constraint` (0dp) width, respecting the 100dp horizontal margin on the parent container.
- No remaining lint errors or unbound namespaces in the XML.

render_diffs(file:///C:/Users/Ian/AndroidStudioProjects/PlatinumKaraoke/app/src/main/res/layout/fragment_settings_default.xml)
