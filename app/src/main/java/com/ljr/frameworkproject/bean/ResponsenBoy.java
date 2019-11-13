package com.ljr.frameworkproject.bean;

import java.util.ArrayList;
import java.util.List;

public class ResponsenBoy {
    public List<BoyBean> mBoyBeanList;

    public List<BoyBean> getBoyBeanList() {
        if (mBoyBeanList == null) {
            return new ArrayList<>();
        }
        return mBoyBeanList;
    }

    public void setBoyBeanList(List<BoyBean> boyBeanList) {
        mBoyBeanList = boyBeanList;
    }
}
