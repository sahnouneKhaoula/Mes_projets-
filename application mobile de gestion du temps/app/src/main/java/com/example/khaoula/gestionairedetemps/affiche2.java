package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by youcef on 19/05/2018.
 */

public class affiche2  extends Activity {
   EditText titr,descriptio,qtq;
    TextView rep, dat,heu, categ,stat,qte2;
    Spinner repetr,sat;
    Switch critiqu;
    BD_sql bd_sql;
    String d,h;
    String affic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pris_med_reu);
        bd_sql=new BD_sql(this);
        rep=findViewById(R.id.rep);
        titr=findViewById(R.id.editText_Titre);
       dat=findViewById(R.id.editText_date);
       heu=findViewById(R.id.editText_time);
        descriptio=findViewById(R.id.editText_description);
        critiqu=findViewById(R.id.switch_critique_event);
       repetr=findViewById(R.id.editText_répéter);
        categ=findViewById(R.id.editText_cate);
        stat=findViewById(R.id.editText_statut);
        qtq=findViewById(R.id.editText_qte);
        qte2=findViewById(R.id.qte2);
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
        String titre= bd_sql.affichet(d,h),repeter=bd_sql.afficheR(d,h),description=bd_sql.affiched(d,h),critique=bd_sql.affichec(d,h),categorie=bd_sql.afficheca(d,h),statut=bd_sql.affiches(d,h),quan=bd_sql.affichecham(d,h);
        titr.setText(titre); dat.setText(d);descriptio.setText(description);
        if(critique.equals("oui")) critiqu.setChecked(true); else critiqu.setChecked(false);
        rep.setText(repeter);categ.setText(categorie);stat.setText(statut);
        heu.setText(h);qtq.setText(quan);
        if(categorie.equals("rencontre")) {
            qte2.setText("Lieu");
        }
        if(categorie.equals("reunion")) {
            qte2.setText("Lieu");
        }

    }

    public void annuler(View view)
    { Calendar calendar = Calendar.getInstance();

      //  calendar.set(Calendar.MINUTE,h.substring(h1+1).trim().getBytes());
       /* calendar.set(Calendar.SECOND, 0);
        int h1=h.indexOf(":");
        int h2=h.lastIndexOf(":");
        String m=h.substring(h1+1).trim();

        String he=h.substring(0,h1).trim();
        //retour à l'activité
        Toast.makeText(this,he+"::"+h.substring(h1+1).trim().getBytes(),Toast.LENGTH_LONG).show();
*/
        affiche2.this.finish();
    }

    public void supprimer(View view){
        Integer resultat =bd_sql.supprim(h,d);
        if(resultat>0) {
            bd_sql.modifiernbrta(d);
            if(affic.equals("cherch")){
            Toast.makeText(affiche2.this,"Evenement supprimé",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(affiche2.this,MainActivity.class);
            startActivity(intent);

            }

          /*  else if(affic.equals("Affichage mensuel")){
                Toast.makeText(affiche2.this,"Evenement supprimer",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(affiche2.this,AfficheAnne.class);
                Bundle bundle = new Bundle();
                bundle.putString("date",d);
                bundle.putString("affic","Affichage mensuel");
                startActivity(intent);
            }

            else if(affic.equals("Affichage annuel")){
                Toast.makeText(affiche2.this,"Evenement supprimer",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(affiche2.this,AfficheAnne.class);
                Bundle bundle = new Bundle();
                bundle.putString("date",d);
                bundle.putString("affic","Affichage annuel");
                startActivity(intent);
            }
            else if(affic.equals("Shopping")){
                Toast.makeText(affiche2.this,"Evenement supprimer",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","shoping");
                bundle.putString("affic","Shopping");
                startActivity(intent);
            }
            else if(affic.equals("Sport")){
                Toast.makeText(affiche2.this,"Evenement supprimer",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","sport");
                bundle.putString("affic","Sport");
                startActivity(intent);
            }
            else if(affic.equals("Prise_Medicament")){
                Toast.makeText(affiche2.this,"Evenement supprimer",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","prise de medicament");
                bundle.putString("affic","Prise de medicament");
                startActivity(intent);
            }
            else if(affic.equals("Programme_TV")){
                Toast.makeText(affiche2.this,"Evenement supprimer",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","programme_tv");
                bundle.putString("affic","Programme_TV");
                startActivity(intent);
            }
            else if(affic.equals("Anniversaire")){
                Toast.makeText(affiche2.this,"Evenement supprimer",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","anniversaire");
                bundle.putString("affic","Anniversaire");
                startActivity(intent);
            }
            else if(affic.equals("Rencontre")){
                Toast.makeText(affiche2.this,"Evenement supprimer",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","rencontre");
                bundle.putString("affic","Rencontre");
                startActivity(intent);
            }
            else if(affic.equals("Reunion")){
                Toast.makeText(affiche2.this,"Evenement supprimer",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","reunion");
                bundle.putString("affic","Reunion");
                startActivity(intent);
            }*/
         else{
                Toast.makeText(affiche2.this,"Evenement supprimé",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(affiche2.this,affichage.class);
                startActivity(intent);
            }

        }
        else {
            Toast.makeText(affiche2.this,"Evenement non supprimé",Toast.LENGTH_SHORT).show();
        }


    }

    public void modifier(View view){
        String c;
        if(critiqu.isChecked()) {
            c ="oui";
        }
        else {
            c ="non";
        }

        Boolean resultat = bd_sql.modifier2(titr.getText().toString(),dat.getText().toString(),heu.getText().toString(),descriptio.getText().toString(),c,repetr.getSelectedItem().toString(),qtq.getText().toString(),sat.getSelectedItem().toString());
        if(resultat==true ) {
            Toast.makeText(affiche2.this, "Evenement modifié", Toast.LENGTH_SHORT).show();
            if(bd_sql.affiches(dat.getText().toString(),heu.getText().toString()).equals("FAIT"))
            {  bd_sql.modifiernbrt(dat.getText().toString());
                bd_sql.modifiernbrnm(dat.getText().toString());}
                if(affic.equals("cherch")){
                    Intent intent = new Intent(affiche2.this,MainActivity.class);
                    startActivity(intent);

                }
         /*   if(affic.equals("Affichage mensuel")){

                Intent intent = new Intent(affiche2.this,AfficheAnne.class);
                Bundle bundle = new Bundle();
                bundle.putString("date",d);
                bundle.putString("affic","Affichage mensuel");
                startActivity(intent);
            }

            else if(affic.equals("Affichage annuel")){
                Intent intent = new Intent(affiche2.this,AfficheAnne.class);
                Bundle bundle = new Bundle();
                bundle.putString("date",d);
                bundle.putString("affic","Affichage annuel");
                startActivity(intent);
            }
            else if(affic.equals("Shopping")){
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","shoping");
                bundle.putString("affic","Shopping");
                startActivity(intent);
            }
            else if(affic.equals("Sport")){
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","sport");
                bundle.putString("affic","Sport");
                startActivity(intent);
            }
            else if(affic.equals("Prise_Medicament")){
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","prise de medicament");
                bundle.putString("affic","Prise de medicament");
                startActivity(intent);
            }
            else if(affic.equals("Programme_TV")){
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","programme_tv");
                bundle.putString("affic","Programme_TV");
                startActivity(intent);
            }
            else if(affic.equals("Anniversaire")){
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","anniversaire");
                bundle.putString("affic","Anniversaire");
                startActivity(intent);
            }
            else if(affic.equals("Rencontre")){
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","rencontre");
                bundle.putString("affic","Rencontre");
                startActivity(intent);
            }
            else if(affic.equals("Reunion")){
                Intent intent = new Intent(affiche2.this,AffichageCategor.class);
                Bundle bundle = new Bundle();
                bundle.putString("categ","reunion");
                bundle.putString("affic","Reunion");
                startActivity(intent);
            }*/
            else{
                Intent intent = new Intent(affiche2.this,affichage.class);
                startActivity(intent);
            }



        }
        else {
            Toast.makeText(affiche2.this,"Evenement non modifié",Toast.LENGTH_SHORT).show();
        }

    }
}

