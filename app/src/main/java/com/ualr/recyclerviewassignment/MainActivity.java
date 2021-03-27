package com.ualr.recyclerviewassignment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.adapter.AdapterList;
import com.ualr.recyclerviewassignment.databinding.ActivityListMultiSelectionBinding;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


// TODO 06. Detect click events on the list items. Implement a new method to toggle items' selection in response to click events
// TODO 07. Detect click events on the thumbnail located on the left of every list row when the corresponding item is selected. Implement a new method to delete the corresponding item in the list
// TODO 08. Create a new method to add a new item on the top of the list. Use the DataGenerator class to create the new item to be added.

public class MainActivity extends AppCompatActivity implements AdapterList.OnItemClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private FloatingActionButton mFAB;

    private ActivityListMultiSelectionBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityListMultiSelectionBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initRecyclerView();
    }
    // TODO 01. Generate the item list to be displayed using the DataGenerator class
    // TODO 03. Do the setup of a new RecyclerView instance to display the item list properly
    // TODO 04. Define the layout of each item in the list

    private void initRecyclerView() {



        List<Inbox> items = DataGenerator.getInboxData(this);
        items.addAll( DataGenerator.getInboxData(this));
        items.addAll( DataGenerator.getInboxData(this));



        RecyclerView InBoxListView = mBinding.recyclerView;



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        InBoxListView.setLayoutManager(layoutManager);

        // TODO 09. Create a new instance of the created Adapter class and bind it to the RecyclerView instance created in step 03
        // TODO 10. Invoke the method created to a new item to the top of the list so it's triggered when the user taps the Floating Action Button
        final AdapterList adapter = new AdapterList(items);

        adapter.setOnItemClickListener(this);
        InBoxListView.setAdapter(adapter);



        mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.addItem(0,DataGenerator.getRandomInboxItem(view.getContext()));
                mBinding.recyclerView.scrollToPosition(0);
            }
        });
    }

    @Override
    public void onItemClick(View view, Inbox obj, int position) {


        Log.d(TAG, String.format("The user %s has tapped on the iem %d", obj.getFrom(), position));
    }
}