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

Int - INTEGER
String - VARCHAR or TEXT
Boolean - BOOLEAN
Float, Double - REAL