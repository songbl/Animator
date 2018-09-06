package com.sbl.animationstudy;



import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView ;
    private ObjectAnimatorModeView objectAnimatorModeView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objectAnimatorModeView = findViewById(R.id.ov_test);
    //    textView = findViewById(R.id.tv_test_one);
   //     animTestJAVA(textView);
    //    objectAnimatorTestJAVA();

        objectmyDefineproperty();
    }


    /**
     * ValueAnimatot.ofInt(int values)
     * 将初始值以整型数值的形式过渡到结束值
     * ValueAnimator
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


    //===================ObjectAnimator================================
    /**
     *  直接对对象属性值进行改变操作，从而实现动画效果
     *  继承自ValueAnimator，低层动画实现机制基于ValueAnimator
     *  本质：还是通过   值   变化，再不断   自动   赋给对象的属性。
     */
    public void objectAnimatorTestJAVA(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView,"alpha",1f,0,1f);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(0);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.start();

    }
    public void objectAnimatorTestXML(Context context,View view){
        Animator animator = AnimatorInflater.loadAnimator(context,R.animator.animator_object_test);
        animator.setTarget(view);
        animator.start();
    }

    /**
     * 自定义属性，实现动画效果
     */
    public void objectmyDefineproperty(){
        ObjectAnimator anim = ObjectAnimator.ofObject(objectAnimatorModeView, "color", new ObjectEvaluator(),
                "#0000FF", "#FF0000");
        // 设置自定义View对象、背景颜色属性值 & 颜色估值器
        // 本质逻辑：
        // 步骤1：根据颜色估值器不断 改变 值
        // 步骤2：调用set（）设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
        // 步骤3：调用invalidate()刷新视图，即调用onDraw（）重新绘制，从而实现动画效果

        anim.setDuration(8000);
        anim.start();
    }

}




















