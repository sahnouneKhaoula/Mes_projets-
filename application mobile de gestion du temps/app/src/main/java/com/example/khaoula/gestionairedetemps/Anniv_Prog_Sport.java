package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

public class Anniv_Prog_Sport extends Activity {
    Button annuler, ajouter;

    private EditText titre, date, heur, description, qte;
    private Spinner repetition;
    private Switch critique;
    private BD_sql bd_sql;
    private String a1 = "";
    int h, m, dv = 0, hv = 0;
    DatePicker datee;

    public Anniv_Prog_Sport() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anniv_prog_sport);
        //
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("catego")) {
                a1 = intent.getStringExtra("catego");
            }
        }
        ////

        bd_sql = new BD_sql(this);
        final Calendar c = Calendar.getInstance();
        final int année = c.get(Calendar.YEAR);
        final int mois = c.get(Calendar.MONTH);
        final int jour = c.get(Calendar.DAY_OF_MONTH);
        final int heure = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        final EditText txtTime = (EditText) findViewById(R.id.editText_time);
        final EditText txtdate = (EditText) findViewById(R.id.editText_date);

        ////////////////// heure

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Anniv_Prog_Sport.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        ///i=heur, i1=minute
                        if (i >= heure) {
                            if (i1 >= minute) hv = 1;
                        } else {
                            hv = 0;
                        }
                        txtTime.setText(i + ":" + i1);
                        h = i;
                        m = i1;
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
                final DatePickerDialog datePickerDialog = new DatePickerDialog(Anniv_Prog_Sport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int a = i1 + 1;
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
        titre = (EditText) findViewById(R.id.editText_Titre);
        date = (EditText) findViewById(R.id.editText_date);
        heur = (EditText) findViewById(R.id.editText_time);
        description = (EditText) findViewById(R.id.editText_description);
        critique = (Switch) findViewById(R.id.switch_critique_event);
        qte = (EditText) findViewById(R.id.editText_qte);
        repetition = (Spinner) findViewById(R.id.editText_répéter);

        ArrayList exempleList = new ArrayList();
        exempleList.add("Une fois");
        exempleList.add("Tous les jours");



		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, exempleList);

        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        repetition.setAdapter(adapter);
    }


    //Fonction annuler l'ajout
    public void annuler(View view) {
        //affichage d'un message
        Toast.makeText(Anniv_Prog_Sport.this, "Ajout annulé", Toast.LENGTH_SHORT).show();
        //retour à l'activité principale
     Anniv_Prog_Sport.this.finish();

    }

    //Fonction ajouter event rencontre
    public void ajouter(View v) {

        String c;
        if (critique.isChecked()) {
            c = "oui";
        } else {
            c = "non";}
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

            //pour la notification
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, h);
            calendar.set(Calendar.MINUTE, m);
            calendar.set(Calendar.SECOND, 0);

            String a = titre.getText().toString();
            if (a.equals("")) Toast.makeText(Anniv_Prog_Sport.this, "Attention : Il faut saisir un titre !  ", Toast.LENGTH_SHORT).show();
            else if (date.getText().toString().equals("")) Toast.makeText(Anniv_Prog_Sport.this, "Attention : Il faut saisir une date !  ", Toast.LENGTH_SHORT).show();
            else if (heur.getText().toString().equals(""))
                Toast.makeText(Anniv_Prog_Sport.this, "Attention : Il faut saisir une heure ! ", Toast.LENGTH_SHORT).show();
            else {
                if (dv == 0) {Toast.makeText(getBaseContext(), " Date erronée  ", Toast.LENGTH_SHORT).show();
                    date.setText("");}

                else if (hv == 0) {
                    Toast.makeText(getBaseContext(), " Heure erronée  ", Toast.LENGTH_SHORT).show();
                    heur.setText("");
                } else {
                    //appel de la fonction d'ajout de la base de données
                    boolean resultat = bd_sql.incertEvent(titre.getText().toString(), date.getText().toString(), heur.getText().toString(), description.getText().toString(), c, "", repetition.getSelectedItem().toString(), a1, "NON FAIT");
                    // BD_sql.incertEvent("tire","dae","hur","desciption","critique","categorie","repetition");
                    if (resultat == true) {
                        Toast.makeText(Anniv_Prog_Sport.this, "Evenement ajouté ", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Reunion_Rencontre.this,da, Toast.LENGTH_SHORT).show();
                        Intent alertIntent = new Intent(getApplicationContext(), AlertReceiver.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("title", titre.getText().toString());
                        alertIntent.putExtras(bundle);
                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                        if (repetition.getSelectedItem().toString().equals("Tous les jours"))
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, PendingIntent.getBroadcast(getApplicationContext(), 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));
                        if (repetition.getSelectedItem().toString().equals("Une fois")) alarmManager.set(AlarmManager.RTC_WAKEUP, c2.getTimeInMillis(), PendingIntent.getBroadcast(getApplicationContext(), 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));

                        //retour a la'activite principale
                        Intent intent = new Intent(Anniv_Prog_Sport.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Anniv_Prog_Sport.this, " Attention : Vous ne pouvez planifier plus d'un évènement en même temps !   ", Toast.LENGTH_SHORT).show();
                        date.setText("");
                        heur.setText("");
                    }

                }
            }
        }


    }
