package com.example.lenovo.prozhito.CustomViews;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.example.lenovo.prozhito.utils.MyApplication;

public class MyEditText extends AppCompatEditText {


    public MyEditText(Context context) {
        super(context);
        setTypeface(MyApplication.normalTypeFace);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApplication.normalTypeFace);
    }

    public String text() {
        if (cleared() != null)
            return cleared();
        return "";
    }

    public String cleared(){
        if (getText() != null) {
            return getText().toString()
                    .replace("۰" , "0")
                    .replace("۱" , "1")
                    .replace("۲" , "2")
                    .replace("۳" , "3")
                    .replace("۴" , "4")
                    .replace("۵" , "5")
                    .replace("۶" , "6")
                    .replace("۷" , "7")
                    .replace("۸" , "8")
                    .replace("۹" , "9")
                    .trim();
        }else return "" ;
    }
}
