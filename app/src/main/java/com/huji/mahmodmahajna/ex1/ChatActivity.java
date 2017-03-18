package com.huji.mahmodmahajna.ex1;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private static final String RECYCLER_CONTENT = "recycler_content";
    final String RECYCLER_STATE = "recycler_state";
    private RecyclerView recyclerView;
    private Parcelable recyclerViewState;
    private RecyclerView.LayoutManager cLayoutManager;
    private ChatAdapter cAdapter;
    private ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initializeButtonFunctionality();
        initializeRecyclerView();

    }

    private void initializeRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        cAdapter = new ChatAdapter(new ArrayList<String>());
        cLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(cLayoutManager);
        recyclerView.setAdapter(cAdapter);

    }

    private void initializeButtonFunctionality() {
        final EditText editText = (EditText) findViewById(R.id.editText);
        initializeButton(editText);
    }

    private void initializeButton(final EditText editText) {
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    cAdapter.addItemToList(editText.getText().toString());
                    editText.setText("");
                    recyclerView.scrollToPosition(cAdapter.getItemCount() - 1 );
                }
            }
        });
    }

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        recyclerViewState = cLayoutManager.onSaveInstanceState();
        //save recyclerView scroll position
        state.putParcelable(RECYCLER_STATE, recyclerViewState);

        //save recyclerView content
        state.putStringArrayList(RECYCLER_CONTENT, cAdapter.getStrings());

    }


    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if (state != null) {
            //restore scroll position
            recyclerViewState = state.getParcelable(RECYCLER_STATE);

            //restore content
            stringArrayList = state.getStringArrayList(RECYCLER_CONTENT);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //restore content
        if (stringArrayList != null) {
            cAdapter.getStrings().addAll(stringArrayList);
        }


        //restore scroll position
        if (recyclerViewState != null) {
            cLayoutManager.onRestoreInstanceState(recyclerViewState);
        }


    }

}