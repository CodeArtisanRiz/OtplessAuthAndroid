package com.t3g.otpless

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResponseActivity : AppCompatActivity() {
    private var userNumberTv: TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)
        userNumberTv = findViewById(R.id.user_phone)
        val extras = intent.extras
        if (extras != null) {
            val passedUserNumber = intent.extras?.getString("number")
            userNumberTv?.text = passedUserNumber.toString()
        }
    }
}