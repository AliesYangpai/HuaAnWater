package org.huaanwater.work.entity;

import java.io.Serializable;

/**
 * Created by Alie on 2018/3/6.
 * 类描述
 * 版本
 */

public class Region implements Serializable {


//    {
//        "id": 0,
//            "uniacid": 0,
//            "name": "string",
//            "province": "string",
//            "city": "string",
//            "district": "string",
//            "address": "string",
//            "longitude": "string",
//            "dimension": "string",
//            "ctime": "string"
//    }


    private int id;//地区Id ,
    private int uniacid;//预留字段 ,
    private String name;//地区名称 ,
    private String province;//省份 ,
    private String city;//城市 ,
    private String district;//区镇
    private String address;//详细地址
    private String longitude ;//经度
    private String dimension ;//纬度
    private String ctime ;//创建时间

    public Region() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUniacid() {
        return uniacid;
    }

    public void setUniacid(int uniacid) {
        this.uniacid = uniacid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", uniacid=" + uniacid +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", longitude='" + longitude + '\'' +
                ", dimension='" + dimension + '\'' +
                ", ctime='" + ctime + '\'' +
                '}';
    }
}
