package com.example.saper3;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Miny();
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        boom_sound = MediaPlayer.create(this, R.raw.sound2);
        win = MediaPlayer.create(this, R.raw.sound3);
        mediaPlayer.start();

    }
    MediaPlayer boom_sound;
    MediaPlayer mediaPlayer;
    MediaPlayer win;
    int wiel= 6;
    Random liczba= new Random();
    int miny= liczba.nextInt(3)+5;  //liczba min

    ArrayList<Integer> X= new ArrayList<>();        //listy współrzędnych min
    ArrayList<Integer> Y= new ArrayList<>();

    void Miny(){
        X.clear();
        Y.clear();
        for (int a = 0; a < miny; a++) {            //losujemy współrzędne
            Random l1 = new Random();
            int x = l1.nextInt(wiel);
            Random l2 = new Random();
            int y = l2.nextInt(wiel);
            int spr=1;
            for (int b = 0; b < X.size(); b++){   //sprawdzamy czy współrzędne się nie powtarzają
                if(X.get(b)!=x & Y.get(b)!=y){
                    spr++;                       //jeśli nie to spr+1
                }
            }
            if(spr<X.size()){a--;}               //jeśli spr jest mniejsze od wielkośći list współrzędnych, to zmniejszamy a o 1 by powtórzyć pętle
            else{                               //jeśli nie jest mniejsze
                X.add(x);
                Y.add(y);
            }
        }
        miny=X.size();
    }

    boolean sprawdz(int w, int k) {
        for( int i=0; i< X.size(); i++) {
            int x=X.get(i);
            int y=Y.get(i);
            if (w==x & k==y) return true;
        }
        return false;
    }


    boolean flaga = false;

    ArrayList<Integer> FlagiX= new ArrayList<Integer>();
    ArrayList<Integer> FlagiY= new ArrayList<Integer>();



    boolean znalezione() {
        int l = 0;
        for (int b = 0; b < FlagiX.size(); b++){
            for (int a = 0; a < miny; a++){
                if (FlagiX.get(b) == X.get(a) && FlagiY.get(b) == Y.get(a) ){
                    l++;
                }
                else {
                    break;
                }
            }
        }
        if ( l == miny ){
            return true;
        }
        else {
            return false;
        }
    }


    boolean wygrana(int w, int k) {                 // sprawdzamy czy wszystkie pola poza minami zostały kliknięte
        int [] TextIds = new int [] {R.id.t0_0, R.id.t0_1,R.id.t0_2,R.id.t0_3,R.id.t0_4,R.id.t0_5,
                R.id.t1_0,R.id.t1_1,R.id.t1_2,R.id.t1_3,R.id.t1_4,R.id.t1_5,
                R.id.t2_0, R.id.t2_1,R.id.t2_2,R.id.t2_3,R.id.t2_4,R.id.t2_5,
                R.id.t3_0,R.id.t3_1,R.id.t3_2,R.id.t3_3,R.id.t3_4,R.id.t3_5,
                R.id.t4_0, R.id.t4_1,R.id.t4_2,R.id.t4_3,R.id.t4_4,R.id.t4_5,
                R.id.t5_0,R.id.t5_1,R.id.t5_2,R.id.t5_3,R.id.t5_4,R.id.t5_5};
        if (sprawdz(w, k) == false) {               //jeśli nie odkryto miny
            int l = 0;
            for (int a = 0; a < TextIds.length; a++) {                  //liczymy pola odblokowane
                TextView tekst=(TextView)findViewById(TextIds[a]);
                if (tekst.getVisibility() == View.INVISIBLE){
                    l++;
                }
            }
            if (l == miny) {         //i liczba min jest równa liczbie nieodkrytych przycisków
                Log.i("liczba", "się zgadza");
                return true;                                    // zwraca true (wygrana)
            }
            else return false;
        }
        else{
            return false;
        }
    }

    void Polecenie(int w, int k){
        if (flaga == true){
            ImageView flaga = findViewById(Wspolrzedne(w,k,"iv"));
            if (flaga.getVisibility()==View.VISIBLE) {
                flaga.setVisibility(View.INVISIBLE);
                for(int i=0; i<FlagiX.size(); i++){
                    if(FlagiX.get(i)==w && FlagiY.get(i)==k){
                        FlagiX.remove(i);
                        FlagiY.remove(i);
                    }
                }
            }
            else {
                flaga.setVisibility(View.VISIBLE);
                FlagiX.add(w);
                FlagiY.add(k);
            }
        }
        else{
            if (sprawdz(w, k) == true){
                ImageView Boom;
                Boom = findViewById(R.id.gif);
                Glide.with(this).load(R.drawable.boom).into(Boom);
                Boom.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                boom_sound.start();
                Button newGame = findViewById(R.id.newGame);
                Button menu = findViewById(R.id.Menu);
                newGame.setVisibility(View.VISIBLE);
                menu.setVisibility(View.VISIBLE);

                int wsp=Wspolrzedne(w, k, "t");
                TextView iloscBomb=(TextView)findViewById(wsp);
                iloscBomb.setText("!BOOM!");
                iloscBomb.setVisibility(View.VISIBLE);

            }
            else {
                int m = 0;
                for (int a = 0; a < X.size(); a++) {              //sprawdzamy ile min jest w okolicy przycisku
                    int x = X.get(a);
                    int y = Y.get(a);
                    if (x == w - 1 & y == k - 1) {
                        m++;
                    }
                    if (x == w - 1 & y == k) {
                        m++;
                    }
                    if (x == w - 1 & y == k + 1) {
                        m++;
                    }
                    if (x == w & y == k - 1) {
                        m++;
                    }
                    if (x == w & y == k + 1) {
                        m++;
                    }
                    if (x == w + 1 & y == k - 1) {
                        m++;
                    }
                    if (x == w + 1 & y == k) {
                        m++;
                    }
                    if (x == w + 1 & y == k + 1) {
                        m++;
                    }
                }
                String otoczenie;
                if (m >0){
                    Resources r = getResources();
                    otoczenie = Integer.toString(m);
                    int wsp=Wspolrzedne(w, k, "t");
                    int wspObrazka =Wspolrzedne(w,k,"i");
                    TextView iloscBomb=(TextView)findViewById(wsp);
                    ImageButton obrazek =(ImageButton) findViewById(wspObrazka);
                    iloscBomb.setText(otoczenie);
                    iloscBomb.setVisibility(View.VISIBLE);
                    obrazek.setVisibility(View.INVISIBLE);

                }
                else{
                    int wsp=Wspolrzedne(w, k, "t");
                    int wspObrazka =Wspolrzedne(w,k,"i");
                    TextView iloscBomb=(TextView)findViewById(wsp);
                    ImageButton obrazek =(ImageButton) findViewById(wspObrazka);
                    iloscBomb.setText("pusto");
                    iloscBomb.setVisibility(View.VISIBLE);
                    obrazek.setVisibility(View.INVISIBLE);

                }
            }
        }
        if (wygrana(w, k) == true){
            ImageView Gif;
            Gif = findViewById(R.id.gif);
            Glide.with(this).load(R.drawable.win).into(Gif);
            Gif.setVisibility(View.VISIBLE);
            mediaPlayer.pause();
            win.start();
            Button newGame = findViewById(R.id.newGame);
            Button menu = findViewById(R.id.Menu);
            newGame.setVisibility(View.VISIBLE);
            menu.setVisibility(View.VISIBLE);
        }
    }

    static int Wspolrzedne(int w, int k, String s){
        int n = k*6+w;
        int id;
        int [] TextIds = new int [] {R.id.t0_0, R.id.t0_1,R.id.t0_2,R.id.t0_3,R.id.t0_4,R.id.t0_5,
                R.id.t1_0,R.id.t1_1,R.id.t1_2,R.id.t1_3,R.id.t1_4,R.id.t1_5,
                R.id.t2_0, R.id.t2_1,R.id.t2_2,R.id.t2_3,R.id.t2_4,R.id.t2_5,
                R.id.t3_0,R.id.t3_1,R.id.t3_2,R.id.t3_3,R.id.t3_4,R.id.t3_5,
                R.id.t4_0, R.id.t4_1,R.id.t4_2,R.id.t4_3,R.id.t4_4,R.id.t4_5,
                R.id.t5_0,R.id.t5_1,R.id.t5_2,R.id.t5_3,R.id.t5_4,R.id.t5_5};

        int [] ImageIds = new int [] {R.id.i0_0, R.id.i0_1,R.id.i0_2,R.id.i0_3,R.id.i0_4,R.id.i0_5,
                R.id.i1_0,R.id.i1_1,R.id.i1_2,R.id.i1_3,R.id.i1_4,R.id.i1_5,
                R.id.i2_0, R.id.i2_1,R.id.i2_2,R.id.i2_3,R.id.i2_4,R.id.i2_5,
                R.id.i3_0,R.id.i3_1,R.id.i3_2,R.id.i3_3,R.id.i3_4,R.id.i3_5,
                R.id.i4_0, R.id.i4_1,R.id.i4_2,R.id.i4_3,R.id.i4_4,R.id.i4_5,
                R.id.i5_0,R.id.i5_1,R.id.i5_2,R.id.i5_3,R.id.i5_4,R.id.i5_5};
        int [] ImageViewIds = new int [] {R.id.iv0_0, R.id.iv0_1,R.id.iv0_2,R.id.iv0_3,R.id.iv0_4,R.id.iv0_5,
                R.id.iv1_0,R.id.iv1_1,R.id.iv1_2,R.id.iv1_3,R.id.iv1_4,R.id.iv1_5,
                R.id.iv2_0, R.id.iv2_1,R.id.iv2_2,R.id.iv2_3,R.id.iv2_4,R.id.iv2_5,
                R.id.iv3_0,R.id.iv3_1,R.id.iv3_2,R.id.iv3_3,R.id.iv3_4,R.id.iv3_5,
                R.id.iv4_0, R.id.iv4_1,R.id.iv4_2,R.id.iv4_3,R.id.iv4_4,R.id.iv4_5,
                R.id.iv5_0,R.id.iv5_1,R.id.iv5_2,R.id.iv5_3,R.id.iv5_4,R.id.iv5_5};

        if (s == "t"){
            id = TextIds[n];
        }
        else if (s == "iv"){
            id = ImageViewIds[n];
        }
        else if (s == "i"){
            id = ImageIds[n];
        }
        else {
            id = 0;
        }
        return id;
    }
    public void Klikniecie(View view){
        int id=view.getId();
        Log.i("liczba flag", Integer.toString(FlagiX.size())+" liczba min "+ Integer.toString(miny));

        if(id == R.id.button){
            flaga= ! flaga;
            CheckBox checkBox = findViewById(R.id.checkBox);
            checkBox.setChecked(!checkBox.isChecked());
        }
        else {
            int[] ImageIds = new int[]{R.id.i0_0, R.id.i0_1, R.id.i0_2, R.id.i0_3, R.id.i0_4, R.id.i0_5,
                    R.id.i1_0, R.id.i1_1, R.id.i1_2, R.id.i1_3, R.id.i1_4, R.id.i1_5,
                    R.id.i2_0, R.id.i2_1, R.id.i2_2, R.id.i2_3, R.id.i2_4, R.id.i2_5,
                    R.id.i3_0, R.id.i3_1, R.id.i3_2, R.id.i3_3, R.id.i3_4, R.id.i3_5,
                    R.id.i4_0, R.id.i4_1, R.id.i4_2, R.id.i4_3, R.id.i4_4, R.id.i4_5,
                    R.id.i5_0, R.id.i5_1, R.id.i5_2, R.id.i5_3, R.id.i5_4, R.id.i5_5};
            int kw = 0;
            for (int i = 0; i < ImageIds.length; i++) {
                if (id == ImageIds[i]) {
                    kw = i;
                }
            }
            int w = kw % 6;
            int k = kw / 6;
            Polecenie(w, k);
            Log.i("codin", String.valueOf(w) + " " + String.valueOf(k));
        }
    }

    public void NewGame(View view){
        int [] ImageViewIds = new int [] {R.id.iv0_0, R.id.iv0_1,R.id.iv0_2,R.id.iv0_3,R.id.iv0_4,R.id.iv0_5,
                R.id.iv1_0,R.id.iv1_1,R.id.iv1_2,R.id.iv1_3,R.id.iv1_4,R.id.iv1_5,
                R.id.iv2_0, R.id.iv2_1,R.id.iv2_2,R.id.iv2_3,R.id.iv2_4,R.id.iv2_5,
                R.id.iv3_0,R.id.iv3_1,R.id.iv3_2,R.id.iv3_3,R.id.iv3_4,R.id.iv3_5,
                R.id.iv4_0, R.id.iv4_1,R.id.iv4_2,R.id.iv4_3,R.id.iv4_4,R.id.iv4_5,
                R.id.iv5_0,R.id.iv5_1,R.id.iv5_2,R.id.iv5_3,R.id.iv5_4,R.id.iv5_5};
        int [] ImageButtonIds = new int [] {R.id.i0_0, R.id.i0_1, R.id.i0_2, R.id.i0_3, R.id.i0_4, R.id.i0_5,
                R.id.i1_0, R.id.i1_1, R.id.i1_2, R.id.i1_3, R.id.i1_4, R.id.i1_5,
                R.id.i2_0, R.id.i2_1, R.id.i2_2, R.id.i2_3, R.id.i2_4, R.id.i2_5,
                R.id.i3_0, R.id.i3_1, R.id.i3_2, R.id.i3_3, R.id.i3_4, R.id.i3_5,
                R.id.i4_0, R.id.i4_1, R.id.i4_2, R.id.i4_3, R.id.i4_4, R.id.i4_5,
                R.id.i5_0, R.id.i5_1, R.id.i5_2, R.id.i5_3, R.id.i5_4, R.id.i5_5};
        int [] TextIds = new int [] {R.id.t0_0, R.id.t0_1,R.id.t0_2,R.id.t0_3,R.id.t0_4,R.id.t0_5,
                R.id.t1_0,R.id.t1_1,R.id.t1_2,R.id.t1_3,R.id.t1_4,R.id.t1_5,
                R.id.t2_0, R.id.t2_1,R.id.t2_2,R.id.t2_3,R.id.t2_4,R.id.t2_5,
                R.id.t3_0,R.id.t3_1,R.id.t3_2,R.id.t3_3,R.id.t3_4,R.id.t3_5,
                R.id.t4_0, R.id.t4_1,R.id.t4_2,R.id.t4_3,R.id.t4_4,R.id.t4_5,
                R.id.t5_0,R.id.t5_1,R.id.t5_2,R.id.t5_3,R.id.t5_4,R.id.t5_5};
        for ( int i=0; i< TextIds.length; i++){
            TextView tekst=(TextView)findViewById(TextIds[i]);
            tekst.setVisibility(View.INVISIBLE);
            ImageView image=(ImageView)findViewById(ImageViewIds[i]);
            image.setVisibility(View.INVISIBLE);
            ImageButton obrazki=(ImageButton)findViewById(ImageButtonIds[i]);
            obrazki.setVisibility(View.VISIBLE);
        }

        Miny();
        ImageView Boom = findViewById(R.id.gif);
        Boom.setVisibility(View.INVISIBLE);
        Button newGame = findViewById(R.id.newGame);
        Button menu = findViewById(R.id.Menu);
        newGame.setVisibility(View.INVISIBLE);
        menu.setVisibility(View.INVISIBLE);
        mediaPlayer.start();
        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(false);
        FlagiY.clear();
        FlagiX.clear();

    }
    public void Menu(View view){
        Intent intent = new Intent(Game.this, Menu.class);
        startActivity(intent);
    }

}