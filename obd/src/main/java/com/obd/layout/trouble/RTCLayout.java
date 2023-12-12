package com.obd.layout.trouble;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.obd.R;
import com.core.views.NormalButton;
import com.obd.command.CommandListener;
import com.obd.command.trouble.TroubleRTC;
import com.obd.layout.DefaultLayout;

public class RTCLayout extends DefaultLayout {
    NormalButton clearButton;

    public RTCLayout(Context context) {
        super(context);
    }

    public RTCLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RTCLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RTCLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void startCommand() {
        new TroubleRTC(new CommandListener() {
            @Override
            public void onSuccess(String value, String unit) {
                Toast toast = Toast.makeText(getContext(), R.string.OBD_錯誤代碼消除成功, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }

            @Override
            public void onFailed(String value, String unit) {
                Toast toast = Toast.makeText(getContext(), R.string.OBD_錯誤代碼消除失敗, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    @Override
    protected void initView(View view) {
        clearButton = view.findViewById(R.id.clear_button);
        clearButton.setOnClickListener(view1 -> startCommand());
    }

    @Override
    protected int getViewId() {
        return R.layout.layout_reset_trouble_codes;
    }
}
