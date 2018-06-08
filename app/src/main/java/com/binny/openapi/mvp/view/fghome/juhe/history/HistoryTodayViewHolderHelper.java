package com.binny.openapi.mvp.view.fghome.juhe.history;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.HistoryDayBean;
import com.binny.openapi.mvp.view.activity.HistoryDetailActivity;
import com.binny.openapi.mvp.view.activity.MainActivity;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * Created by binny on 2018/6/1.
 * 历史上的今天
 */

public class HistoryTodayViewHolderHelper implements IViewHolderHelper<HistoryTodayViewHolder, HistoryDayBean.ResultBean> {


    @Override
    public IViewHolder initItemViewHolder(HistoryTodayViewHolder viewHolder, View convertView) {
        viewHolder = new HistoryTodayViewHolder();
        viewHolder.mDate = convertView.findViewById(R.id.history_date);
        viewHolder.mTitle = convertView.findViewById(R.id.history_title);
        viewHolder.mLayout = convertView.findViewById(R.id.history_rl);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<HistoryDayBean.ResultBean> iBaseBeanList, HistoryTodayViewHolder viewHolder, int position) {
        viewHolder.mDate.setText(iBaseBeanList.get(position).getDate());
        viewHolder.mTitle.setText(iBaseBeanList.get(position).getTitle());

        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) context;
                Intent intent = new Intent(activity, HistoryDetailActivity.class);
                intent.putExtra("e_id",iBaseBeanList.get(position).getE_id());
                activity.intoActivityWithAnimotion(intent);
            }
        });
    }


}
