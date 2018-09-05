package com.sbl.animationstudy;



import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_test_one);
        animTestJAVA(textView);
    }


    /**
     * ValueAnimatot.ofInt(int values)
     * 将初始值以整型数值的形式过渡到结束值
     * @param view
     */
    public void animTestJAVA(final View view){
        /**
         *  ofInt()作用。1.创建动画实例2.将传入的多个（可变参数）Int参数进行平滑过渡
         *  内置了估值器，可直接使用。默认设置了从初始值到结束值
         */
        ValueAnimator anim = ValueAnimator.ofInt(view.getLayoutParams().width,1000);

        anim.setDuration(2000);//设置运行时长
        anim.setStartDelay(500);//延时播放时间
        anim.setRepeatCount(0);//重复播放次数
        anim.setRepeatMode(ValueAnimator.RESTART);//RESTART正序播放，REVERSE倒序

        //步骤三，将改变的值手动赋给对象属性：通过动画的更新监听器。值每次改变，变化一次，该方法调用一次
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentValur = (Integer) valueAnimator.getAnimatedValue();
                Log.e("songbl", String.valueOf(currentValur));

                //步骤四：将改变后的值赋给对象的属性（伪代码）
            //    view.setproperty(currentValur);
                view.getLayoutParams().width = currentValur;

                //步骤五：刷新视图，重新绘制，实现动画效果
                view.requestLayout();
            }
        });
        anim.start();//启动
    }

    /**
     *  方案二，在XML中定义（在res/animator）的文件夹里创建对应的动画.xml
     */

    public void animTestXML(Context context,View view){
        Animator animation = AnimatorInflater.loadAnimator(context,R.animator.animator_test_one);//载入xml
        animation.setTarget(view);//设置动画对象
        animation.start();
    }

    /**
     *  ValueAnimator.ofObject()
     *  将初始值以对象的形式过度到结束值
     */
    public void animationObjTest(){

    }























}
