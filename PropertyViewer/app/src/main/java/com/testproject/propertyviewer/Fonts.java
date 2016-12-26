package com.testproject.propertyviewer;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;


public class Fonts {

    public static final int BANDY_REGULAR = 0;
    public static final int CATHERINE = 1;

    private static final String BANDY_REGULAR_NAME = "Bandy-Regular.ttf";
    private static final String CATHERINE_NAME = "Catherine.ttf";

    private static final String FONTS_FOLDER = "fonts/";

    private static Fonts fontsManager;

    public static Fonts getSharedFontsManager() {
        if (fontsManager == null) {
            fontsManager = new Fonts();
        }
        return fontsManager;
    }

    public static void setFont(Context context, TextView textView, int font) {
        String fontPath = FONTS_FOLDER + getFontName(font);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
        textView.setTypeface(typeface);
    }

    private static String getFontName(int font) {
        String fontName = "";
        switch (font) {
            case BANDY_REGULAR:
                fontName = BANDY_REGULAR_NAME;
                break;
            case CATHERINE:
                fontName = CATHERINE_NAME;
                break;

        }
        return fontName;
    }
}
