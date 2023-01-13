package com.example.saper3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class Menu extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

    }

    public void NewGame(View view) {

        Intent intent = new Intent(Menu.this, Game.class);
        startActivity(intent);
    }

    public void Quit(View view){
        finishAffinity();
        System.exit(0);
    }
}
