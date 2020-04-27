package br.cassio.devmedia.firebase_messenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_message.*


class NewMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        supportActionBar?.title = "Selecione User"

        val adapter = GroupAdapter<ViewHolder>()
        adapter.add()

        recyclerview_newmessage.adapter
       // recyclerview_newmessage.layoutManager = LinearLayoutManager(this)

    }
}
class UserItem: Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
//serão chamados em nossa lista para cada objeto de usuário mais tarde
    }

    override fun getLayout(): Int {
        return  R.layout

    }

}