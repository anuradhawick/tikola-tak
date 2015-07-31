package com.example.anuradha.app1.Effects;

import android.app.Activity;
import android.widget.ImageButton;

import com.example.anuradha.app1.R;

/**
 * Created by anuradha on 7/31/15.
 */
public class UImanager {
    private ImageButton btn00;
    private ImageButton btn01;
    private ImageButton btn02;
    private ImageButton btn10;
    private ImageButton btn11;
    private ImageButton btn12;
    private ImageButton btn20;
    private ImageButton btn21;
    private ImageButton btn22;
    private Activity activity;

    public UImanager(Activity runningActivity) {
        this.activity = runningActivity;
        btn00 = (ImageButton) activity.findViewById(R.id.bt00);
        btn01 = (ImageButton) activity.findViewById(R.id.bt01);
        btn02 = (ImageButton) activity.findViewById(R.id.bt02);
        btn10 = (ImageButton) activity.findViewById(R.id.bt10);
        btn11 = (ImageButton) activity.findViewById(R.id.bt11);
        btn12 = (ImageButton) activity.findViewById(R.id.bt12);
        btn20 = (ImageButton) activity.findViewById(R.id.bt20);
        btn21 = (ImageButton) activity.findViewById(R.id.bt21);
        btn22 = (ImageButton) activity.findViewById(R.id.bt22);
    }

    public void updateUI(int i, int j, char in, boolean winner) {
        //if it is a blank space
        if (in == '-') {
            String s = i + "" + j;
            switch (s) {
                case "00":
                    btn00.setImageResource(R.drawable.rsz_spc);
                    break;
                case "01":
                    btn01.setImageResource(R.drawable.rsz_spc);
                    break;
                case "02":
                    btn02.setImageResource(R.drawable.rsz_spc);
                    break;
                case "10":
                    btn10.setImageResource(R.drawable.rsz_spc);
                    break;
                case "11":
                    btn11.setImageResource(R.drawable.rsz_spc);
                    break;
                case "12":
                    btn12.setImageResource(R.drawable.rsz_spc);
                    break;
                case "20":
                    btn20.setImageResource(R.drawable.rsz_spc);
                    break;
                case "21":
                    btn21.setImageResource(R.drawable.rsz_spc);
                    break;
                case "22":
                    btn22.setImageResource(R.drawable.rsz_spc);
                    break;
            }
            return;

            //if it is not a blank space
        } else {
            String s = i + "" + j;
            switch (s) {
                case "00":
                    if (winner) {
                        btn00.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn00.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "01":
                    if (winner) {
                        btn01.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn01.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "02":
                    if (winner) {
                        btn02.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn02.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "10":
                    if (winner) {
                        btn10.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn10.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "11":
                    if (winner) {
                        btn11.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn11.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "12":
                    if (winner) {
                        btn12.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn12.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "20":
                    if (winner) {
                        btn20.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn20.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "21":
                    if (winner) {
                        btn21.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn21.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
                case "22":
                    if (winner) {
                        btn22.setImageResource((in == 'X') ? R.drawable.x_won : R.drawable.o_won);
                        return;
                    }
                    btn22.setImageResource((in == 'X') ? R.drawable.rsz_x : R.drawable.rsz_o);
                    break;
            }
        }

    }
}
