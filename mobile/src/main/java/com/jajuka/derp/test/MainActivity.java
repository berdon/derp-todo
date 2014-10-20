package com.jajuka.derp.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jajuka.derp.Bind;
import com.jajuka.derp.DataBinding;
import com.jajuka.derp.Derp;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {
    // Create a DataBinding for a list of Models
    @Bind(value = R.id.listview, repeat = true, layoutId = R.layout.item_text)
    private DataBinding<List<String>> mModels = new DataBinding<List<String>>(new LinkedList<String>());

    @InjectView(R.id.new_todo)
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inject our views
        ButterKnife.inject(this);

        // Bind our data
        Derp.bind(this);
    }

    @OnClick(R.id.add_todo)
    public void onAddText(View view) {
        // Add the item to the list
        mModels.get().add(mEditText.getText().toString());

        // Tell the DataBinding an element in the list has been added
        mModels.update();

        // Clear the text for the next item
        mEditText.setText("");
    }

    public void onDeleteClicked(View rootView, View view, int position) {
        // Delete the Model at the clicked position
        mModels.get().remove(position);

        // Tell the DataBinding an element in the list has changed
        mModels.update();
    }
}
