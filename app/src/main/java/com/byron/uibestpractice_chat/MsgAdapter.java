package com.byron.uibestpractice_chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> mMsgList;

    public MsgAdapter(List<Msg> mMsgList) {
        this.mMsgList = mMsgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.msg_item,
                parent,
                false);

        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Msg msg = mMsgList.get(position);
                Toast.makeText(v.getContext(), msg.getContent(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        if(msg.getType() == Msg.TYPE_RECEIVE) {
            holder.leftLayout.setVisibility(LinearLayout.VISIBLE);
            holder.rightLayout.setVisibility(LinearLayout.GONE);
            holder.msgLeft.setText(msg.getContent());
        } else if( msg.getType() == Msg.TYPE_SENT) {
            holder.leftLayout.setVisibility(LinearLayout.GONE);
            holder.rightLayout.setVisibility(LinearLayout.VISIBLE);
            holder.msgRight.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout leftLayout;
        private LinearLayout rightLayout;
        private TextView msgLeft;
        private TextView msgRight;

        public ViewHolder(View itemView) {
            super(itemView);
            leftLayout = itemView.findViewById(R.id.left_layout);
            rightLayout = itemView.findViewById(R.id.right_layout);
            msgLeft = itemView.findViewById(R.id.left_msg);
            msgRight = itemView.findViewById(R.id.right_msg);

        }
    }
}
