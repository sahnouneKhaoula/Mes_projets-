package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;


/**
 * Created by youcef on 16/03/2018.
 */

public class Cherchevent extends Activity {
    ListView affichage;
    private BD_sql bd_sql;
    TextView date, nom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chercherevent);
        affichage = (ListView) findViewById(R.id.listedesevent);
        bd_sql = new BD_sql(this);
        //afficherliste();
        date = (TextView) findViewById(R.id.EditText_chercherDate);
        nom = (TextView) findViewById(R.id.EditText_chercherNom);


    }

    public void chercher(View v) {
        if (!(date.getText().toString().equals("")) && (nom.getText().toString().equals(""))) {
            ArrayList aff = bd_sql.affiche(date.getText().toString());
            if (aff.isEmpty()) Toast.makeText(Cherchevent.this, "pas d'evenement", Toast.LENGTH_SHORT).show();
            else {
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aff);
                affichage.setAdapter(arrayAdapter);
                affichage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {
                        String a = (String) affichage.getItemAtPosition(i);
                        int h1 = a.indexOf("\n");
                        int h = a.lastIndexOf("\n");
                        String heur = a.substring(h).trim();
                        String date = a.substring(h1, h).trim();
                        String catego = bd_sql.afficheca(date, heur);
                        if (catego.equals("prise de medicament")) {
                            Intent intent = new Intent(Cherchevent.this, affiche2.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("datec", date);
                            bundle.putString("heurc", heur);
                            bundle.putString("affic", "cherch");
                            bundle.putString("titre", nom.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else if (catego.equals("shoping")) {
                            Intent intent = new Intent(Cherchevent.this, ShopingA.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("datec", date);
                            bundle.putString("heurc", heur);
                            bundle.putString("affic", "cherch");
                            bundle.putString("titre", nom.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(Cherchevent.this, AfficheListe.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("datec", date);
                            bundle.putString("heurc", heur);
                            bundle.putString("affic", "cherch");
                            bundle.putString("titre", nom.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }


                    }
                });

                affichage.getAdapter().toString();
            }
        } else if ((date.getText().toString().equals("")) && (nom.getText().toString() != "")) {
            ArrayList aff = bd_sql.afficheN(nom.getText().toString());
            if (aff.isEmpty()) Toast.makeText(Cherchevent.this, "pas d'evenement", Toast.LENGTH_SHORT).show();
            else {
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aff);
                affichage.setAdapter(arrayAdapter);
                affichage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {
                        String a = (String) affichage.getItemAtPosition(i);
                        int h1 = a.indexOf("\n");
                        int h = a.lastIndexOf("\n");
                        String heur = a.substring(h).trim();
                        String date = a.substring(h1, h).trim();
                        //  Toast.makeText( AffichageCategor.this,bd_sql.afficheca(date,heur), Toast.LENGTH_SHORT).show();
                        //  Toast.makeText( AffichageCategor.this,h, Toast.LENGTH_SHORT).show();
                        String catego = bd_sql.afficheca(date, heur);
                        if (catego.equals("prise de medicament")) {
                            Intent intent = new Intent(Cherchevent.this, affiche2.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("datec", date);
                            bundle.putString("heurc", heur);
                            bundle.putString("affic", "cherch");
                            bundle.putString("titre", nom.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else if (catego.equals("shoping")) {
                            Intent intent = new Intent(Cherchevent.this, ShopingA.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("datec", date);
                            bundle.putString("heurc", heur);
                            bundle.putString("affic", "cherch");
                            bundle.putString("titre", nom.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(Cherchevent.this, AfficheListe.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("datec", date);
                            bundle.putString("heurc", heur);
                            bundle.putString("affic", "cherch");
                            bundle.putString("titre", nom.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }


                    }
                });
                affichage.getAdapter().toString();
            }
        } else if ((date.getText().toString().equals("")) && (nom.getText().toString().equals(""))) {
            Toast.makeText(Cherchevent.this, "il faut saisir un titre ou bien une date ou bien les deux", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList aff = bd_sql.affiche2(date.getText().toString(), nom.getText().toString());
            if (aff.isEmpty()) Toast.makeText(Cherchevent.this, "pas d'evenement", Toast.LENGTH_SHORT).show();
            else {
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aff);
                affichage.setAdapter(arrayAdapter);
                affichage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {
                        String a = (String) affichage.getItemAtPosition(i);
                        int h1 = a.indexOf("\n");
                        int h = a.lastIndexOf("\n");
                        String heur = a.substring(h).trim();
                        String date = a.substring(h1, h).trim();
                        String catego = bd_sql.afficheca(date, heur);
                        if (catego.equals("prise de medicament")) {
                            Intent intent = new Intent(Cherchevent.this, affiche2.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("datec", date);
                            bundle.putString("heurc", heur);
                            bundle.putString("affic", "cherch");
                            bundle.putString("titre", nom.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else if (catego.equals("shoping")) {
                            Intent intent = new Intent(Cherchevent.this, ShopingA.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("datec", date);
                            bundle.putString("heurc", heur);
                            bundle.putString("affic", "cherch");
                            bundle.putString("titre", nom.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(Cherchevent.this, AfficheListe.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("datec", date);
                            bundle.putString("heurc", heur);
                            bundle.putString("affic", "cherch");
                            bundle.putString("titre", nom.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }


                    }
                });
                affichage.getAdapter().toString();

            }
        }
    }

    /*  public void afficherliste()
      {
          ArrayList aff=bd_sql.afficher();
          ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,aff);
          affichage.setAdapter(arrayAdapter);
         affichage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 String a= (String)affichage.getItemAtPosition(i);
                 int h1=a.indexOf("\n");
                 int h= a.lastIndexOf("\n");
                 String heur=a.substring(h).trim();
                 String date=a.substring(h1,h).trim();
                 //  Toast.makeText( AffichageCategor.this,bd_sql.afficheca(date,heur), Toast.LENGTH_SHORT).show();
                 //  Toast.makeText( AffichageCategor.this,h, Toast.LENGTH_SHORT).show();
                 String catego=bd_sql.afficheca(date,heur);
                 if(catego.equals("prise de medicament")){
                     Intent intent = new Intent(Cherchevent.this,affiche2.class);
                     Bundle bundle = new Bundle();
                     bundle.putString("datec",date);
                     bundle.putString("heurc",heur);
                     bundle.putString("affic","cherch");
                     intent.putExtras(bundle);
                     startActivity(intent);}
                 else if(catego.equals("shoping")){
                     Intent intent = new Intent(Cherchevent.this, ShopingA.class);
                     Bundle bundle = new Bundle();
                     bundle.putString("datec",date);
                     bundle.putString("heurc",heur);
                     bundle.putString("affic","cherch");
                     intent.putExtras(bundle);
                     startActivity(intent);}
                 else{
                     Intent intent = new Intent(Cherchevent.this, AfficheListe.class);
                     Bundle bundle = new Bundle();
                     bundle.putString("datec",date);
                     bundle.putString("heurc",heur);
                     bundle.putString("affic","cherch");
                     intent.putExtras(bundle);
                     startActivity(intent);}


             }
         });
          affichage.getAdapter().toString();

      }*/

    public void onClick(View v){
        Intent intent = new Intent(this, Agendat.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
    }
    public void main(View view){
        Intent intent=new Intent(Cherchevent.this,MainActivity.class);
        startActivity(intent);
    }
    public void retour(View view)
    {
        Cherchevent.this.finish();}


}
