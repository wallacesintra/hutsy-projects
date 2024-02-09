# State
state is any value that changes over time.

[Composition] - description of UI built by Jetpack Compose when it executes composable.

Initial Composition - creation of a composition by running composable the first time
Recomposition - re-running composable to update the composition when data changes.



Composable function can use [remember] API to store an object in memory.
[remember] can be used to store both mutable and immutable objects.

Note: remember stores objects in the Composition, and 
forgets the object when the composable that called remember 
is removed from the Composition.

ways of declaring **MutableState** object in a composable:
1. val mutableState = remember { mutableStateOf(default)}
2. var value by remember {mutableStateOf(default)}
3. val (value, setValue) = remember {mutableStateOf(default)}


[remember] retains state across recompositions but not across configuration changes. You 
must use [rememberSavable]


**Stateful** - composables that uses remember to store object.

**Stateless** - composables that doesn't hold any state, it uses _state hoisting_



# State Hoisting
State hoisting is a pattern of moving state to a composable's caller to make a composable stateless.

It is achieved by replacing the state variable with two parameter:
    * value: T - the current value to display.
    * onValueChange: (T) -> Unit - event that requests the value to change, where T is the proposed new value.

`@Composable
fun HelloScreen() {
var name by rememberSaveable { mutableStateOf("") }

    HelloContent(name = name, onNameChange = { name = it })
}

@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
Column(modifier = Modifier.padding(16.dp)) {
Text(
text = "Hello, $name",
modifier = Modifier.padding(bottom = 8.dp),
style = MaterialTheme.typography.bodyMedium
)
OutlinedTextField(value = name, onValueChange = onNameChange, label = { Text("Name") })
}
}`

## Rules when state hoisting
1. State should be hoisted to at _lowest common parent_ of all composable that use the state(read).
2. State should be hoisted to at least the _highest level it may be changed_(write).
3. If two states change in response to the same events they should be hoisted together.

## Ways to store state
1. Parcelize
2. MapSaver
3. ListSaver


# State holder in Compose
[State holders] - manage logic and state of composables.