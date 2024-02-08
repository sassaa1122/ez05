package com.example.ez05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ez05.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private  lateinit var  binding: ActivityLoginBinding
    private  lateinit var  firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        //ไปยังหน้า register
        binding.textView.setOnClickListener {
            val intent = Intent(this ,register::class.java)
            startActivity(intent)
        }
        //เมื่อกดปุ่ม
        binding.button.setOnClickListener {
            val email = binding.emailEr.text.toString()
            val pass = binding.passEr.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
            //นำข้อมูลเออกมาเช็ค
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this ,MainActivity::class.java)
                        startActivity((intent))
                    }else{
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }

                val intent = Intent(this ,MainActivity::class.java)
                startActivity((intent))
            }else{
                Toast.makeText(this,"กรุณากรอกให้ครบ", Toast.LENGTH_SHORT).show()
            }
        }


    }
    //เงื่อนไขเช็คว่ามีค่าซำ้ไหมฆ
    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser !=null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }


}