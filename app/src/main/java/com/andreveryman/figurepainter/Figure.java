package com.andreveryman.figurepainter;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Figure {

    private int color;

    private Paint paint = new Paint();

    public Paint getPaint(){
        return paint;
    }

    public void setPaint(Paint paint){
        this.paint = paint;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
    }

    public Figure(int color){
        this.color = color;
        paint.setColor(color);
        paint.setStrokeWidth(30);
        paint.setStyle(Paint.Style.STROKE);
    }

    public abstract void draw(Canvas canvas);

    public abstract void setStartingPoint(float x, float y);
    public abstract void setEndPoint(float x,float y);

}

