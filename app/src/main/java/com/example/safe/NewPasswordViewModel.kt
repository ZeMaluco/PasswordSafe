package com.example.safe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.safe.data.Password
import com.example.safe.data.PasswordDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val mDb: PasswordDatabase? = PasswordDatabase.getInstance(application)

    private val allPasswords = MutableLiveData<List<Password>>()
    private val currentPassword = MutableLiveData<List<Password>>()

    fun storePassword(website: String, password1:String, description:String) {

        val password = Password()
        password.website = website
        password.password = password1
        password.description = description

        GlobalScope.launch {
            mDb?.passwordDao()?.insert(password)
        }
    }

    fun retrievePasswords(): LiveData<List<Password>> {

        GlobalScope.launch {
            val list = mDb?.passwordDao()?.getAll()
            Timber.i("retrievePasswords list count ${list?.size}")
            allPasswords.postValue(list)
        }

        return allPasswords
    }

    fun DeletePassword(item: Password){
        GlobalScope.launch {
            mDb?.passwordDao()?.delete(item)
        }


    }
    fun getPassword(id: Int): LiveData<List<Password>> {
        GlobalScope.launch {
            val list = mDb?.passwordDao()?.getPassword(id)
            currentPassword.postValue(list)
        }
        return currentPassword
    }

    fun update(item: Password ){
        GlobalScope.launch {
            mDb?.passwordDao()?.update(item)
        }
    }

}
