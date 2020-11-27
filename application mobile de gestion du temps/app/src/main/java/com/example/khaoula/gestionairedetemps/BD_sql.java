package com.example.khaoula.gestionairedetemps;

import android.app.job.JobParameters;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by youcef on 23/03/2018.
 */

public class BD_sql extends SQLiteOpenHelper {
    public static final String BDname="data.db";

    public BD_sql(Context context) {
        super(context, BDname,null,23
        ) ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("create table Evenement(titre TEXT   ,date TEXT  ,heur TEXT  ,description TEXT ,critique TEXT ,categorie TEXT ,champs TEXT ,repeter TEXT,statut TEXT, constraint pk_event " +
             //   "primary key ( date, heur))");
       sqLiteDatabase.execSQL("create table Evenements(titre TEXT  not null ,date TEXT not null,heur TEXT not null ,description TEXT ,critique TEXT ,categorie TEXT ,champs TEXT ,repeter TEXT,statut TEXT, constraint pk_event " +
        "primary key ( date, heur))");

        sqLiteDatabase.execSQL("create table Produit(id INTEGER PRIMARY KEY AUTOINCREMENT ,date TEXT not null,heur TEXT not null,titre TEXT  not null ,qte TEXT , constraint pk_prod foreign key(date,heur) references Evenements(date, heur))" );
        sqLiteDatabase.execSQL("create table Statistiques(id INTEGER PRIMARY KEY AUTOINCREMENT ,dateStat TEXT UNIQUE,nbrETotal INTEGER ,nbrETermines INTEGER,nbrNonfait INTEGER, nbrEAnnules INTEGER)" );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Evenements");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Produit");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Statistiques");
        onCreate(sqLiteDatabase);
    }

    public String affichedateS(String s) {
     String date=null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Statistiques where dateStat =\""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            date=c.getString(1);
            c.moveToNext();
        }

        return date;}
    public int nbra(String s) {
        int nbrt=0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Statistiques where dateStat =\""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            nbrt=c.getInt(5);
            c.moveToNext();
        }return nbrt;}


    public int nbrA(String s) {
        int nbrt=0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select SUM(nbrEAnnules) from Statistiques where dateStat LIKE \""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            nbrt=c.getInt(0);
            c.moveToNext();
        }return nbrt;}



    public int nbrn(String s) {
        int nbrt=0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Statistiques where dateStat =\""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            nbrt=c.getInt(4);
            c.moveToNext();
        }return nbrt;}
    public int nbrN(String s) {
        int nbrt=0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select SUM(nbrNonfait) from Statistiques where dateStat LIKE \""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            nbrt=c.getInt(0);
            c.moveToNext();
        }return nbrt;}
    public int nbrt(String s) {
        int nbrt=0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Statistiques where dateStat =\""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            nbrt=c.getInt(3);
            c.moveToNext();
        }
        return nbrt;}
    public int nbrT(String s) {
        int nbrt=0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select SUM(nbrETermines) from Statistiques where dateStat LIKE \""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            nbrt=c.getInt(0);
            c.moveToNext();
        }return nbrt;}
    public int nbrto(String s) {
        int nbrt=0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Statistiques where dateStat =\""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            nbrt=c.getInt(2);
            c.moveToNext();
        }

        return nbrt;}
    public int nbrTO(String s) {
        int nbrt=0;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select SUM(nbrETotal)  from Statistiques where dateStat LIKE \""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            nbrt=c.getInt(0);
            c.moveToNext();
        }return nbrt;}

    public boolean modifiernbrta(String date)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("nbrEAnnules",this.nbra(date)+1);
        sqLiteDatabase.update("Statistiques",contentValues,"dateStat = ? ", new String[]{date});
        return true;
    }

    public boolean modifiernbrto(String date)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("nbrETotal",this.nbrto(date)+1);
        sqLiteDatabase.update("Statistiques",contentValues,"dateStat = ? ", new String[]{ date});
        return true;
    }
    public boolean modifiernbrt(String date)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("nbrETermines",this.nbrt(date)+1);
        sqLiteDatabase.update("Statistiques",contentValues,"dateStat = ? ", new String[]{ date});
        return true;
    }
    public boolean modifiernbrn(String date)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("nbrNonfait",this.nbrn(date)+1);
        sqLiteDatabase.update("Statistiques",contentValues,"dateStat = ? ", new String[]{date});
        return true;
    }
    public boolean modifiernbrnm(String date)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("nbrNonfait",this.nbrn(date)-1);
        sqLiteDatabase.update("Statistiques",contentValues,"dateStat = ? ", new String[]{date});
        return true;
    }
    public boolean incerstat(String date){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("dateStat",date);
        contentValues.put("nbrNonfait",1);
        contentValues.put("nbrETotal",1);
        long resultat =sqLiteDatabase.insert("Statistiques",null,contentValues);
        if(resultat==-1) return false;
        else return true;
    }

    public boolean incerProd(String date ,String heur,String titre,String qte ){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("date",date);
        contentValues.put("heur",heur);
        contentValues.put("titre",titre);
        contentValues.put("qte",qte);
        long resultat =sqLiteDatabase.insert("Produit",null,contentValues);
        if(resultat==-1) return false;
        else return true;
    }

    public boolean incertEvent(String titre, String date, String heur, String description, String critique, String champs, String repeter, String categorie, String statut)
{
    SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
    ContentValues contentValues=new ContentValues();
    contentValues.put("titre",titre);
    contentValues.put("date",date);
    contentValues.put("statut",statut);
    contentValues.put("heur",heur);
    contentValues.put("description",description);
    contentValues.put("critique",critique);
    contentValues.put("champs",champs);
    contentValues.put("categorie",categorie);
    contentValues.put("repeter",repeter);
    long resultat =sqLiteDatabase.insert("Evenements",null,contentValues);
    if(resultat==-1) return false;

        return true;

}


    public ArrayList afficheN(String s) {
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where titre =\""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            String t=c.getString(0);
            String d=c.getString(1);
            String h=c.getString(2);
            aff.add(t+"\n"+d+"\n"+h);
            c.moveToNext();
        }
        return aff;
    }


    public ArrayList affiche2(String s,String t) {
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where  date =\""+s+"\""+"and titre =\""+t+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            String titre=c.getString(0);
            String date=c.getString(1);
            String heur=c.getString(2);
            String description=c.getString(3);
            String critique =c.getString(4);
            String champs =c.getString(5);
            String repeter =c.getString(6) ;
            String categorie=c.getString(7);
            String statut=c.getString(8);
            aff.add(titre+"\n"+date+"\n"+heur);
            c.moveToNext();
        }
return aff;
    }
    public String affichet(String s,String t) {
        String titre=null;
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where  date =\""+s+"\""+"and heur=\""+t+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
             titre=c.getString(0);
            c.moveToNext();
        }

         return titre;
    }
    public String afficheR(String s,String t) {
        String repeter=null;
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where  date =\""+s+"\""+"and heur=\""+t+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            repeter=c.getString(7);
            c.moveToNext();
        }
        return repeter;
    }
    public String affichecham(String s,String t) {
        String champs=null;
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where  date =\""+s+"\""+"and heur=\""+t+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            champs=c.getString(6);
            c.moveToNext();
        }
        return champs;
    }
    public String affiched(String s,String t) {
        String description=null,critique,champs,repeter,categorie,statut;
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where  date =\""+s+"\""+"and heur =\""+t+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            description=c.getString(3);
            c.moveToNext();
        }

        return description;
    }

    public String affichec(String s,String t) {
        String critique=null,champs,repeter,categorie,statut;
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where  date =\""+s+"\""+"and heur =\""+t+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            critique =c.getString(4);
            c.moveToNext();
        }

        return critique;
    }
    public String afficheca(String s,String t) {
        String repeter,categorie=null,statut;
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where  date=\""+s+"\""+"and heur=\""+t+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            categorie=c.getString(5);
            c.moveToNext();
        }

        return categorie;
    }
    public String affiches(String s,String t) {
        String repeter,statut=null;
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where  date =\""+s+"\""+"and heur =\""+t+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            statut=c.getString(8);
            c.moveToNext();
        }

        return statut;
    }


    public ArrayList affiche(String s) {
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where date =\""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            String t=c.getString(0);
            String d=c.getString(1);
            String h=c.getString(2);
            aff.add(t+"\n"+d+"\n"+h);
            c.moveToNext();
        }
        return aff;
    }

    public ArrayList afficheA(String s) {
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where date LIKE \""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            String t=c.getString(0);
            String d=c.getString(1);
            String h=c.getString(2);
            aff.add(t+"\n"+d+"\n"+h);
            c.moveToNext();
        }
        return aff;
    }

    public ArrayList affichec(String s) {
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements where categorie =\""+s+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            String t=c.getString(0);
            String d=c.getString(1);
            String h=c.getString(2);
            aff.add(t+"\n"+d+"\n"+h);
            c.moveToNext();
        }
        return aff;
    }
public ArrayList affichett(){
    ArrayList aff = new ArrayList();
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor c = sqLiteDatabase.rawQuery("select * from Produit ",null);
    // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
    boolean b = c.moveToFirst();
    while (c.isAfterLast()==false){
        String r1=c.getString(1);
        String r2=c.getString(2);
        String r3=c.getString(3);
        String r=c.getString(4);
        aff.add(r1+"\n"+r2+"\nLe produit: "+r3+ "\nLa quantité: "+r);
        c.moveToNext();
    }
    return aff;
}

    public ArrayList affich(String d,String h) {
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from Produit where date =\""+d+"\""+"and heur =\""+h+"\"",null);
        // Cursorz c =sqLiteDatabase.rawQuery("select * from Evenement weher titre= \"ggg\" ",null);
        boolean b = c.moveToFirst();
        while (c.isAfterLast()==false){
            String r1=c.getString(3);
            String r=c.getString(4);
            aff.add("Le produit: "+r1+ "\nLa quantité: "+r);
            c.moveToNext();
        }
        return aff;
    }

    public Integer supprim(String h,String d){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
       return Integer.valueOf(sqLiteDatabase.delete("Evenements", "heur = ? and date = ? ", new String[]{h, d}));
    }
    public boolean modifier(String titre, String date,String heure,String descr,String crit,String repet,String stat)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("titre",titre);
        contentValues.put("date",date);
        contentValues.put("statut",stat);
        contentValues.put("heur",heure);
        contentValues.put("description",descr);
        contentValues.put("critique",crit);
        contentValues.put("repeter",repet);
        sqLiteDatabase.update("Evenements",contentValues,"heur = ? and date = ? ", new String[]{heure, date});
return true;
    }
    public Integer supprimp(String h,String d){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        return Integer.valueOf(sqLiteDatabase.delete("Produit", "heur = ? and date = ? ", new String[]{h, d}));
    }

    public boolean modifier2(String titre, String date,String heure,String descr,String crit,String repet,String quan,String stat)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("titre",titre);
        contentValues.put("date",date);
        contentValues.put("statut",stat);
        contentValues.put("heur",heure);
        contentValues.put("description",descr);
        contentValues.put("champs",quan);
        contentValues.put("critique",crit);
        contentValues.put("repeter",repet);
        sqLiteDatabase.update("Evenements",contentValues,"heur = ? and date = ? ", new String[]{heure, date});
        return true;
    }
    public boolean modifierStatu(String date,String heure,String quan)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("statut",quan);
        sqLiteDatabase.update("Evenements",contentValues,"heur = ? and date = ? ", new String[]{heure, date});
        return true;
    }
   public ArrayList afficher() {
        ArrayList aff = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        /* Cursor c= sqLiteDatabase.query(true,"Evenement", null,"titre ="+ s ,null,null,null,null,null); */
      //Cursor c =sqLiteDatabase.query(true,"Evenement", null,"titre ="+ s ,null,null,null,null,null);
        Cursor c = sqLiteDatabase.rawQuery("select * from Evenements ",null);
        c.moveToFirst();
        while (c.isAfterLast()==false){

            String t=c.getString(0);
            String d=c.getString(1);
            String h=c.getString(2);

            aff.add(" "+t+"\n"+d+"à "+h);
            c.moveToNext();
        }
        return aff;
    }

}

