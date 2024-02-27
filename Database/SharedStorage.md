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

### **Media location permission**
In android 10&higher, needs to retrieve unredacted EXIF metadata from the photos, you need to declare the [ACCESS_MEDIA_LOCATION] permission in the app's manifest, then request this permission at runtime.


## **Check for updates to media store**
call getVersion(). the returned version is unique string that changes whenever the media store changes substantially.

If the returned version is different from the last synced version, rescan & resync app's media cache.

## **Query a media collection**
To find media that satisfies a particular set of conditions.

```kotlin
// Need the READ_EXTERNAL_STORAGE permission if accessing video files that your
// app didn't create.

// Container for information about each video.
data class Video(val uri: Uri,
    val name: String,
    val duration: Int,
    val size: Int
)
val videoList = mutableListOf<Video>()

val collection =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Video.Media.getContentUri(
            MediaStore.VOLUME_EXTERNAL
        )
    } else {
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    }

val projection = arrayOf(
    MediaStore.Video.Media._ID,
    MediaStore.Video.Media.DISPLAY_NAME,
    MediaStore.Video.Media.DURATION,
    MediaStore.Video.Media.SIZE
)

// Show only videos that are at least 5 minutes in duration.
val selection = "${MediaStore.Video.Media.DURATION} >= ?"
val selectionArgs = arrayOf(
    TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES).toString()
)

// Display videos in alphabetical order based on their display name.
val sortOrder = "${MediaStore.Video.Media.DISPLAY_NAME} ASC"

val query = ContentResolver.query(
    collection,
    projection,
    selection,
    selectionArgs,
    sortOrder
)
query?.use { cursor ->
    // Cache column indices.
    val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
    val nameColumn =
            cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
    val durationColumn =
            cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
    val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)

    while (cursor.moveToNext()) {
        // Get values of columns for a given video.
        val id = cursor.getLong(idColumn)
        val name = cursor.getString(nameColumn)
        val duration = cursor.getInt(durationColumn)
        val size = cursor.getInt(sizeColumn)

        val contentUri: Uri = ContentUris.withAppendedId(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            id
        )

        // Stores column values and the contentUri in a local object
        // that represents the media file.
        videoList += Video(contentUri, name, duration, size)
    }
}
```


## **Load file thumbnail**
show multiple media files and request user to select one of these files.

use loadThumbnail() and pass in the size of the thumbnail:

```kotlin
// Load thumbnail of a specific media item.
val thumbnail: Bitmap =
        applicationContext.contentResolver.loadThumbnail(
        content-uri, Size(640, 480), null)

```

## **Open media file**
### File descriptor
open media file use a file descriptor.

```kotlin
// Open a specific media item using ParcelFileDescriptor.
val resolver = applicationContext.contentResolver

// "rw" for read-and-write.
// "rwt" for truncating or overwriting existing file contents.
val readOnlyMode = "r"
resolver.openFileDescriptor(content-uri, readOnlyMode).use { pfd ->
    // Perform operations on "pfd".
}
```

### File stream
using file stream

```kotlin
// Open a specific media item using InputStream.
val resolver = applicationContext.contentResolver
resolver.openInputStream(content-uri).use { stream ->
    // Perform operations on "stream".
}

```

### Direct file paths
Use either:
1. File API
2. Native libraries, ie fopen()

## **Consideration when accessing media content**


# **Photo Picker**
it provides browsable, searchable interface for user to select arranged from the newest to the oldest.

## Use Jetpack Activity contracts
* PickVisualMedia - select a single image/video
* PickMultipleVisualMedia - select multiple images/videos

### **select a single media item**
use PickVisualMedia

```kotlin
// Registers a photo picker activity launcher in single-select mode.
val pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
    // Callback is invoked after the user selects a media item or closes the
    // photo picker.
    if (uri != null) {
        Log.d("PhotoPicker", "Selected URI: $uri")
    } else {
        Log.d("PhotoPicker", "No media selected")
    }
}

// Include only one of the following calls to launch(), depending on the types
// of media that you want to let the user choose from.

// Launch the photo picker and let the user choose images and videos.
pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageAndVideo))

// Launch the photo picker and let the user choose only images.
pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))

// Launch the photo picker and let the user choose only videos.
pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.VideoOnly))

// Launch the photo picker and let the user choose only images/videos of a
// specific MIME type, such as GIFs.
val mimeType = "image/gif"
pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.SingleMimeType(mimeType)))
```

### **Select multiple media items**

```kotlin
// Registers a photo picker activity launcher in multi-select mode.
// In this example, the app lets the user select up to 5 media files.
val pickMultipleMedia =
        registerForActivityResult(PickMultipleVisualMedia(5)) { uris ->
    // Callback is invoked after the user selects media items or closes the
    // photo picker.
    if (uris.isNotEmpty()) {
        Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
    } else {
        Log.d("PhotoPicker", "No media selected")
    }
}

// For this example, launch the photo picker and let the user choose images
// and videos. If you want the user to select a specific type of media file,
// use the overloaded versions of launch(), as shown in the section about how
// to select a single media item.
pickMultipleMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageAndVideo))
```

## Device availability
- android 11 and higher
- receive changes to Modular system Components through Google System Updates

Android 10 & lower can install a backported version of photo picker. Automatic installation of the backported photo picker module through Google Play services, add the following entry to the <application> tag in the app manifest file:

```kotlin
<!-- Trigger Google Play services to install the backported photo picker module. -->
<service android:name="com.google.android.gms.metadata.ModuleDependencies"
         android:enabled="false"
         android:exported="false"
         tools:ignore="MissingClass">
    <intent-filter>
        <action android:name="com.google.android.gms.metadata.MODULE_DEPENDENCIES" />
    </intent-filter>
    <meta-data android:name="photopicker_activity:0:required" android:value="" />
</service>
```


## persist media file access
By default, the system grants your app access to media files until the device is restarted or until your app stops. If your app performs long-running work, such as uploading a large file in the background, you might need this access to be persisted for a longer period of time. To do so, call the takePersistableUriPermission() method:

```kotlin
val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
context.contentResolver.takePersistableUriPermission(uri, flag)
```
