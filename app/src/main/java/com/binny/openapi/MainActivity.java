package com.binny.openapi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.binny.openapi.mvp.ui.fragment.home.HomeFragment;
import com.binny.openapi.mvp.ui.fragment.mine.MineFragment;
import com.binny.openapi.mvp.ui.fragment.music.MusicFragment;
import com.binny.openapi.mvp.ui.fragment.picture.PictureFragment;
import com.binny.openapi.mvp.ui.fragment.video.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/*
* 主界面
* 切换fragment
*  */

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

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
    private MineFragment mMineFragment;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private int mPostion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroup = findViewById(R.id.rg_main);
        mButtonMine = findViewById(R.id.rb_mine);
        mButtonVideo = findViewById(R.id.rb_video);
        mButtonPicture = findViewById(R.id.rb_picture);
        mButtonHome = findViewById(R.id.rb_home);

        mRadioGroup.setOnCheckedChangeListener(this);
        initFragment();
    }

    private void initFragment() {

        mHomeFragment = new HomeFragment();
        mPictureFragment = new PictureFragment();
        mMusicFragment = new MusicFragment();
        mVideoFragment = new VideoFragment();
        mMineFragment = new MineFragment();

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
