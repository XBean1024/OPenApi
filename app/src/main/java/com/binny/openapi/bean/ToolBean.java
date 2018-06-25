package com.binny.openapi.bean;

import java.io.Serializable;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/25 15:49
 * @Description:
 */
public class ToolBean implements Serializable {
    private String mItemText;

    public String getItemText() {
        return mItemText;
    }

    public ToolBean setItemText(String itemText) {
        mItemText = itemText;
        return this;
    }
}