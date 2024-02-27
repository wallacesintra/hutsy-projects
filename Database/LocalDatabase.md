# **Save data in a local database using Room**
**Setup**

```kotlin
dependencies {
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$room_version")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$room_version")

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation("androidx.room:room-guava:$room_version")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$room_version")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")
}
```

## **Primary components in Room**
* Database class - holds the database, the main access point for connection to the app's persistent data.
* Data entities - represent tables in the app database
* Data access objects(DAOs) - provide methods that app can use to query, update, insert, and delete data in the database.

### **Data entity**
User data entity. Each instance of User represents a row in the user table in the app's database.

```kotlin
@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)
```

### **Data access object**
DAO - provides the methods the app uses to interact with data in the user table.

```kotlin
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
           "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}
```

### **Database**
define AppDatabase class to hold the database.
AppDatabase defines configuration and the app's main access point to persistent data.

Database class must :
1. be annotated with a @Database that includes an entities array that lists all of the data entities associated with the database.

2. class must be an abstract class that extends RoomDatabase.

3. For each DAO class that is associated with the db, db class must define an abstract method that has zero arguments & returns an instance of the DAO class.

```kotlin
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
```



*
Note: If your app runs in a single process, you should follow the singleton design pattern when instantiating an AppDatabase object. Each RoomDatabase instance is fairly expensive, and you rarely need access to multiple instances within a single process.

If your app runs in multiple processes, include enableMultiInstanceInvalidation() in your database builder invocation. That way, when you have an instance of AppDatabase in each process, you can invalidate the shared database file in one process, and this invalidation automatically propagates to the instances of AppDatabase within other processes.*


**Usage**

```kotlin
val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
```

use abstract methods from the AppDatabase to get instance of the DAO. you can use the methods from the DAO instance to interact with the database:

```kotlin
val userDao = db.userDao()
val users: List<User> = userDao.getAll()
```


# **Define data using Room entities*

define Room entity annotate the class with @Entity.

```kotlin
@Entity
data class User(
    @PrimaryKey val id: Int,

    val firstName: String?,
    val lastName: String?
)
```
tableName property of the @Entity sets the table name.
add @ColumnInfo annotation and set the name property.

```kotlin
@Entity(tableName = "Users")
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)
```

## define a composite primary key

```kotlin
@Entity(primaryKeys = ["firstName", "lastName"])
data class User(
    val firstName: String,
    val lastName: String
)
```

## ignore fields
use @Ignore if you dont want to persist a field in an entity.

```kotlin
@Entity
data class User(
    @PrimaryKey val id: Int,
    val firstName: String?,
    val lastName: String?,
    @Ignore val picture: Bitmap?
)
```

in cases the entity inherits fields from a parent entity, use ignoredColumns property of the @Entity attritube.

```kotlin
open class User {
    var picture: Bitmap? = null
}

@Entity(ignoredColumn = ["picture"])
data class RemoteUser(
    @PrimaryKey val id: Int,
    val hasVpn: Boolean
) : User()
```

