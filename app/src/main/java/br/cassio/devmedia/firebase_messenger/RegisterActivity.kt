package br.cassio.devmedia.firebase_messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button_register.setOnClickListener {
            perFormaRegister()
        }

        selectphoto_select_button_register.setOnClickListener{
            Log.d("MainActivity","try show photo selector ")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)


        }


        already_have_an_account_text_view.setOnClickListener {
            Log.d("MainActivity","Trocar de activity")

            //navegar entre as activity
            val intent =  Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }

    }

    private fun perFormaRegister(){

        val name = name_edittext_register.text.toString()
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        //validação de campos de cadastro
        if(email.isEmpty()||password.isEmpty()||name.isEmpty()){
            Toast.makeText(this,"Os campos são OBRIGATÓRIOS ",Toast.LENGTH_SHORT).show()
            return
        }
        //For The Fallen Dreams
        Log.d("MainActivity","Name: $name")
        Log.d("MainActivity","E-mail is:"+email)
        Log.d("MainActivity","Password: $password")

        //FireBase Authentication to create a user with email and passoword
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener

                //else if successful
                Log.d("Main", "( Boa )Successfully created user with uid: ${it.result?.user?.uid}")
                Toast.makeText(this,"Conta cadastrada: ${it.result?.user?.uid}",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{

                Log.d("Main", "( FALHA )Failure created user: ${it.message}")
                Toast.makeText(this,"Erro ao criar user: ${it.message}",Toast.LENGTH_SHORT).show()
            }
    }
}


