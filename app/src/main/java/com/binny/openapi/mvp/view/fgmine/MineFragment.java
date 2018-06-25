package com.binny.openapi.mvp.view.fgmine;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.binny.openapi.R;
import com.binny.openapi.bean.ToolBean;
import com.binny.openapi.callback.OnPermissionCallback;
import com.binny.openapi.constant.ConstantParams;
import com.binny.openapi.mvp.view.base.AbsBaseFragment;
import com.binny.openapi.util.FileUtils;
import com.binny.openapi.util.UtilsLog;
import com.binny.openapi.util.UtilsPerMission;
import com.binny.openapi.widget.dialog.HuoDongDialog;
import com.smart.holder.CommonAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * author  binny
 * date 5/9
 */
public class MineFragment extends AbsBaseFragment implements IToolItemClickedListener {
    private TextView tvCacheSize;

    private GridView mGridView;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        tvCacheSize = view.findViewById(R.id.tv_cache_size);
        RelativeLayout textView = view.findViewById(R.id.rl_clear_cache);

        mGridView = view.findViewById(R.id.tool_gv);
        textView.setOnClickListener(v -> UtilsPerMission.getPermission(new OnPermissionCallback() {
            @Override
            public void onGranted() {
                toast("请求成功");
            }

            @Override
            public void onDeny() {
                toast("请求权限失败");
            }
        }, getActivity(), textView, Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    private void toast(String s) {
        Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initRefreshView(final View containerView) {

    }

    @Override
    protected void getData() {
        String size = getSize();
        tvCacheSize.setText(size);
        List<ToolBean> items = new ArrayList<>();
        for (String anItemString : itemString) {
            items.add(new ToolBean().setItemText(anItemString));
        }
        mGridView.setAdapter(new CommonAdapter<>(getActivity(), items, R.layout.item_fragment_tool, new ToolViewHolderHelper(this)));
    }

    private String getSize() {
        return FileUtils.getTotalCacheSize(getActivity(), ConstantParams.PATH_PICTURE);
    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onLoadMore() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setCacheSize() {
        String size = getSize();
        if (tvCacheSize != null) {
            tvCacheSize.setText(size);
        }
    }

    @Override
    public void onItemClicked(int position, View view) {

        switch (position) {
            case 0://活动弹窗
                new HuoDongDialog(mActivity)
                        .setBtnBgColor("35462156")
                        .setMarginBottom(30)
                        .setHuoDongMessage("adafasfnsljkfhsljkfhsf\nfadhsashasasas\ndhlhgalhashad\n")
                        .setHuoDongImgUrl("http://dynamic-image.yesky.com/740x-/uploadImages/2015/163/50/690V3VHW0P77.jpg")
                        .setScaleHeight(0.8f)
                        .setScaleWidth(0.8f)
                        .show();
                break;
            case 1://清除缓存
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("敏感操作，谨慎选择");
                builder.setMessage("确定要删除所有缓存么，包括文字、picture、音乐和视频?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FileUtils.cleanApplicationData(getActivity(), ConstantParams.PATH_PICTURE);
                        String size = getSize();
                        tvCacheSize.setText(size);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UtilsLog.i("取消删除缓存!");
                    }
                });
                builder.show();
                break;
            case 2:
                UtilsPerMission.getPermission(new OnPermissionCallback() {
                    @Override
                    public void onGranted() {
                        toast("请求成功");
                    }

                    @Override
                    public void onDeny() {
                        toast("请求权限失败");
                    }
                }, getActivity(), view, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
        }
    }

    private final String[] itemString = new String[]{
            "活动对话框",
            "清除缓存",
            "请求权限"
    };
}
