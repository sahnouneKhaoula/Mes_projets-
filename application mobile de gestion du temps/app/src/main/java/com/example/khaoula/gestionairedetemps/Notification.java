package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.temporal.JulianFields;
import java.util.jar.JarFile;

/**
 * Created by youcef on 26/05/2018.
 */

public class Notification extends Activity {
    TextView text1;
    Prise_Medicament prise_medicament=new Prise_Medicament();
    String dat=prise_medicament.getDate();
    String heu=prise_medicament.getHeur();
      String titre;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        text1=(TextView) findViewById(R.id.textf);
        AlertReceiver alertReceiver=new AlertReceiver();


      //  titre=savedInstanceState.getString("titre");
        Intent intent = getIntent();
        if(intent.hasExtra("tit")){
            String titre = intent.getStringExtra("tit");}
            String s;s=alertReceiver.getTitree();
        text1.setText(s);
    }

    public void quiter(View view)
    {
        Toast.makeText(Notification.this, "  Attention : Vous ne pouvez "/*+dat.getText().toString()+"jk"*/+titre, Toast.LENGTH_SHORT).show();

  //this.finish();
        //Process.sendSignal(this.myPid, SIGNAL_KILL);

    }
}
