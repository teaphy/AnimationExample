package com.example.tea.animationexample;

/**
 * 属性动画
 * @Author Tiany
 * @Time 2016/11/3.
 */
public class PropertyConstant {
    // 两个属性控制了View所处的位置，它们的值是由layout容器设置的，是相对于坐标原点（0，0左上角）
    public static String PROPERTY_TRANSLATION_X = "translationX";
    public static String PROPERTY_TRANSLATION_Y = "translationY";
    // 控制View绕着轴点（pivotX和pivotY）旋转
    public static String PROPERTY_ROTATION = "rotation";
    public static String PROPERTY_ROTATION_X = "rotationX";
    public static String PROPERTY_ROTATION_Y = "rotationY";
    // 旋转的轴点和缩放的基准点，默认是View的中心点
    public static String PROPERTY_PRVOT_X = "pivotX";
    public static String PROPERTY_PRVOT_Y = "pivotY";
    // 控制View基于pivotX和pivotY的缩放
    public static String PROPERTY_SCALE_X = "scaleX";
    public static String PROPERTY_SCALE_Y = "scaleY";
    // 描述了view在其父容器中的最终位置，是左上角左标和偏移量（translationX，translationY）的和
    public static String PROPERTY_X = "x";
    public static String PROPERTY_Y = "y";
    // 透明度，1是完全不透明，0是完全透明
    public static String PROPERTY_ALPHA = "aplha";
}
