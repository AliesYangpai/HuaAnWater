package org.huaanwater.work.test;

import org.huaanwater.work.R;
import org.huaanwater.work.constant.ConstLocalData;
import org.huaanwater.work.constant.ConstSign;
import org.huaanwater.work.entity.CommentEntity;
import org.huaanwater.work.entity.News;
import org.huaanwater.work.entity.Region;
import org.huaanwater.work.entity.active.ActiveParticipate;

import java.util.ArrayList;
import java.util.List;

import rx.Producer;

/**
 * Created by Alie on 2018/1/18.
 * 类描述
 * 版本
 */

public class TestContent {

    public static final boolean isLogin = true;

    /**
     * web的style属性
     */
    public static final String WEB_IMG_STYLE = "<style> img{ max-width:100%; height:auto;} </style>";

    public static final String html = "<p>\n" +
            "\tThink Defferent\n" +
            "</p>\n" +
            "<p>\n" +
            "\tHere’s to the crazy ones. The misfits. The rebels. The troublemakers. The round pegs in the square holes. The ones who see things differently. They’re not fond of rules. And they have no respect for the status quo. You can quote them, disagree with them, glorify or vilify them. About the only thing you can’t do is ignore them. Because they change things. They push the human race forward. And while some may see them as the crazy ones, we see genius. Because the people who are crazy enough to think they can change the world, are the ones who do.\n" +
            "</p>\n" +
            "<h1>\n" +
            "\t- Apple Inc.大西瓜\n" +
            "</h1>\n" +
            "<p>\n" +
            "\t<img src=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517085727052&di=ac23a5f5bea899e9858f1c2cee05a3ea&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fa5c27d1ed21b0ef47ecfc962d6c451da81cb3ee2.jpg\" alt=\"\" />\n" +
            "</p>\n" +
            "<p>\n" +
            "\t<br />\n" +
            "</p>";









    public static List<TestConsume> getTest() {

        List<TestConsume> testConsumes = new ArrayList<>();
        TestConsume testConsume = new TestConsume();
        testConsume.setInfo("350ml*6瓶");
        testConsume.setState("取消");
        testConsume.setTime("2017-8-25  12:26:35");
        testConsume.setMoney("25");
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);

        return testConsumes;
    }


    public static List<Region> getTest3() {

        List<Region> testConsumes = new ArrayList<>();
        Region testConsume = new Region();
        testConsume.setAddress("谁看见看见蓝思科技");

        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);

        return testConsumes;
    }










    public static List<TestConsume> getTestConsumes() {

        List<TestConsume> testConsumes = new ArrayList<>();

        TestConsume testConsume = new TestConsume("20170608", "15.36");

        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);
        testConsumes.add(testConsume);

        return testConsumes;
    }





    public static List<News> getNewsTestList() {

        List<News> list = new ArrayList<>();


        News news1 = new News();
        news1.setIs_comment(true);
        news1.setTitle("仙丹真的能长生不老吗？");
        news1.setCategory_name("玄幻");
        news1.setReply_count(5);
        news1.setCreate_time("2017-12-29");
        news1.setImage_url("http://p2.ifengimg.com/a/2018_06/968a7eba2ea9fdf_size66_w640_h361.jpg");

        News news2 = new News();
        news2.setIs_comment(true);
        news2.setTitle("仙丹真的能长生不老吗？");
        news2.setCategory_name("玄幻");
        news2.setReply_count(5);
        news2.setCreate_time("2017-12-29");
        news2.setImage_url("http://p2.ifengimg.com/a/2018_06/968a7eba2ea9fdf_size66_w640_h361.jpg" + ConstSign.DOU_COMMA + "http://p2.ifengimg.com/a/2018_06/968a7eba2ea9fdf_size66_w640_h361.jpg");

        News news3 = new News();
        news3.setIs_comment(true);
        news3.setTitle("仙丹真的能长生不老吗,奥斯卡到家了空间啊卡老师讲道理科技看了就立刻？");
        news3.setCategory_name("玄幻");
        news3.setReply_count(5);
        news3.setCreate_time("2017-12-29");
        news3.setImage_url("http://p2.ifengimg.com/a/2018_06/968a7eba2ea9fdf_size66_w640_h361.jpg" + ConstSign.DOU_COMMA + "http://p2.ifengimg.com/a/2018_06/968a7eba2ea9fdf_size66_w640_h361.jpg" + ConstSign.DOU_COMMA + "http://p2.ifengimg.com/a/2018_06/968a7eba2ea9fdf_size66_w640_h361.jpg");

        list.add(news1);
        list.add(news2);
        list.add(news3);
        list.add(news1);
        list.add(news1);
        list.add(news2);
        list.add(news3);
        return list;

    }


    public static List<ActiveParticipate> getActiveParticipateList() {

        List<ActiveParticipate> list = new ArrayList<>();

        ActiveParticipate activeParticipate = new ActiveParticipate();
        activeParticipate.setAvatar("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3738231171,2350240686&fm=200&gp=0.jpg");
        list.add(activeParticipate);
        list.add(activeParticipate);
        list.add(activeParticipate);
        return list;

    }





    public static List<ActiveParticipate> getActiveParticipateTestList() {

        List<ActiveParticipate> list = new ArrayList<>();

        ActiveParticipate activeParticipate = new ActiveParticipate();
        activeParticipate.setFull_name("西三瓜");
        activeParticipate.setPhone("15756684598");

        ActiveParticipate activeParticipate2 = new ActiveParticipate();
        activeParticipate2.setFull_name("西五瓜");
        activeParticipate2.setPhone("15756684598");


        list.add(activeParticipate);
        list.add(activeParticipate2);
        list.add(activeParticipate);
        list.add(activeParticipate2);
        list.add(activeParticipate);
        list.add(activeParticipate2);

        return list;

    }
}
