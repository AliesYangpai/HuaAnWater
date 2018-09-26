package org.huaanwater.work.entity;

import java.io.Serializable;

/**
 * Created by Alie on 2018/2/27.
 * 类描述  用户积分
 * 版本
 */

public class UserPoint  implements Serializable{


//          "user_point_flow_record_id": 0,
//                  "user_id": 0,
//                  "points": 0,
//                  "flow_direction": "Income",
//                  "handle_type": "SignIn",
//                  "total_points": 0,
//                  "create_time": "2018-02-27T15:26:30.895Z"

    private int user_point_flow_record_id; //用户积分流水记录Id ,
    private int user_id;
    private int points; //积分数 ,
    private String flow_direction;//流水方向 = ['Income', 'Outlay'],
    private String handle_type; //操作类型说明 = ['SignIn', 'Consumption', 'Recharge'],
    private int total_points;//总积分数
    private String  create_time;//创建时间

    public UserPoint() {
    }


    public int getUser_point_flow_record_id() {
        return user_point_flow_record_id;
    }

    public void setUser_point_flow_record_id(int user_point_flow_record_id) {
        this.user_point_flow_record_id = user_point_flow_record_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getFlow_direction() {
        return flow_direction;
    }

    public void setFlow_direction(String flow_direction) {
        this.flow_direction = flow_direction;
    }

    public String getHandle_type() {
        return handle_type;
    }

    public void setHandle_type(String handle_type) {
        this.handle_type = handle_type;
    }

    public int getTotal_points() {
        return total_points;
    }

    public void setTotal_points(int total_points) {
        this.total_points = total_points;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "UserPoint{" +
                "user_point_flow_record_id=" + user_point_flow_record_id +
                ", user_id=" + user_id +
                ", points=" + points +
                ", flow_direction='" + flow_direction + '\'' +
                ", handle_type='" + handle_type + '\'' +
                ", total_points=" + total_points +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
