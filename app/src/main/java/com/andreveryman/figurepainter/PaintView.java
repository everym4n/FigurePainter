package com.andreveryman.figurepainter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PaintView extends View {


    private ArrayList<Figure> figures = new ArrayList<>();

    private SparseArray<Figure> currentFigures = new SparseArray<>();
    private PaintMode mode = PaintMode.BOX;
    private int color = Color.RED;

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setMode(PaintMode mode) {
        this.mode = mode;
    }

    public void clear() {
        figures.clear();
        invalidate();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;

    }

    //Рисование реализовано с использованием мультитача, т.е. пятью пальцами можно проводить пять линий одновременно

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int pointerId = event.getPointerId(index);
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                Figure newFigure = getNewFigure();
                event.getPointerCoords(index, pointerCoords);
                newFigure.setStartingPoint(pointerCoords.x, pointerCoords.y);
                newFigure.setEndPoint(pointerCoords.x, pointerCoords.y);
                currentFigures.put(pointerId, newFigure);
                break;


            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int tempIndex = event.getPointerId(i);
                    Figure figure = currentFigures.get(tempIndex);

                    event.getPointerCoords(i, pointerCoords);
                    if (figure != null)
                        figure.setEndPoint(pointerCoords.x, pointerCoords.y);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                figures.add(currentFigures.get(pointerId));
                currentFigures.remove(pointerId);
                break;

            case MotionEvent.ACTION_UP:
                for (int i = 0; i < currentFigures.size(); i++) {
                    figures.add(currentFigures.get(currentFigures.keyAt(i)));
                }
                currentFigures.clear();
                break;

            case MotionEvent.ACTION_CANCEL:
                currentFigures.clear();
                break;

        }
        invalidate();
        return true;
    }


    private Figure getNewFigure() {
        switch (mode) {
            case BOX:
                return new FigureBox(color);
            case LINE:
                return new FigureLine(color);
            case PATH:
                return new FigurePath(color);
            default:
                return new FigurePath(color);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Figure figure : figures)
            figure.draw(canvas);
        for (int i = 0; i < currentFigures.size(); i++)
            currentFigures.get(currentFigures.keyAt(i)).draw(canvas);


    }


}
