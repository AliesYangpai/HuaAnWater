package org.huaanwater.work.entity.thepakege;

import org.huaanwater.work.entity.News;
import org.huaanwater.work.entity.active.Active;

import java.io.Serializable;

/**
 * Created by Alie on 2018/3/15.
 * 类描述
 * 版本
 */

public class PakegeActive implements Serializable {


    private News news;
    private Active active;


    public PakegeActive() {
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "PakegeActive{" +
                "news=" + news +
                ", active=" + active +
                '}';
    }
}
