package com.live.aksd.mvp.adapter.Mine;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.live.aksd.R;

import java.util.List;

/**
 * @author Created by stone
 * @since 17/9/8
 */

public class LogisticsCompanyAdapter extends RecyclerArrayAdapter<String> {

    public LogisticsCompanyAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LogisticsCompanyHolder(parent, viewType);
    }

    public class LogisticsCompanyHolder extends BaseViewHolder<String> {

        TextView company;

        public LogisticsCompanyHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_company_list);
            company = $(R.id.company);
        }

        @Override
        public void setData(String companyname) {
            company.setText(companyname);

        }
    }
}
