package com.example.safe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class MainActivity : AppCompatActivity(), NewPasswordFragment.OnFragmentInteractionListener,
    PasswordListFragment.OnFragmentInteractionListener{
    private var web= ""
    private var pass= ""
    private var desc= ""
    private var id = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        if (savedInstanceState == null) {
            goToPasswordListFragment()
        }
    }

    override fun goToNewPasswordFragment() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.flContent, NewPasswordFragment.newInstance(id,web,pass,desc))
        transaction.commit()
    }


    override fun goToPasswordListFragment() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.flContent, PasswordListFragment.newInstance())
        transaction.commit()
    }
}