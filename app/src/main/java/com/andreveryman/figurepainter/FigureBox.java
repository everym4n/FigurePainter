package com.andreveryman.figurepainter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class FigureBox extends Figure {

    private PointF startPoint = new PointF();
    private PointF endPoint = new PointF();

    public FigureBox(int color) {
        super(color);
        getPaint().setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(startPoint.x,startPoint.y,endPoint.x,endPoint.y,getPaint());
    }

    @Override
    public void setStartingPoint(float x, float y) {
        startPoint.x = x; startPoint.y = y;
    }

    @Override
    public void setEndPoint(float x, float y) {
        endPoint.x = x; endPoint.y = y;

    }


}
