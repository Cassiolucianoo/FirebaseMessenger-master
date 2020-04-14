package br.cassio.devmedia.firebase_messenger

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {

    companion object{
        val TAG = "RegisterActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button_register.setOnClickListener {
            perFormaRegister()
        }

        already_have_an_account_text_view.setOnClickListener {
            Log.d("MainActivity", "Trocar de activity")

            //navegar entre as activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
        selectphoto_select_button_register.setOnClickListener {
            Log.d(TAG, "try show photo selector ")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

    }

    var selectPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(resultCode, requestCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d(TAG, "Photo was selected")

            selectPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectPhotoUri)
            select_image_view_register.setImageBitmap(bitmap)
            selectphoto_select_button_register.alpha = 0f
            //  val bitmapDrawable = BitmapDrawable(bitmap)
            // selectphoto_select_button_register.setBackgroundDrawable(bitmapDrawable)
        }
    }

    private fun perFormaRegister() {

        val name = name_edittext_register.text.toString()
        val email = email_edittext_register.text.toString()
        val password = password_edittext_register.text.toString()

        //validação de campos de cadastro
        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Os campos são OBRIGATÓRIOS ", Toast.LENGTH_SHORT).show()
            return
        }
        //For The Fallen Dreams
        Log.d(TAG, "Name: $name")
        Log.d(TAG, "E-mail is:" + email)
        Log.d(TAG, "Password: $password")

        //FireBase Authentication to create a user with email and passoword
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                //else if successful
                Log.d(TAG,
                    "( Boa )Successfully created user with uid: ${it.result?.user?.uid}"
                )
                Toast.makeText(
                    this,
                    "Conta cadastrada: ${it.result?.user?.uid}",
                    Toast.LENGTH_SHORT
                ).show()

                updateFirebaseImageToStorage()
            }
            .addOnFailureListener {

                Log.d(TAG, "( FALHA )Failure created user: ${it.message}")
                Toast.makeText(
                    this, "Erro ao criar user: ${it.message}", Toast
                        .LENGTH_SHORT
                ).show()
            }
    }

    private fun updateFirebaseImageToStorage() {
        if (selectPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("images/$filename")

        ref.putFile(selectPhotoUri!!).addOnSuccessListener {
            Log.d("RegisterActivity", "Upload sucesso : ${it.metadata?.path}")

            ref.downloadUrl.addOnSuccessListener {
                it.toString()
                Log.d(TAG, "File Location : ${it}")

                saveUserToFirebaseDatabase(it.toString())
            }

        }
            .addOnFailureListener {
                Log.d(TAG, "Failed to update image to storage ${it.message}")
            }
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/user/$uid")

        val user = User(uid, name_edittext_register.text.toString(), profileImageUrl)

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d(TAG, "Finaly we save the user Firebase")

                val intent = Intent(this, LatestMessagesActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
            .addOnFailureListener {
                Log.d(TAG, "Failed to set value to fdatabase${it.message}")
            }

    }
}

class User(val uid: String, val username: String, val profileImageUrl: String)
