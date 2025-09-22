package com.rizzard23.kuizu.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class QuizzesViewModel : ViewModel() {

    val counter  = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                counter.value++
            }
        }
    }

}