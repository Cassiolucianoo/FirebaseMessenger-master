package br.cassio.devmedia.firebase_messenger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

//biblioteca synthetic.main.activity_login.* acessar activity layout
import kotlinx.android.synthetic.main.activity_login.*


import kotlinx.android.synthetic.main.activity_register.already_have_an_account_text_view

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            /**
             * acessar seus campos de texto
             */
            val email = email_edit_login.text.toString()
            val password = password_edit_login.text.toString()


            /**
             * Registre uma mensagem de depuração dentro do logcat
             */
        Log.d("login", "Email and Password: $email / *******")
        }

        already_have_an_account_text_view.setOnClickListener {
            Log.d("MainActivity","Trocar de activity")

            //navegar entre as activity
            val intent =  Intent(this,RegisterActivity::class.java)
            startActivity(intent)

        }


    }



}