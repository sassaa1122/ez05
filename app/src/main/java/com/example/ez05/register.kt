package com.example.ez05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ez05.databinding.ActivityLoginBinding
import com.example.ez05.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class register : AppCompatActivity() {

    private  lateinit var  binding: ActivityRegisterBinding
    private  lateinit var  firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        //ไปยังหน้า login
        binding.textView.setOnClickListener {
            val intent = Intent(this ,login::class.java)
            startActivity(intent)
        }
        //เมื่อกดปุ่ม
        binding.button.setOnClickListener {
            val email = binding.emailEr.text.toString()
            var pass = binding.passEr.text.toString()
        //นำข้อมูลเข้าสู่ ฐานข้อมูล  หากสมัครสำเร็จให้ไแหน้า login
            if(email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this ,login::class.java)
                        startActivity((intent))
                    }else{
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }

                val intent = Intent(this ,login::class.java)
                startActivity((intent))
            }else{
                //เช็คค่าว่าว่างหรือไม่ถ้าว่าให้แจ้งเตื่อน
                Toast.makeText(this,"กรุณากรอกให้ครบ", Toast.LENGTH_SHORT).show()
            }
        }

    }

}