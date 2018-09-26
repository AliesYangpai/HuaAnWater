package org.huaanwater.work.entity;

/**
 * Created by Alie on 2018/3/6.
 * 类描述  用于设置用户类型的type
 * 版本
 */

public class UserType {


    private String typeCode;
    private String typeHz;


    public UserType(String typeCode, String typeHz) {
        this.typeCode = typeCode;
        this.typeHz = typeHz;
    }

    public UserType() {
    }

    public String getTypeHz() {
        return typeHz;
    }

    public void setTypeHz(String typeHz) {
        this.typeHz = typeHz;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "typeCode='" + typeCode + '\'' +
                ", typeHz='" + typeHz + '\'' +
                '}';
    }
}
