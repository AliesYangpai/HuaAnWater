package org.huaanwater.work.http;

/**
 * Created by Administrator on 2016/9/17 0017.
 * 类描述   网络交互的所有常量
 * 版本
 */
public class HttpConst {


    public static final String HTTP_PREFIX = "http://";

    public static final String LOG_REQUEST = "log_request";

    public static final String SERVER_BACK = "server_back";

    public static final String CONTENT_TYPE = "Content-Type";


    public static final String CONTENT_TYPE_APPLICATION = "application/json; charset=UTF-8";

    public static final String BASE_URL = "http://api.anttribal.cn/";  //辅助胡含

//    public static final String BASE_URL = "http://192.168.1.100:5000/";  //转转环境
    public static final String API_VERSION = "api/v1/";


    public static final int CODE_200 = 200;//success回调中使用的返回码


    public static final int CODE_201 = 201;//success回调中使用的返回码


    public static final int CODE_204 = 204;//success回调中使用的返回码


    public static final int CODE_0 = 0;//网络链接异常

    public static final int CODE_401 = 401;//未授权


    public static final int CODE_502 = 502;//服务器异常

    public static final int NO_RESOURCE = 204;//未知资源，比如更新数据时，没有找到该数据


    public static final String UPLOAD_DATA_KEY = "data";

    public static final String UPLOAD_DATA_KEY_FILE_NAME = "filename";


    public static final String UPLOAD_DATA_VALUE_NAME = "img.jpg";

    public static final int HTTP_WHAT = 0;//当前的请求标记


    public static final int NO_HTTP_CONNECT_ERROR = 0;

    public static final int SERVER_ERROR = 502;//服务器异常（比如服务器没网了）

    public static final int UNAUTHORIZED = 401;// 未授权、token过期等等;


    public static final String AUTHORIZATION = "Authorization";//授权

    public static final String BASIC = "Basic ";//获取token时候，需要加入的basci

    public static final String BEARER = "Bearer ";
    public static final String SEPARATOR = "/";//分隔符（用于get请求中有参数在URL中即可）

    public class URL {

        /**
         * s生成验收集证码
         * post /api/v1/Sms/GeneratePassCode
         */
        public static final String GENERATE_PASS_CODE = BASE_URL + API_VERSION + "Sms/GeneratePassCode";


        /**
         * 验证手机验证码
         * post /api/v1/Sms/ValidatePassCode
         */
        public static final String VALIDATE_PASS_CODE = BASE_URL + API_VERSION + "Sms/ValidatePassCode";


        /**
         * 用户注册
         * <p>
         * post /api/v1/User/Register
         */
        public static final String REGISTER = BASE_URL + API_VERSION + "User/Register";


        /**
         * 用户登录
         * post /api/v1/User/Logon
         */
        public static final String LOGON = BASE_URL + API_VERSION + "User/Logon";


        /**
         * 获取当前用户信息
         *
         get /api/v1/User/Current
         */
        public static final String USER_CURRENT = BASE_URL + API_VERSION + "User/Current";



        /**
         * 上传头像
         *

         post /api/v1/Upload
         */
        public static final String UPLOAD = BASE_URL + API_VERSION + "Upload";



        /**
         *获取新闻列表
         get /api/v1/NewsContent
         */
        public static final String NEWS_CONTENT = BASE_URL + API_VERSION + "NewsContent";





        /**
         *修改用户信息
         put /api/v1/User
         */
        public static final String USER_EDIT = BASE_URL + API_VERSION + "User";






        /**
         *修改密码
         put /api/v1/User/Password
         */
        public static final String USER_MODIFY_PASS = BASE_URL + API_VERSION + "User/Password";


        /**
         *修改电话号码
         put /api/v1/User/Phone
         */
        public static final String USER_MODIFY_PHONE = BASE_URL + API_VERSION + "User/Phone";







        /**
         *
         *获取授权列表信息

         get /api/v1/UserAuthorization/GetAuthList
         */
        public static final String GET_AUTH_LIST = BASE_URL + API_VERSION + "UserAuthorization/GetAuthList";



        /**
         *
         *验证是否授权
         post /api/v1/UserAuthorization/ValidAuth
         */
        public static final String VALID_AUTH = BASE_URL + API_VERSION + "UserAuthorization/ValidAuth";





        /**
         *
         *绑定授权
         post /api/v1/UserAuthorization/BindAuth
         */
        public static final String BIND_AUTH = BASE_URL + API_VERSION + "UserAuthorization/BindAuth";


        /**
         *
         *解除绑定授权
         post /api/v1/UserAuthorization/UnBindAuth
         */
        public static final String UN_BIND_AUTH = BASE_URL + API_VERSION + "UserAuthorization/UnBindAuth";







        /**
         *
         *获取阿里的相应授权参数信息，以此信息作为客户端调用阿里sdk的参数
         get /api/v1/UserAuthorization/AlipayAuthParameters
         */
        public static final String ALI_AUTH_PARAM = BASE_URL + API_VERSION + "UserAuthorization/AlipayAuthParameters";






        /**
         *
         *创建支付

         post /api/v1/Payment/Create
         */
        public static final String CREATE_PAY = BASE_URL + API_VERSION + "Payment/Create";


        /**
         * 查询验证支付结果Alipay
         * 支付宝支付
         *
         post /api/v1/Payment/AlipayCheckPaymentResultInformation
         */
        public static final String CHECK_ALI_PAY_RESULT_INFORMATION = BASE_URL + API_VERSION + "Payment/AlipayCheckPaymentResultInformation";




        /**
         * 查询验证支付结果wx
         * 支付宝支付
         *
         post /api/v1/Payment/WeipayCheckPaymentResultInformation
         */
        public static final String CHECK_WX_PAY_RESULT_INFORMATION = BASE_URL + API_VERSION + "Payment/WeipayCheckPaymentResultInformation";







        /**
         *
         *获取消费列表
         get /api/v1/Payment/Channels
         */
        public static final String PAY_CHANNELS = BASE_URL + API_VERSION + "Payment/Channels";



        /**
         *
         *头像修改方法
         put /api/v1/User/Avatar
         */
        public static final String AVATAR = BASE_URL + API_VERSION + "User/Avatar";



        /**
         *
         *获取新闻评论内容列表

          /api/v1/NewsContentComment
         */
        public static final String NEWS_CONTENT_COMMENT = BASE_URL + API_VERSION + "NewsContentComment";





        /**
         *
         *获取新闻评论内容列表
         get /api/v1/Feedback
         */
        public static final String FEED_BACK = BASE_URL + API_VERSION + "Feedback";




        /**
         *
         *
         post /api/v1/FeedbackReply
         添加反馈回复信息
         */
        public static final String FEED_BACK_REPLY = BASE_URL + API_VERSION + "FeedbackReply";



        /**
         *
         *获取用户积分列表详情

         get /api/v1/sim/UserPoints
         */
        public static final String USER_POINT_RECORDS = BASE_URL + API_VERSION + "UserPoints";


        /**
         *
         *获取订单
         get /api/v1/Order
         */
        public static final String ORDER_RECORDS = BASE_URL + API_VERSION + "Order";



        /**
         *
         *获取充值记录

         get /api/v1/PaymentRecord
         */
        public static final String RECHARGE_RECORDS = BASE_URL + API_VERSION + "PaymentRecord";


        /**
         *
         *获取消费明细，由于订单列表就是消费列表，因此这里的接口实际上就是获取订单列表的接口
         get /api/v1/Order
         */
        public static final String CONSUME_RECORDS = BASE_URL + API_VERSION + "Order";

        /**
         *
         *出水
         post /api/v1/QRScanning/OutputWater
         */
        public static final String OUT_PUT_WATER = BASE_URL + API_VERSION + "QRScanning/OutputWater";


        /**
         *
         *
         put /api/v1/User/UserSignIn
         用户签到
         */
        public static final String USER_SIGN_IN = BASE_URL + API_VERSION + "User/UserSignIn";






        /**
         *获取版本信息
         get /api/v1/NewsContent
         这里也NewsContent 接口相同只是传参不同
         */
        public static final String VERSION = BASE_URL + API_VERSION + "NewsContent";




        /**
         * 点赞
         put /api/v1/NewsContent/UpdateLikeCount/{news_content_id}
         */
        public static final String UPDATE_LIKE_COUNT = BASE_URL + API_VERSION + "NewsContent/UpdateLikeCount";


        /**
         * 点赞

         get /api/v1/NewsContentComment/GetChildrenList
         */
        public static final String GET_CHILD_LIST = BASE_URL + API_VERSION + "NewsContentComment/GetChildrenList";




        /**
         * 获取区域列表
         get /api/v1/Regions
         */
        public static final String REGIONS = BASE_URL + API_VERSION + "Regions";



        /**
         * 绑定安信账号
         put /api/v1/User/BindingAnXinAccount
         */
        public static final String BIND_ANXIN_ACCOUNT = BASE_URL + API_VERSION + "User/BindingAnXinAccount";




        /**
         * 获取活动列表
         get /api/v1/ActivityManagement
         */
        public static final String ACTIVES = BASE_URL + API_VERSION + "ActivityManagement";



        /**
         * 检查用户是否参与此活动

         get /api/v1/ActivityParticipationRecord/CheckActivityUserExists/{activity_management_id}
         */
        public static final String CHECK_ACTIVITY_USER_EXIST = BASE_URL + API_VERSION + "ActivityParticipationRecord/CheckActivityUserExists";




        /**
         * 活动信息参与者列表接口、参与活动
          /api/v1/ActivityParticipationRecord
         */
        public static final String ACTIVES_PARTICIPATION_RECORD = BASE_URL + API_VERSION + "ActivityParticipationRecord";



        /**
         * 活动信息参与者列表接口、参与活动
         put /api/v1/NewsContent/UpdateReprintCount/{news_content_id}
         */
        public static final String UPDATE_REPRINT_COUNT = BASE_URL + API_VERSION + "NewsContent/UpdateReprintCount";




    }


}
