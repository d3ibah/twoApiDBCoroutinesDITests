package com.twcc.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.twcc.data.db.Database
import com.twcc.data.repository.UserRepositoryImpl
import com.twcc.data.storage.UserDbStorageImpl
import com.twcc.data.storage.UserNetworkStorageImpl
import com.twcc.domain.models.UserDomain
import com.twcc.domain.usecase.GetUsersUseCase
import com.twcc.presentation.TwApp
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

    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY]) as TwApp
                val repository by lazy {
                    UserRepositoryImpl(
                        UserNetworkStorageImpl(), UserDbStorageImpl(
                            Database.getDb(
                                application
                            )
                        )
                    )
                }
                val getDataUseCase by lazy { GetUsersUseCase(repository) }

                MainFragmentViewModel(getDataUseCase)
            }
        }
    }
}