package com.example.anuradha.app1.GameModes;

import android.app.Activity;

import com.example.anuradha.app1.Effects.UImanager;
import com.example.anuradha.app1.GameAI.Board;

/**
 * Created by anuradha on 8/1/15.
 */
public class HumanHumanNetwork extends HumanHuman {

    public boolean play;

    public HumanHumanNetwork(UImanager uimgr, Board bd, Activity act, boolean play_first) {
        super(uimgr, bd, act);
        this.play = play_first;
    }
}
