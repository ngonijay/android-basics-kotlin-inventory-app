package com.example.inventory.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.inventory.InventoryApplication
import com.example.inventory.R
import com.example.inventory.databinding.FragmentRegisterUserBinding
import com.example.inventory.databinding.FragmentUserLogInBinding


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterUserFragment : Fragment() {

    private var _binding : FragmentRegisterUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by activityViewModels {
        UserViewModel.UserViewModelFactory(
            (activity?.application as InventoryApplication).database
                .userDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterUserBinding.inflate(inflater, container, false)
        viewInit()
        return binding?.root
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterUserFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun viewInit() {

        binding.signUpBtn.setOnClickListener {

            val phoneNumber = _binding?.userPhoneNumberTxt?.text.toString()
            val password = _binding?.userPasswordTxt?.text.toString()

            signUp(phoneNumber, password)
        }

    }

    private fun signUp( userPhoneNumber:String , userPassword:String) {
        viewModel.addNewUser(userPhoneNumber,userPassword)
        val action = UserLogInFragmentDirections.actionUserLogInFragmentToItemListFragment()
        this.findNavController().navigate(action)

    }
}