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


# **Accessing data using Room DAOs*

define each DAO as either an interface/abstract class
always annotate DAOs with @Dao

```kotlin
@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delect(user: User)

    @Query("SELECT * FROM user")
    fun getAll(): List<User>
}
```

## types of DAO methods
### 1. Convenience methods
perform simple insertion, update, and deletion without writing SQL statement.

**Insert**
@Insert allows to define methods that insert parameters into the table in the db.

```kotlin
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: User)

    @Insert
    fun insertBothusers(user1: User, user2: User)

    @Insert
    fun insertUsersAndFriends(user: User, friends: List<User>)
}
```

**Update**
@Update annotation lets you define methods that update specific rows in the db table.

```kotlin
@Dao
interface UserDao {
    @Update
    fun updateUsers(vararg users: User)
}
```

**Delete**
@Delete

```kotlin
@Dao
interface UserDao {
    @Delete
    fun deleteUsers(vararg users: User)
}
```

### 2. Query methods
@Query annotation - write SQL statement as expose them as DAO methods.
can use it to perform more complex insertion, updates, and deletion.

```kotlin
@Query("SELECT * FROM user")
fun loadAllUser(): Array<User>
```

```kotlin
data class NameTuple(
    @ColumnInfo(name = "first_name") val firstName: String?,
    @Column(name = "last_name") val lastName: String?
)

// query method
@Query("SELECT first_name, last_name FROM user")
fun loadFullName(): List<NameTuple>
```

**pass simple parameters to a query**
DAO methods accept parameter to filter operations.

```kotlin
@Query("SELECT * FROM user WHERE age > :minAge")
fun loadAllUsersOlderThan(minAge: Int): Array<User>
```

```kotlin
@Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
fun loadAllUsersBetweenAges(minAge: Int, maxAge: Int): Array<User>

@Query("SELECT * FROM user WHERE first_name LIKE :search" +
        "OR last_name LIKE :search")
fun findUserWithName(search: String): List<User>
```

**pass a collection of parameters to a query**

```kotlin
@Query("SELECT * FROM user WHERE region IN (:regions)")
fun loadUsersFromRegions(regions: List<String>): List<User>
```

**Return a multimap**
In Room 2.4 and higher, can query columns from multiple tables without defining an additional data class by writing query methods that return a multimap.

```kotlin
@Query(
    "SELECT * FROM user" +
    "JOIN book ON user.id = book.user_id"
)
fun loadUserAndBookNames(): Map<User, List<Book>>
```

```kotlin
@Query(
    "SELECT * FROM user" +
    "JOIN book ON user.id = book.user_id" +
    "GROUP BY user.name WHERE COUNT(book.id) >= 3"
)
fun loadUserAndBookNames(): Map<User, List<Book>>
```


### Special return types

**Paginated queries with the Paging library**
Room support paginated queries through intergration with the Paging library.

```kotlin
@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE label LIKE :query")
    fun pagingSource(query: String): PagingSource<Int, User>
}
```

**Direct cursor access**
DAO methods can return a Cursor object.

```kotlin
@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE age > :minAge LIMIT 5")
    fun loadRawUsersOlderThan(minAge: Int): Cursor
}
```


# **Define relationship between objects**
define relationships using: 

1. ### **Intermediate data class**
define a data class that models the relational btn the Room entities. The data class holds the pairings between instances of one entity and instances of another entity as embedded objects.

```kotlin
@Dao
interface UserBookDao {
    @Query(
        "SELECT user.name AS userName, book.name AS bookName " +
        "FROM user, book " +
        "WHERE user.id = book.user_id"
    )
    fun loadUserAndBookNames(): LiveData<List<UserBook>>
}

data class UserBook(val userName: String?, val bookName: String?)
```
2. ### **Multimap return types**
define a multimap return type methods based on the map structure that you want and define the relationship between your entities directly in the SQL query.

```kotlin
@Query(
    "SELECT * FROM user" +
    "JOIN book ON user.id = book.user_id"
)
fun loadUserAndBookNames(): Map<User, List<Book>>
```

## **Create embedded objects**
@Embedded annaotation - represent an object that you'd like to decompose into its subfields within a table.

```kotlin
data class Address(
    val street: String?,
    val state: String?,
    val city: String?,
    @ColumnInfo(name = "post_code") val postCode: Int
)

@Entity
data class User(
    @PrimaryKey val id: Int,
    val firstName: String?,
    @Embedded val address: Address?
)
```

the table representing a User object then contains columns with names: id, firstName, street, state, city, and post_code

## **define one-to-one relationships**
is a relationship where each instance of the parent entity corresponds to exactly one instance of the child entity, and the reverse is also true.

example: a music streaming app where the user has a library of songs that they own. Each user has only one library, and each library corresponds to exactly one user.

1. first define a class for entities, one of the entities must include a variable that is a reference to the primary key of the other entity.

```kotlin
@Entity
data class User(
    @PrimaryKey val userId: Long,
    val name: String,
    val age: Int
)

@Entity
data class Library(
    @PrimaryKey val libraryId: Long,
    val userOwnerId: Long
)
```
2. model one-to-one relationship between the two entities, create a new data class where each instance holds an instance of the parent entity and the corresponding instance of the child entity.
add the @Relation annotation to the instance of the child entity, with parentColumn set to the primary key column of the parent entity and entityColumn set to the name of the column of the child entity that reference the parent entity's primary key.

```kotlin
data class UserAndLibrary(
    @Embedded val user: User,
    @Relation(
         parentColumn = "userId",
         entityColumn = "userOwnerId"
    )
    val library: Library
)
```

3. add a method to the DAO class that returns all instances of the data class that pairs the parent entity and the child entity. add the @Transaction annotation to this method so that the whole operation is performed atomically.

```kotlin
@Transaction
@Query("SELECT * FROM User")
fun getUsersAndLibraries(): List<UserAndLibrary>
```

## **define one-to-many relationship**
is a relationship where each instance of the parent entity corresponds to zero/more instances of the child entity, but each instance of the child entity can only correspond to exactly one instance of the parent entity.

example: music streaming app, the user has the ability to organize their songs into playlists. Each user can create as many playlists as they want, but each playlist is created by exactly one user. Therefore, there is a one-to-many relationship between the User entity and the Playlist entity.


```kotlin
@Entity
data class User(
    @PrimaryKey val userId: Long,
    val name: String,
    val age: Int
)

@Entity
data class Playlist(
    @PrimaryKey val playlistId: Long,
    val userCreatorId: Long,
    val playlistName: String
)
```

@Relation annotation to instance of child entity, with parentColumn set to the name of the primary key column of the parent entity and entityColumn set to name of the column of the child entity that references the parent entity's primary key.

```kotlin
data class UserWithPlaylists(
    @Embedded val user: User,
    @Relation(
          parentColumn = "userId",
          entityColumn = "userCreatorId"
    )
    val playlists: List<Playlist>
)
```


@Transaction annotation to DAO method so that the whole operation is performed atomically.

```kotlin
@Transaction
@Query("SELECT * FROM User")
fun getUsersWithPlaylists(): List<UserWithPlaylists>
```

## **define many-to-many relationship**
is a relationship where each instance of the parent entity corresponds to zero/more instances of the child entity, the reverse is also true.

example: In the music streaming app example, consider the songs in the user-defined playlists. Each playlist can include many songs, and each song can be a part of many different playlists. Therefore, there is a many-to-many relationship between the Playlist entity and the Song entity.

1. create a class for the entities. 
2. create an associate entity or cross-reference table among the other entities, cross-reference table must have columns for the primary key from each entity in the many-to-many relationship represented in the table.

```kotlin
@Entity
data class Playlist(
    @PrimaryKey val playlistId: Long,
    val playlistName: String
)

@Entity
data class Song(
    @PrimaryKey val songId: Long,
    val songName: String,
    val artist: String
)

@Entity(primaryKeys = ["playlistId", "songId"])
data class PlaylistSongCrossRef(
    val playlistId: Long,
    val songId: Long
)
```

3. depend on how you want to query these related entities.
    * If you want to query playlists and a list of the corresponding songs for each playlist, create a new data class that contains a single Playlist object and a list of all of the Song objects that the playlist includes.
    * If you want to query songs and a list of the corresponding playlists for each, create a new data class that contains a single Song object and a list of all of the Playlist objects in which the song is included.


4. model the relationship between the entities by using associateBy property in the @Relation annotation in each of these classes to identify the cross-reference entity providing the relationship btn the entities.

```kotlin
data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(
         parentColumn = "playlistId",
         entityColumn = "songId",
         associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val songs: List<Song>
)

data class SongWithPlaylists(
    @Embedded val song: Song,
    @Relation(
         parentColumn = "songId",
         entityColumn = "playlistId",
         associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val playlists: List<Playlist>
)
```

5. add method to DAO class to expose the query functionality the app needs.

```kotlin
@Transaction
@Query("SELECT * FROM Playlist")
// returns all the resulting PlaylistWithSongs objects.
fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

@Transaction
@Query("SELECT * FROM Song")
// returns all the resulting SongWithPlaylists objects.
fun getSongsWithPlaylists(): List<SongWithPlaylists>
```


## **define nested relationship**
 query a set of three or more tables that are all related to each other. In that case, you define nested relationships between the tables.

 example: in the music streaming app example, you want to query all the users, all the playlists for each user, and all the songs in each playlist for each user. Users have a one-to-many relationship with playlists, and playlists have a many-to-many relationship with songs. 


# **Write asynchronous DAO queries**
write DAO methods asynchronous to prevent queries from blocking the UI.
