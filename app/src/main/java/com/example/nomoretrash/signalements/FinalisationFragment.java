package com.example.nomoretrash.signalements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

public class FinalisationFragment extends Fragment {
    public FinalisationFragment(){
        //vide
    }

    public static FinalisationFragment newInstance() {
        return (new FinalisationFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String recap = "Recapitulatif : ";
        View rootView = inflater.inflate(R.layout.finalisation_fragment, container, false);

        Button boutonFinaliser = rootView.findViewById(R.id.boutonFinir);
        boutonFinaliser.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                FinalisationFragment.this.getActivity().finish();

            }
        });


        if(DescriptionFragment.DECHET_UNIQUE)
            recap+="ceci est un dechet unique";


        ((TextView)rootView.findViewById(R.id.recap)).setText(recap);



        return rootView;
    }
}
