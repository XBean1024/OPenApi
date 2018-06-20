package com.binny.openapi.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author xubinbin
 * @e-mail 596928539@qq.com
 * @date 2018/6/20 16:31
 * @Description:
 */
public class MeiZiTuBean implements Serializable {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"newslist":[{"title":"大宝贝小不点","picUrl":"http://image.hnol.net/c/2015-11/02/13/201511021325239431-3182465.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6835588-0-1.html"},{"title":"花海丽人","picUrl":"http://image.hnol.net/bbs/uface/a/2014-11/11/09/2014111109330752354.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6835607-0-1.html"},{"title":"【欣怡】 - （8）    [分享]","picUrl":"http://image.hnol.net/c/2015-11/02/13/201511021330263331-3182465.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6828736-0-1.html"},{"title":"秋意  花海","picUrl":"http://image.hnol.net/bbs/uface/a/2014-11/11/09/2014111109330752354.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6834960-0-1.html"},{"title":"时尚靓妹[灌水]","picUrl":"http://image.hnol.net/c/2015-10/29/00/201510290049129581-4758611.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6834976-0-1.html"},{"title":"【美D共享】◆ Elma梦 明媚的Gir","picUrl":"http://image.hnol.net/c/2015-11/01/15/201511011523276361-2089977.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6834353-0-1.html"},{"title":"[贴图]小美女模特小龙女","picUrl":"http://image.hnol.net/c/2015-10/31/09/201510310930417491-4217076.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6833006-0-1.html"},{"title":"[贴图]美女那朵","picUrl":"http://image.hnol.net/c/2015-10/31/10/201510311008039621-4217076.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6834101-0-1.html"},{"title":"美腿秀274（爱秀015 贝贝 2015.11.01）","picUrl":"http://image.hnol.net/c/2015-11/01/20/201511012047296151-2285289.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6834721-0-1.html"},{"title":"白色蕾丝连体衣绮里嘉户外阳光秀","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_fe9bea92980bb30c7fb9cc3c33c992048-760x500.jpg","description":"美女图片","ctime":"2016-03-06 14:11","url":"http://m.xxxiao.com/582"}],"code":200,"msg":"success"}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean implements Serializable{
        /**
         * newslist : [{"title":"大宝贝小不点","picUrl":"http://image.hnol.net/c/2015-11/02/13/201511021325239431-3182465.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6835588-0-1.html"},{"title":"花海丽人","picUrl":"http://image.hnol.net/bbs/uface/a/2014-11/11/09/2014111109330752354.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6835607-0-1.html"},{"title":"【欣怡】 - （8）    [分享]","picUrl":"http://image.hnol.net/c/2015-11/02/13/201511021330263331-3182465.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6828736-0-1.html"},{"title":"秋意  花海","picUrl":"http://image.hnol.net/bbs/uface/a/2014-11/11/09/2014111109330752354.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6834960-0-1.html"},{"title":"时尚靓妹[灌水]","picUrl":"http://image.hnol.net/c/2015-10/29/00/201510290049129581-4758611.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6834976-0-1.html"},{"title":"【美D共享】◆ Elma梦 明媚的Gir","picUrl":"http://image.hnol.net/c/2015-11/01/15/201511011523276361-2089977.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6834353-0-1.html"},{"title":"[贴图]小美女模特小龙女","picUrl":"http://image.hnol.net/c/2015-10/31/09/201510310930417491-4217076.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6833006-0-1.html"},{"title":"[贴图]美女那朵","picUrl":"http://image.hnol.net/c/2015-10/31/10/201510311008039621-4217076.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6834101-0-1.html"},{"title":"美腿秀274（爱秀015 贝贝 2015.11.01）","picUrl":"http://image.hnol.net/c/2015-11/01/20/201511012047296151-2285289.jpg","description":"华声美女","ctime":"2016-03-06 14:11","url":"http://bbs.voc.com.cn/mm/meinv-6834721-0-1.html"},{"title":"白色蕾丝连体衣绮里嘉户外阳光秀","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2015/04/m.xxxiao.com_fe9bea92980bb30c7fb9cc3c33c992048-760x500.jpg","description":"美女图片","ctime":"2016-03-06 14:11","url":"http://m.xxxiao.com/582"}]
         * code : 200
         * msg : success
         */

        private int code;
        private String msg;
        private List<NewslistBean> newslist;

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

        public List<NewslistBean> getNewslist() {
            return newslist;
        }

        public void setNewslist(List<NewslistBean> newslist) {
            this.newslist = newslist;
        }

        public static class NewslistBean implements Serializable{
            /**
             * title : 大宝贝小不点
             * picUrl : http://image.hnol.net/c/2015-11/02/13/201511021325239431-3182465.jpg
             * description : 华声美女
             * ctime : 2016-03-06 14:11
             * url : http://bbs.voc.com.cn/mm/meinv-6835588-0-1.html
             */

            private String title;
            private String picUrl;
            private String description;
            private String ctime;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
