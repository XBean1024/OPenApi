package com.binny.openapi.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author  binny
 * date 5/8
 */
public class PictureBean implements Serializable {

    /**
     * code : 200
     * msg : 成功!
     * data : [{"createdAt":"2016-02-24T00:22:24.855Z","publishedAt":"2016-02-24T04:42:32.66Z","type":"美图","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f1a47fpjacj20f00imtam.jpg"},{"createdAt":"2016-02-23T02:24:19.518Z","publishedAt":"2016-02-23T05:08:46.837Z","type":"美图","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f19241kkpwj20f00hfabt.jpg"},{"createdAt":"2016-02-22T02:48:39.585Z","publishedAt":"2016-02-22T04:20:23.542Z","type":"美图","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f17x6wmh09j20f00m1mzh.jpg"},{"createdAt":"2016-02-19T02:15:50.180Z","publishedAt":"2016-02-19T03:45:05.172Z","type":"美图","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f14fbwrfptj20zk0npgtu.jpg"},{"createdAt":"2016-02-18T01:35:05.348Z","publishedAt":"2016-02-18T04:12:05.777Z","type":"美图","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f138l9egrmj20f00mbdij.jpg"},{"createdAt":"2016-02-01T13:47:21.517Z","publishedAt":"2016-02-17T04:47:21.644Z","type":"美图","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1f0k67zz05jj20ku0rs0y1.jpg"},{"createdAt":"2016-02-15T09:16:22.91Z","publishedAt":"2016-02-16T04:46:45.140Z","type":"美图","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f1052bhjauj20f00l6q4o.jpg"},{"createdAt":"2016-02-05T13:10:47.694Z","publishedAt":"2016-02-15T03:49:24.352Z","type":"美图","url":"http://ww4.sinaimg.cn/large/7a8aed7bgw1f0orab74l4j20go0p0jw5.jpg"},{"createdAt":"2016-02-01T13:47:04.494Z","publishedAt":"2016-02-04T07:14:01.791Z","type":"美图","url":"http://ww4.sinaimg.cn/large/7a8aed7bgw1f0k67eluxej20fr0m8whw.jpg"},{"createdAt":"2016-02-01T13:46:46.600Z","publishedAt":"2016-02-03T04:32:45.907Z","type":"美图","url":"http://ww2.sinaimg.cn/large/7a8aed7bgw1f0k6706308j20vg18gqfl.jpg"},{"createdAt":"2016-02-01T13:46:26.71Z","publishedAt":"2016-02-02T03:57:06.977Z","type":"美图","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1f0k66sk2qbj20rs130wqf.jpg"},{"createdAt":"2016-01-31T12:11:52.570Z","publishedAt":"2016-02-01T03:49:41.430Z","type":"美图","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1f0ixu5rmtcj20hs0qojv5.jpg"},{"createdAt":"2016-01-28T07:50:39.415Z","publishedAt":"2016-01-29T04:18:12.684Z","type":"美图","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f0f9fkzu78j20f00qo0xl.jpg"},{"createdAt":"2016-01-27T08:24:58.116Z","publishedAt":"2016-01-28T04:44:09.773Z","type":"美图","url":"http://ww3.sinaimg.cn/large/7a8aed7bjw1f0e4suv1tgj20hs0qo0w5.jpg"},{"createdAt":"2016-01-26T06:42:17.769Z","publishedAt":"2016-01-27T05:14:00.966Z","type":"美图","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1f0cw7swd9tj20hy0qogoo.jpg"},{"createdAt":"2016-01-25T09:14:17.609Z","publishedAt":"2016-01-26T04:02:34.316Z","type":"美图","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1f0buzmnacoj20f00liwi2.jpg"},{"createdAt":"2016-01-25T01:59:46.62Z","publishedAt":"2016-01-25T06:59:09.1Z","type":"美图","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1f0bifjrh39j20v018gwtj.jpg"},{"createdAt":"2016-01-22T02:26:06.396Z","publishedAt":"2016-01-22T05:14:47.832Z","type":"美图","url":"http://ww4.sinaimg.cn/large/7a8aed7bjw1f082c0b6zyj20f00f0gnr.jpg"},{"createdAt":"2016-01-21T04:37:11.978Z","publishedAt":"2016-01-21T04:50:36.905Z","type":"美图","url":"http://ww3.sinaimg.cn/large/610dc034jw1f070hyadzkj20p90gwq6v.jpg"},{"createdAt":"2016-01-20T01:25:05.568Z","publishedAt":"2016-01-20T04:59:02.792Z","type":"美图","url":"http://ww1.sinaimg.cn/large/7a8aed7bjw1f05pbp0p0yj20go0mu77b.jpg"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * createdAt : 2016-02-24T00:22:24.855Z
         * publishedAt : 2016-02-24T04:42:32.66Z
         * type : 美图
         * url : http://ww3.sinaimg.cn/large/7a8aed7bjw1f1a47fpjacj20f00imtam.jpg
         */

        private String createdAt;
        private String publishedAt;
        private String type;
        private String url;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "createdAt='" + createdAt + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PictureBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data==null?" null ":data.toString() +
                '}';
    }
}
