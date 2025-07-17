package com.example.suitmediatestapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatestapp.R
import com.example.suitmediatestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCheck.setOnClickListener{
            isPalindrome()
        }
        sendName()


    }

    private fun checkPalindrome(word: String): Boolean{
        val clean = word.replace(" ", "").lowercase()
        val reversed = clean.reversed()
        return clean == reversed
    }

    private fun isPalindrome(){
        val inputWords = binding.etIsPalindrome.text.toString()
        if (inputWords.isEmpty()){
            Toast.makeText(this, "Please input a word or phrase first.", Toast.LENGTH_SHORT).show()
        }else if (checkPalindrome(inputWords)){
            Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "notPalindrome", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendName(){
        binding.btnNext.setOnClickListener{
            val name = binding.etName.text.toString().trim()
            if (name.isEmpty()){
                Toast.makeText(this, "Input name first.", Toast.LENGTH_SHORT).show()
            } else{
//                val intentName = Intent(this, SecondActivity::class.java)
//                intentName.putExtra("username", name)
//                startActivity(intentName)
                val sharedPref = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
                sharedPref.edit().putString("username", name).apply()

                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
//                finish()
            }
        }
    }




}