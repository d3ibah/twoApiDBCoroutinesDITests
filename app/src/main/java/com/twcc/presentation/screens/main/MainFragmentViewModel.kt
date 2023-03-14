package com.twcc.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.twcc.domain.models.UserDomain
import com.twcc.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(private val getDataUseCase: GetUsersUseCase) : ViewModel() {

    private val _getDataLiveData = MutableLiveData<List<UserDomain>>()
    internal val getDataLiveData: LiveData<List<UserDomain>> = _getDataLiveData

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _getDataLiveData.postValue(getDataUseCase.execute())
        }
    }

    fun onItemClicked(user: UserDomain) {
//      TODO: Add navigation
    }
}