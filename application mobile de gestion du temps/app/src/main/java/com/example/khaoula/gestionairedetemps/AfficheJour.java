package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by youcef on 19/03/2018.
 */

public class AfficheJour extends Activity{
    ListView affichage ;
    private BD_sql bd_sql;
    private String a="";
    TextView textView;String affic;

    public AfficheJour() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_jour);
        textView=findViewById(R.id.textView2);
        affichage=(ListView) findViewById(R.id.listedeseventparjour);
        bd_sql=new BD_sql(this);
        Intent intent=getIntent();
        if(intent!=null){
       if(intent.hasExtra("date")){
           a=intent.getStringExtra("date");
       }
            if(intent.hasExtra("affic")) affic=intent.getStringExtra("affic");}
        textView.setText(affic);
        afficherliste();


    }
    public void setA(String a) {
        this.a = a;
    }
    public void afficherliste ()
    {

        ArrayList aff=bd_sql.affiche(a);
        if (aff.isEmpty()) Toast.makeText(AfficheJour.this, " pas d'evenement a cette date  ", Toast.LENGTH_SHORT).show();
            else {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aff);
            affichage.setAdapter(arrayAdapter);
            affichage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String a= (String)affichage.getItemAtPosition(i);
                    int h1=a.indexOf("\n");
                    int h= a.lastIndexOf("\n");
                    String heur=a.substring(h).trim();
                    String date=a.substring(h1,h).trim();
                    String catego=bd_sql.afficheca(date,heur);
                    if(catego.equals("prise de medicament")){
                        Intent intent = new Intent(AfficheJour.this,affiche2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("affic", affic);
                        bundle.putString("datec",date);
                        bundle.putString("heurc",heur);
                        intent.putExtras(bundle);
                        startActivity(intent);}
                    else if(catego.equals("rencontre")){
                        Intent intent = new Intent(AfficheJour.this,affiche2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("affic", affic);
                        bundle.putString("datec",date);
                        bundle.putString("heurc",heur);
                        intent.putExtras(bundle);
                        startActivity(intent);}
                    else if(catego.equals("reunion")){
                        Intent intent = new Intent(AfficheJour.this,affiche2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("affic", affic);
                        bundle.putString("datec",date);
                        bundle.putString("heurc",heur);
                        intent.putExtras(bundle);
                        startActivity(intent);}
                    else if(catego.equals("shoping")){
                        Intent intent = new Intent(AfficheJour.this, ShopingA.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("affic",affic);
                        bundle.putString("datec",date);
                        bundle.putString("heurc",heur);
                        intent.putExtras(bundle);
                        startActivity(intent);}
                    else{
                        Intent intent = new Intent(AfficheJour.this, AfficheListe.class);
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
        Intent intent=new Intent(AfficheJour.this,MainActivity.class);
        startActivity(intent);
    }
    public void retour(View view)
    { AfficheJour.this.finish();}
}

