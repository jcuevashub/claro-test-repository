package com.example.clarotest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clarotest.util.Resource
import com.example.clarotest.domain.models.EntryDetails
import com.example.clarotest.domain.use_cases.GetEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getEntriesUseCase: GetEntriesUseCase
) : ViewModel() {
    private val _entries = MutableStateFlow<Resource<List<EntryDetails>>>(Resource.Loading())
    val entries = _entries.asStateFlow()

    init {
        getAllEntries()
    }

    fun getAllEntries() {
        viewModelScope.launch {
            getEntriesUseCase().collect {
                _entries.value = it
            }
        }
    }
}