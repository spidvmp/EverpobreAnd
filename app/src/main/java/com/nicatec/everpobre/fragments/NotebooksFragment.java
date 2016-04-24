package com.nicatec.everpobre.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicatec.everpobre.R;
import com.nicatec.everpobre.adapters.NotebookAdapter;
import com.nicatec.everpobre.model.Notebook;
import com.nicatec.everpobre.model.Notebooks;
import com.nicatec.everpobre.model.db.dao.NotebookDAO;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotebooksFragment extends Fragment {


    @Bind(R.id.fragment_notebooks_recycler_view)
    RecyclerView notebooksRecyclerView;

    public NotebooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notebooks, container, false);

        ButterKnife.bind(this, view);

        //el recyclerview siempre necesita un layoutmanager
        notebooksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        NotebookDAO notebookDAO = new NotebookDAO();
        Notebooks notebooks = notebookDAO.query();
        NotebookAdapter adapter = new NotebookAdapter(notebooks, getActivity());
        notebooksRecyclerView.setAdapter(adapter);


        return view;
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        NotebookDAO notebookDao = new NotebookDAO();
        Notebooks notebooks = notebookDao.query();

        NotebookAdapter adapter = new NotebookAdapter(notebooks, getActivity());
        notebooksRecyclerView.setAdapter(adapter);
    }
    */
}
