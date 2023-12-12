package com.obd.command;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.core.views.NormalText;
import com.obd.R;
import com.obd.layout.DefaultLayout;

public abstract class CommandLayout extends DefaultLayout {
    protected NormalText obdNameText;
    protected NormalText obdValueText;
    protected NormalText obdUnitText;

    public CommandLayout(Context context) {
        super(context);
    }

    public CommandLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommandLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CommandLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initView(View view) {
        obdNameText = view.findViewById(R.id.obd_name_text);
        obdValueText = view.findViewById(R.id.obd_value_text);
        obdUnitText = view.findViewById(R.id.obd_unit_text);

        obdNameText.setText(getCommandName());
    }

    @Override
    protected int getViewId() {
        return R.layout.layout_obd_command;
    }

    protected abstract String getCommandName();
}
