package com.vincyan.ncextensions.bean;

/**
 * 天朗信息技术有限公司
 *
 * @author liuzongze
 *         Created by Shinelon on 2017/6/19.
 * @link http://www.jiahetianlang.com
 * $desc$
 */
public class News {


    /**
     * id : 658
     * title : “万能社区”凸显一些部门懒政思维
     * content : abcd
     * cover_photo : /ups/Admin/2017/06/16/1497596287.jpg
     * news_from : 德州晚报
     * type_id : 11
     * add_time : 1497596287
     */

    private String id;
    private String title;
    private String content;
    private String cover_photo;
    private String news_from;
    private String type_id;
    private String add_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
    }

    public String getNews_from() {
        return news_from;
    }

    public void setNews_from(String news_from) {
        this.news_from = news_from;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
