package com.nicatec.everpobre.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nicatec.everpobre.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotebookRowViewHolder extends RecyclerView.ViewHolder{

    private String NotebookName;
    @Bind(R.id.row_notebook_notebook_name) TextView notebookNameTextView;

    public NotebookRowViewHolder(View rowNotebook) {
        super(rowNotebook);
        //notebookNameTextView = (TextView) rowNotebook.findViewById(R.id.row_notebook_notebook_name);
        ButterKnife.bind(this, rowNotebook);
    }

    public void setNotebookName(String name){
        notebookNameTextView.setText(name);
    }
}
