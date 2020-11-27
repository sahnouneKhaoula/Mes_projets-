package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by youcef on 29/05/2018.
 */

public class Statistiques extends Activity {
    BD_sql bd_sql;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistique);
        bd_sql=new BD_sql(this);

    }

    public void jour(View view){


            Intent intent = new Intent(Statistiques.this,autre.class);
            startActivity(intent);
        }


    public void chercherevent(View view){
        Intent intent = new Intent(Statistiques.this,Semaine.class);
       // Bundle bundle = new Bundle();
        startActivity(intent);

    }

    public void statmois(View view){
        Intent intent = new Intent(Statistiques.this,mois.class);
        Bundle bundle = new Bundle();
        startActivity(intent);

    }
    public void annuels(View view){
        Intent intent = new Intent(Statistiques.this,annee.class);
        Bundle bundle = new Bundle();
        startActivity(intent);

    }
    public void main(View view){
        Intent intent=new Intent(Statistiques.this,MainActivity.class);
        startActivity(intent);
    }
    public void retour(View view)
    {
        Statistiques.this.finish();}
}
