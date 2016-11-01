package com.example.tea.animationexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_tween)
    Button btnTween;
    @BindView(R.id.btn_frame)
    Button btnFrame;
    @BindView(R.id.btn_property)
    Button btnProperty;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_tween, R.id.btn_frame, R.id.btn_property})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_tween:
                intent = new Intent(this, TweenActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_frame:
                intent = new Intent(this, FrameActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_property:
                intent = new Intent(this, PropertyActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
