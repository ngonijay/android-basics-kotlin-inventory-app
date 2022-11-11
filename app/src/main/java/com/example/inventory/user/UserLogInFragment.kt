package com.example.inventory.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.inventory.InventoryApplication
import com.example.inventory.InventoryViewModel
import com.example.inventory.InventoryViewModelFactory
import com.example.inventory.Item.ItemListFragmentDirections
import com.example.inventory.R
import com.example.inventory.databinding.FragmentUserLogInBinding
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserLogInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserLogInFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels {
        UserViewModel.UserViewModelFactory(
            (activity?.application as InventoryApplication).database
                .userDao()
        )
    }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentUserLogInBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun viewInit() {

        binding.signBtn.setOnClickListener {

            val phoneNumber = _binding?.userPhoneNumberTxt?.text.toString()
            val password = _binding?.userPasswordTxt?.text.toString()

            signIn(phoneNumber, password)
         }
    }

    private fun signIn( userPhoneNumber:String , userPassword:String) {
        viewModel.addNewUser(userPhoneNumber,userPassword)
        val action = UserLogInFragmentDirections.actionUserLogInFragmentToItemListFragment()
        this.findNavController().navigate(action)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserLogInBinding.inflate(inflater, container, false)
        viewInit()
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserLogInFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserLogInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}