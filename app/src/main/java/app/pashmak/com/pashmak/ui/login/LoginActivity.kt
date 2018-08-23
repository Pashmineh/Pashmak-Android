package app.pashmak.com.pashmak.ui.login

import androidx.databinding.DataBindingUtil
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.ActivityLoginBinding
import app.pashmak.com.pashmak.ui.base.BaseActivity

class LoginActivity: BaseActivity<LoginViewModel, ActivityLoginBinding>()
{
    override val viewModel: LoginViewModel by getLazyViewModel()
    override val binding: ActivityLoginBinding
            by lazy { DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login) }
}