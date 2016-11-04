package com.example.tea.animationexample;

import android.animation.TimeInterpolator;
import android.view.animation.BaseInterpolator;
import android.view.animation.Interpolator;

/**
 * @Author Tiany
 * @Time 2016/11/4.
 */
public class MyInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float inter;
        inter = 1 - input * input;
        return inter;
    }
}
