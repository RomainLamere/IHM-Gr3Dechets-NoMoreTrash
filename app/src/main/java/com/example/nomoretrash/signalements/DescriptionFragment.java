package com.example.nomoretrash.signalements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;


import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;


public class DescriptionFragment extends Fragment {

    public static boolean DECHET_UNIQUE = false;
    public static boolean DECHARGE_SAUVAGE = false;



    public static boolean VERRE = false;
    public static boolean CARTON = false;
    public static boolean PAPIER = false;
    public static boolean PLASTIQUE = false;
    public static boolean METAL = false;
    public static boolean AUTRE = false;


    public static boolean GROS = false;
    public static boolean PETIT = false;



    public DescriptionFragment() {//vide
    }

    public static DescriptionFragment newInstance() {
        return (new DescriptionFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.description_fragment, container, false);


        Button boutonAnnuler = rootView.findViewById(R.id.boutonAnnuler);
        boutonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescriptionFragment.this.getActivity().finish();

            }
        });
        checkBoxCreation(rootView);
        return rootView;
    }



    private void checkBoxCreation(View rootView) {
        CheckBox checkBox_dechet_unique = rootView.findViewById(R.id.checkbox_dechet_unique);
        CheckBox checkbox_decharge_sauvage = rootView.findViewById(R.id.checkbox_decharge_sauvage);
        CheckBox checkbox_verre = rootView.findViewById(R.id.checkbox_verre);
        CheckBox checkbox_carton = rootView.findViewById(R.id.checkbox_carton);
        CheckBox checkbox_papier = rootView.findViewById(R.id.checkbox_papier);
        CheckBox checkbox_plastique = rootView.findViewById(R.id.checkbox_plastique);
        CheckBox checkbox_metal = rootView.findViewById(R.id.checkbox_metal);
        CheckBox checkbox_autre = rootView.findViewById(R.id.checkbox_autre);
        CheckBox checkbox_petit = rootView.findViewById(R.id.checkbox_petit);
        CheckBox checkbox_gros = rootView.findViewById(R.id.checkbox_gros);


        checkBox_dechet_unique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !DECHET_UNIQUE)
                    DECHET_UNIQUE = true;
                else
                    DECHET_UNIQUE = false;

                Log.d("DECHET_UNIQUE", DECHET_UNIQUE+"");
            }
        });

        checkbox_decharge_sauvage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !DECHARGE_SAUVAGE)
                    DECHARGE_SAUVAGE = true;
                else
                    DECHARGE_SAUVAGE = false;

                Log.d("DECHARGE_SAUVAGE", DECHARGE_SAUVAGE+"");
            }
        });

        checkbox_verre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !VERRE)
                    VERRE = true;
                else
                    VERRE = false;

                Log.d("VERRE", VERRE+"");
            }
        });
       
        checkbox_carton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !CARTON)
                    CARTON = true;
                else
                    CARTON = false;

                Log.d("CARTON", CARTON+"");
            }
        });

        checkbox_papier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !PAPIER)
                    PAPIER = true;
                else
                    PAPIER = false;

                Log.d("PAPIER", PAPIER+"");
            }
        });

        checkbox_plastique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !PLASTIQUE)
                    PLASTIQUE = true;
                else
                    PLASTIQUE = false;

                Log.d("PLASTIQUE", PLASTIQUE+"");
            }
        });

        checkbox_metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !METAL)
                    METAL = true;
                else
                    METAL = false;

                Log.d("METAL", METAL+"");
            }
        });

        checkbox_autre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !AUTRE)
                    AUTRE = true;
                else
                    AUTRE = false;

                Log.d("AUTRE", AUTRE+"");
            }
        });

        checkbox_gros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !GROS)
                    GROS = true;
                else
                    GROS = false;

                Log.d("GROS", GROS+"");
            }
        });

        checkbox_petit.setOnClickListener(new View.OnClickListener() {
            @Override
            
            public void onClick(View view) {
                if( !PETIT)
                    PETIT = true;
                else
                    PETIT = false;


                Log.d("PETIT", PETIT+"");
            }
        });
    /*
    ########################### GETTERS ###########################
     */

    public static boolean isDechetUnique() {
        return DECHET_UNIQUE;
    }

    public static boolean isDechargeSauvage() {
        return DECHARGE_SAUVAGE;
    }

    public static boolean isVERRE() {
        return VERRE;
    }

    public static boolean isCARTON() {
        return CARTON;
    }

    public static boolean isPAPIER() {
        return PAPIER;
    }

    public static boolean isPLASTIQUE() {
        return PLASTIQUE;
    }

    public static boolean isMETAL() {
        return METAL;
    }

    public static boolean isAUTRE() {
        return AUTRE;
    }

    public static boolean isPETIT() {
        return PETIT;
    }

    public static boolean isGROS() {
        return GROS;
    }
}
