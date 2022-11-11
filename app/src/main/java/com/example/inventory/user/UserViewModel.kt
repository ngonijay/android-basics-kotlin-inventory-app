package com.example.inventory.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.User
import com.example.inventory.data.UserDao
import kotlinx.coroutines.launch

class UserViewModel (private val userDto:UserDao): ViewModel() {
    /**
     * Inserts the new User into database.
     */
    fun addNewUser(userPhoneNUmber: String, userPassword: String) {
        val newUser = getNewUserEntry(userPhoneNUmber, userPassword)
        insertUser(newUser)
    }

    private fun insertUser(newUser: User) {
        viewModelScope.launch {
            userDto.addUser(newUser)
        }
    }

    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewUserEntry(userPhoneNUmber: String, userPassword: String): User {
        return User(
            phoneNumber = userPhoneNUmber,
            passWord = userPassword,
        )
    }

    /**
     * Factory class to instantiate the [ViewModel] instance.
     */
    class UserViewModelFactory(private val userDto: UserDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(userDto) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}