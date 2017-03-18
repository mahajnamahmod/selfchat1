package com.huji.mahmodmahajna.ex1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahmodmahajna on 15/03/2017.
 */

class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    protected List<String> strings;

    public ArrayList<String> getStrings() {
        return (ArrayList<String>) strings;
    }

    public ChatAdapter(List<String> strings) {

        this.strings = strings;
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        return new ChatHolder(v);
    }
    public void addItemToList(String item){
        strings.add(item);
        notifyDataSetChanged();
    }
    public void clearList(){
        strings.clear();
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        holder.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }


    public class ChatHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ChatHolder(View view) {
            super(view);
            textView = (TextView)view.findViewById(R.id.text_view);
        }


        public void setText(String text)
        {
            textView.setText(text);
        }
    }



}