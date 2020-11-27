package com.example.khaoula.gestionairedetemps;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CalendarView calenda;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter ;
    BD_sql bd_sql;
    public String da;
    private ActionBarDrawerToggle mToggle;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
        bd_sql=new BD_sql(this);}

    public void ajouterevent(View view) {
        Intent intent = new Intent(MainActivity.this, Button_Event.class);
        startActivity(intent);
        }

    public void chercherevent(View view) {
        Intent intent = new Intent(this, Cherchevent.class);
        startActivity(intent);

    }


 public void affichage(View view){
     Intent intent = new Intent(this, affichage.class);
     startActivity(intent);
 }

    //fonction qui permet d'ouvrir la fenêtre des informations
    public void ouvririnformations(View v){
        Intent intent = new Intent(MainActivity.this,Informations.class);
        startActivity(intent);
    }
    //fonction qui permet d'ouvrir la fenêtre des statistiques
    public void ouvrirstatistiques(View v){
        Intent intent = new Intent(MainActivity.this,Statistiques.class);
        startActivity(intent);
    }
}
