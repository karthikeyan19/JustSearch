package com.example.karthik.justsearch;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class AnimationUtil {
    public static void animate(RecyclerView.ViewHolder holder) {

        YoYo.with(Techniques.RubberBand)
                .duration(1000)
                .playOn(holder.itemView);


    }
    public static void animateToolbar(ViewGroup containerToolbar) {



       containerToolbar.setRotationX(-90);
       containerToolbar.setAlpha(0.2F);
       containerToolbar.setPivotX(0.0F);
        containerToolbar.setPivotY(0.0F);

        Animator alpha = ObjectAnimator.ofFloat(containerToolbar, "alpha", 0.2F, 0.4F, 0.6F, 0.8F, 1.0F).setDuration(4000);
        Animator rotationX = ObjectAnimator.ofFloat(containerToolbar, "rotationX", -90, 60, -45, 45, -10, 30, 0, 20, 0, 5, 0).setDuration(8000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
       animatorSet.playTogether(alpha, rotationX);
        animatorSet.start();
    }

}
