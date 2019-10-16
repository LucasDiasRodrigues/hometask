package com.lucas.hometask.casa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.lucas.hometask.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TarefasFragment extends Fragment {


    View cardContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarefas, container, false);

        cardContent = view.findViewById(R.id.cardContent);

        MaterialButton button = view.findViewById(R.id.btnCardExpand);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardContent.setVisibility(
                        cardContent.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        return view;
    }
}
