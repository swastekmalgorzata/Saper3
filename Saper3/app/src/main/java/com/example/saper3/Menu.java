package com.example.saper3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Menu extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

    }

    public void NewGame(View view) {
        int[] ImageViewIds = new int[]{R.id.iv0_0, R.id.iv0_1, R.id.iv0_2, R.id.iv0_3, R.id.iv0_4, R.id.iv0_5,
                R.id.iv1_0, R.id.iv1_1, R.id.iv1_2, R.id.iv1_3, R.id.iv1_4, R.id.iv1_5,
                R.id.iv2_0, R.id.iv2_1, R.id.iv2_2, R.id.iv2_3, R.id.iv2_4, R.id.iv2_5,
                R.id.iv3_0, R.id.iv3_1, R.id.iv3_2, R.id.iv3_3, R.id.iv3_4, R.id.iv3_5,
                R.id.iv4_0, R.id.iv4_1, R.id.iv4_2, R.id.iv4_3, R.id.iv4_4, R.id.iv4_5,
                R.id.iv5_0, R.id.iv5_1, R.id.iv5_2, R.id.iv5_3, R.id.iv5_4, R.id.iv5_5};
        int[] ImageButtonIds = new int[]{R.id.i0_0, R.id.i0_1, R.id.i0_2, R.id.i0_3, R.id.i0_4, R.id.i0_5,
                R.id.i1_0, R.id.i1_1, R.id.i1_2, R.id.i1_3, R.id.i1_4, R.id.i1_5,
                R.id.i2_0, R.id.i2_1, R.id.i2_2, R.id.i2_3, R.id.i2_4, R.id.i2_5,
                R.id.i3_0, R.id.i3_1, R.id.i3_2, R.id.i3_3, R.id.i3_4, R.id.i3_5,
                R.id.i4_0, R.id.i4_1, R.id.i4_2, R.id.i4_3, R.id.i4_4, R.id.i4_5,
                R.id.i5_0, R.id.i5_1, R.id.i5_2, R.id.i5_3, R.id.i5_4, R.id.i5_5};
        int[] TextIds = new int[]{R.id.t0_0, R.id.t0_1, R.id.t0_2, R.id.t0_3, R.id.t0_4, R.id.t0_5,
                R.id.t1_0, R.id.t1_1, R.id.t1_2, R.id.t1_3, R.id.t1_4, R.id.t1_5,
                R.id.t2_0, R.id.t2_1, R.id.t2_2, R.id.t2_3, R.id.t2_4, R.id.t2_5,
                R.id.t3_0, R.id.t3_1, R.id.t3_2, R.id.t3_3, R.id.t3_4, R.id.t3_5,
                R.id.t4_0, R.id.t4_1, R.id.t4_2, R.id.t4_3, R.id.t4_4, R.id.t4_5,
                R.id.t5_0, R.id.t5_1, R.id.t5_2, R.id.t5_3, R.id.t5_4, R.id.t5_5};
        for (int i = 0; i < TextIds.length; i++) {
            TextView tekst = (TextView) findViewById(TextIds[i]);
            tekst.setVisibility(View.INVISIBLE);
            ImageView image = (ImageView) findViewById(ImageViewIds[i]);
            image.setVisibility(View.INVISIBLE);
            ImageButton obrazki = (ImageButton) findViewById(ImageButtonIds[i]);
            obrazki.setVisibility(View.VISIBLE);
        }

        Intent intent = new Intent(Menu.this, MainActivity.class);
        startActivity(intent);
    }
}
