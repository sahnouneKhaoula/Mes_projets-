package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by youcef on 04/03/2018.
 */

public class SupprimetEvent extends Activity {
    private EditText titre,qte;
    ListView affichage ;
    private BD_sql bd_sql;
   private  String str = "", he = "";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produit);
        titre = (EditText) findViewById(R.id.edit_nom_prod);
        qte = (EditText) findViewById(R.id.edit_qte_prod);
        affichage=(ListView) findViewById(R.id.listproduit);
        bd_sql=new BD_sql(this);
        Intent intent = getIntent();
        if (intent != null) {

            if (intent.hasExtra("date")) {
                str = intent.getStringExtra("date");
            }
            if (intent.hasExtra("heure")) {
                he = intent.getStringExtra("heure");
            }
        }


    }



    //Fonction annuler l'ajout
    public void terminer(View v) {

        Toast.makeText(SupprimetEvent.this, "ajout des produit terminer", Toast.LENGTH_SHORT).show();
        //retour à l'activité principale
        Intent intent = new Intent(SupprimetEvent.this, MainActivity.class);
        startActivity(intent);
    }

    public void afficherliste ()
    {
        ArrayList aff=bd_sql.affich(str,he);
        if (aff.isEmpty()) Toast.makeText(SupprimetEvent.this, "pas de produit à cet événement ", Toast.LENGTH_SHORT).show();
        else {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aff);
            affichage.setAdapter(arrayAdapter);
            affichage.getAdapter().toString();

        }
    }

    public void insererP(View v){
        //Toast.makeText(SupprimetEvent.this, "Produit non ajouter ☻ ", Toast.LENGTH_SHORT).show();
       boolean resultat=bd_sql.incerProd(str,he,titre.getText().toString(),qte.getText().toString());
        if (resultat == true) {
            Toast.makeText(SupprimetEvent.this, "Produit ajouter ☻ ", Toast.LENGTH_SHORT).show();
            afficherliste ();
        }
        else
            Toast.makeText(SupprimetEvent.this, "Produit non ajouter ☻ ", Toast.LENGTH_SHORT).show();

        }
}
