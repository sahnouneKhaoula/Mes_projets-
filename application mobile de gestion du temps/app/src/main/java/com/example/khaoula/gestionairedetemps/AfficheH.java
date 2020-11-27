package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by youcef on 15/05/2018.
 */

public class AfficheH extends Activity{
    ListView affichage ;
    private BD_sql bd_sql;
    TextView textView;String affic;
   ArrayList a=new ArrayList();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_jour);
        affichage=(ListView) findViewById(R.id.listedeseventparjour);
        textView=findViewById(R.id.textView2);
        bd_sql=new BD_sql(this);
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("list")){
                a=intent.getCharSequenceArrayListExtra("list");
            }
            if(intent.hasExtra("affic")) affic=intent.getStringExtra("affic");}
        textView.setText(affic);
        afficherlisteh(a);
    }
    public void afficherlisteh(ArrayList aff)
    {

        if (aff.isEmpty()) Toast.makeText(AfficheH.this, " pas d'evenement a cette date  ", Toast.LENGTH_SHORT).show();
        else {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aff);
            affichage.setAdapter(arrayAdapter);
            affichage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {String a= (String)affichage.getItemAtPosition(i);
                    int h1=a.indexOf("\n");
                    int h= a.lastIndexOf("\n");
                    String heur=a.substring(h).trim();
                    String date=a.substring(h1,h).trim();
                    String catego=bd_sql.afficheca(date,heur);
                    if(catego.equals("prise de medicament")){
                        Intent intent = new Intent(AfficheH.this,affiche2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("affic", affic);
                        bundle.putString("datec",date);
                        bundle.putString("heurc",heur);
                        intent.putExtras(bundle);
                        startActivity(intent);}
                    else if(catego.equals("rencontre")){
                        Intent intent = new Intent(AfficheH.this,affiche2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("affic", affic);
                        bundle.putString("datec",date);
                        bundle.putString("heurc",heur);
                        intent.putExtras(bundle);
                        startActivity(intent);}
                    else if(catego.equals("reunion")){
                        Intent intent = new Intent(AfficheH.this,affiche2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("affic", affic);
                        bundle.putString("datec",date);
                        bundle.putString("heurc",heur);
                        intent.putExtras(bundle);
                        startActivity(intent);}
                    else if(catego.equals("shoping")){
                        Intent intent = new Intent(AfficheH.this, ShopingA.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("affic",affic);
                        bundle.putString("datec",date);
                        bundle.putString("heurc",heur);
                        intent.putExtras(bundle);
                        startActivity(intent);}
                    else{
                        Intent intent = new Intent(AfficheH.this, AfficheListe.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("affic",affic);
                        bundle.putString("datec",date);
                        bundle.putString("heurc",heur);
                        intent.putExtras(bundle);
                        startActivity(intent);}


                }
            });
            affichage.getAdapter().toString();

        }
    }

    public void main(View view){
        Intent intent=new Intent(AfficheH.this,MainActivity.class);
        startActivity(intent);
    }
    public void retour(View view)
    { AfficheH.this.finish();}
}
