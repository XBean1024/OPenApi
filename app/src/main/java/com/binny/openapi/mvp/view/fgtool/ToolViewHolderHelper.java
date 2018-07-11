package com.binny.openapi.mvp.view.fgtool;

import android.content.Context;
import android.view.View;

import com.binny.openapi.R;
import com.binny.openapi.bean.ToolBean;
import com.smart.holder.iinterface.IViewHolder;
import com.smart.holder.iinterface.IViewHolderHelper;

import java.util.List;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/25 15:55
 * @Description:
 */
public class ToolViewHolderHelper implements IViewHolderHelper<ToolViewHolder,ToolBean> {
    private IToolItemClickedListener mItemClickedListener;

    public ToolViewHolderHelper(IToolItemClickedListener itemClickedListener) {
        mItemClickedListener = itemClickedListener;
    }

    @Override
    public IViewHolder initItemViewHolder(ToolViewHolder viewHolder, View convertView) {
        viewHolder = new ToolViewHolder();
        viewHolder.mToolItemTv = convertView.findViewById(R.id.tool_item_tv);
        return viewHolder;
    }

    @Override
    public void bindListDataToView(Context context, List<ToolBean> iBaseBeanList, ToolViewHolder viewHolder, int position) {
        viewHolder.mToolItemTv.setText(iBaseBeanList.get(position).getItemText());
        viewHolder.mToolItemTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickedListener.onItemClicked(position, v);
            }
        });
    }
}
