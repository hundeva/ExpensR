package com.hdeva.expensr.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.hdeva.expensr.databinding.ActivityHomeBinding
import com.hdeva.expensr.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun createBinding() = ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
