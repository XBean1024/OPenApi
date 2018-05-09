package com.binny.openapi.mvp.presenter.register;

import java.io.File;

/**
 * author  binny
 * date 5/7
 */
public interface IRegisterPresenter {
    void getData(String phone, String passwd, String name, String text, String other, String other2, File imageFile);
}
