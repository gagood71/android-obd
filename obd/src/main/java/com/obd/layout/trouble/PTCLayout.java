package com.obd.layout.trouble;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.obd.R;
import com.core.views.NormalButton;
import com.obd.command.CommandListener;
import com.obd.command.trouble.TroublePTC;
import com.obd.layout.DefaultLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class PTCLayout extends DefaultLayout {
    RecyclerView recyclerView;

    NormalButton searchButton;

    TroubleCodesAdapter adapter;

    ArrayList<String> troubleCodes;

    public PTCLayout(Context context) {
        super(context);
    }

    public PTCLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PTCLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PTCLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void startCommand() {
        new TroublePTC(new CommandListener() {
            @Override
            public void onSuccess(String value, String unit) {
                troubleCodes.clear();
                troubleCodes.addAll(Arrays.asList(value.split(",")));

                if (adapter == null) {
                    adapter = new TroubleCodesAdapter(getContext());

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                }

                adapter.setList(troubleCodes);
            }

            @Override
            public void onFailed(String value, String unit) {
            }
        });
    }

    @Override
    public void initView(View view) {
        recyclerView = findViewById(R.id.recycler_view);

        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(view1 -> startCommand());
    }

    @Override
    protected int getViewId() {
        return R.layout.layout_trouble_codes;
    }
}
