package com.example.societyhub

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.societyhub.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity()  {
    private lateinit var viewBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.tvRegister.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = viewBinding.edtEmail.text.toString()
        val pass = viewBinding.edtPassword.text.toString()
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener {
            if(it.isSuccessful){
                if (email=="admin@gmail.com") {
                    startActivity(Intent(this, Admin::class.java))
                    Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}