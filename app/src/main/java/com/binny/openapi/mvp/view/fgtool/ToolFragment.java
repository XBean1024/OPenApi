package com.binny.openapi.mvp.view.fgtool;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.binny.openapi.R;
import com.binny.openapi.bean.ToolBean;
import com.binny.openapi.callback.OnPermissionCallback;
import com.binny.openapi.constant.ConstantParams;
import com.binny.openapi.mvp.view.activity.BluetoochActivity;
import com.binny.openapi.mvp.view.activity.WebActivity;
import com.binny.openapi.mvp.view.base.AbsBaseFragment;
import com.binny.openapi.notification.NotificationUtils;
import com.binny.openapi.util.AppUtils;
import com.binny.openapi.util.FileUtils;
import com.binny.openapi.util.UtilsLog;
import com.binny.openapi.util.UtilsPerMission;
import com.binny.openapi.widget.dialog.HuoDongDialog;
import com.smart.holder.CommonAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author binny date 5/9
 */
public class ToolFragment extends AbsBaseFragment implements IToolItemClickedListener {
    private final String[] itemString =
            new String[]{"活动对话框", "清除缓存", "请求权限", "蓝牙", "优词词典", "截图", "短信", "通知", "弹窗","app信息"};
    private TextView tvCacheSize;
    private GridView mGridView;
    private PopupWindow mPopupWindow;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        tvCacheSize = view.findViewById(R.id.tv_cache_size);
        RelativeLayout textView = view.findViewById(R.id.rl_clear_cache);
        mImmersionBar.with(this).titleBar(view.findViewById(R.id.tool_ll)).execute();
        mGridView = view.findViewById(R.id.tool_gv);
        textView.setOnClickListener(
                v ->
                        UtilsPerMission.getPermission(
                                new OnPermissionCallback() {
                                    @Override
                                    public void onGranted() {
                                        toast("请求成功");
                                    }

                                    @Override
                                    public void onDeny() {
                                        toast("请求权限失败");
                                    }
                                },
                                getActivity(),
                                textView,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE));
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
        mGridView.setAdapter(
                new CommonAdapter<>(
                        getActivity(), items, R.layout.item_fragment_tool, new ToolViewHolderHelper(this)));
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
            case 0: // 活动弹窗
                new HuoDongDialog(mActivity)
                        .setBtnBgColor("35462156")
                        .setMarginBottom(0)
                        .setHuoDongMessage(
                                "1、要很少的控制点就能够生成复杂平滑曲线要很少的控制点就能够生成复杂平滑曲线\n2、要很少的控制点就能够生成复杂平滑曲线\n3、的方法，来辅助汽车车体的工业设计")
                        .setHuoDongImgUrl(
                                "http://dynamic-image.yesky.com/740x-/uploadImages/2015/163/50/690V3VHW0P77.jpg")
                        .setScaleWH(1.0)
                        .setScaleWidth(0.8f)
                        .show();
                break;
            case 1: // 清除缓存
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("敏感操作，谨慎选择");
                builder.setMessage("确定要删除所有缓存么，包括文字、picture、音乐和视频?");
                builder.setPositiveButton(
                        "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FileUtils.cleanApplicationData(getActivity(), ConstantParams.PATH_PICTURE);
                                String size = getSize();
                                tvCacheSize.setText(size);
                            }
                        });
                builder.setNegativeButton(
                        "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UtilsLog.i("取消删除缓存!");
                            }
                        });
                builder.show();
                break;
            case 2:
                UtilsPerMission.getPermission(
                        new OnPermissionCallback() {
                            @Override
                            public void onGranted() {
                                toast("请求成功");
                            }

                            @Override
                            public void onDeny() {
                                toast("请求权限失败");
                            }
                        },
                        getActivity(),
                        view,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case 3: // 蓝牙
                startActivity(new Intent(mActivity, BluetoochActivity.class));
                break;
            case 4: // 词典
                Intent intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("loadUrl", "http://www.youdict.com/");
                intent.putExtra("adblock", false);
                startActivity(intent);
                break;
            case 5: // 截图
                // https://m.jb51.net/article/119881.htm
                break;
            case 6: // 短信
                UtilsPerMission.getPermission(
                        new OnPermissionCallback() {
                            @Override
                            public void onGranted() {
                                toast("请求成功");
                            }

                            @Override
                            public void onDeny() {
                                toast("请求权限失败");
                            }
                        },
                        getActivity(),
                        view,
                        Manifest.permission.READ_SMS);

                break;
            case 7:
                NotificationUtils.getInstance().init(mActivity).setRelativeLongMill(6000).setTitle("biny").setContent("年终聚惠，手慢无").toggle(true);
                break;
            case 8:
                Toast.makeText(mActivity, "弹窗", Toast.LENGTH_SHORT).show();
                showPopWindow(mContainerView, R.layout.pop_window);
                break;
            case 9:
                AppUtils.loadApps(getActivity());
                break;
        }
    }

    private Uri SMS_INBOX = Uri.parse("content://sms/");

    public void getSmsFromPhone() {
        ContentResolver cr = mActivity.getContentResolver();
        String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
        Cursor cur = cr.query(SMS_INBOX, projection, null, null, "date desc");
        if (null == cur) {
            Log.i("ooc", "************cur == null");
            return;
        }
        int i = 0;
        while (cur.moveToNext()) {
            String number = cur.getString(cur.getColumnIndex("address")); // 手机号
            String name = cur.getString(cur.getColumnIndex("person")); // 联系人姓名列表
            String body = cur.getString(cur.getColumnIndex("body")); // 短信内容
            // 至此就获得了短信的相关的内容, 以下是把短信加入map中，构建listview,非必要。
            Log.i(TAG, "getSmsFromPhone: name : " + name);
            Log.i(TAG, "getSmsFromPhone: number : " + number);
            Log.i(TAG, "getSmsFromPhone: body : " + body);
            i++;
        }
        Log.i(TAG, "getSmsFromPhone:  = " + i);
        cur.close();
    }


    /*
     * 弹出选择直播方式的弹框
     * View v ：显示在那个父view内
     * int convertViewResource ：要填充到popupWindow中的布局文件id
     * int drawbelResource ：int drawbelResource
     * */
    private void showPopWindow(View parentView, int convertViewResource) {
        //创建一个popUpWindow
        View popLayout = LayoutInflater.from(mActivity).inflate(convertViewResource, null);
        //给popUpWindow内的空间设置点击事件

        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(popLayout, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            //产生背景变暗效果
            WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
            lp.alpha = 0.3f;
            mActivity.getWindow().setAttributes(lp);
            //点击外面popupWindow消失
            mPopupWindow.setOutsideTouchable(true);
            //popupWindow获取焦点
            mPopupWindow.setFocusable(true);
            mPopupWindow.setTouchable(true);
//            ColorDrawable dw = new ColorDrawable(-00000);
//            mPopupWindow.setBackgroundDrawable(dw);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            mPopupWindow.setAnimationStyle(R.style.red_packet_share_in_out_style);

            //设置popupWindow消失时的监听
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                //在dismiss中恢复透明度
                public void onDismiss() {
                    WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
                    lp.alpha = 1f;
                    mActivity.getWindow().setAttributes(lp);
                }
            });
            mPopupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
        } else {
            //如果popupWindow正在显示，接下来隐藏
            if (mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            } else {
                //产生背景变暗效果
                WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
                lp.alpha = 0.3f;
                mActivity.getWindow().setAttributes(lp);
                mPopupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
            }
        }
    }
}
