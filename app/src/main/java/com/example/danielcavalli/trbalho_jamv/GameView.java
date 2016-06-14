package com.example.danielcavalli.trbalho_jamv;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View implements Runnable{

    public Handler handler = new Handler();

    public Player Jamv;
    public ArrayList<Platform> pL;
    public static boolean movControl;
    public TextWrite score;
    public TextWrite highscore;
    public int Highs;
    public void start(Context c)
    {
        int o = 900;
        Jamv = new Player(50,0,c);
        pL = new ArrayList<Platform>();
        pL.add(new Platform(o, c));
        pL.add(new Platform(2 * o, c));
        pL.add(new Platform(3 * o, c));
        pL.add(new Platform(4 * o, c));
        pL.add(new Platform(5 * o, c));
        pL.add(new Platform(6 * o, c));
        pL.add(new Platform(7 * o, c));
        pL.add(new Platform(8 * o, c));
        score = new TextWrite(10, 72);
        highscore = new TextWrite(10, 108);
    }
    public GameView (Context c)
    {
        super(c);
        start(c);
    }
    public void d(Canvas canvas)
    {
        for(Platform p : pL){
            p.Update(canvas);
            p.Draw(canvas, p.r, p.gc,p.b);
            Log.d("MainActivity", p.x + "," + p.y);
        }
        score.Write(canvas,"Score:" + String.valueOf(Jamv.score), 72, 255, 0);
        highscore.Write(canvas,"High:" + String.valueOf(Jamv.high), 36, 255, 0);
        Jamv.Draw(canvas);
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        invalidate();
        super.onDraw(canvas);
        d(canvas);
        Jamv.Update(canvas.getWidth(), canvas.getHeight(), pL, canvas);
    }

    @Override
    public void run(){
        handler.postDelayed(this,30);
        invalidate();
    }
}