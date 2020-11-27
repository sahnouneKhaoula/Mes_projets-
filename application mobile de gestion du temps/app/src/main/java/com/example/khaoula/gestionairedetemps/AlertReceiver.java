package com.example.khaoula.gestionairedetemps;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by youcef on 06/05/2018.
 */

public class AlertReceiver extends BroadcastReceiver {
    BD_sql bd_sql;
    public AlertReceiver() {
    }

    public AlertReceiver(String titree) {
        this.titree = titree;
    }

    public static final String NOTIFICATION = "Notification";
    private String titree;
    String  str;
    String se="titre";
    @Override

    public void onReceive(Context context, Intent intent) {
        //intent.getIntArrayExtra("heure");
        Calendar c=Calendar.getInstance();
        int i2=c.getTime().getDate();
        int i1= c.getTime().getMonth();
        int i= c.getTime().getYear()-100+2000;
        int mois=i1+1;
        String  date=i2+"/"+mois+"/"+i;
        bd_sql= new BD_sql(context);
        if(bd_sql.affichedateS(date)==null)
        {  bd_sql.incerstat(date);}
        else {bd_sql.modifiernbrto(date);
            bd_sql.modifiernbrn(date);
        }
        if (intent != null) {

            if (intent.hasExtra("title")) {
              str = intent.getStringExtra("title");

            }
if(intent.hasExtra("cat")){
               titree=intent.getStringExtra("cat");
}
        }

        Intent intent1=new Intent(context,Cherchevent.class);

PendingIntent notification = PendingIntent.getActivity(context,0,intent1,0);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notif)
                .setContentTitle("N'oublier pas "+ str)
                .setContentText(" Si vous allez faire "+str+" Cliquez ici pour cherchez "+str+" et changer son statut de non fait a fait pour évaluer les statistique");


        builder.setContentIntent(notification);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setAutoCancel(true);
        NotificationManager mm =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mm.cancel(1);
        mm.notify(1,builder.build());


    }
    public String getTitree() {
        return this.titree;
    }

}
