package com.example.tea.animationexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TweenActivity extends AppCompatActivity {

    @BindView(R.id.tb_tween)
    Toolbar tbTween;
    @BindView(R.id.iv_tween)
    ImageView ivTween;
    @BindView(R.id.activity_tween)
    LinearLayout activityTween;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tbTween.setTitle("TweenDemo");
        setSupportActionBar(tbTween);
        tbTween.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mn_alphaXml:
                        doAlphaXml();
                        break;
                    case R.id.mn_alphaCode:
                        doAlphaCode();
                        break;
                    case R.id.mn_scaleXml:
                        doScaleXml();
                        break;
                    case R.id.mn_scaleCode:
                        doScaleCode();
                        break;
                    case R.id.mn_translateXml:
                        doTranslateXml();
                        break;
                    case R.id.mn_translateCode:
                        doTranslateCode();
                        break;
                    case R.id.mn_rotateXml:
                        doRotateXml();
                        break;
                    case R.id.mn_rotateCode:
                        doRotateCode();
                        break;
                    case R.id.mn_animationSet:
                        doAnimationSet();
                        break;
                    case R.id.mn_fillAfter:
                        doFillAfter();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tween, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void doAlphaXml() {
        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_tea);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivTween.startAnimation(alphaAnimation);
    }

    private void doAlphaCode() {
        // 1.创建一个AnimationSet对象，参数为Boolean型
        // true表示使用Animation的interpolator，false则是使用自己的
        AnimationSet as = new AnimationSet(true);
        // 2.创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
        // 第一个参数：fromAlpha 开始的透明度
        // 第二个参数：toAlpha 结束的透明度
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0f);
        // 3.设置动画属性
        //设置动画执行的时间
        alphaAnimation.setDuration(3000);
        // 4.将alphaAnimation对象添加到AnimationSet当中
        as.addAnimation(alphaAnimation);

        // 5.使用ImageView的startAnimation方法执行动画
        ivTween.startAnimation(as);
    }

    private void doScaleXml() {
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_tea);
        ivTween.startAnimation(scaleAnimation);
    }

    private void doScaleCode() {
        AnimationSet animationSet = new AnimationSet(true);
        //参数1：x轴的初始值
        //参数2：x轴收缩后的值
        //参数3：y轴的初始值
        //参数4：y轴收缩后的值
        //参数5：确定x轴坐标的类型
        //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
        //参数7：确定y轴坐标的类型
        //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 1f,1.5f,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(3000);
        animationSet.addAnimation(scaleAnimation);
        ivTween.startAnimation(animationSet);
    }

    private void doTranslateXml() {
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_tea);
        ivTween.startAnimation(translateAnimation);
    }

    private void doTranslateCode() {
        AnimationSet animationSet = new AnimationSet(true);
        //参数1～2：x轴的开始位置
        //参数3～4：y轴的开始位置
        //参数5～6：x轴的结束位置
        //参数7～8：x轴的结束位置
        TranslateAnimation translateAnimation =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF,0f,
                        Animation.RELATIVE_TO_SELF,1f,
                        Animation.RELATIVE_TO_SELF,0f,
                        Animation.RELATIVE_TO_SELF,1f);
        translateAnimation.setDuration(3000);
        animationSet.addAnimation(translateAnimation);
        ivTween.startAnimation(animationSet);
    }

    private void doRotateXml() {
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_tea);
        ivTween.startAnimation(rotateAnimation);
    }

    private void doRotateCode() {
        AnimationSet animationSet = new AnimationSet(true);
        //参数1：从哪个旋转角度开始
        //参数2：转到什么角度
        //后4个参数用于设置围绕着旋转的圆的圆心在哪里
        //参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
        //参数4：旋转中心点，x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
        //参数5：确定y轴坐标的类型
        //参数6：旋转中心点，y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        // 设置旋转属性
        rotateAnimation.setDuration(3000);
        animationSet.addAnimation(rotateAnimation);
        ivTween.startAnimation(animationSet);
    }

    private void doAnimationSet() {
        Animation animationSet = AnimationUtils.loadAnimation(this, R.anim.set_tea);
        ivTween.startAnimation(animationSet);
    }

    private void doFillAfter() {
        Animation animationSet = AnimationUtils.loadAnimation(this, R.anim.set_tea);
        animationSet.setFillAfter(true);
        animationSet.setFillBefore(false);
        animationSet.setFillEnabled(false);
        ivTween.startAnimation(animationSet);
    }

}
