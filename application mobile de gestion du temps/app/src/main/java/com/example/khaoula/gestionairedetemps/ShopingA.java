package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by youcef on 25/05/2018.
 */

public class ShopingA  extends Activity {
    EditText titr,descriptio;
    Spinner repetr,sat;
    TextView categ,stat,dat,heu;
    Button qtq;
    Switch critiqu;
    BD_sql bd_sql;
    String d,h;
    ListView affichage ;
    TextView rep;
    String affic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppinga);
        rep=findViewById(R.id.rep);
        bd_sql=new BD_sql(this);
        affichage=(ListView) findViewById(R.id.listproduit);
        titr=findViewById(R.id.editText_Titre);
        dat=findViewById(R.id.editText_date);
        heu=findViewById(R.id.editText_time);
        descriptio=findViewById(R.id.editText_description);
        critiqu=findViewById(R.id.switch_critique_event);
        repetr=findViewById(R.id.editText_répéter);
        categ=findViewById(R.id.editText_cate);
        stat=findViewById(R.id.editText_statut);
        qtq=findViewById(R.id.qte2);
        ArrayList exempleList = new ArrayList();
        exempleList.add("Une fois");
        exempleList.add("Tous les jours");



		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList
        );

        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        repetr.setAdapter(adapter);

        sat=findViewById(R.id.spin);
        ArrayList statleList = new ArrayList();
        statleList.add("NON FAIT");
        statleList.add("FAIT");

        ArrayAdapter adapter1 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                statleList
        );

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sat.setAdapter(adapter1);

        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("datec")){
                d=intent.getStringExtra("datec");
            }
            if(intent.hasExtra("heurc")){
                h=intent.getStringExtra("heurc");
            }

            if(intent.hasExtra("affic")) affic=intent.getStringExtra("affic");

        }affichag();
    }
    public void affichag(){


        String titre= bd_sql.affichet(d,h),repeter=bd_sql.afficheR(d,h),description=bd_sql.affiched(d,h),critique=bd_sql.affichec(d,h),categorie=bd_sql.afficheca(d,h),statut=bd_sql.affiches(d,h);
        titr.setText(titre); dat.setText(d);descriptio.setText(description);
        if(critique.equals("oui")) critiqu.setChecked(true); else critiqu.setChecked(false);
       categ.setText(categorie);stat.setText(statut);
       rep.setText(repeter);
         heu.setText(h);

    }


    public void Spprimer(View view){
        Integer resultat =bd_sql.supprim(h,d);
        Integer r1=bd_sql.supprimp(h,d);

    if (resultat > 0) {
        bd_sql.modifiernbrta(d);
        Toast.makeText(ShopingA.this, "Evenement supprimer", Toast.LENGTH_SHORT).show();
        if(affic.equals("cherch")){
            Toast.makeText(ShopingA.this,"Evenement supprimer",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ShopingA.this,MainActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivity(intent);
            AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
            Intent alertIntent=new Intent(getApplicationContext(),AlertReceiver.class);
        }
        else {
            Intent intent = new Intent(ShopingA.this,affichage.class);
            startActivity(intent);
        }

    } else {
        Toast.makeText(ShopingA.this, "Evenement non supprimé", Toast.LENGTH_SHORT).show();
    }


    }
    public void afficherliste ()
    {
        ArrayList aff=bd_sql.affich(d,h);
        if (aff.isEmpty()) {Toast.makeText(ShopingA.this,"pas de produit à cet événement ", Toast.LENGTH_SHORT).show();}
        else {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aff);
            affichage.setAdapter(arrayAdapter);
            affichage.getAdapter().toString();

        }
    }

    public void aaa(View view){
        afficherliste ();
}
    public void Mdifier(View view){

        String c;
        if(critiqu.isChecked()) {
            c ="oui";
        }
        else {
            c ="non";
        }

        Boolean resultat = bd_sql.modifier(titr.getText().toString(),dat.getText().toString(),heu.getText().toString(),descriptio.getText().toString(),c,repetr.getSelectedItem().toString(),sat.getSelectedItem().toString());
        if(resultat==true ){
            Toast.makeText(ShopingA.this,"Evenement modifié",Toast.LENGTH_SHORT).show();
            if(bd_sql.affiches(dat.getText().toString(),heu.getText().toString()).equals("FAIT"))
            {  bd_sql.modifiernbrt(dat.getText().toString());
                bd_sql.modifiernbrnm(dat.getText().toString());}
            if(affic.equals("cherch")){
                Intent intent = new Intent(ShopingA.this,MainActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(ShopingA.this, affichage.class);
                startActivity(intent);
            }

        }
        else {
            Toast.makeText(ShopingA.this,"Evenement non modifié",Toast.LENGTH_SHORT).show();
        }

    }
    public void annuler(View view){

        ShopingA.this.finish();
    }
}
