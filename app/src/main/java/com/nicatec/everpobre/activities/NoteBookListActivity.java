package com.nicatec.everpobre.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nicatec.everpobre.R;
import com.nicatec.everpobre.fragments.NotebooksFragment;

public class NoteBookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_book_list);

        FragmentManager fm = getSupportFragmentManager();
        if ( fm != null ){

            NotebooksFragment notebooksFragment = new NotebooksFragment();

            fm.beginTransaction()
                    .add(R.id.activity_notebook_fragment_container, notebooksFragment)
                    .commit();
        }
    }
}
