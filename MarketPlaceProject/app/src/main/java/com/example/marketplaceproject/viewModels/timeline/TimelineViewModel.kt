package com.example.marketplaceproject.viewModels.timeline

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketplaceproject.models.User
import com.example.marketplaceproject.repository.Repository

class TimelineViewModel(val context: Context, val repository: Repository): ViewModel() {
    var token: MutableLiveData<String> = MutableLiveData()
    var user = MutableLiveData<User>()
}