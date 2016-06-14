package com.example.danielcavalli.trbalho_jamv;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.Random;


public class Player
{
    public int x;
    public int y;
    public int w = 128;
    public int h = 256;
    public float g = 10;
    public Paint color;
    public Context ctx;
    public static boolean paint;
    public static int score;
    boolean C = false;
    public int high = 0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public Player(int x2, int y2,Context c)
    {
        x = x2;
        y = y2;
        ctx = c;
        sp = c.getSharedPreferences("com.example.danielcavalli.trbalho_jamv.High", c.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void Draw(Canvas canvas)
    {
        color=new Paint();
        Bitmap b = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.player);
        color.setColor(Color.RED);
        canvas.drawBitmap(b, x, y, color);
    }

    boolean Collision(int x1,int y1,int w1, int h1)
    {
        return ((x < x1 + w1) ||
                (x + w > x1) ||
                (y < y1 + h1) ||
                (h + y > y1));
    }

    public void stayMiddle(Canvas c, ArrayList<Platform> pL)
    {
        if (y < c.getHeight()/2 && g < 0)
        {
            for(Platform p : pL)
            {
                p.g = c.getHeight() / 192f - (g);
            }
        }
        else
        {
            for(Platform p : pL)
            {
                p.g = c.getHeight() / 192f;
            }
            y += g;
        }
    }

    public void Update(int cW,int cH, ArrayList<Platform> pL ,Canvas c)
    {
        for(Platform p : pL)
        {
            if((x < p.x + p.w && x + w > p.x && y + h > p.y+p.h && y+h < p.y+(p.h*2)) && (g >= 0) &&(C == false))
            {
                g =-cH/38.4f;
                p.g=cH/128f;
                C = true;
                p.col = true;
                p.colorize();
            }
        }
        for(int i = 0;i < pL.size();i++)
        {
            if(g > 0)
            {
                C = false;
            }
            else
            {
                score++;
            }
        }
        if(g < cH/38.4f) {
            g += cH/960f;
        }
        x -= MainActivity.x * 2.3f;
        if(y >= cH)
        {
            for(int i = 0;i < pL.size();i++)
            {
                pL.get(i).x = pL.get(i).x1[(new Random()).nextInt(5)];
                pL.get(i).y = cH/(i+1)*2;
                pL.get(i).col = false;
                pL.get(i).colorize();
            }
            x=cW/2;
            y = cH/2-h;
            if(score > high)
            {
                editor.putInt("Highscore", score).commit();
                high = sp.getInt("Highscore", 0);
            }
            score=0;
        }
        if(x +w < 0)
        {
            x = c.getWidth();
        }
        else if (x > c.getWidth())
        {
            x = -w;
        }
        stayMiddle(c, pL);
    }
}

