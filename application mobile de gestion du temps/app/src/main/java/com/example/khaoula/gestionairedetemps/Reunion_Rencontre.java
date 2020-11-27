package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by youcef on 09/04/2018.
 */

public class Reunion_Rencontre extends Activity {
    Button annuler, ajouter;

    private EditText titre, date, heur,lieu, description;
    private Spinner repetition;
    private Switch critique;
    private BD_sql bd_sql;
    private String a1="";
    int h,m,dv = 0, hv = 0;

    public Reunion_Rencontre() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reunion_rencontre);

        //
        bd_sql = new BD_sql(this);
        final Calendar c = Calendar.getInstance();
        final int année = c.get(Calendar.YEAR);
        final int mois = c.get(Calendar.MONTH);
        final int jour = c.get(Calendar.DAY_OF_MONTH);
        final int heure = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        final EditText txtTime = (EditText) findViewById(R.id.editText_time);
        final EditText txtdate = (EditText) findViewById(R.id.editText_date);
///
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("catego")){
                a1=intent.getStringExtra("catego");
            }}

        ////////////////// heure

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Reunion_Rencontre.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        ///i=heur, i1=minute
                        if (i >= heure) {
                            if (i1 >= minute) hv = 1;
                        } else {
                            hv = 0;
                        }
                        txtTime.setText(i + ":" + i1); h=i;m=i1;
                    }
                }, heure, minute, true);
                timePickerDialog.setTitle("Choisir temps");
                timePickerDialog.show();

            }
        });

//////////date

        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Reunion_Rencontre.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int a= i1+1;
                        txtdate.setText(i2 + "/" + a + "/" + i);
                        if (i >= année) {
                            if (a >= mois) {
                                if (i2 >= jour) {
                                    dv = 1;
                                } else Toast.makeText(getBaseContext(), " Date erronée : Date que vous avez choisi est déjà dépassée ", Toast.LENGTH_SHORT).show();
                            } else Toast.makeText(getBaseContext(), " Date erronée : Date que vous avez choisi est déjà dépassée ", Toast.LENGTH_SHORT).show();
                        } else {
                            dv = 0;
                            Toast.makeText(getBaseContext(), " Date erronée : Date que vous avez choisi est déjà dépassée ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, année, mois, jour);
                datePickerDialog.setTitle("Choisir date");
                datePickerDialog.show();
            }
        });


///bdd
        annuler = (Button) findViewById(R.id.annuler_ajout_event);
        ajouter = (Button) findViewById(R.id.annuler_ajout_event);
        lieu=findViewById(R.id.lieu);
        titre = (EditText) findViewById(R.id.editText_Titre);
        date = (EditText) findViewById(R.id.editText_date);
        heur = (EditText) findViewById(R.id.editText_time);
        description = (EditText) findViewById(R.id.editText_description);
        critique = (Switch) findViewById(R.id.switch_critique_event);

        repetition = (Spinner) findViewById(R.id.editText_répéter);

        ArrayList exempleList = new ArrayList();
        exempleList.add("Une fois");
        exempleList.add("Tous les jours");



		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList
        );

        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        repetition.setAdapter(adapter);

    }


    //Fonction annuler l'ajout
    public void annuler(View view) {
        //affichage d'un message
        Toast.makeText(Reunion_Rencontre.this, "Ajout annulé  ", Toast.LENGTH_SHORT).show();
        //retour à l'activité principale
        Intent intent = new Intent(Reunion_Rencontre.this, MainActivity.class);
        startActivity(intent);


    }

    //Fonction ajouter event rencontre
   public void ajouter(View v) {
       //pour la notification
       Calendar calendar = Calendar.getInstance();
       calendar.set(Calendar.HOUR_OF_DAY, h);
       calendar.set(Calendar.MINUTE, m);
       calendar.set(Calendar.SECOND, 0);
    /*  Calendar c1=Calendar.getInstance();
       int i2=c1.getTime().getHours();
       int i1= c1.getTime().getMinutes();
       int i= c1.getTime().getDate();
       int mois=i1+1;*/
       String c;
       if (critique.isChecked()) {
           c = "oui";
       } else {
           c = "non";
       }


       Date di = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
       try {
           di = sdf.parse(date.getText().toString());
       } catch (ParseException e) {
           e.printStackTrace();
       }
       Calendar c2 = Calendar.getInstance();
       c2.setTime(di);
       c2.set(Calendar.HOUR_OF_DAY, h);
       c2.set(Calendar.MINUTE, m);
       c2.set(Calendar.SECOND, 0);

       //
       String d = date.getText().toString();
       String a = titre.getText().toString();
       if (a.equals(""))
           Toast.makeText(Reunion_Rencontre.this, "Attention : Il faut saisir un titre ! ", Toast.LENGTH_SHORT).show();
       else if (date.getText().toString().equals(""))
           Toast.makeText(Reunion_Rencontre.this, "Attention : Il faut saisir une date ! ", Toast.LENGTH_SHORT).show();
       else if (heur.getText().toString().equals(""))
           Toast.makeText(Reunion_Rencontre.this, "Attention : Il faut saisir une heure ! ", Toast.LENGTH_SHORT).show();
       else {
           //appel de la fonction d'ajout de la base de données

           if (dv == 0) {
               Toast.makeText(getBaseContext(), " Date erronée  ", Toast.LENGTH_SHORT).show();
               date.setText("");
           } else if (hv == 0) {
               Toast.makeText(getBaseContext(), " Heure erronée ", Toast.LENGTH_SHORT).show();
               heur.setText("");
           } else {
               boolean resultat = bd_sql.incertEvent(a, date.getText().toString(), heur.getText().toString(), description.getText().toString(), c, lieu.getText().toString(), repetition.getSelectedItem().toString(), a1, "NON FAIT");
               if (resultat == true) {

                   //bd_sql.modifiernbrto(date.getText().toString());
                   Toast.makeText(Reunion_Rencontre.this, "Evenement ajouté ", Toast.LENGTH_SHORT).show();
                   Intent alertIntent = new Intent(getApplicationContext(), AlertReceiver.class);
                   Bundle bundle = new Bundle();
                   bundle.putString("title", titre.getText().toString());
                   alertIntent.putExtras(bundle);
                   AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                   if (repetition.getSelectedItem().toString().equals("Tous les jours"))
                       alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(getApplicationContext(), 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));
                   if (repetition.getSelectedItem().toString().equals("Une fois"))
                       alarmManager.set(AlarmManager.RTC_WAKEUP, c2.getTimeInMillis(), PendingIntent.getBroadcast(getApplicationContext(), 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));

                   //retour a la'activite principale
                   Intent intent = new Intent(Reunion_Rencontre.this, MainActivity.class);
                   startActivity(intent);
               } else {
                   Toast.makeText(Reunion_Rencontre.this, " Attention : Vous ne pouvez planifier plus d'un évènement en même temps ! ", Toast.LENGTH_SHORT).show();
                   date.setText("");
                   heur.setText("");

               }
           }
       }


   }

}



// on a oublier la durée