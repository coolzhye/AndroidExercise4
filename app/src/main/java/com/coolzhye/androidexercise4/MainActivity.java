package com.coolzhye.androidexercise4;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.DynamicAnimation.OnAnimationEndListener;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean isLeft = true;
    private boolean isUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = findViewById(R.id.imageView);

        final SpringAnimation animX = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_X);
        final SpringAnimation animY = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_Y);

        animX.animateToFinalPosition(500);
        animX.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);

        animX.addEndListener(new OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                isLeft = !isLeft;
                animY.animateToFinalPosition(isUp ? 500 : 0);
                animY.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
            }
        });

        animY.addEndListener(new OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                isUp = !isUp;
                animX.animateToFinalPosition(isLeft ? 500 : 0);
                animX.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
            }
        });
    }
}
