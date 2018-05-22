package com.binny.openapi.mvp.bean;

import java.io.Serializable;

/** 观止App
 *
 * author  binny
 * date 5/22
 */
public class ArticleBean implements Serializable {

    /**
     * date : {"curr":"20170216","prev":"20170215","next":"20170217"}
     * author : 村上春树
     * title : 迈锡尼的小行星旅馆
     * digest : 希腊有个叫迈锡尼的村庄,因谢里曼*发现阿伽门农*墓而变得有名起来。虽说有名，但还是个很小的村庄，规模也就竹下路*大小。旅游大巴来的时候到处是
     * content : <p>希腊有个叫迈锡尼的村庄,.......我心想，能有几个日本人在被问到是否幸福时这样回答呢？</p>
     * wc : 469
     */

    /*
    * date：日期
       curr：今日日期，yyyyMMdd 格式
       prev：昨日日期，yyyyMMdd 格式
       next：明日日期，yyyyMMdd 格式
       author：作者
       titile：标题
       digest：首段
        content：正文内容
        wc：字数(word count)
        */
    private DateBean date;
    private String author;
    private String title;
    private String digest;
    private String content;
    private int wc;

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWc() {
        return wc;
    }

    public void setWc(int wc) {
        this.wc = wc;
    }

    public static class DateBean {
        /**
         * curr : 20170216
         * prev : 20170215
         * next : 20170217
         */

        private String curr;
        private String prev;
        private String next;

        public String getCurr() {
            return curr;
        }

        public void setCurr(String curr) {
            this.curr = curr;
        }

        public String getPrev() {
            return prev;
        }

        public void setPrev(String prev) {
            this.prev = prev;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "DateBean{" +
                    "curr='" + curr + '\'' +
                    ", prev='" + prev + '\'' +
                    ", next='" + next + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ArticleBean{" +
                "date=" + date.toString() +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", digest='" + digest + '\'' +
                ", content='" + content + '\'' +
                ", wc=" + wc +
                '}';
    }
}
