package com.example.tea.animationexample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LayoutTransitionActivity extends AppCompatActivity {

    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_remove)
    Button btnRemove;
    @BindView(R.id.btn_removeAll)
    Button btnRemoveAll;
    @BindView(R.id.gl_content)
    GridLayout glContent;
    @BindView(R.id.activity_layout_transition)
    LinearLayout activityLayoutTransition;

    @BindView(R.id.lv_content)
    ListView lvContent;

    int mCount;
    List<String> mLists;
    MyAdapter mMyAdapter;

    LayoutTransition mTransition;
    LayoutAnimationController lac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition);
        ButterKnife.bind(this);

        mLists = new ArrayList<>();

        mMyAdapter = new MyAdapter();
        mCount = 1;

//
//        resetTransition();



        lvContent.setAdapter(mMyAdapter);
//        resetTransition();
    }

    @OnClick({R.id.btn_add, R.id.btn_remove, R.id.btn_removeAll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                doAdd();
                break;
            case R.id.btn_remove:
                doRemove();
                break;
            case R.id.btn_removeAll:
                doRemoveAll();
                break;
            default:
                break;
        }
    }

    private void doAdd() {
//        Button button = new Button(this);
//        button.setText("Button- " + mCount);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        button.setLayoutParams(params);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                glContent.removeView(v);
//            }
//        });
//        if (glContent.getChildCount() > 1) {
//            glContent.addView(button, 1);
//        } else {
//            glContent.addView(button);
//        }

        for (int i = 0; i < 15; i++) {
            mLists.add("list - " + i);
        }

        Animation animation = (Animation) AnimationUtils.loadAnimation(
                this, R.anim.left);
        lac = new LayoutAnimationController(animation);
        lac.setDelay(0.4f);  //设置动画间隔时间
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL); //设置列表的显示顺序
        lvContent.setLayoutAnimation(lac);

        mMyAdapter.notifyDataSetChanged();
        lac.start();
        mCount++;
    }

    private void doRemove() {
//        if (glContent.getChildCount() > 1) {
//            glContent.removeView(glContent.getChildAt(1));
//        } else {
//            glContent.removeView(glContent.getChildAt(0));
//        }
        if (mLists.size() > 1) {
            mLists.remove(1);
            mMyAdapter.notifyDataSetChanged();
        } else {
            mLists.remove(0);
            mMyAdapter.notifyDataSetChanged();
        }
    }

    private void doRemoveAll() {
//        if (glContent.getChildCount() > 0) {
//            glContent.removeAllViews();
//        }

        if (mLists.size() > 0) {
            mLists.clear();
            mMyAdapter.notifyDataSetChanged();
        }
    }

    // 重新生成LayoutTransition对象并设置给container
    private void resetTransition() {
        mTransition = new LayoutTransition();
        setupCustomAnimations();
        glContent.setLayoutTransition(mTransition);
    }

    // 生成自定义动画
    private void setupCustomAnimations() {

        // 控制ViewGroup其他自View的移动(除去添加或者移除的子View)
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0,
                1);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom",
                0, 1);

        // 动画：CHANGE_APPEARING
        // Changing while Adding
        PropertyValuesHolder pvhTransX = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_TRANSLATION_X,
                1f, 0f, 1f);
        PropertyValuesHolder pvhPivotX = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_PIVOT_X, 0.5f);
        PropertyValuesHolder pvhPivotY = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_PIVOT_Y, 0.5f);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_SCALE_X, 1f, 0f,
                1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_SCALE_Y, 1f, 0f,
                1f);


        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhTransX, pvhScaleX,
                pvhScaleY, pvhPivotX, pvhPivotY).setDuration(
                mTransition.getDuration(LayoutTransition.CHANGE_APPEARING));
        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
        changeIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });

        // 动画：CHANGE_DISAPPEARING
        // Changing while Removing
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(0.5f, 2f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe(
                PropertyConstant.PROPERTY_SCALE_X, kf0, kf1, kf2);
        final ObjectAnimator changeOut = ObjectAnimator
                .ofPropertyValuesHolder(this, pvhLeft, pvhTop, pvhRight,
                        pvhBottom, pvhRotation)
                .setDuration(
                        mTransition
                                .getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
                changeOut);
        changeOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setAlpha(1f);
            }
        });

        // 动画：APPEARING
        // view出现时 view自身的动画效果
        PropertyValuesHolder appInScaleX = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_SCALE_X, 0f,
                1f);
        PropertyValuesHolder appInScaleY = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_SCALE_Y, 0f,
                1f);
        PropertyValuesHolder appInPivotX = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_PIVOT_X, 0.5f);
        PropertyValuesHolder appInPivotY = PropertyValuesHolder.ofFloat(PropertyConstant.PROPERTY_PIVOT_Y, 0.5f);
        ObjectAnimator animIn = ObjectAnimator.ofPropertyValuesHolder(this, appInPivotX, appInPivotY,
                appInScaleX, appInScaleY).setDuration(
                mTransition.getDuration(LayoutTransition.APPEARING));
        mTransition.setAnimator(LayoutTransition.APPEARING, animIn);
        animIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });

        // 动画：DISAPPEARING
        // view 消失时，view自身的动画效果
        ObjectAnimator animOut = ObjectAnimator.ofFloat(this, "rotationX", 0f,
                90f).setDuration(
                mTransition.getDuration(LayoutTransition.DISAPPEARING));
        mTransition.setAnimator(LayoutTransition.DISAPPEARING, animOut);
        animOut.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationX(0f);
            }
        });

    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mLists.size();
        }

        @Override
        public Object getItem(int position) {
            return mLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            Button button;
            view = convertView;

            if (null == view) {
                LayoutInflater inflater = LayoutInflater.from(LayoutTransitionActivity.this);
                view = inflater.inflate(R.layout.item, null, false);
                button = (Button) view.findViewById(R.id.btn_item);
                view.setTag(button);
            } else {
                button = (Button) view.getTag();
            }
            button.setText(mLists.get(position));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(LayoutTransitionActivity.this, "position: " + position, Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }
    }


}
