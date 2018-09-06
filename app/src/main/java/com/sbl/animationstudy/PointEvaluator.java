package com.sbl.animationstudy;

import android.animation.TypeEvaluator;
import android.util.Log;

import com.sbl.Point;


/**
 *  定义估值器（设置动画是如何从初始值过渡到结束值）
 */
public class PointEvaluator implements TypeEvaluator {

    //在evaluate中写入动画对象过渡的逻辑，v：动画完成度
    @Override
    public Object evaluate(float v, Object o, Object t1) {
        Point startPoint = (Point)o;
        Point endPoint = (Point) t1;

        //根据v（动画完成度）计算当前动画的x和y的值
        float x = startPoint.getX() + v*(endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + v*(endPoint.getY() - startPoint.getY());
        //(其实还是值的运算)
        Log.e("songbl","估值器完成度"+v);
        //将计算后的坐标封装成一个新的Point对象并返回
        Point point = new Point(x,y);
        return point;
    }
























}
