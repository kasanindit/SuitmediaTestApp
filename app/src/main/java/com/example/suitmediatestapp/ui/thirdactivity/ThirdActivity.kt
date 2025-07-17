package com.example.suitmediatestapp.ui.thirdactivity

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.suitmediatestapp.R
import com.example.suitmediatestapp.adapter.UserAdapter
import com.example.suitmediatestapp.data.ViewModelFactory
import com.example.suitmediatestapp.data.api.ApiConfig
import com.example.suitmediatestapp.data.repository.UserRepository
import com.example.suitmediatestapp.data.response.DataItem
import com.example.suitmediatestapp.databinding.ActivityThirdBinding
import com.example.suitmediatestapp.ui.SecondActivity
import java.util.logging.Handler

class ThirdActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.thirdScreen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val factoryViewModel = ViewModelFactory(UserRepository(ApiConfig.getApiService()))
        viewModel = ViewModelProvider(this, factoryViewModel)[UserViewModel::class.java]

        showLoading(true)
        setupViewModel()
        viewModel.getDataUsers(2)


        binding.btnBack.setOnClickListener {
//            finish()
            onBackPressedDispatcher.onBackPressed()
        }


        binding.thirdScreen.setOnRefreshListener {
            showLoading(false)
            setupViewModel()
            viewModel.getDataUsers(2)

        }
    }

    private fun setupViewModel(){
        viewModel.listUser.observe(this) { users ->
            userAdapter = UserAdapter(users){ selectedUser ->
                val username = intent.getStringExtra("username")
                val intent = Intent(this@ThirdActivity, SecondActivity::class.java).apply{
                    putExtra("selected_name", "${selectedUser.firstName} ${selectedUser.lastName}")
//                    intent.putExtra("username", username)
                }
                startActivity(intent)
                finish()

            }
            binding.rvAccounts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rvAccounts.adapter = userAdapter

            showLoading(false)
            binding.thirdScreen.isRefreshing = false
        }

        viewModel.error.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            showLoading(false)
            binding.thirdScreen.isRefreshing = false
        }
    }

    private fun showLoading(isLoading: Boolean){
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }
}