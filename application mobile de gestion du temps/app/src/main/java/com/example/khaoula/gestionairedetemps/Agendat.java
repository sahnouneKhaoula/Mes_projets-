package com.example.khaoula.gestionairedetemps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by youcef on 09/05/2018.
 */

public class Agendat extends Activity {
    private CalendarView calenda;
    ArrayAdapter<CharSequence> adapter ;
    BD_sql bd_sql;
    public String da;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendat);
        bd_sql = new BD_sql(this);


        ///////////////////////////calendrier
        calenda = (CalendarView) findViewById(R.id.calendar);
        calenda.setOnDateChangeListener(new   CalendarView.OnDateChangeListener() {
                                            AfficheJour a=new AfficheJour();
                                            @Override
                                            public void onSelectedDayChange( CalendarView calendarView, int i, int i1, int i2) {
                                                int mois=i1+1;
                                                da=i2+"/"+mois+"/"+i;
                                                ArrayList  aff=bd_sql.affiche(da);
                                                if (aff.isEmpty())
                                                    Toast.makeText(Agendat.this, " pas d'evenement a cette date  ", Toast.LENGTH_SHORT).show();

                                                else  {
                                                    a.setA(+i2 + "/" + mois + "/" + i);
                                                    Intent intent = new Intent(Agendat.this, AfficheJour.class);
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("affic","Affichage de la journée"+ da);
                                                    bundle.putString("date", da);
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);
                                                }
                                            }
                                        }
        );}

    public void main(View view){
        Intent intent=new Intent(Agendat.this,MainActivity.class);
        startActivity(intent);
    }
    public void retour(View view)
    { Agendat.this.finish();}

    }

