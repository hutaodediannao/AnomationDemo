package com.app.anomationdemo;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FindViewByReflection {

    public static void bindTarget(final Activity activity) {
        Class cla = activity.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(BindView.class)) {
                BindView bindView = f.getAnnotation(BindView.class);
                int viewId = bindView.value();
                try {
                    if (viewId != 0) {
                        f.setAccessible(true);
                        f.set(activity, activity.findViewById(viewId));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        final Method[] methods = cla.getDeclaredMethods();
        for (final Method method : methods) {
            if (method.isAnnotationPresent(BindClick.class)) {
                method.setAccessible(true);
                BindClick bindClick = method.getAnnotation(BindClick.class);
                int viewId = bindClick.value();
                final View view = activity.findViewById(viewId);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            method.invoke(activity, view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

}
