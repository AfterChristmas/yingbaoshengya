package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2020/2/15 23:12
 * @Auther: wanyandongchen
 */
public class StudySchoolResutlBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 0
     * result : [{"id":5,"name":"北京市东直门中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":6,"name":"北京市交道口中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":7,"name":"北京市和平北路学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":9,"name":"北京市景山学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":11,"name":"北京市第一七一中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":12,"name":"北京市第一七七中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":13,"name":"北京市第一中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":16,"name":"北京市第一二五中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":17,"name":"北京市第一二六中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":19,"name":"北京市第一六五中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":20,"name":"北京市第一六六中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":24,"name":"北京市第二中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":25,"name":"北京市第二中学分校","provinceId":110000,"cityId":110101,"areaId":null},{"id":26,"name":"北京市第二十一中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":27,"name":"北京市第二十七中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":28,"name":"北京市第二十二中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":29,"name":"北京市第二十五中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":30,"name":"北京市第二十四中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":31,"name":"北京市第五中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":32,"name":"北京市第五中学分校","provinceId":110000,"cityId":110101,"areaId":null},{"id":33,"name":"北京市第五十五中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":34,"name":"北京市第五十四中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":35,"name":"北京市第八十五中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":36,"name":"北京市第六十五中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":37,"name":"北京市翔宇中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":40,"name":"北京市东城区职业教育中心学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":45,"name":"北京市第十六中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":76,"name":"北京市第六十六中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":142,"name":"北京市前门外国语学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":143,"name":"北京市第十一中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":144,"name":"北京市天坛中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":146,"name":"北京市崇文门中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":147,"name":"北京市广渠门中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":148,"name":"北京市文汇中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":149,"name":"北京市第一一五中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":151,"name":"北京市第一一六中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":152,"name":"北京市第十一中学实验学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":153,"name":"北京市第一七九中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":154,"name":"北京市第一七八中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":155,"name":"北京市第一零九中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":156,"name":"北京市第九十中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":157,"name":"北京第五十中学分校","provinceId":110000,"cityId":110101,"areaId":null},{"id":158,"name":"北京市第二二三中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":159,"name":"北京市第五十中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":162,"name":"北京市龙潭中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":163,"name":"北京汇文中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":164,"name":"北京现代职业学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":76056,"name":"中央工艺美术学院附属中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":77118,"name":"北京市东城区教师研修中心","provinceId":110000,"cityId":110101,"areaId":null},{"id":77189,"name":"北京教育学院崇文分院","provinceId":110000,"cityId":110101,"areaId":null},{"id":78375,"name":"北京宏志中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":79457,"name":"北京思研教育咨询有限责任公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":80193,"name":"北京市第九十六中学","provinceId":110000,"cityId":110101,"areaId":null},{"id":81582,"name":"北京市京客网科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":82445,"name":"北京国际职业教育学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":430571,"name":"北京龙腾八方文化有限责任公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":431088,"name":"学易云专用","provinceId":110000,"cityId":110101,"areaId":null},{"id":456701,"name":"北京市第十一中学分校","provinceId":110000,"cityId":110101,"areaId":null},{"id":456883,"name":"北京顺天府学教育中心","provinceId":110000,"cityId":110101,"areaId":null},{"id":457363,"name":"北京市东城区教委","provinceId":110000,"cityId":110101,"areaId":null},{"id":463846,"name":"中国移动政企","provinceId":110000,"cityId":110101,"areaId":null},{"id":464406,"name":"孔庙国子监成贤国学馆","provinceId":110000,"cityId":110101,"areaId":null},{"id":464416,"name":"金沙古载教育咨询北京有限责任有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":464572,"name":"北京大学教育基金会","provinceId":110000,"cityId":110101,"areaId":null},{"id":464818,"name":"德胜智课教育","provinceId":110000,"cityId":110101,"areaId":null},{"id":465496,"name":"峰会体验账户","provinceId":110000,"cityId":110101,"areaId":null},{"id":466831,"name":"北京市外国语学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":466832,"name":"北京市育人中等职业学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":466833,"name":"北京百年农工子弟职业学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":466834,"name":"北京市东城区特殊教育学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":466835,"name":"北京市东城区工读学校北京市东城区古城职业高中","provinceId":110000,"cityId":110101,"areaId":null},{"id":466840,"name":"北京市东城区中央音乐学院鼎石实验学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":467143,"name":"北京华夏爱童文化发展有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":467147,"name":"宝篮贝贝北京教育咨询有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":467339,"name":"北京市卫生学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":467346,"name":"北京市财经学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":472877,"name":"北京新思维博仁教育咨询有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":474173,"name":"北京育英领航教育科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":475213,"name":"测试陈煦","provinceId":110000,"cityId":110101,"areaId":null},{"id":475519,"name":"北京精仪达盛科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":475732,"name":"北京瑞森教育咨询有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":475813,"name":"河南好学教育科技股份有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":475919,"name":"华夏科文（北京）信息技术有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":476409,"name":"北京大风车教育科技发展有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":476640,"name":"北京任学教育科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":476661,"name":"北京可圈可点科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":477111,"name":"中国出版集团公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":477730,"name":"中科数字出版传媒有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":478236,"name":"北京弘成教育科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":478567,"name":"北京盛唐联合科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":478661,"name":"好学智慧教育科技北京有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":479218,"name":"北京中油瑞飞信息技术有限责任公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":479352,"name":"北京中青网脉科技发展有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":479904,"name":"北京星澜科技发展有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":479924,"name":"北京翼时代网络科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":480773,"name":"北京东方联盟科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":481705,"name":"北京广渠门中学教育集团","provinceId":110000,"cityId":110101,"areaId":null},{"id":482336,"name":"天利文化教育科技集团","provinceId":110000,"cityId":110101,"areaId":null},{"id":483341,"name":"杜威国际学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":484620,"name":"人民东方(北京)书业有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":484721,"name":"北京市太极华青信息系统有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":485025,"name":"北京市东城区学大教育培训学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":485461,"name":"北京现代中小学网络教育研究院","provinceId":110000,"cityId":110101,"areaId":null},{"id":485648,"name":"化学工业出版社","provinceId":110000,"cityId":110101,"areaId":null},{"id":485665,"name":"人民东方出版传媒有限公司东方出版社","provinceId":110000,"cityId":110101,"areaId":null},{"id":485696,"name":"中国科技出版传媒股份有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":485698,"name":"中国科技出版传媒股份有限公司北京科学书店","provinceId":110000,"cityId":110101,"areaId":null},{"id":485714,"name":"中国出版传媒股份有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":485715,"name":"商务印书馆有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":485843,"name":"中国协和医科大学出版社","provinceId":110000,"cityId":110101,"areaId":null},{"id":485855,"name":"北京贝壳悦读文化艺术有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":485903,"name":"中国青年出版社","provinceId":110000,"cityId":110101,"areaId":null},{"id":485945,"name":"联合天际(北京)文化传媒有限公司东城分公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":486774,"name":"北京创联教育投资有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":486910,"name":"华夏出版社","provinceId":110000,"cityId":110101,"areaId":null},{"id":486912,"name":"台海出版社","provinceId":110000,"cityId":110101,"areaId":null},{"id":487054,"name":"东方音像电子出版社","provinceId":110000,"cityId":110101,"areaId":null},{"id":487073,"name":"人民文学出版社有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":487118,"name":"北京昊福文化传播股份有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":487185,"name":"世界图书出版有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":487212,"name":"语文出版社有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":487429,"name":"北京广版新世纪文化传媒有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":487609,"name":"人民出版社三联书店合并","provinceId":110000,"cityId":110101,"areaId":null},{"id":487624,"name":"后浪出版咨询北京有限责任公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":487706,"name":"光明日报出版社","provinceId":110000,"cityId":110101,"areaId":null},{"id":487948,"name":"北京禾智通言教育科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":488223,"name":"北京聚联创信息技术有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":488769,"name":"北京市东城区教育研究中心","provinceId":110000,"cityId":110101,"areaId":null},{"id":489419,"name":"学习出版社","provinceId":110000,"cityId":110101,"areaId":null},{"id":489589,"name":"北京淑欣文化传播有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":489745,"name":"北京开卷信息技术有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":489804,"name":"北京中环智学图书有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":490023,"name":"中国少年儿童新闻出版总社","provinceId":110000,"cityId":110101,"areaId":null},{"id":490196,"name":"北京市东城区教育委员会","provinceId":110000,"cityId":110101,"areaId":null},{"id":490602,"name":"中国美术出版总社有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":490954,"name":"北京科文剑桥图书有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":492692,"name":"北京慧跃科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":493477,"name":"北京华文盛世教育发展有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":493519,"name":"北京中保天和信息科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":494382,"name":"北京安顺知达网络技术有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":494730,"name":"北京中少金开明书刊发行有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":495007,"name":"北京伟东数据科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":495655,"name":"中奥文化投资有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":496318,"name":"北京慧思雅教育咨询有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":496402,"name":"决胜教育科技集团股份有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":496537,"name":"北京汇网兴云科技发展有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":496565,"name":"北京时代凤凰教育科技研究院","provinceId":110000,"cityId":110101,"areaId":null},{"id":496682,"name":"北京新起点盛和教育科技有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":496799,"name":"开迪乐博教育科技（北京）有限公司","provinceId":110000,"cityId":110101,"areaId":null},{"id":496948,"name":"北京市东城区美克培训学校","provinceId":110000,"cityId":110101,"areaId":null},{"id":497135,"name":"北京清大知好乐教育科技有限公司","provinceId":110000,"cityId":110101,"areaId":null}]
     * timestamp : 1581779522919
     */

    private boolean success;
    private String message;
    private int code;
    private long timestamp;
    private List<ResultBean> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 5
         * name : 北京市东直门中学
         * provinceId : 110000
         * cityId : 110101
         * areaId : null
         */

        private int id;
        private String name;
        private int provinceId;
        private int cityId;
        private Object areaId;
        private String index;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public Object getAreaId() {
            return areaId;
        }

        public void setAreaId(Object areaId) {
            this.areaId = areaId;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }
    }
}
