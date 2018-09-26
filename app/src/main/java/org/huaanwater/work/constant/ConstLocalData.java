package org.huaanwater.work.constant;

/**
 * Created by Alie on 2018/1/28.
 * 类描述
 * 版本
 */

public class ConstLocalData {



    public static final int NUM_INCREMENT_1 = 1; //默认加载更多

    public static final int DEFAULT_INCREMENT_SIZE = 6;  //默认订单数增量

    public static final int ACTIVE_PARTICIPATE_INCREMENT_SIZE = 12;  //默认参加活动人数数增量
    public static final int ACTIVE_PARTICIPATE_BOTTOM_SIZE = 4;  //获取部分默认参加的人数显示在底部
    public static final int FEEDBACK_INCREMENT_SIZE = 12;  //默认订单数增量
    public static final int USERPOINTS_INCREMENT_SIZE = 12;  //默认用户积分数增量
    public static final int RECHARGE_INCREMENT_SIZE = 12;  //默认用户充值数增量
    public static final int CONSUME_INCREMENT_SIZE = 12;  //默认用户消费数增量
    public static final int DEFUALT_LIST_INDEX = 1; //默认加载更多

    public static final String WX_APPID = "wx17db7f515de7dccb"; //微信appId


    public static final String WX = "Weixin"; //微信标记
    public static final String ALI = "Alipay"; //ali标记
    public static final String QQ = "QQ"; //QQ标记


    public static final String PAY_SIDE_USER = "User";//付款方


    /**
     * 动态adapter布局
     */
    public static final int ADATER_PIC_0 = 0;  //0种图片的
    public static final int ADATER_PIC_1 = 1;  //1种图片的
    public static final int ADATER_PIC_2 = 2;  //2种图片的
    public static final int ADATER_PIC_3 = 3;  //3种图片的
    public static final int ADATER_ACTIVE = 4;  //活动的布局

    /**
     * 用于加载webView的
     */
    public static final String WEB_IMG_STYLE = "<style> img{ max-width:100%; height:auto;} </style>";//web的style属性
    public static final String MIME_TYPE = "text/html";//webView的mimeType
    public static final String CODING_TYPE = "utf-8";//webView的编码类型


    /**
     * 积分说明
     */
    public static final String  INCOME = "Income";  //流入
    public static final String  OUTLAY = "Outlay";  //流出
    public static final String  SIGNIN = "SignIn";  //签到
    public static final String  CONSUMPTION = "Consumption";  //消费
    public static final String  RECHARGE = "Recharge";  //充值


    /**
     *  请求升降序标记
     */
    public static final boolean ASCENDING_TRUE = true;
    public static final boolean ASCENDING_FALSE = false;

    public static final String  REPLY_ADMIN = "Admin";  //回复管理员回复


    /**
     * 订单相关
     * :

     状态 初始状态 -等待放水 -放水中 -已结束 -放水失败 -异常结束 = ['Init', 'Waiting', 'Watering', 'Closed', 'WaterFailed', 'ErrorClosed']
     string
     Enum:	"Init", "Waiting", "Watering", "Closed", "WaterFailed", "ErrorClosed"
     ,
     */

    public static final String  ORDER_STATUE_INIT = "Init";  //初始状态
    public static final String  ORDER_STATUE_WAITING = "Waiting";  //等待放水
    public static final String  ORDER_STATUE_WATERING = "Watering";  //放水中
    public static final String  ORDER_STATUE_CLOSE = "Closed";  //已结束
    public static final String  ORDER_STATUE_WATERFAILED = "WaterFailed";  //放水失败
    public static final String  ORDER_STATUE_ERRORCLOSED = "ErrorClosed";  //异常结束


    /**
     * 订单类型
     * 订单类型 用户下单 刷卡消费 = ['User', 'Card']
     string
     Enum:	"User", "Card"
     ,
     */
    public static final String  ORDER_TYPE_USER = "User";  //用户下单
    public static final String  ORDER_TYPE_CARD = "Card";  //刷卡消费






    public static final String  QHY_APP_NEWS = "QHYAppNews";  //氢活益App新闻标记码
    public static final String  QHY_APP_ABOUT = "QHYAbout";  //  氢活益App关于我们标记码
    public static final String  QHY_APP_VERSION = "QHYVersion";  // 氢活益App版本说明标记码
    public static final String  QHY_APP_HELP = "QHYHelp";  // 氢活益App帮助标记码
    public static final String  QHY_APP_GOODS = "QHYGoods";  //益App好货标记码



    public static final String REPLIED = "Replied";//反馈回复



    public static final String STUDENT = "Student";//学生
    public static final String ENTERPRISE = "Enterprise";//企业
    public static final String HOUSING_ESTATE = "HousingEstate";//小区
    public static final String OTHER = "Other";//其他


    public static final boolean IS_APP_IN_BIND = true;//是app内绑定
}
