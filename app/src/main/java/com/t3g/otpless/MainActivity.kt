package com.t3g.otpless

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.otpless.dto.OtplessResponse
import com.otpless.views.OtplessManager
import com.otpless.views.WhatsappLoginButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OtplessManager.getInstance().init(this)
//      Add this to your activity or fragment in which you have added the button
        val button = findViewById<View>(R.id.whatsapp_login) as WhatsappLoginButton
        button.setResultCallback { data: OtplessResponse ->
//          if (data != null){
            val waid = data.waId
//            call api pass waid to body
//      Send the waId to your server and pass the waId in getUserDetail API to retrive the users whatsapp mobile number and name.
//      Handle the signup/signin process here
//            handleSignUp(data)
            apiCall(waid.toString())
        }
    }


    private fun showToast(waName: String) {
        Toast.makeText(applicationContext, waName, Toast.LENGTH_LONG).show()
    }

    private fun apiCall(waid: String) {
        val queue = Volley.newRequestQueue(applicationContext)
        val url = "https://techno3gamma.in/otp?waId="+waid

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val statusCode = response.getInt("statusCode")
                val data = response.getJSONObject("data")
                val metadata = data.getJSONObject("metadata")
//                val package = metadata.getString("package")
                val osVersion = metadata.getString("osVersion")
                val sdkVersion = metadata.getString("sdkVersion")
                val appVersionName = metadata.getString("appVersionName")
                val deviceId = metadata.getString("deviceId")
                val appVersionCode = metadata.getString("appVersionCode")
                val platform = metadata.getString("platform")
                val manufacturer = metadata.getString("manufacturer")
                val userMobile = data.getString("userMobile")
                val userName = data.getString("userName")
                val timestamp = data.getString("timestamp")

                val user = response.getJSONObject("user")
                val waId = user.getString("waId")
                val userMetadata = user.getJSONObject("metadata")
                val userPackage = userMetadata.getString("package")
                val userOsVersion = userMetadata.getString("osVersion")
                val userSdkVersion = userMetadata.getString("sdkVersion")
                val userAppVersionName = userMetadata.getString("appVersionName")
                val userDeviceId = userMetadata.getString("deviceId")
                val userAppVersionCode = userMetadata.getString("appVersionCode")
                val userPlatform = userMetadata.getString("platform")
                val userManufacturer = userMetadata.getString("manufacturer")
                val waNumber = user.getString("waNumber")
                val waName = user.getString("waName")
                val userTimestamp = user.getString("timestamp")

                val success = response.getBoolean("success")
                val ok = response.getBoolean("ok")
                val status = response.getString("status")

                // Handle the parsed values as needed
                showToast(waNumber)
                responseActivity(waNumber)
            },
            Response.ErrorListener { error ->
                // Handle the error as needed
                showToast("error")
            }
        )

        queue.add(jsonObjectRequest)

    }

    private fun responseActivity(waNumber: String) {
        val intent = Intent(applicationContext, ResponseActivity::class.java)
        intent.putExtra("number", waNumber)
        startActivity(intent)
    }
}