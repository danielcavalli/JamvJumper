package com.example.danielcavalli.trbalho_jamv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

public class Platform {
    public int x;
    public int y;
    public float w;
    public float h;
    public float g = 10;
    public int change;
    Random rand = new Random();
    public Paint color;
    public Context ctx;
    public int[] x1 = new int[5];
    public int[] y1 = new int[5];
    boolean create = false;
    public boolean col = false;
    public int r;
    public int gc;
    public int b;

    public void onC()
    {
        for(int i = 0;i<5;i++)
        {
            if(i != 0){
                x1[i] = (int)w*i;
                y1[i] = -((int)h + ((int)h*i));
            }
        }
    }
    public Platform(int lY, Context c){
        change = lY;
        y = lY + (new Random()).nextInt(210);
        ctx = c;
        colorize();
    }

    public void Draw(Canvas canvas, int r,int gc, int b)
    {
        color=new Paint();
        color.setColor(Color.argb(255,r,gc,b));
        canvas.drawRect(x, y, w + x, h + y, color);
    }
    public void Update(Canvas canvas)
    {
        if(create == false)
        {
            w=canvas.getWidth()/5.4f;
            h=canvas.getHeight()/25.6f;
            g=canvas.getHeight()/192f;
            onC();
            create = true;
        }
        y += g;
        if(y >= canvas.getHeight())
        {
            y = y1[(new Random()).nextInt(5)];
            x =  x1[(new Random()).nextInt(5)];
        }
    }
    public void colorize()
    {
        r = (new Random()).nextInt(255);
        gc = (new Random()).nextInt(255);
        b = (new Random()).nextInt(255);
    }
}