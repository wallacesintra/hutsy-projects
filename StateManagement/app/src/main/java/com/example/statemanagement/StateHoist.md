# Where to hoist state
hoist to lowest common ancestor btn all composables that read and write it.

## UI state: 
It is property that describe the UI.
**types of UI state:**
1. Screen UI State - what you need to display on the screen.
2. UI element state - properties intrinsic to UI elements that influence how they are rendered.


## Logic
1. Business Logic - implementation of product requirement for app data. eg bookmarking an article in news reader app when user taps a button.
2. UI Logic - how to display UI state on the screen.


### UI Logic
composable as state owner, if the logic is simple.
no state hoisting needed, state can be kept internal in a composable when no other composable need to control it.

Hoisting within composables, if you need to share UI element state with other composables and apply UI logic to it in different places,
you can hoist it higher in the UI hierarchy. this makes your composables more reusable and easier to test.

_Plain state holder class as state owner_
State holder holds complex UI logic that involves one/multiple state fields of a UI element.


### Business Logic
screen level state holder is in charge:
* Providing access to the business logic of the application that is usually placed in other layers of the hierarchy such as the business and data layers.
* Preparing the application data for presentation in a particular screen, which becomes the screen UI state.

ViewModel as state owner
[ViewModel] - implementation detail of state holder with certain responsibilities. 