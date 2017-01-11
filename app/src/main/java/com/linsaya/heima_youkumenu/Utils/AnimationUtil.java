package com.linsaya.heima_youkumenu.Utils;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/1/10.
 */

public class AnimationUtil {
    public static int isRunningCount = 0;

    public static void rotateOutAnim(RelativeLayout layout, long delay) {
        RotateAnimation ra = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        //设置动画执行延迟时间
        ra.setStartOffset(delay);
        ra.setDuration(500);
        //设置动画结束后保持结束位置
        ra.setFillAfter(true);
        //设置动画事件监听，执行内部代码（动画开始时标志位+1，在动画结束前不再重新执行动画）
        ra.setAnimationListener(new MyAnimListener());
        layout.startAnimation(ra);
    }

    public static void rotateInAnim(RelativeLayout layout, long delay) {
        RotateAnimation ra = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        ra.setStartOffset(delay);
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setAnimationListener(new MyAnimListener());
        layout.startAnimation(ra);
    }
    static class MyAnimListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            //动画开始时，给标志位执行+1赋值
            isRunningCount++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //动画开始时，给标志位执行-1赋值
            isRunningCount--;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
