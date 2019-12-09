package com.andreveryman.figurepainter;


public enum PaintMode {
    PATH(R.string.path),
    BOX(R.string.box),
    LINE (R.string.line);





    PaintMode(int name){
        this.name = name;
    }

    public final int name;

}
