package com.byron.uibestpractice_chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initMsg();

        initWidgets();

        setRecyclerViewLayoutManager();

        msgAdapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(msgAdapter);

    }

    private void setRecyclerViewLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
    }

    private void initWidgets() {
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.msg_recycle_view);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Msg msg = new Msg(inputText.getText().toString(), Msg.TYPE_SENT);
                msgList.add(msg);
                msgAdapter.notifyItemInserted(msgList.size() - 1);
                msgRecyclerView.scrollToPosition(msgList.size() - 1);
                inputText.setText("");
            }
        });
    }

    private void initMsg() {
        insertMsg("Hello guy", Msg.TYPE_RECEIVE);
        insertMsg("Hello. Who is that?", Msg.TYPE_SENT);
        insertMsg("This is Tom. Nice to talking to you.", Msg.TYPE_RECEIVE);
    }

    private void insertMsg(String content, int type) {
        msgList.add(new Msg(content, type));
    }
}
