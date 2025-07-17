package com.example.suitmediatestapp.ui.thirdactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediatestapp.data.api.ApiConfig
import com.example.suitmediatestapp.data.api.ApiService
import com.example.suitmediatestapp.data.repository.UserRepository
import com.example.suitmediatestapp.data.response.DataItem
import com.example.suitmediatestapp.data.response.Response
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _listUser = MutableLiveData<List<DataItem>>()
    val listUser: LiveData<List<DataItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getDataUsers(page : Int = 2){
        _isLoading.value = true
        viewModelScope.launch{
            try {
                val response = userRepository.getUsers(page)
                _listUser.value = response.data?.filterNotNull() ?: emptyList()
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }

    }
}