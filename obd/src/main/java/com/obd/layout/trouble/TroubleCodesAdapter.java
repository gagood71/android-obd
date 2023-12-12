package com.obd.layout.trouble;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.obd.R;
import com.core.adapters.RecyclerAdapter;

public class TroubleCodesAdapter extends RecyclerAdapter<String, TroubleCodesHolder> {
    public TroubleCodesAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public TroubleCodesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_trouble_code_item, null);

        return new TroubleCodesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TroubleCodesHolder holder, int position) {
        holder.troubleCodeText.setText(dataList.get(position));
    }
}
