package com.example.a95306.clock;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import static android.R.attr.id;

/**
 * Created by 95306 on 2019-06-16.
 */

public class SelectRemindWayPopup implements View.OnClickListener {
    private TextView remaind_way1, remaind_way2;
    public PopupWindow mPopupWindow;
    private SelectRemindWayPopupOnClickListener selectRemindWayPopupListener;

    public PopupWindow getmPopupWindow() {
        return mPopupWindow;
    }

    private Context mContext;

    @SuppressWarnings("deprecation")
    public SelectRemindWayPopup(Context context) {
        mContext = context;
        mPopupWindow = new PopupWindow(context);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        mPopupWindow.setHeight(WindowManager.LayoutParams.FILL_PARENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.AnimBottom);
        mPopupWindow.setContentView(initViews());

        mPopupWindow.getContentView().setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mPopupWindow.setFocusable(false);
                mPopupWindow.dismiss();
                return true;
            }
        });

    }

    public View initViews() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.selectremindway_pop_window,
                null);

        remaind_way1 = (TextView) view.findViewById(R.id.tv_drugway_1);
        remaind_way2 = (TextView) view.findViewById(R.id.tv_drugway_2);
        remaind_way1.setOnClickListener(this);
        remaind_way2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_drugway_1){
            selectRemindWayPopupListener.obtainMessage(0);
        }
        else if(v.getId()==R.id.tv_drugway_2){
            selectRemindWayPopupListener.obtainMessage(1);
        }
        dismiss();

    }

    public interface SelectRemindWayPopupOnClickListener {
        void obtainMessage(int flag);
    }

    public void setOnSelectRemindWayPopupListener(SelectRemindWayPopupOnClickListener l) {
        this.selectRemindWayPopupListener = l;
    }

    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void showPopup(View rootView) {
        // 第一个参数是要将PopupWindow放到的View，第二个参数是位置，第三第四是偏移值
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }
}
