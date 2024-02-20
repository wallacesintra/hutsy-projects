# **DATABASE**
__Relational Database__ are databases that has relationship between multiple tables.

Relational database has :
- tables - high level groupings of data.
- columns - define the data that each row in a table contains
- rows - contains the actual data that consists of values for each column in the table.

structure of a relational db is similar to classes & objects in kotlin.
`data class Student(
    id: Int,
    name: String,
    major: String,
    gpa: Double
)`

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

