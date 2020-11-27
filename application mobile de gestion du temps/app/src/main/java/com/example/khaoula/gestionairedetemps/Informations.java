package com.example.khaoula.gestionairedetemps;



        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.View;
        import android.widget.ImageButton;

public class Informations extends Activity {
    ImageButton home;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fenetre_informations);
        home=(ImageButton) findViewById(R.id.btn_home);
    }
    public void retour(View v){
        Intent intent=new Intent(Informations.this,MainActivity.class);
        startActivity(intent);
    }
}
