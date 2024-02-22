# Shared Storage
use shared storage when user data can/should be used by other apps even if the app is uninstalled.

Android provides APIs for storing and accessing:
1. Media Content - MediaStore API
2. Documents and other files - use the platform's Storage Access Framework.
3. Datasets - for android 11 & higher, use BlobStoreManager API

### **Access media files from shared storage**
media store helps to users to retrieve and update media files.

[Note: If your app works with media files that provide value to the user only within your app, it's best to store them in app-specific directories within external storage.]

### **Photo picker**
alternative to media store, Android photo picker tool where the user can access media files without having access to the entire media library.

Only available in supported devices.


## **Media Store**
interact with media store abstraction, use a ContextResolver object that you retrieve from app's context:

```kotlin
val projection = arrayOf(media-database-columns-to-retrieve)
val selection = sql-where-clause-with-placeholder-variables
val selectionArgs = values-of-placeholder-variables
val sortOrder = sql-order-by-clause

applicationContext.contentResolver.query(
    MediaStore.media-type.Media.EXTERNAL_CONTENT_URI,
    projection,
    selection,
    selectionArgs,
    sortOrder
)?.use { cursor ->
    while (cursor.moveToNext()) {
        // Use an ID column from the projection to get
        // a URI representing the media item itself.
    }
}
```

system scans an external storage volume & adds media files to:
* Images (photographs, screenshots) stored in the DCIM/ and Pictures/ directories. The system will these files to the [MediaStore.Images] table.

* Videos stored in the DCIM/, Movies/ and Pictures/ directories. The system add these files to the [MediaStore.Video] table.

* Audio files stored in the Alarms/, Audiobooks/, Music/, Notifications/, Podcasts/, and Ringtones/ directories. The system adds these files to the [MediaStore.Audio] table.

* Downloaded files stored in the Downloads/ directory. The system add these files in [MediaStore.Downloads] table. unavailable for android 9 and lower.


## **Request necessary permissions**
make sure app has the permission to access the files before perform operations.

**Access your own media files**
android 10/higher, dont need permission to access files created the app.

**Access other apps' media files**
must declare the appropriate storage-related permission, and files must be in the media collections:
* MediaStore.Images
* MediaStore.Video
* MediaStore.Audio

```kotlin
<!-- Required only if your app needs to access images or photos
     that other apps created. -->
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />

<!-- Required only if your app needs to access videos
     that other apps created. -->
<uses-permission android:name="android.permission.READ_MEDIA_VIDEO" />

<!-- Required only if your app needs to access audio files
     that other apps created. -->
<uses-permission android:name="android.permission.READ_MEDIA_AUDIO" />

<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
                 android:maxSdkVersion="29" />
```


on andriod 9 & lower,
 you must request the [READ_EXTERNAL_STORAGE] permission to access media files
 [WRITE_EXTERNAL_STORAGE] permission to modify media files.

### **Storage Access Framework required for accessing other apps downloads**
must use Storage Access Framework

## **Media location permission**
