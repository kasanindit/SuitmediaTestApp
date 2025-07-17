package com.example.suitmediatestapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatestapp.R
import com.example.suitmediatestapp.databinding.ActivitySecondBinding
import com.example.suitmediatestapp.ui.thirdactivity.ThirdActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getData()

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
//            finish()
        }

        binding.btnBack.setOnClickListener {
//            onBackPressedDispatcher.onBackPressed()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()

        }
    }

    private fun getData(){
        val sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE)
        val nameWelcome = sharedPref.getString("username", "-")
        binding.txtUsername.text = nameWelcome

        val selectedName = intent.getStringExtra("selected_name")
        if (selectedName != null && selectedName.isNotEmpty()) {
            binding.txtSelectedUname.text = selectedName
        } else{

        }

    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }


}