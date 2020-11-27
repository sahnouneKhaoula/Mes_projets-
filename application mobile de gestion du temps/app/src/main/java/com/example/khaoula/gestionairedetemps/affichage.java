package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by youcef on 09/05/2018.
 */

public class affichage extends Activity{
    BD_sql bd_sql;AfficheJour afficheJour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage);
        bd_sql = new BD_sql(this);
        afficheJour=new AfficheJour();
    }

    public void afficheJ(View v){
        Calendar c=Calendar.getInstance();
        int i2=c.getTime().getDate();
        int i1= c.getTime().getMonth();
        int i= c.getTime().getYear()-100+2000;
        int mois=i1+1;
       String  da=i2+"/"+mois+"/"+i;
        ArrayList aff=bd_sql.affiche(da);
        if (aff.isEmpty())
            Toast.makeText(affichage.this," ya pas d'evenement aujourd'hui", Toast.LENGTH_SHORT).show();

        else  {
            //a.setA(+i2 + "/" + mois + "/" + i);
            Intent intent = new Intent(affichage.this, AfficheJour.class);
            Bundle bundle = new Bundle();
            bundle.putString("date", da);
            bundle.putString("affic","Affichage journalier");
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    @SuppressWarnings("UseOfObsoleteDateTimeApi")
    public void afficheS(View v){
        ArrayList aff=new ArrayList();
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
        aff.addAll(bd_sql.affiche(p3));


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
        aff.addAll(bd_sql.affiche(p3));

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
        aff.addAll(bd_sql.affiche(p3));
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
        aff.addAll(bd_sql.affiche(p3));

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
        aff.addAll(bd_sql.affiche(p3));



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

        aff.addAll(bd_sql.affiche(p3));

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
        aff.addAll(bd_sql.affiche(p3));

        if (aff.isEmpty())
            Toast.makeText(affichage.this, "ya pas des evenements cette semaine", Toast.LENGTH_SHORT).show();
        else{
        Intent intent = new Intent(affichage.this,AfficheH.class);
        Bundle bundle = new Bundle();
        bundle.putCharSequenceArrayList("list",aff);
        bundle.putString("affic","Affichage hebdomadaire");
        intent.putExtras(bundle);
        startActivity(intent);}

        /*if (aff.isEmpty())
            Toast.makeText(affichage.this, da, Toast.LENGTH_SHORT).show();*/
       // else
         //   originale.addAll(bd_sql.affiche(p3));


    }
    public void afficheM(View v){

        Calendar c=Calendar.getInstance();
        int i= c.getTime().getMonth()+1;
        String  da="%/"+i+"%";
        ArrayList aff=bd_sql.afficheA(da);
        if (aff.isEmpty())
            Toast.makeText(affichage.this,"ya pas des evenement ce mois ", Toast.LENGTH_SHORT).show();

        else  {
            //a.setA(+i2 + "/" + mois + "/" + i);
            Intent intent = new Intent(affichage.this,AfficheAnne.class);
            Bundle bundle = new Bundle();
            bundle.putString("date", da);
            bundle.putString("affic","Affichage mensuel");
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
    public void afficheA(View v){

        Calendar c=Calendar.getInstance();
        int i= c.getTime().getYear()-100+2000;;
        String  da="%"+i;
        ArrayList aff=bd_sql.afficheA(da);
        if (aff.isEmpty())
            Toast.makeText(affichage.this,"ya pas des evenement cette année ", Toast.LENGTH_SHORT).show();

        else  {
            //a.setA(+i2 + "/" + mois + "/" + i);
            Intent intent = new Intent(affichage.this,AfficheAnne.class);
            Bundle bundle = new Bundle();
            bundle.putString("date", da);
            bundle.putString("affic","Affichage annuel");
            intent.putExtras(bundle);
            startActivity(intent);
        }


    }
    public void afficheSO(View v){
        String da="shoping";
        ArrayList aff=bd_sql.affichec(da);
        if (aff.isEmpty())
            Toast.makeText(affichage.this," ya pas du shopinng a faire :) ", Toast.LENGTH_SHORT).show();

        else  {
            //a.setA(+i2 + "/" + mois + "/" + i);
            Intent intent = new Intent(affichage.this, AffichageCategor.class);
            Bundle bundle = new Bundle();
            bundle.putString("categ", da);
            bundle.putString("affic","Shopping");
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
    public void afficheP(View v){
        String da="prise de medicament";
        ArrayList aff=bd_sql.affichec(da);
        if (aff.isEmpty())
            Toast.makeText(affichage.this, " ya pas de prise de médicament", Toast.LENGTH_SHORT).show();

        else  {
            //a.setA(+i2 + "/" + mois + "/" + i);
            Intent intent = new Intent(affichage.this, AffichageCategor.class);
            Bundle bundle = new Bundle();
            bundle.putString("categ", da);
            bundle.putString("affic","Prise de medicament");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    public void afficheSP(View v){
        String da="sport";
        ArrayList aff=bd_sql.affichec(da);
        if (aff.isEmpty())
            Toast.makeText(affichage.this,"ya pas de sport a faire", Toast.LENGTH_SHORT).show();

        else  {
            //a.setA(+i2 + "/" + mois + "/" + i);
            Intent intent = new Intent(affichage.this, AffichageCategor.class);
            Bundle bundle = new Bundle();
            bundle.putString("categ", da);
            bundle.putString("affic","Sport");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    public void afficheTV(View v){
        String da="programme_tv";
        ArrayList aff=bd_sql.affichec(da);
        if (aff.isEmpty())
            Toast.makeText(affichage.this, "ya pas de programme TV", Toast.LENGTH_SHORT).show();

        else  {
            //a.setA(+i2 + "/" + mois + "/" + i);
            Intent intent = new Intent(affichage.this, AffichageCategor.class);
            Bundle bundle = new Bundle();
            bundle.putString("categ", da);
            bundle.putString("affic","Programme_TV");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    public void afficheAN(View v){
        String da="anniversaire";
        ArrayList aff=bd_sql.affichec(da);
        if (aff.isEmpty())
            Toast.makeText(affichage.this, "ya pas d'anniversaire", Toast.LENGTH_SHORT).show();

        else  {
            //a.setA(+i2 + "/" + mois + "/" + i);
            Intent intent = new Intent(affichage.this, AffichageCategor.class);
            Bundle bundle = new Bundle();
            bundle.putString("categ", da);
            bundle.putString("affic","Anniversaire");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    public void afficheRE(View v){
        String da="rencontre";
        ArrayList aff=bd_sql.affichec(da);
        if (aff.isEmpty())
            Toast.makeText(affichage.this,"ya pas de  rencontre ", Toast.LENGTH_SHORT).show();

        else  {
            //a.setA(+i2 + "/" + mois + "/" + i);
            Intent intent = new Intent(affichage.this, AffichageCategor.class);
            Bundle bundle = new Bundle();
            bundle.putString("categ",da);
            bundle.putString("affic","Rencontre");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    public void afficheRu(View v){
        String da="reunion";
        ArrayList aff=bd_sql.affichec(da);
        if (aff.isEmpty())
            Toast.makeText(affichage.this,"ya pas de reunion  ", Toast.LENGTH_SHORT).show();

        else  {
            //a.setA(+i2 + "/" + mois + "/" + i);
            Intent intent = new Intent(affichage.this, AffichageCategor.class);
            Bundle bundle = new Bundle();
            bundle.putString("categ",da);
            bundle.putString("affic","Reunion");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void onClick(View v){
        Intent intent = new Intent(this, Agendat.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
    public void main(View view){
        Intent intent=new Intent(affichage.this,MainActivity.class);
        startActivity(intent);
    }
    public void retour(View view)
    {
        affichage.this.finish();}


}
