package com.andreveryman.figurepainter;

import android.graphics.Canvas;
import android.graphics.Path;

public class FigurePath extends Figure {

    private Path path = new Path();

    public FigurePath(int color) {
        super(color);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(path,getPaint());
    }

    @Override
    public void setStartingPoint(float x, float y) {
        path.moveTo(x,y);
    }

    @Override
    public void setEndPoint(float x, float y) {
        path.lineTo(x,y);
    }
}
