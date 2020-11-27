package com.example.khaoula.gestionairedetemps;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by youcef on 17/05/2018.
 */

public class autre extends Activity {
    TextView stat_event_fait,stat_event_annule,stat_event_nfait,stat_event_total,degre;

    BD_sql bd_sql;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autre);
        //
        stat_event_nfait=findViewById(R.id.stat_event_nfait);
        stat_event_fait=findViewById(R.id.stat_event_fait);
        stat_event_annule=findViewById(R.id.stat_event_annule);
        stat_event_total=findViewById(R.id.stat_event_total);
        degre=findViewById(R.id.stat_degré);
        bd_sql=new BD_sql(this);
        //
        Calendar c=Calendar.getInstance();
        int i2=c.getTime().getDate();
        int i1= c.getTime().getMonth();
        int i= c.getTime().getYear()-100+2000;
        int mois=i1+1;
        String  da=i2+"/"+mois+"/"+i;
        int aff=bd_sql.nbra(da);

         int na =bd_sql.nbra(da),nn=bd_sql.nbrn(da),e=bd_sql.nbrt(da),to=bd_sql.nbrto(da);
         float d=(100*e)/to;
        // stat_event_annule.setText(na);
        String n=na+"";
        String n1=nn+"",n2=e+"";
       stat_event_annule.setText(n);
       stat_event_nfait.setText(n1);
       stat_event_fait.setText(n2);
       stat_event_total.setText(to+"");
        degre.setText(e+"%");
    }

        ////



    public void main(View view){
        Intent intent=new Intent(autre.this,MainActivity.class);
        startActivity(intent);
    }
    public void retour(View view)
    {
        autre.this.finish();}
}




