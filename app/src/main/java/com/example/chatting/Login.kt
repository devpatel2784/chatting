package com.example.chatting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.chatting.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth= FirebaseAuth.getInstance()




        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            login(email,password)
        }

    }

    private  fun  login(email: String,password: String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent= Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                   Toast.makeText(this@Login,"User does not exist",Toast.LENGTH_SHORT).show()
                }
            }


    }

    override fun onStart() {
        super.onStart()
        mAuth= FirebaseAuth.getInstance()

        if (mAuth.currentUser !=null){
            val intent= Intent(this@Login,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}