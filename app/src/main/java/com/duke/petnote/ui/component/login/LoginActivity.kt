package com.duke.petnote.ui.component.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.duke.petnote.R
import com.google.android.material.snackbar.Snackbar
import com.duke.petnote.data.Resource
import com.duke.petnote.data.dto.login.LoginResponse
import com.duke.petnote.databinding.LoginActivityBinding
import com.duke.petnote.ui.base.BaseActivity
import com.duke.petnote.ui.component.home.HomeActivity
import com.duke.petnote.utils.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by AhmedEltaher
 */
@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tip.setText(getString(R.string.tip_login_no_account) + " " + getString(R.string.action_sign_up));
        StringUtils.setTextLast(this, binding.tip, getString(R.string.tip_login_no_account)
            , " " + getString(R.string.action_sign_up)
            , R.color.white);
        binding.login.setOnClickListener { doLogin() }
    }

    override fun initViewBinding() {
        binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun observeViewModel() {
        observe(loginViewModel.loginLiveData, ::handleLoginResult)
        observeSnackBarMessages(loginViewModel.showSnackBar)
        observeToast(loginViewModel.showToast)
    }

    private fun doLogin() {
        loginViewModel.doLogin(
            binding.phoneEdit.text.trim().toString(),
            binding.passwordEdit.text.toString()
        )
    }

    private fun handleLoginResult(status: Resource<LoginResponse>) {
        when (status) {
            is Resource.Loading -> binding.loaderView.toVisible()
            is Resource.Success -> status.data?.let {
                binding.loaderView.toGone()
                navigateToMainScreen()
            }
            is Resource.DataError -> {
                binding.loaderView.toGone()
                status.errorCode?.let { loginViewModel.showToastMessage(it) }
            }
        }
    }

    private fun navigateToMainScreen() {
        val nextScreenIntent = Intent(this, HomeActivity::class.java)
        startActivity(nextScreenIntent)
        finish()
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }
}
