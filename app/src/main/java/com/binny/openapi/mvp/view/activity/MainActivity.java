package com.binny.openapi.mvp.view.activity;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.binny.openapi.R;
import com.binny.openapi.callback.OnPermissionCallback;
import com.binny.openapi.mvp.view.base.AbsBaseActivity;
import com.binny.openapi.mvp.view.fghome.HomeFragment;
import com.binny.openapi.mvp.view.fgmine.ToolFragment;
import com.binny.openapi.mvp.view.fgmusic.MusicFragment;
import com.binny.openapi.mvp.view.fgpicture.PictureFragment;
import com.binny.openapi.mvp.view.fgvideo.VideoFragment;
import com.binny.openapi.util.UtilsLog;
import com.binny.openapi.util.UtilsPerMission;

import java.util.ArrayList;
import java.util.List;

/*
* 主界面
* 切换fragment
*  */

public class MainActivity extends AbsBaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private RadioButton mButtonHome;
    private RadioButton mButtonPicture;
    private RadioButton mButtonMusic;
    private RadioButton mButtonVideo;
    private RadioButton mButtonMine;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private HomeFragment mHomeFragment;
    private PictureFragment mPictureFragment;
    private MusicFragment mMusicFragment;
    private VideoFragment mVideoFragment;
    private ToolFragment mMineFragment;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private int mPostion;

    @Override
    protected void onStart() {
        super.onStart();
        UtilsPerMission.getPermission(new OnPermissionCallback() {
            @Override
            public void onGranted() {
                UtilsLog.i("请求成功");
            }

            @Override
            public void onDeny() {
                UtilsLog.e("请求权限失败");
            }
        },this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void handleIntent() {

    }

    @Override
    protected void initView() {
        mRadioGroup = findViewById(R.id.rg_main);
        mButtonMine = findViewById(R.id.rb_mine);
        mButtonVideo = findViewById(R.id.rb_video);
        mButtonPicture = findViewById(R.id.rb_picture);
        mButtonHome = findViewById(R.id.rb_home);

        mRadioGroup.setOnCheckedChangeListener(this);
        initFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initFragment() {

        mHomeFragment = new HomeFragment();
        mPictureFragment = new PictureFragment();
        mMusicFragment = new MusicFragment();
        mVideoFragment = new VideoFragment();
        mMineFragment = new ToolFragment();

        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mPictureFragment);
        mFragmentList.add(mMusicFragment);
        mFragmentList.add(mVideoFragment);
        mFragmentList.add(mMineFragment);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fm_contariner, mHomeFragment);
        mFragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        hideNow();//先隐藏
        switch (checkedId) {
            case R.id.rb_home:
                mPostion = 0;
                break;
            case R.id.rb_picture:
                mPostion = 1;
                break;
            case R.id.rb_music:
                mPostion = 2;
                break;
            case R.id.rb_video:
                mPostion = 3;
                break;
            case R.id.rb_mine:
                mPostion = 4;
                mMineFragment.setCacheSize();
                break;


        }
        showNext();
        mFragmentTransaction.commit();
    }

    private void hideNow() {
        if (!mFragmentList.get(mPostion).isAdded()) {
            mFragmentTransaction.add(R.id.fm_contariner, mFragmentList.get(mPostion));
        } else {
            mFragmentTransaction.hide(mFragmentList.get(mPostion));
        }
    }

    private void showNext() {
        if (!mFragmentList.get(mPostion).isAdded()) {
            mFragmentTransaction.add(R.id.fm_contariner, mFragmentList.get(mPostion));
        } else {
            mFragmentTransaction.show(mFragmentList.get(mPostion));
        }
    }

}
