package com.example.safe


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safe.data.Password
import com.example.safe.data.PasswordDatabase
import kotlinx.android.synthetic.main.fragment_password_list.*
import kotlinx.android.synthetic.main.recycler_item.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.FieldPosition



class PasswordListFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var pViewModel: NewPasswordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pViewModel = ViewModelProviders.of(this).get(NewPasswordViewModel::class.java)
        pViewModel.retrievePasswords().observe(this,  Observer {

            Timber.i("received the passwords ${it.size}")

            rvList.adapter = PasswordRecyclerAdapter(it)

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAdd.setOnClickListener {

            val dao =  PasswordDatabase.getInstance(this.context!!)?.passwordDao()
            GlobalScope.launch {
                dao?.getAll()
            }

            listener?.goToNewPasswordFragment()
        }
        rvList.layoutManager = LinearLayoutManager(activity)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        fun goToNewPasswordFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PasswordListFragment()
    }

}