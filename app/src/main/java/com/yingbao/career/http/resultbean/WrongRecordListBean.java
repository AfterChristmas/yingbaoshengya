package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2020/5/30 21:26
 * @Auther: wanyan
 */
public class WrongRecordListBean {

    /**
     * success : true
     * message : 操作成功！
     * code : 200
     * result : [{"id":4,"questionId":16501092,"childQuestionId":0,"questionStem":"<p><span style=\"\" >若全集<\/span><math><mrow><mi>U<\/mi><mo>=<\/mo><mrow><mo>{<\/mo><mrow><mn>1<\/mn><mo>,<\/mo><mn>2<\/mn><mo>,<\/mo><mn>3<\/mn><mo>,<\/mo><mn>4<\/mn><mo>,<\/mo><mn>5<\/mn><mo>,<\/mo><mn>6<\/mn><mo>,<\/mo><mn>7<\/mn><\/mrow><mo>}<\/mo><\/mrow><\/mrow><\/math><span style=\"\" >，集合<\/span><math><mrow><mi>A<\/mi><mo>=<\/mo><mrow><mo>{<\/mo><mrow><mn>1<\/mn><mo>,<\/mo><mn>3<\/mn><mo>,<\/mo><mn>5<\/mn><mo>,<\/mo><mn>7<\/mn><\/mrow><mo>}<\/mo><\/mrow><\/mrow><\/math><span style=\"\" >，集合<\/span><math><mrow><mi>B<\/mi><mo>=<\/mo><mrow><mo>{<\/mo><mrow><mn>1<\/mn><mo>,<\/mo><mn>4<\/mn><mo>,<\/mo><mn>7<\/mn><\/mrow><mo>}<\/mo><\/mrow><\/mrow><\/math><span style=\"\" >，则集合<\/span><math><mrow><mrow><mo>(<\/mo><mrow><msub><mi>C<\/mi><mi>U<\/mi><\/msub><mi>A<\/mi><\/mrow><mo>)<\/mo><\/mrow><mo>∩<\/mo><mi>B<\/mi><mo>=<\/mo><\/mrow><\/math><span style=\"\" >（    ）<\/span><\/p>","subjectId":1002,"subjectName":"数学","answerRecord":"C","rightEnable":-1,"answerTime":"2020-05-30 00:00:00"},{"id":3,"questionId":19376156,"childQuestionId":0,"questionStem":"<p><span style=\"\" >已知某几何体的三视图如图所示，则该几何体的体积等于(    )<\/span><\/p><p><img  src=\"http://qbm-images.oss-cn-hangzhou.aliyuncs.com/QBM/2018/3/7/1896897829855232/1898284438429696/STEM/d54ff1f95e324666a9cb1b2ddba94acf.png\" width=\"117\" height=\"146\" /><\/p>","subjectId":1002,"subjectName":"数学","answerRecord":"A","rightEnable":-1,"answerTime":"2020-05-30 00:00:00"},{"id":2,"questionId":16586815,"childQuestionId":0,"questionStem":"<p><span style=\"\" >下列说法中：①4是数据4,6,7,7,9,4的众数；②如果数据<\/span><math><msub><mrow><mi>x<\/mi><\/mrow><mrow><mn>1<\/mn><\/mrow><\/msub><\/math><span style=\"\" >，<\/span><math><msub><mrow><mi>x<\/mi><\/mrow><mrow><mn>2<\/mn><\/mrow><\/msub><\/math><span style=\"\" >，\u2026，<\/span><math><msub><mrow><mi>x<\/mi><\/mrow><mrow><mi>n<\/mi><\/mrow><\/msub><\/math><span style=\"\" > 的平均数为3，方差为0.2，则<\/span><math><mn>3<\/mn><msub><mrow><mi>x<\/mi><\/mrow><mrow><mn>1<\/mn><\/mrow><\/msub><mo>+<\/mo><mn>5<\/mn><\/math><span style=\"\" >，<\/span><math><mn>3<\/mn><msub><mrow><mi>x<\/mi><\/mrow><mrow><mn>2<\/mn><\/mrow><\/msub><mo>+<\/mo><mn>5<\/mn><\/math><span style=\"\" >，\u2026， <\/span><math><mn>3<\/mn><msub><mrow><mi>x<\/mi><\/mrow><mrow><mi>n<\/mi><\/mrow><\/msub><mo>+<\/mo><mn>5<\/mn><\/math><span style=\"\" >的平均数和方差分别为14和1.8；③用辗转相除法可得228与1995的最大公约数为57；④把四进制数<\/span><math><msub><mrow><mn>1000<\/mn><\/mrow><mrow><mo>(<\/mo><mn>4<\/mn><mo>)<\/mo><\/mrow><\/msub><\/math><span style=\"\" >化为二进制数是<\/span><math><msub><mrow><mn>1000000<\/mn><\/mrow><mrow><mo>(<\/mo><mn>2<\/mn><mo>)<\/mo><\/mrow><\/msub><\/math><span style=\"\" >；⑤已知甲、乙两组数据如茎叶图所示，若它们的中位数相同，平均数也相同，则图中<\/span><math><mi>m<\/mi><\/math><span style=\"\" >，<\/span><math><mi>n<\/mi><\/math><span style=\"\" >的比值<\/span><math><mfrac><mrow><mi>m<\/mi><\/mrow><mrow><mi>n<\/mi><\/mrow><\/mfrac><mo>=<\/mo><mfrac><mrow><mn>3<\/mn><\/mrow><mrow><mn>8<\/mn><\/mrow><\/mfrac><mo>.<\/mo><\/math><span style=\"\" > 正确说法的个数为（     ）<\/span><\/p><p><img  src=\"http://qbm-images.oss-cn-hangzhou.aliyuncs.com/QBM/2017/4/21/1670508008407040/1673912172634112/STEM/459e171a2ea740e194cb8b88076de83d.png\" width=\"205\" height=\"87\" /><\/p>","subjectId":1002,"subjectName":"数学","answerRecord":"A","rightEnable":-1,"answerTime":"2020-05-30 00:00:00"}]
     * timestamp : 1590852849773
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
         * id : 4
         * questionId : 16501092
         * childQuestionId : 0
         * questionStem : <p><span style="" >若全集</span><math><mrow><mi>U</mi><mo>=</mo><mrow><mo>{</mo><mrow><mn>1</mn><mo>,</mo><mn>2</mn><mo>,</mo><mn>3</mn><mo>,</mo><mn>4</mn><mo>,</mo><mn>5</mn><mo>,</mo><mn>6</mn><mo>,</mo><mn>7</mn></mrow><mo>}</mo></mrow></mrow></math><span style="" >，集合</span><math><mrow><mi>A</mi><mo>=</mo><mrow><mo>{</mo><mrow><mn>1</mn><mo>,</mo><mn>3</mn><mo>,</mo><mn>5</mn><mo>,</mo><mn>7</mn></mrow><mo>}</mo></mrow></mrow></math><span style="" >，集合</span><math><mrow><mi>B</mi><mo>=</mo><mrow><mo>{</mo><mrow><mn>1</mn><mo>,</mo><mn>4</mn><mo>,</mo><mn>7</mn></mrow><mo>}</mo></mrow></mrow></math><span style="" >，则集合</span><math><mrow><mrow><mo>(</mo><mrow><msub><mi>C</mi><mi>U</mi></msub><mi>A</mi></mrow><mo>)</mo></mrow><mo>∩</mo><mi>B</mi><mo>=</mo></mrow></math><span style="" >（    ）</span></p>
         * subjectId : 1002
         * subjectName : 数学
         * answerRecord : C
         * rightEnable : -1
         * answerTime : 2020-05-30 00:00:00
         */

        private int id;
        private int questionId;
        private int childQuestionId;
        private String questionStem;
        private int subjectId;
        private String subjectName;
        private String answerRecord;
        private int rightEnable;
        private String answerTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public int getChildQuestionId() {
            return childQuestionId;
        }

        public void setChildQuestionId(int childQuestionId) {
            this.childQuestionId = childQuestionId;
        }

        public String getQuestionStem() {
            return questionStem;
        }

        public void setQuestionStem(String questionStem) {
            this.questionStem = questionStem;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getAnswerRecord() {
            return answerRecord;
        }

        public void setAnswerRecord(String answerRecord) {
            this.answerRecord = answerRecord;
        }

        public int getRightEnable() {
            return rightEnable;
        }

        public void setRightEnable(int rightEnable) {
            this.rightEnable = rightEnable;
        }

        public String getAnswerTime() {
            return answerTime;
        }

        public void setAnswerTime(String answerTime) {
            this.answerTime = answerTime;
        }
    }
}
