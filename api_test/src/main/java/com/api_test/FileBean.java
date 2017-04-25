package com.api_test;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by NEDUsoftware on 2017/4/25.
 */

public class FileBean extends BmobObject {
    private String id;
    private BmobFile file;

    public FileBean(String id, BmobFile file) {
        this.id = id;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BmobFile getFile() {
        return file;
    }

    public void setFile(BmobFile file) {
        this.file = file;
    }
}
