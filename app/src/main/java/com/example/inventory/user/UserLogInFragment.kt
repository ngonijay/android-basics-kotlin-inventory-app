package com.example.inventory.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.inventory.InventoryApplication
import com.example.inventory.databinding.FragmentUserLogInBinding


/* A simple [Fragment] subclass.
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

     private var _binding: FragmentUserLogInBinding? = null
     private val binding get() = _binding!!


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
     }

     private fun viewInit() {

         binding.signInBtn.setOnClickListener {

             val phoneNumber = _binding?.userPhoneNumberTxt?.text.toString()
             val password = _binding?.userPasswordTxt?.text.toString()

             signIn(phoneNumber, password)
         }

         binding.signUpBtn.setOnClickListener {
             val action =
                 UserLogInFragmentDirections.actionUserLogInFragmentToRegisterUserFragment()
             this.findNavController().navigate(action)
         }
     }

     private fun signIn(userPhoneNumber: String, userPassword: String) {
         viewModel.getUserDetails(userPhoneNumber, userPassword).observe(viewLifecycleOwner) {
             if (it != null) {
                 val action =
                     UserLogInFragmentDirections.actionUserLogInFragmentToItemListFragment()
                 this.findNavController().navigate(action)
             } else
                 Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
         }
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

         // TODO: Rename and change types and number of parameters
         @JvmStatic
         fun newInstance(param1: String, param2: String) =
             UserLogInFragment().apply {
                 arguments = Bundle().apply {

                 }
             }
     }
 }