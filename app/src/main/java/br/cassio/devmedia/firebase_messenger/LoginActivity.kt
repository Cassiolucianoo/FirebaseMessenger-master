package br.cassio.devmedia.firebase_messenger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.already_have_an_account_text_view

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
=======

>>>>>>> upload-image-profile-Save-User-CicleImageView
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            val email = email_edit_login.text.toString()
            val password = password_edit_login.text.toString()

        Log.d("login", "Email and Password: $email / *******")
        }

        already_have_an_account_text_view.setOnClickListener {
            Log.d("MainActivity","Trocar de activity")

            //navegar entre as activity
            val intent =  Intent(this,MainActivity::class.java)
            startActivity(intent)

        }


    }



}