package com.andreveryman.figurepainter;

import android.graphics.Canvas;
import android.graphics.PointF;

public class FigureLine extends Figure {

    private PointF startPoint = new PointF();
    private PointF endPoint = new PointF();

    public FigureLine(int color) {
        super(color);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(startPoint.x,startPoint.y,endPoint.x,endPoint.y,getPaint());
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
