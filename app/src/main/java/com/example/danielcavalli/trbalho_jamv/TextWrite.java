package com.example.danielcavalli.trbalho_jamv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.w3c.dom.Text;


public class TextWrite
{
    public int x;
    public int y;
    public int w = 128;
    public int h = 256;
    public float g = 10;
    public Paint color;
    public Context ctx;

    public TextWrite(int x2, int y2)
    {
        x = x2;
        y = y2;
    }
    public void Write(Canvas c, String text, int size, int color, int backcolor)
    {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(size);
        c.drawText(text, x, y, paint);

    }
}

