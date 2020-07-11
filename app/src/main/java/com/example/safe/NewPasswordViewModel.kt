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
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val mDb: PasswordDatabase? = PasswordDatabase.getInstance(application)

    private val allPasswords = MutableLiveData<List<Password>>()
    private val currentPassword = MutableLiveData<List<Password>>()

    fun storePassword(website: String, password1:String, description:String) {

        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")


        val now = System.currentTimeMillis()

        val calendar: Calendar = Calendar.getInstance()
        calendar.setTimeInMillis(now)
        val currentDate = formatter.format(calendar.getTime())

        val password = Password()
        password.website = website
        password.password = password1
        password.description = description
        password.date = currentDate




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
            item.date = System.currentTimeMillis().toString()
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

    fun update(item: Password){
        GlobalScope.launch {
            val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")


            val now = System.currentTimeMillis()

            val calendar: Calendar = Calendar.getInstance()
            calendar.setTimeInMillis(now)
            val currentDate = formatter.format(calendar.getTime())

            item.date = currentDate

            mDb?.passwordDao()?.update(item)
        }
    }

}
