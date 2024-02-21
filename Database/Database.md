# **DATABASE**
__Relational Database__ are databases that has relationship between multiple tables.

Relational database has :
- tables - high level groupings of data.
- columns - define the data that each row in a table contains
- rows - contains the actual data that consists of values for each column in the table.

structure of a relational db is similar to classes & objects in kotlin.
```kotlin
data class Student(
    id: Int,
    name: String,
    major: String,
    gpa: Double
)
```

- classes, like tables, models the data you want to represent in the app.
- properties, like columns, define the specific pieces of data that every  instance of the class should contain.
- objects, like rows, are the actual data. they contain values for each property defined in the class.

# SQLite
it is lightweight C library for relational database management with SQL.

* Int - INTEGER 
* String - VARCHAR or TEXT
* Boolean - BOOLEAN
* Float, Double - REAL


# **Data and file storage**

**options to save app data**
* App-specific storage - only meant for the app.
    - it has a dedicated directory within an internal storage volume or 
    - different dedicated directories within external storage.

* Shared storage - stores files that the app will share with other apps, including media, documents, etc.

* Preference - stores private primitive data in a key-value pairs.

* Databases - store structured data in a private database using the Room persistence library.

**solution you choose depends on your specific needs**
* Space the data requires:
    internal storage has limited space for app-specific data, use other types of data storage.

* Reliability of the data:
    if the data is need for basic functtionality of the app, it should be stored in the internal storage directory/database.

* Kind of data you want to store:
    - data only meaningful to app - app-specific storage
    - shareable media - shared storage
    - structured data - preferences(for key-value data) 0r databases(for data that has more than 2 columns)

* Privacy of the data
    storing sensitive data use internal storage, preferences, or a database.


## Categories of Storage Location
* internal storage
* external storage ie SD card

## Permission and access to external storage
READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, and MANAGE_EXTERNAL_STORAGE.

[READ_EXTERNAL_STORAGE] - in earlier versions, permission to access any file outside app-specific directories on external storage.
[WRITE_EXTERNAL_STORAGE] - permission to write to any file outside the app-specific directory.

[MANAGE_EXTERNAL_STORAGE] -in Android 11 & higher, permission that provides write access to files outside the app-specific directory and **MediaStore**


**Scoped Storage**
To give users more control over their files and to limit file clutter, apps that target Android 10 (API level 29) and higher are given scoped access into external storage, or scoped storage, by default. Such apps have access only to the app-specific directory on external storage, as well as specific types of media that the app has created.


# Access app-specific files
App creates files that other app shouldn't or don't need to access.

system provides:
* **Internal storage directories**
They are for:
- storing persistent data
- storing cache data
system prevents other apps from accessing these locations, therefore they are good for storing sensitive data that only the app can access.

* **External Storage Directories**
- store persistent files
- store cache data
Another apps can access these directories if they have proper permissions


When the user uninstalls the app, files saved in app-specific storage are removed.


## **Access from internal storage**
for each app, the system provides directories within internal storage where an app can organize its files.
- one directory is designed for persistents files
- one directory contains cached files.
The app doesn't require any system permission to read and write to files in these directories.

these directories tend to be small. Before writing app-specific files to internal storage, your app should query the free space on the device.

### **Access persistent files**
access using [filesDir] property of a context objects

**Access and store files**
use File API to access and store files.

```kotlin
val file = File(context.filesDir, filename)
```


**store a file using a stream**
can call [openFileOutput()] to get a [FileOutputStream] that writes a file within the filesDir directory.

```kotlin
val filename = "myfile"
val fileContents = "Hello, its Sinatra!"
context.openFileOutput(filename, Context.MODE_PRIVATE).use {
    it.write(fileContents.toByteArray())
}
```

 On devices that run Android 7.0 (API level 24) or higher, unless you pass the Context.MODE_PRIVATE file mode into openFileOutput(), a SecurityException occurs.

To allow other apps to access files stored in this directory within internal storage, use a FileProvider with the FLAG_GRANT_READ_URI_PERMISSION attribute.

**Access a file using a stream**
to read a file as a stream, use [openFileInput()]

```kotlin
context.openFileInput(filename).bufferedReader().useLines { lines ->
        lines.fold("") {some, text ->
            "$some \n $text"
        }
}
```

**view list of files**
can get an array containing the names of all files within the filesDir directory by calling [fileList()]

```kotlin
var files: Array<String> = context.fileList()
```

**create nested directories**
- can create nested directories, open an inner directory, by calling 
[getDir()] in Kotlin-based code
```kotlin
context.getDir(dirName, Context.MODE_PRIVATE)
```


- by passing the root directory and a new directory named into a File construct in Java-based code
```java
File directory = context.getFilesDir();
File file = new File(directory, filename);
```


### **Create cache files**
to store sensitive data temporarily, use the app's designated cache directory in the internal storage to save the data.

[Note: This cache directory is designed to store a small amount of your app's sensitive data. To determine how much cache space is currently available for your app, call getCacheQuotaBytes().]

To create a cached file, call [File.createTempFile()]
```kotlin
File.createTempFile(filename, null, context.cacheDir)
```

To access a file in cache dir use [cacheDir] property of a context object and the File API.
```kotlin
val cacheFile = File(context.cacheDir, filename)
```

[Caution: When the device is low on internal storage space, Android may delete these cache files to recover space. So check for the existence of your cache files before reading them.]

### **Remove cache files**
use [delete()] method on a File object that represent the files.
```kotlin
cacheFile.delete()
```

use deleteFile() method of the app's context, passing in the name of the file.
```kotlin
context.deleteFile(cacheFileName)
```


## **Access from external storage**
System provides directories within the external storage to organize files.
* one dir for persistent files
* one dir for cached files

android 9/lower, app can access the app-specific files that belong to other apps if they have the appropriate permissions.

android 10/higher, users have more control over their files & limit file clutter, users are given scoped access into external storage. when scoped storage is enabled, the apps can't access the app-specific dir that belong to other apps.

### **verify that storage is available**
check if the external storage is available before reading or writing into it.
can query the volume's state by calling [Environment.getExternalStorageState()]:
- if returned state is [MEDIA_MOUNTED] - read and write app-specific files in external storage.
- if its [MEDIA_MOUNTED_READ_ONLY] - read only app-specific files in external storage.

```kotlin
// Checks if a volume containing external storage is available
// for read and write.
fun isExternalStorageWritable(): Boolean {
    return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
}

// Checks if a volume containing external storage is available to at least read.
fun isExternalStorageReadable(): Boolean {
     return Environment.getExternalStorageState() in
        setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
}
```


On devices without removable external storage, use the following command to enable a virtual volume for testing your external storage availability logic:
```kotlin
adb shell sm set-virtual-disk true
```

### **Select a physical storage location**
to access the different locations, call [ContextCompat.getExternalFilesDirs()] - it will return an array.

the first element is considered the primary external storage volume, use this volume unless its full/unavailable.

```kotlin
val externalStorageVolumes: Array<out File> =
        ContextCompat.getExternalFilesDirs(applicationContext, null)
val primaryExternalStorage = externalStorageVolumes[0]
```

[Note: If your app is used on a device that runs Android 4.3 (API level 18) or lower, then the array contains just one element, which represents the primary external storage volume.]

### **Access persistent files**
to access app-specific files from external storage, call [getExternalFilesDir()]

to help maintain app's performance, dont open & close the same file multiple times.

```kotlin
val appSpecificExternalDir = File(context.getExternalFilesDir(null), filename)
```

[Note: On Android 11 (API level 30) and higher, apps cannot create their own app-specific directory on external storage.]


### **Create cache files**
use [externalCacheDir]

```kotlin
val externalCacheFile = File(context.externalCacheDir, filename)
```

**Remove cache files**
use [delete()] on a File onject that represents the file.

```kotlin
externalCacheFile.delete()
```

### **Media Content**
if app uses media files, store them in app-specific directories within external storage.

```kotlin
fun getAppSpecificAlbumStorageDir(context: Context, albumName: String): File? {
    // Get the pictures directory that's inside the app-specific directory on
    // external storage.
    val file = File(context.getExternalFilesDir(
            Environment.DIRECTORY_PICTURES), albumName)
    if (!file?.mkdirs()) {
        Log.e(LOG_TAG, "Directory not created")
    }
    return file
}
```
It's important that you use directory names provided by API constants like DIRECTORY_PICTURES. These directory names ensure that the files are treated properly by the system. If none of the pre-defined sub-directory names suit your files, you can instead pass null into getExternalFilesDir(). This returns the root app-specific directory within external storage.

## **Query free space**
to find out how much space the device can provide the app, call [getAllocatableBytes()]

the return value of getAllocatableBytes() might be larger than the current amount of free space on the device. This is because the system has identified files that it can remove from other apps' cache directories.

if there is enough space to save your app's data, call [allocateBytes()]
otherwise request the user to remove some files/remove all cache files from the device.

```kotlin
// App needs 10 MB within internal storage.
const val NUM_BYTES_NEEDED_FOR_MY_APP = 1024 * 1024 * 10L;

val storageManager = applicationContext.getSystemService<StorageManager>()!!
val appSpecificInternalDirUuid: UUID = storageManager.getUuidForPath(filesDir)
val availableBytes: Long =
        storageManager.getAllocatableBytes(appSpecificInternalDirUuid)
if (availableBytes >= NUM_BYTES_NEEDED_FOR_MY_APP) {
    storageManager.allocateBytes(
        appSpecificInternalDirUuid, NUM_BYTES_NEEDED_FOR_MY_APP)
} else {
    val storageIntent = Intent().apply {
        // To request that the user remove all app cache files instead, set
        // "action" to ACTION_CLEAR_APP_CACHE.
        action = ACTION_MANAGE_STORAGE
    }
}
```
[Note: You aren't required to check the amount of available space before you save your file. You can instead try writing the file right away, then catch an IOException if one occurs. You may need to do this if you don't know exactly how much space you need. For example, if you change the file's encoding before you save it by converting a PNG image to JPEG, you don't know the file's size beforehand.]

**Create a storage management activity**
you can declare custom manage space activity using and [android:manageSpaceActivity] attribute in the manifest file

 File manager apps can invoke this activity even when your app doesn't export the activity; that is, when your activity sets android:exported to false.

**Ask user to remove some device files**
invoke an intent that includes the [ACTION_MANAGE_STORAGE] action. 
This intent displays a promps to the user, can show the amount of free space available on the device.

```kotlin
StorageStatsManager.getFreeBytes() / StorageStatsManager.getTotalBytes()
```

**Ask user to remove all cache files**
can request the user to clear the cache files from all apps on the devices.
invoke an intent that includes the [ACTION_CLEAR_APP_CACHE] intent action.

[Caution: The ACTION_CLEAR_APP_CACHE intent action can substantially affect device battery life and might remove a large number of files from the device.]