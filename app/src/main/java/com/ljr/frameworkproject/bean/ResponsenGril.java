package com.ljr.frameworkproject.bean;

import java.util.ArrayList;
import java.util.List;

public class ResponsenGril {
    public List<GirlBean> mGirlBeanList;

    public List<GirlBean> getGirlBeanList() {
        if (mGirlBeanList == null) {
            return new ArrayList<>();
        }
        return mGirlBeanList;
    }

    public void setGirlBeanList(List<GirlBean> girlBeanList) {
        mGirlBeanList = girlBeanList;
    }
}
