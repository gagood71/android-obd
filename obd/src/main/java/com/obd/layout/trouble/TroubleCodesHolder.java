package com.obd.layout.trouble;

import android.view.View;

import androidx.annotation.NonNull;

import com.obd.R;
import com.core.adapters.RecyclerHolder;
import com.core.views.NormalText;

public class TroubleCodesHolder extends RecyclerHolder {
    public NormalText troubleCodeText;

    public TroubleCodesHolder(@NonNull View itemView) {
        super(itemView);

        troubleCodeText = itemView.findViewById(R.id.trouble_code_text);
    }
}
