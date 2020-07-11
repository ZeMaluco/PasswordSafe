package com.example.safe


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.safe.data.Password
import kotlinx.android.synthetic.main.fragment_new_password.*

private const val ARG_PARAM4 = "id"
private const val ARG_PARAM1 = "website"
private const val ARG_PARAM2 = "password"
private const val ARG_PARAM3 = "descripton"

class NewPasswordFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: Int?    = null
    private val tes2te = Password()

    private lateinit var pViewModel: NewPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getInt(ARG_PARAM4)
        }

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        pViewModel = ViewModelProviders.of(this).get(NewPasswordViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
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
        Toast.makeText(activity, "ADDED$param4 $param1 $param2 $param3", Toast.LENGTH_SHORT).show()
        if(param1!=null && param2!=null && param3!=null && param4 != null) {
            tes2te.id = param4 as Int
            tes2te.website = param1 as String
            tes2te.password = param2 as String
            tes2te.description = param3 as String

            editTextWeb.setText(param1)
            editTextPass.setText(param2)
            editTextDesc.setText(param3)

        }


        button.setOnClickListener {
            Toast.makeText(activity, "$param4", Toast.LENGTH_SHORT).show()
            if(param4 === 0) {
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
                Toast.makeText(activity, "ADDED$param4", Toast.LENGTH_SHORT).show()

                listener?.goToPasswordListFragment()
            }
            else {
                param1 = editTextWeb.text.toString().trim()
                param2 = editTextPass.text.toString().trim()
                param3 = editTextDesc.text.toString().trim()

                if (param1!!.isEmpty()) {
                    Toast.makeText(activity, "Website required", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (param2!!.isEmpty()) {
                    Toast.makeText(activity, "Password required", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (param3!!.isEmpty()) {
                    Toast.makeText(activity, "Description required", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                tes2te.id = param4 as Int
                tes2te.website = param1 as String
                tes2te.password = param2 as String
                tes2te.description = param3 as String


                pViewModel.update(tes2te)

                listener?.goToPasswordListFragment()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    private fun deletePassword() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes") { _, _ ->
                Toast.makeText(activity, "Cant delete $tes2te", Toast.LENGTH_SHORT).show()
                pViewModel.DeletePassword(tes2te)
                Toast.makeText(activity, "DELETED$tes2te", Toast.LENGTH_SHORT).show()
                listener?.goToPasswordListFragment()
            }
            setNegativeButton("No") { _, _ ->

            }
        }.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.delete -> if (param4 != 0) deletePassword() else Toast.makeText(activity, "Cant delete $tes2te", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
    
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    fun updatePassword(position : Int) {


        Toast.makeText(activity, "$position", Toast.LENGTH_SHORT).show()
    }

    interface OnFragmentInteractionListener {
        fun goToPasswordListFragment()
    }

    companion object {

        @JvmStatic
        fun newInstance(param4: Int,param1: String, param2: String, param3 : String) =
            NewPasswordFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putInt(ARG_PARAM4, param4!!)
                }
            }
    }
}
