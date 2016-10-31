package com.example.tea.animationexample;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FrameActivity extends AppCompatActivity {

    @BindView(R.id.tb_frame)
    Toolbar tbFrame;
    @BindView(R.id.iv_frame)
    ImageView ivFrame;
    @BindView(R.id.activity_frame)
    LinearLayout activityFrame;

    AnimationDrawable mAnimationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_frame, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        tbFrame.setTitle("FrameDemo");
        setSupportActionBar(tbFrame);
        tbFrame.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mn_xmlStart:
                        doXmlStart();
                        break;
                    case R.id.mn_xmlStop:
                        doXmlStop();
                        break;
                    case R.id.mn_codeStart:
                        doCodeStart();
                        break;
                    case R.id.mn_codeStop:
                        doCodeStop();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void doXmlStart() {
        AnimationDrawable animationDrawable = (AnimationDrawable) ivFrame.getDrawable();

        if (animationDrawable != null) {
            animationDrawable.start();
        }
    }

    private void doXmlStop() {
        AnimationDrawable animationDrawable = (AnimationDrawable) ivFrame.getDrawable();
        if (null != animationDrawable && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }

    private void doCodeStart() {
        mAnimationDrawable = new AnimationDrawable();

        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_11), 200);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_10), 200);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_9), 200);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_8), 200);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_7), 200);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_6), 200);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_5), 200);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_4), 200);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_3), 200);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_2), 200);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.ic_1), 200);

        mAnimationDrawable.setOneShot(false);

        ivFrame.setImageDrawable(mAnimationDrawable);
        mAnimationDrawable.start();
    }

    private void doCodeStop() {
        if (null != mAnimationDrawable && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }
}
