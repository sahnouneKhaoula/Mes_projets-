package com.example.khaoula.gestionairedetemps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by youcef on 02/06/2018.
 */

public class annee  extends Activity{
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
        int i= c.getTime().getYear()-100+2000;;
        String  da="%"+i;

        int na =bd_sql.nbrA(da);
        int nn=bd_sql.nbrN(da);
        int e=bd_sql.nbrT(da);
        int to=bd_sql.nbrTO(da);
        float d=(100*e)/to;
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
        Intent intent=new Intent(annee.this,MainActivity.class);
        startActivity(intent);
    }
    public void retour(View view)
    {
        annee.this.finish();}
}




