package com.example.safe


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_new_password.*


/**
 * A simple [Fragment] subclass.
 */
class NewPasswordFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var pViewModel: NewPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        pViewModel = ViewModelProviders.of(this).get(NewPasswordViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_password, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button.setOnClickListener {
            val sWebsite = editTextWeb.text.toString().trim()
            val sPassword = editTextPass.text.toString().trim()
            val sDesc = editTextDesc.text.toString().trim()

            if (sWebsite.isEmpty()) {
                Toast.makeText(activity, "Website required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (sWebsite.isEmpty()) {
                Toast.makeText(activity, "Password required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (sDesc.isEmpty()) {
                Toast.makeText(activity, "Description required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            pViewModel.storePassword(sWebsite,sPassword,sDesc)

            Toast.makeText(activity, "$sWebsite $sPassword $sDesc entered", Toast.LENGTH_SHORT).show()
            listener?.goToPasswordListFragment()
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun goToPasswordListFragment()
    }

    companion object {

        @JvmStatic
        fun newInstance() = NewPasswordFragment()
    }
}
