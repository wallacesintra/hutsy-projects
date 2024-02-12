# ViewModel 
It is class for business logic / screen level state holder.
It exposes state to the UI and encapsulates related business logic.

**Benefits of ViewModels.**
1. allows you to persist UI state, you don't have fetch everytime after common configuration changes.[caching]
2. provides access to business logic, it will be in charge of handling events and delegating them to other layers
of the hierarchy when the business logic needs to be applied to modify application data.

### **Scope**

[ViewModelStoreOwner] - interface
to instantiate a ViewModel, you pass it an object that implements the ViewModelStoreOwner interface.
It can be Navigation destination, navigation graph, activity, fragment or any other type that implement the interface.

Your ViewModel is then scoped to the LifeCycle of the ViewModelStoreOwner. It remains in memory until its ViewModelStoreOwner goes permanently.


**SavedStateHandle**
It allows you to persist data through configuration changes and process recreation. the UI state will be intact
even after the user closes the app.


## **Jetpack Compose**
In Jetpack Compose, ViewModel is mainly used to expose screen UI state to composables.



