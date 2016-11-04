package com.example.tea.animationexample;

import android.animation.TypeEvaluator;
import android.graphics.Color;
import android.util.Log;

/**
 * @Author Tiany
 * @Time 2016/11/3.
 */
public class MyEvaluator implements TypeEvaluator<MyPoint> {
    @Override
    public MyPoint evaluate(float fraction, MyPoint pointStart, MyPoint pointEnd) {
        Log.d("MyEvaluator", "fraction:" + fraction);
        MyPoint pointCurrent = new MyPoint();

        pointCurrent.setColor(getCurrentColor(fraction, pointStart.getColor(), pointEnd.getColor()));
        pointCurrent.setTrance(getCurrentTrance(fraction, pointStart.getTrance(), pointEnd.getTrance()));
        pointCurrent.setScale(getCurrentScale(fraction, pointStart.getScale(), pointEnd.getScale()));
        return pointCurrent;
    }

    /**
     * 根据fraction值来计算当前的颜色。
     */
    private int getCurrentColor(float fraction, int startColor, int endColor) {
        int redCurrent;
        int blueCurrent;
        int greenCurrent;
        int alphaCurrent;

        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);
        int alphaStart = Color.alpha(startColor);

        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);
        int alphaEnd = Color.alpha(endColor);

        int redDifference = redEnd - redStart;
        int blueDifference = blueEnd - blueStart;
        int greenDifference = greenEnd - greenStart;
        int alphaDifference = alphaEnd - alphaStart;

        redCurrent = (int) (redStart + fraction * redDifference);
        blueCurrent = (int) (blueStart + fraction * blueDifference);
        greenCurrent = (int) (greenStart + fraction * greenDifference);
        alphaCurrent = (int) (alphaStart + fraction * alphaDifference);

        return Color.argb(alphaCurrent, redCurrent, greenCurrent, blueCurrent);
    }

    /**
     * 根据fraction值来计算当前的移动距离
     */
    private float getCurrentTrance(float fraction, float tranceStart, float tranceEnd) {
        float tranceCurrent;
        float difference;

        difference = tranceEnd - tranceStart;

        tranceCurrent = tranceStart + difference * fraction;

        return tranceCurrent;
    }

    /**
     * 根据fraction值来计算当前缩放比例
     */
    private float getCurrentScale(float fraction, float scaleStart, float scaleEnd) {
        float scaleCurrent;
        float difference;

        difference = scaleEnd - scaleStart;

        scaleCurrent = scaleStart + difference * fraction;

        return scaleCurrent;
    }
}
