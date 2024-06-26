package com.example.dacs3.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dacs3.dao.UserDao
import com.example.dacs3.data.User
import com.example.dacs3.repository.MusicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    var userDao: MusicRepository
) : ViewModel() {

    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> = _userId.asStateFlow()

    private val _fullname = MutableStateFlow("")
    val fullname: StateFlow<String> = _fullname.asStateFlow()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    private val _msg = MutableStateFlow("")
    val msg: StateFlow<String> = _msg.asStateFlow()


    fun setString() {
        _fullname.value = ""
        _username.value = ""
        _password.value = ""
        _confirmPassword.value = ""
    }

    fun onFullNameChange(newFullName: String) {
        _fullname.value = newFullName
    }

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
    }

    val userList = MutableStateFlow<List<User>>(listOf())

    suspend fun login(context: Context, onLoginSuccess: (Int) -> Unit): Boolean {
        return viewModelScope.async(Dispatchers.IO) {
            val user = userDao.getUser(_username.value, _password.value)
            val result = if (user != null) {
                _msg.value = "Đăng nhập thành công"
                _userId.value = user.user_id
                withContext(Dispatchers.Main) {
                    onLoginSuccess(_userId.value!!)
                }
                true
            } else {
                _msg.value = "Đăng nhập thất bại"
                false
            }
            withContext(Dispatchers.Main) {
                showToast(context, _msg.value)
            }
            result
        }.await()
    }

    fun insertUser(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val users = userDao.getAllUsers()
            userList.value = users

            if (checkUserName(users)) {
                userDao.insert(User(0, _fullname.value, _username.value, _password.value))
                _msg.value = "Đăng ký thành công"
            }

            // Chuyển sang Dispatchers.Main để hiển thị Toast trên luồng chính
            withContext(Dispatchers.Main) {
                showToast(context, _msg.value)
            }
        }
    }

    private fun checkUserName(users: List<User>): Boolean {
        users.forEach { user ->
            if (user.userName == _username.value) {
                _msg.value = "Tài khoản đã tồn tại"
                return false
            }
        }
        if (_password.value != _confirmPassword.value) {
            _msg.value = "Mật khẩu không trùng khớp"
            return false
        }
        return true
    }

    fun showToast(context: Context?, msg: String) {
        context?.let {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
    fun register() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(User(0, _fullname.value, _username.value, _password.value))
        }
    }
}
