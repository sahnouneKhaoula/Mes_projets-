package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by youcef on 09/04/2018.
 */

public class Button_Event extends Activity implements View.OnClickListener {
    ImageButton anniv,sport,tv,reunion,rencontre,priseM,shop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.button_event);

   anniv=(ImageButton) findViewById(R.id.btn_anniversaire);

        anniv.setOnClickListener(this);
    sport=(ImageButton) findViewById(R.id.btn_pratiquer_sport);
        sport.setOnClickListener(this);
    tv=(ImageButton) findViewById(R.id.btn_programme_tv);
        tv.setOnClickListener(this);
    reunion=(ImageButton) findViewById(R.id.btn_réunion);
        reunion.setOnClickListener(this);
    rencontre=(ImageButton) findViewById(R.id.btn_rencontre_personne);
        rencontre.setOnClickListener(this);
    priseM=(ImageButton) findViewById(R.id.btn_prise_médicament);
        priseM.setOnClickListener(this);
    shop=(ImageButton) findViewById(R.id.btn_shopping);
        shop.setOnClickListener(this);
}

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_anniversaire :
                intent=new Intent(Button_Event.this,Anniv_Prog_Sport.class);
                Bundle bundle = new Bundle();
                bundle.putString("catego","anniversaire");
               intent.putExtras(bundle);
                startActivity(intent);
                break;
             case R.id.btn_programme_tv :
                intent=new Intent(Button_Event.this,Anniv_Prog_Sport.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("catego","programme_tv");
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
            case R.id.btn_shopping :
                intent=new Intent(Button_Event.this,Shopping.class);
                startActivity(intent);
                break;
            case R.id.btn_pratiquer_sport :intent=new Intent(Button_Event.this,Anniv_Prog_Sport.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("catego","sport");
                intent.putExtras(bundle2);
                startActivity(intent);
                break;
            case R.id.btn_prise_médicament :intent=new Intent(Button_Event.this,Prise_Medicament.class);
                startActivity(intent);
                break;
            case R.id.btn_rencontre_personne :intent=new Intent(Button_Event.this,Reunion_Rencontre.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString("catego","rencontre");
                intent.putExtras(bundle3);
                startActivity(intent);

                break;
            case R.id.btn_réunion :intent=new Intent(Button_Event.this,Reunion_Rencontre.class);
                Bundle bundle4= new Bundle();
                bundle4.putString("catego","reunion");
                intent.putExtras(bundle4);
                startActivity(intent);
                break;


        }
    }

    public void main(View view){
        Intent intent=new Intent(Button_Event.this,MainActivity.class);
        startActivity(intent);
    }
    public void retour(View view)
    {
        Button_Event.this.finish();}


}
