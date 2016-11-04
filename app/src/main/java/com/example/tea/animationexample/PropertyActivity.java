package com.example.tea.animationexample;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertyActivity extends AppCompatActivity {

    @BindView(R.id.tb_property)
    Toolbar tbProperty;
    @BindView(R.id.btn_property)
    Button btnProperty;
    @BindView(R.id.activity_property)
    LinearLayout activityProperty;
    @BindView(R.id.btn_propertyA)
    Button btnPropertyA;

    ValueAnimator mValueAnimator;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        ButterKnife.bind(this);

        initView();

        instanceValueAnimator();

        btnProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PropertyActivity.this, "doOnClick", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        tbProperty.setTitle("PropertyDemo");
        setSupportActionBar(tbProperty);
    }

    private void instanceValueAnimator() {

        //起始颜色为红色
        int startColor = 0xffff0000;
        //终止颜色为绿色
        int endColor = 0xff00ff00;

        // 通过静态方法构建一个ValueAnimator对象
        // 设置数值集合
//        mValueAnimator = ValueAnimator.ofArgb(startColor, endColor);
        mValueAnimator = ValueAnimator.ofFloat(0f, 400f);
        // 设置作用对象
        mValueAnimator.setTarget(btnProperty);
        // 设置动画无限循环
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        // 设置动画执行时间
        mValueAnimator.setDuration(4000);
        // 添加动画更新监听
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 获取当前值
                Float mValue = (Float) animation.getAnimatedValue();
                // 动画进度(t/duration)
                float fraction = animation.getAnimatedFraction();

                Log.d("PropertyActivity", "mValue:" + mValue);
                Log.d("PropertyActivity", "fraction:" + fraction + " | t:" + fraction * animation.getDuration());

                // 设置横向偏移量
                btnProperty.setTranslationX(mValue * 0.5f);
                // 设置纵向偏移量
                btnProperty.setTranslationY(mValue);
//                int valueColor = (int) animation.getAnimatedValue();
//                Log.d("PropertyActivity", "mValue:" + valueColor);
//                btnProperty.setBackgroundColor(valueColor);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_property, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_valueAnimatorStart:
                doValueAnimatorStart();
                break;
            case R.id.mn_valueAnimatorPause:
                doValueAnimatorPause();
                break;
            case R.id.mn_valueAnimatorReverse:
                doValueAnimatorReverse();
                break;
            case R.id.mn_valueAnimatorCancel:
                doValueAnimatorCancel();
                break;
            case R.id.mn_valueAnimatorEnd:
                doValueAnimatorEnd();
                break;
            case R.id.mn_valueAnimatorRestart:
                doValueAnimatorRestart();
                break;
            case R.id.mn_defineTypeEvaluator:
                doDefineTypeEvaluator();
                break;
            case R.id.mn_propertyValuesHolder:
                doPropertyValuesHolder();
                break;
            case R.id.mn_objectAnimator:
                doObjectAnimator();
                break;
            case R.id.mn_interpolator:
                doInterpolator();
                break;
            default:
                break;
        }
        return true;
    }

    private void doValueAnimatorStart() {
        mValueAnimator.start();
    }

    private void doValueAnimatorPause() {
        // API > 19
        // 暂停运行的动画，将控件停留在当前帧
        mValueAnimator.pause();
    }


    private void doValueAnimatorReverse() {
        mValueAnimator.reverse();
    }

    private void doValueAnimatorCancel() {
        // ，将控件停留在当前帧
        mValueAnimator.cancel();
    }

    private void doValueAnimatorEnd() {
        // 动画停止，将控件停留在最后一帧
        mValueAnimator.end();
    }

    private void doValueAnimatorRestart() {
        mValueAnimator.resume();
    }


    private void doDefineTypeEvaluator() {

        //起始颜色为红色
        int startColor = 0xffff0000;
        //终止颜色为绿色
        int endColor = 0xff00ff00;

        MyPoint pointStart = new MyPoint(0f, startColor, 1.0f);
        MyPoint pointEnd = new MyPoint(400f, endColor, 0.2f);

        // 通过静态方法构建一个ValueAnimator对象
        // 动画属性值计算器使用自定义的计算器(MyEvaluator)
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new MyEvaluator(), pointStart, pointEnd);
        // 设置作用对象
        valueAnimator.setTarget(btnProperty);
        // 设置动画无限循环
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        // 设置动画执行时间
        valueAnimator.setDuration(4000);
        valueAnimator.setInterpolator(new MyInterpolator());
        // 添加动画更新监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 获取当前值
                MyPoint mValue = (MyPoint) animation.getAnimatedValue();
                // 动画进度(t/duration)
                float fraction = animation.getAnimatedFraction();

                Log.d("PropertyActivity", "mValue:" + mValue.toString());
                Log.d("PropertyActivity", "fraction:" + fraction + " | t:" + fraction * animation.getDuration());

                // 设置横向偏移量
                btnProperty.setTranslationX(mValue.getTrance() * 0.5f);
                // 设置纵向偏移量
                btnProperty.setTranslationY(mValue.getTrance());
                // 设置背景颜色
                btnProperty.setBackgroundColor(mValue.getColor());
                // 设置横向缩放
                btnProperty.setScaleX(mValue.getScale());
                // 设置纵向缩放
                btnProperty.setScaleX(mValue.getScale());
            }
        });

        valueAnimator.start();
    }

    private void doPropertyValuesHolder() {
        PropertyValuesHolder holderRight = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_TRANSLATION_X, 100f);
        PropertyValuesHolder holderTop = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_TRANSLATION_Y, 300f);

        ValueAnimator valueAnimator = ValueAnimator.ofPropertyValuesHolder(holderRight, holderTop);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(3000);
        valueAnimator.setTarget(btnProperty);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float left = (float) valueAnimator.getAnimatedValue(PropertyConstant.PROPERTY_TRANSLATION_X);
                float top = (float) valueAnimator.getAnimatedValue(PropertyConstant.PROPERTY_TRANSLATION_Y);

                btnProperty.setTranslationX(left);
                btnProperty.setTranslationY(top);
            }
        });
        valueAnimator.start();
    }

    private void doObjectAnimator() {
//        ObjectAnimator objectAnimator = ObjectAnimator
//                .ofFloat(btnProperty, PropertyConstant.PROPERTY_TRANSLATION_X,0,300f, 200f);
//        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        objectAnimator.setDuration(3000);
//        objectAnimator.start();

        PropertyValuesHolder holderRight = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_TRANSLATION_X, 100f);
        PropertyValuesHolder holderTop = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_TRANSLATION_Y, 300f);

        ObjectAnimator objectAnimator = ObjectAnimator
                .ofPropertyValuesHolder(btnProperty, holderRight, holderTop)
                .setDuration(3000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    private void doInterpolator() {
        LinearInterpolator ll = new LinearInterpolator();
        ObjectAnimator animator = ObjectAnimator.ofFloat(btnProperty, "rotation",
                0f, 360f);
        animator.setInterpolator(ll);
        animator.setDuration(5000);
        animator.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator animatorA = ObjectAnimator.ofFloat(btnPropertyA, "rotation",
                0f, 360f);
        animatorA.setInterpolator(new MyInterpolator());
        animatorA.setRepeatCount(ValueAnimator.INFINITE);
        animatorA.setDuration(5000);

        animator.start();
        animatorA.start();
    }

}
