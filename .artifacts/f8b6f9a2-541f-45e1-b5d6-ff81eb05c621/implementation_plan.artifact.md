# Fix "The file name must end with .xml" Build Error

The build error `:app:mergeDebugResources` fails with "The file name must end with .xml" because the `res/` directory contains invalid subdirectories and files that do not follow Android resource naming conventions or directory structures.

## User Review Required

> [!IMPORTANT]
> I will be deleting several directories inside `app/src/main/res` that are invalid.
> - `app/src/main/res/assets/`: Redundant images already present in `res/drawable`.
> - `app/src/main/res/drawable/figma/`: Redundant images already present in `res/drawable`.
> - `app/src/main/res/drawable/Genre/`: Contains a ZIP file and images with invalid names (spaces) in subfolders. These are not referenced in the code and are causing build errors.
> - `app/src/main/res/Fon/` and `app/src/main/res/Font/`: Invalid/empty resource directories.
> - `app/src/main/font/`: Invalid directory outside of `res/`.

## Proposed Changes

### [Resource Cleanup]

I will remove all invalid resource directories and files to satisfy the AAPT2 resource merger.

#### [DELETE] `app/src/main/res/assets/`
#### [DELETE] `app/src/main/res/drawable/figma/`
#### [DELETE] `app/src/main/res/drawable/Genre/`
#### [DELETE] `app/src/main/res/Fon/`
#### [DELETE] `app/src/main/res/Font/`
#### [DELETE] `app/src/main/font/`

## Verification Plan

### Automated Tests
- Run `./gradlew :app:mergeDebugResources` to verify the error is resolved.
- Run `./gradlew assembleDebug` to ensure the project builds successfully.

### Manual Verification
- Verify that the app still displays categories in the `HomeFragment` correctly, as it uses the valid drawables in `app/src/main/res/drawable/`.
