package com.example.lenovo.prozhito.CustomViews;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.example.lenovo.prozhito.utils.MyApplication;

public class MyTextView extends AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
        setTypeface(MyApplication.normalTypeFace);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApplication.normalTypeFace);
    }
}
