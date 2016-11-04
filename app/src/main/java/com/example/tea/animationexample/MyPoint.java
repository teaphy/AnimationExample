package com.example.tea.animationexample;

/**
 * @Author Tiany
 * @Time 2016/11/2.
 */
public class MyPoint {
    float trance; // 移动距离
    int color; // 颜色
    float scale; // 缩放比例

    public MyPoint() {
    }

    public MyPoint(float trance, int color, float scale) {
        this.trance = trance;
        this.color = color;
        this.scale = scale;
    }

    public float getTrance() {
        return trance;
    }

    public void setTrance(float trance) {
        this.trance = trance;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "trance=" + trance +
                ", color=" + color +
                ", scale=" + scale +
                '}';
    }
}
