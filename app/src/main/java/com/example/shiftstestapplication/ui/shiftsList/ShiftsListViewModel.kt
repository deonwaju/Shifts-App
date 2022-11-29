package com.example.shiftstestapplication.ui.shiftsList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shiftstestapplication.data.responses.Shift
import com.example.shiftstestapplication.domain.usecase.ShiftUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Gideon Olarewaju on 29/11/2022.
 */

@HiltViewModel
class ShiftsListViewModel @Inject constructor(
    private val shiftUsecase: ShiftUsecase,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    init {
        getLists()
    }
    var shiftList = mutableStateOf<List<Shift>>(listOf())
    private var cachedShiftList = listOf<Shift>()

    private fun getLists() {
        viewModelScope.launch(dispatcher) {
            shiftUsecase.getShifts().collect{
                shiftList.value = it
            }
        }
    }

}