package br.cassio.devmedia.firebase_messenger.messages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.cassio.devmedia.firebase_messenger.R
import br.cassio.devmedia.firebase_messenger.models.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*

class ChatLogActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        //val username = intent.getStringExtra(NewMessageActivity.USER_KEY)

        val user = intent.getParcelableExtra<User>(NewMessageActivity.USER_KEY)
        supportActionBar?.title = user.username

        val adapter = GroupAdapter<ViewHolder>()

        adapter.add(ChatTOItem())
        adapter.add(ChatFromItem())
        adapter.add(ChatTOItem())
        adapter.add(ChatFromItem())
        adapter.add(ChatTOItem())
        adapter.add(ChatFromItem())
        adapter.add(ChatTOItem())
        adapter.add(ChatFromItem())



        recyclerview_chat_log.adapter = adapter

    }
}

class ChatFromItem: Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
   return R.layout.chat_from_row
    }
}
class ChatTOItem: Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }
}