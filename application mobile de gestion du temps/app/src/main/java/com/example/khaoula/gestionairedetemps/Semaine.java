package com.example.khaoula.gestionairedetemps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by youcef on 03/06/2018.
 */

public class Semaine extends Activity {
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
        int na=0,nn=0,e=0,to=0;
        float d=0;

        SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
        Calendar today=Calendar.getInstance();


        Calendar sunday =(Calendar) today.clone();
        sunday.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        sunday.set(Calendar.HOUR_OF_DAY,0);
        sunday.set(Calendar.MINUTE,0);
        sunday.set(Calendar.SECOND,0);
        sunday.set(Calendar.MILLISECOND,0);
        String  da=sdf.format(sunday.getTime());
        String  str=da.substring(0,1).trim();
        String p1=null,p2=null;
        String  p = da;
        String  str1=p.substring(3,4).trim();
        String p3=p;
        if(str.equals("0")){
            p = da.substring(1,10);
            p3=p;

            str1=p.substring(2,3).trim();
            if(str1.equals("0")) {
                p1=p.substring(0,2);
                p2=p.substring(3,9);
                p3=p1+p2;
            }
        }
        if(p.substring(3,4).trim().equals("0")) {
            p1=p.substring(0,3);
            p2=p.substring(4,10);
            p3=p1+p2;
        }
        na=na+ bd_sql.nbra(p3);
        nn=nn+bd_sql.nbrn(p3);
        e=e+bd_sql.nbrt(p3);
        to=to+bd_sql.nbrto(p3);
        d=d+(100*e)/to;


        Calendar monday =(Calendar) today.clone();
        monday.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        monday.set(Calendar.HOUR_OF_DAY,0);
        monday.set(Calendar.MINUTE,0);
        monday.set(Calendar.SECOND,0);
        monday.set(Calendar.MILLISECOND,0);
        da=sdf.format(monday.getTime());
        str=da.substring(0,1).trim();
        p = da;
        str1=p.substring(3,4).trim();
        p3=p;
        if(str.equals("0")){
            p = da.substring(1,10);
            p3=p;

            str1=p.substring(2,3).trim();
            if(str1.equals("0")) {
                p1=p.substring(0,2);
                p2=p.substring(3,9);
                p3=p1+p2;
            }
        }
        if(p.substring(3,4).trim().equals("0")) {
            p1=p.substring(0,3);
            p2=p.substring(4,10);
            p3=p1+p2;
        }
        na=na+ bd_sql.nbra(p3);
        nn=nn+bd_sql.nbrn(p3);
        e=e+bd_sql.nbrt(p3);
        to=to+bd_sql.nbrto(p3);
        d=d+(100*e)/to;

        Calendar tuesday =(Calendar) today.clone();
        tuesday.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
        tuesday.set(Calendar.HOUR_OF_DAY,0);
        tuesday.set(Calendar.MINUTE,0);
        tuesday.set(Calendar.SECOND,0);
        tuesday.set(Calendar.MILLISECOND,0);
        da=sdf.format(tuesday.getTime());
        str=da.substring(0,1).trim();
        p = da;
        str1=p.substring(3,4).trim();
        p3=p;
        if(str.equals("0")){
            p = da.substring(1,10);
            p3=p;

            str1=p.substring(2,3).trim();
            if(str1.equals("0")) {
                p1=p.substring(0,2);
                p2=p.substring(3,9);
                p3=p1+p2;
            }
        }
        if(p.substring(3,4).trim().equals("0")) {
            p1=p.substring(0,3);
            p2=p.substring(4,10);
            p3=p1+p2;
        }
        na=na+ bd_sql.nbra(p3);
        nn=nn+bd_sql.nbrn(p3);
        e=e+bd_sql.nbrt(p3);
        to=to+bd_sql.nbrto(p3);
        d=d+(100*e)/to;

        Calendar wedesday =(Calendar) today.clone();
        wedesday.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
        wedesday.set(Calendar.HOUR_OF_DAY,0);
        wedesday.set(Calendar.MINUTE,0);
        wedesday.set(Calendar.SECOND,0);
        wedesday.set(Calendar.MILLISECOND,0);
        da=sdf.format(wedesday.getTime());
        str=da.substring(0,1).trim();
        p = da;
        str1=p.substring(3,4).trim();
        p3=p;
        if(str.equals("0")){
            p = da.substring(1,10);
            p3=p;

            str1=p.substring(2,3).trim();
            if(str1.equals("0")) {
                p1=p.substring(0,2);
                p2=p.substring(3,9);
                p3=p1+p2;
            }
        }
        if(p.substring(3,4).trim().equals("0")) {
            p1=p.substring(0,3);
            p2=p.substring(4,10);
            p3=p1+p2;
        }
        na=na+ bd_sql.nbra(p3);
        nn=nn+bd_sql.nbrn(p3);
        e=e+bd_sql.nbrt(p3);
        to=to+bd_sql.nbrto(p3);
        d=d+(100*e)/to;


        Calendar thursday =(Calendar) today.clone();
        thursday.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
        thursday.set(Calendar.HOUR_OF_DAY,0);
        thursday.set(Calendar.MINUTE,0);
        thursday.set(Calendar.SECOND,0);
        thursday.set(Calendar.MILLISECOND,0);
        da=sdf.format(thursday.getTime());
        str=da.substring(0,1).trim();
        p = da;
        str1=p.substring(3,4).trim();
        p3=p;
        if(str.equals("0")){
            p = da.substring(1,10);
            p3=p;

            str1=p.substring(2,3).trim();
            if(str1.equals("0")) {
                p1=p.substring(0,2);
                p2=p.substring(3,9);
                p3=p1+p2;
            }
        }
        if(p.substring(3,4).trim().equals("0")) {
            p1=p.substring(0,3);
            p2=p.substring(4,10);
            p3=p1+p2;
        }
        na=na+ bd_sql.nbra(p3);
        nn=nn+bd_sql.nbrn(p3);
        e=e+bd_sql.nbrt(p3);
        to=to+bd_sql.nbrto(p3);
        d=d+(100*e)/to;



        Calendar vendredi =(Calendar) today.clone();
        vendredi.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
        vendredi.set(Calendar.HOUR_OF_DAY,0);
        vendredi.set(Calendar.MINUTE,0);
        vendredi.set(Calendar.SECOND,0);
        vendredi.set(Calendar.MILLISECOND,0);
        da=sdf.format(vendredi.getTime());
        str=da.substring(0,1).trim();
        p = da;
        str1=p.substring(3,4).trim();
        p3=p;
        if(str.equals("0")){
            p = da.substring(1,10);
            p3=p;

            str1=p.substring(2,3).trim();
            if(str1.equals("0")) {
                p1=p.substring(0,2);
                p2=p.substring(3,9);
                p3=p1+p2;
            }
        }
        if(p.substring(3,4).trim().equals("0")) {
            p1=p.substring(0,3);
            p2=p.substring(4,10);
            p3=p1+p2;
        }

        na=na+ bd_sql.nbra(p3);
        nn=nn+bd_sql.nbrn(p3);
        e=e+bd_sql.nbrt(p3);
        to=to+bd_sql.nbrto(p3);
        d=d+(100*e)/to;


        Calendar saturday =(Calendar) today.clone();
        saturday.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
        saturday.set(Calendar.HOUR_OF_DAY,0);
        saturday.set(Calendar.MINUTE,0);
        saturday.set(Calendar.SECOND,0);
        saturday.set(Calendar.MILLISECOND,0);
        da=sdf.format(saturday.getTime());
        str=da.substring(0,1).trim();
        p = da;
        str1=p.substring(3,4).trim();
        p3=p;
        if(str.equals("0")){
            p = da.substring(1,10);
            p3=p;

            str1=p.substring(2,3).trim();
            if(str1.equals("0")) {
                p1=p.substring(0,2);
                p2=p.substring(3,9);
                p3=p1+p2;
            }
        }
        if(p.substring(3,4).trim().equals("0")) {
            p1=p.substring(0,3);
            p2=p.substring(4,10);
            p3=p1+p2;
        }

        na=na+ bd_sql.nbra(p3);
        nn=nn+bd_sql.nbrn(p3);
        e=e+bd_sql.nbrt(p3);
        to=to+bd_sql.nbrto(p3);
        d=d+(100*e)/to;


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
        Intent intent=new Intent(Semaine.this,MainActivity.class);
        startActivity(intent);
    }
    public void retour(View view)
    {
        Semaine.this.finish();}

}
