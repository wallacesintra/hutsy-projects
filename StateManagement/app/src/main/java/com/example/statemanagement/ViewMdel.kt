package com.example.statemanagement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

//implementing ViewModel
data class DiceUiState(
    val firstDieValue: Int? = null,
    val secondDieValue: Int? = null,
    val numberOfRolls: Int = 0
)

class DiceRollViewModel: ViewModel(){
    //Expose screen UI state
    private val _uiState = MutableStateFlow(DiceUiState())

    val uiState: StateFlow<DiceUiState> = _uiState.asStateFlow()//accessing read only. exposes the current value of the _uiState property to the UI layer. any change made to _uiState will be reflected here.


    //handle business logic
    fun rollDice(){
        _uiState.update { currentState ->
    //update function updates _uiState, takes the current state as input and allows you to modify,
            //creates a copy of the current state, and updates the uistate data class properties.
            currentState.copy(
                firstDieValue = Random.nextInt(from = 1, until = 7),
                secondDieValue = Random.nextInt(from = 1, until = 7),
                numberOfRolls = currentState.numberOfRolls + 1,
            )
        }
    }
}

//access the ViewModel from an activity.

//import androidx.activity.viewModels
//
//class DiceRollActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        // Create a ViewModel the first time the system calls an activity's onCreate() method.
//        // Re-created activities receive the same DiceRollViewModel instance created by the first activity.
//
//        // Use the 'by viewModels()' Kotlin property delegate
//        // from the activity-ktx artifact
//        val viewModel: DiceRollViewModel by viewModels()
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.uiState.collect {
//                    // Update UI elements
//                }
//            }
//        }
//    }
//}


//accessing the ViewModel in a composable

//@Composable
//fun ComposableName(
//    nameViewModel: nameViewModel = viewModel()
//){
//    val nameUiState by nameViewModel.uiState.collectAsState() - gives access the read only UI state

//      {nameUiState.property} - accessing the UI element
//      {nameViewModel.publicFunction()} - accessing the business logic function.
//}

@Composable
fun DiceScreen(
    viewModel: DiceRollViewModel = viewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    //Update UI elements
}
