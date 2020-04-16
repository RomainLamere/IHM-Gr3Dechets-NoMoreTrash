package com.example.nomoretrash.signalements;


import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.nomoretrash.MainActivity;
import com.example.nomoretrash.ApplicationDemo;
import com.example.nomoretrash.R;
import com.example.nomoretrash.map.MainMapActivity;

import static com.example.nomoretrash.ApplicationDemo.CHANNEL_ID;

public class FinalisationFragment extends Fragment {

    private SignalementObject signalementObject;
    ImageView mImageView;

    public FinalisationFragment() {
        //on récupère l'objet signalemnt
        this.signalementObject = DescriptionFragment.getSignalementObject();
    }


    private String recap = "";

    public static boolean part1 = false;
    public static boolean part2 = false;
    public static boolean  part3 = false;
    public static boolean notComplete = false;


    private int notificationId = 0;

    public static FinalisationFragment newInstance() {
        return (new FinalisationFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recap = "";

        View rootView = inflater.inflate(R.layout.finalisation_fragment, container, false);

        Button boutonFinaliser = rootView.findViewById(R.id.boutonFinir);
        boutonFinaliser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (part1 && part2 && part3) {
                    Toast.makeText(getContext(), "Signalement enregistré !", Toast.LENGTH_LONG).show();
                    sendNotification();
                } else {
                    notComplete = true;
                    Toast.makeText(getContext(), "Champs non remplis", Toast.LENGTH_LONG).show(); //Affichage du toast
                    SignalementActivity.pager.setCurrentItem(0); // retour automatique sur la page description
                }
            }
        });

        setRecap();
        ((TextView) rootView.findViewById(R.id.recap)).setText(recap);
        displayPhoto(rootView);

        return rootView;
    }



    private void displayPhoto(View rootView) {
        //Affichage de la photo
        if (this.signalementObject.getPhoto() != null) {
            mImageView = rootView.findViewById(R.id.photo);
            mImageView.setImageBitmap(this.signalementObject.getPhoto());
            mImageView.setRotation(90);
        }
    }


    private void sendNotification() {
        // Create two threads:
        Thread thread1 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra("mon_signalement", DescriptionFragment.getSignalementObject().toString());
                getActivity().setResult(Activity.RESULT_OK, resultIntent);
                getActivity().finish();
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                Context saveContext = getActivity().getApplicationContext();
                String saveChannelId = CHANNEL_ID;
                String titleConfirmation = "Prise en compte de votre signalement";
                String notifConfirmation = "Il sera traité dans les plus brefs délais.";
                String titleChecked = "Déchet nettoyé";
                String notifChecked = "Le déchet signalé a été nettoyé, Merci !";
                sendNotificationOnChannel(R.drawable.chargement, titleConfirmation, notifConfirmation, saveChannelId, NotificationCompat.PRIORITY_DEFAULT, saveContext, signalementObject.getPhoto());
                sendNotificationOnChannel(R.drawable.validation, titleChecked, notifChecked, saveChannelId, NotificationCompat.PRIORITY_HIGH, saveContext, null);
            }
        };

        // Start the downloads.
        thread1.start();
        thread2.start();

        // Wait for them both to finish
        try {
            thread1.join();
            //thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void sendNotificationOnChannel(int icon, String title, String message, String channelId, int priority, Context context, Bitmap photo) {

        try {
            for(int i = 1 ; i <= 10; i++){
                Thread.sleep(1000);
                System.out.println(i+" seconde(s) se sont écoulée");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, channelId);
        if(photo!=null){
             notification.setSmallIcon(icon).setContentTitle(title).setContentText(message).setPriority(priority)
                    .setLargeIcon(RotateBitmap(signalementObject.getPhoto(), 90));
        }
        else {
            notification.setSmallIcon(icon).setContentTitle(title).setContentText(message).setPriority(priority);
        }
        ApplicationDemo.getNotificationManager().notify( ++notificationId, notification.build());
    }


    public void setRecap() {
        recap = "Recapitulatif : ";
        if (signalementObject.isDECHET_UNIQUE() || signalementObject.isDECHARGE_SAUVAGE()) {
            part1 = true;
            if (signalementObject.isDECHET_UNIQUE())
                recap += "dechet unique";
            else
                recap += "décharge sauvage";
        } else part1 = false;

        if (signalementObject.isVERRE() || signalementObject.isCARTON() || signalementObject.isPAPIER() || signalementObject.isPLASTIQUE() || signalementObject.isMETAL() || signalementObject.isAUTRE()) {
            recap += ", composé de";
            part2 = true;
            if (signalementObject.isVERRE())
                recap += " verre,";
            if (signalementObject.isCARTON())
                recap += " carton,";
            if (signalementObject.isPAPIER())
                recap += " papier,";
            if (signalementObject.isPLASTIQUE())
                recap += " plastique,";
            if (signalementObject.isMETAL())
                recap += " métal,";
            if (signalementObject.isAUTRE())
                // TODO: 12/04/2020 a modifier en fonction de se qu'écrit l'utilisateur
                recap += " autre,";
        } else part2 = false;
        if (signalementObject.isGROS() || signalementObject.isPETIT()) {
            part3 = true;
            if (signalementObject.isGROS())
                recap += " mesurant plus de 30 cm";
            else
                recap += " mesurant moins de 30 cm";
        } else part3 = false;
    }


    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}

