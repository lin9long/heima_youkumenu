package com.linsaya.heima_youkumenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.linsaya.heima_youkumenu.Utils.AnimationUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ib_home;
    private ImageButton ib_menu;
    private RelativeLayout rl_level1;
    private RelativeLayout rl_level2;
    private RelativeLayout rl_level3;

    //boolean判断值，判断当前菜单是否存在
    boolean isLevel3Display = true;
    boolean isLevel2Display = true;
    boolean isLevel1Display = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {

        ib_home = (ImageButton) findViewById(R.id.ib_home);
        ib_menu = (ImageButton) findViewById(R.id.ib_menu);

        rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);

        ib_home.setOnClickListener(this);
        ib_menu.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (AnimationUtil.isRunningCount > 0) {
            return;
        }
        switch (view.getId()) {
            case R.id.ib_home:
                //设置延时时间，当3级菜单存在时，2级菜单转动出屏幕有延迟效果
                long delay = 0;
                if (isLevel2Display) {
                    if (isLevel3Display) {
                        AnimationUtil.rotateOutAnim(rl_level3, 0);
                        isLevel3Display = false;
                        delay += 150;
                    }
                    AnimationUtil.rotateOutAnim(rl_level2, delay);
                } else {
                    AnimationUtil.rotateInAnim(rl_level2, 0);
                }
                isLevel2Display = !isLevel2Display;
                break;
            case R.id.ib_menu:
                if (isLevel3Display) {
                    AnimationUtil.rotateOutAnim(rl_level3, 0);
                } else {
                    AnimationUtil.rotateInAnim(rl_level3, 0);
                }
                isLevel3Display = !isLevel3Display;
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果按下返回菜单按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long delay = 0;
            //判断第一层菜单是否显示
            if (isLevel1Display) {

                if (isLevel3Display) {
                    AnimationUtil.rotateOutAnim(rl_level3, 0);
                    isLevel3Display = false;
                    delay += 200;
                }
                if (isLevel2Display) {
                    AnimationUtil.rotateOutAnim(rl_level2, delay);
                    isLevel3Display = false;
                    delay += 200;
                }
                AnimationUtil.rotateOutAnim(rl_level1, delay);

            } else {
                //如果三层菜单均不存在，逐个执行转入动画，添加延时
                AnimationUtil.rotateInAnim(rl_level1, 0);
                AnimationUtil.rotateInAnim(rl_level2, 200);
                AnimationUtil.rotateInAnim(rl_level3, 400);

                isLevel2Display = true;
                isLevel3Display = true;
            }
            isLevel1Display = !isLevel1Display;
            return true;
        }
        return true;
    }
}

