package com.example.latihanfirebase

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import com.example.latihanfirebase.databinding.ActivityMainBinding
import com.example.latihanfirebase.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
//    lateinit var editEmail : EditText
//    lateinit var editPassword : EditText
//    lateinit var btnLogin: Button
//    lateinit var toRegister : TextView
    lateinit var progressDialog : ProgressDialog

    var firebaseAuth = FirebaseAuth.getInstance()

//    override fun onStart() {
//        super.onStart()
//        if(firebaseAuth.currentUser!=null){
//            startActivity(Intent(this, Profile::class.java))
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(
            crashButton, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )


//        editEmail = findViewById(R.id.email)
//        editPassword = findViewById(R.id.passwordLogin)
//        btnLogin = findViewById(R.id.btnlogin)
//        toRegister = findViewById(R.id.toRegister)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Logging")
        progressDialog.setMessage("Silahkan Tunggu")

        binding.btnlogin.setOnClickListener {
            if (binding.email.text.isNotEmpty() && binding.passwordLogin.text.isNotEmpty()) {
                prosesLogin()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Silahkan isi Email dan Password terlebih dahulu",
                    LENGTH_SHORT
                ).show()
            }
        }


        binding.toRegister.setOnClickListener() {
            startActivity(Intent(this, Register::class.java))

            }
        }



    fun prosesLogin(){
        val email = binding.email.text.toString()
        val password = binding.passwordLogin.text.toString()

        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this@MainActivity, Profile::class.java))
            }
            .addOnFailureListener {error ->
                Toast.makeText(this, error.localizedMessage, LENGTH_SHORT).show()
            }
            .addOnCompleteListener{
                progressDialog.dismiss()
            }

    }
}