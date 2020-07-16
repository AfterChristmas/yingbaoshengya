package com.yingbao.career.ui.question.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuesDetail implements Serializable {

    private int id;
    private int quesTypeId;
    private String homeworkId;
    private String homeworkassignid;
    private String quesType = "";
    private String gradeid;
    private String chapterid;
    private int bankId;
    private String gradename;
    private String chaptername;
    private String createTime;
    private List<QuesDetail> childQuesList;
    private boolean isBigQues = false;
    private String ParentQuesBody = "";
    private String ParentQuesType = "";
    private int parentId = 0;
    private int categoryId;
    private String categoryName = "";
    private String childQuesNumber = "";
    private String enterPointStudentName = "";
    private int AnswerStatu;
    private String quesAbility;
    private String quesBody;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String optionF;
    private String optionG;
    private int HomeWorkID;
    private String QuesAnswer;
    private String QuesParse;
    private String QuesDoneAnswer = "";
    private int doneResult;
    private String categoryname;
    private float point;
    private String studentAnswer = "";
    private String quesTypeName;
    private int result;
    private boolean isUpdated = false;
    private int quesTag = 0;
    private int quesNumber;
    private int rightOrNot;
    private int orderNumber;
    private String correctStudentName;
    private String videoPath;
    private String audioPath;
    private boolean isDifficult;
    private float score;
    private String videoIds;
    private boolean checked;
    private String parseVideoURL;
    private Map<String, String> options = new LinkedHashMap<>();
    private boolean isRight;
    private boolean isAddedErrorQues;
    private boolean isAnswered;
    private boolean isAnswerStateUpdate;
    private int redoGrade;
    private boolean isErrorQues;
    private int isSelect ;
    //是否是材料题
    private int isCailiaoQues;

    public int getIsCailiaoQues() {
        return isCailiaoQues;
    }

    public void setIsCailiaoQues(int isCailiaoQues) {
        this.isCailiaoQues = isCailiaoQues;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    public boolean isErrorQues() {
        return isErrorQues;
    }

    public void setErrorQues(boolean errorQues) {
        isErrorQues = errorQues;
    }

    public int getAnswerStatu() {
        return AnswerStatu;
    }

    public void setAnswerStatu(int answerStatu) {
        AnswerStatu = answerStatu;
    }

    public boolean isAddedErrorQues() {
        return isAddedErrorQues;
    }

    public void setAddedErrorQues(boolean addedErrorQues) {
        isAddedErrorQues = addedErrorQues;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getCorrectStudentName() {
        return correctStudentName;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public void setCorrectStudentName(String correctStudentName) {
        this.correctStudentName = correctStudentName;
    }

    public int getHomeWorkID() {
        return HomeWorkID;
    }

    public void setHomeWorkID(int homeWorkID) {
        HomeWorkID = homeWorkID;
    }

    public String getEnterPointStudentName() {
        return enterPointStudentName;
    }

    public void setEnterPointStudentName(String enterPointStudentName) {
        this.enterPointStudentName = enterPointStudentName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getQuesNumber() {
        return quesNumber;
    }

    public void setQuesNumber(int quesNumber) {
        this.quesNumber = quesNumber;
    }

    public int getRightOrNot() {
        return rightOrNot;
    }

    public void setRightOrNot(int rightOrNot) {
        this.rightOrNot = rightOrNot;
    }

    public String getChildQuesNumber() {
        return childQuesNumber;
    }

    public void setChildQuesNumber(String childQuesNumber) {
        this.childQuesNumber = childQuesNumber;
    }

    public String getParentQuesType() {
        return ParentQuesType;
    }

    public void setParentQuesType(String parentQuesType) {
        ParentQuesType = parentQuesType;
    }

    public String getParentQuesBody() {
        return ParentQuesBody;
    }

    public void setParentQuesBody(String parentQuesBody) {
        ParentQuesBody = parentQuesBody;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<QuesDetail> getChildQuesList() {
        return childQuesList;
    }

    public void setChildQuesList(List<QuesDetail> childQuesList) {
        this.childQuesList = childQuesList;
    }

    public boolean isBigQues() {
        return isBigQues;
    }

    public void setBigQues(boolean isBigQues) {
        this.isBigQues = isBigQues;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getQuesTag() {
        return quesTag;
    }

    public void setQuesTag(int quesTag) {
        this.quesTag = quesTag;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getHomeworkassignid() {
        return homeworkassignid;
    }

    public void setHomeworkassignid(String homeworkassignid) {
        this.homeworkassignid = homeworkassignid;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }

    public String getGradeid() {
        return gradeid;
    }

    public void setGradeid(String gradeid) {
        this.gradeid = gradeid;
    }

    public String getChapterid() {
        return chapterid;
    }

    public void setChapterid(String chapterid) {
        this.chapterid = chapterid;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getQuesTypeName() {
        return quesTypeName;
    }

    public void setQuesTypeName(String quesTypeName) {
        this.quesTypeName = quesTypeName;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getQuesTypeId() {
        return quesTypeId;
    }

    public void setQuesTypeId(int quesTypeId) {
        this.quesTypeId = quesTypeId;
    }

    public boolean isSubjectQues() {
        return !isSingleChoice() && !isMultipleChoice();
    }

    public boolean isSingleChoice() {
        return quesTypeId == 2 || quesTypeId == 3 || quesTypeId == 6;
    }

    public boolean isMultipleChoice() {
        return quesTypeId == 4 || quesTypeId == 5 || quesTypeId == 7;
    }

    public boolean isChoiceQues() {
        return isSingleChoice() || isMultipleChoice();
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public String getQuesDoneAnswer() {
        return QuesDoneAnswer;
    }

    public void setQuesDoneAnswer(String quesDoneAnswer) {
        QuesDoneAnswer = quesDoneAnswer;
    }

    public int getDoneResult() {
        return doneResult;
    }

    public void setDoneResult(int doneResult) {
        this.doneResult = doneResult;
    }

    public QuesDetail() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuesType() {
        return quesType;
    }

    public void setQuesType(String quesType) {
        this.quesType = quesType;
    }

    public String getQuesAbility() {
        return quesAbility;
    }

    public void setQuesAbility(String quesAbility) {
        this.quesAbility = quesAbility;
    }

    public String getQuesBody() {
        return quesBody;
    }

    public void setQuesBody(String quesBody) {
        this.quesBody = quesBody;
    }

    public Map<String, String> getOptions() {
        options.put("A", getOptionA());
        options.put("B", getOptionB());
        options.put("C", getOptionC());
        options.put("D", getOptionD());
        options.put("E", getOptionE());
        options.put("F", getOptionF());
        options.put("G", getOptionG());
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getOptionA() {
        if (optionA == null) {
            optionA = "";
        }
        return optionA.trim();
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        if (optionB == null) {
            optionB = "";
        }
        return optionB.trim();
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        if (optionC == null) {
            optionC = "";
        }
        return optionC.trim();
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        if (optionD == null) {
            optionD = "";
        }
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getOptionE() {
        if (optionE == null) {
            optionE = "";
        }
        return optionE.trim();
    }

    public void setOptionE(String optionE) {
        this.optionE = optionE;
    }

    public String getOptionF() {
        if (optionF == null) {
            optionF = "";
        }
        return optionF.trim();
    }

    public void setOptionF(String optionF) {
        this.optionF = optionF;
    }

    public String getOptionG() {
        if (optionG == null) {
            optionG = "";
        }
        return optionG.trim();
    }

    public void setOptionG(String optionG) {
        this.optionG = optionG;
    }

    public String getQuesAnswer() {
        return QuesAnswer;
    }

    public void setQuesAnswer(String quesAnswer) {
        QuesAnswer = quesAnswer;
    }

    public String getQuesParse() {
        String quesParse = QuesParse;
        Pattern p = Pattern.compile("<video.*?</video>");
        Matcher m = p.matcher(QuesParse);
        while (m.find()) {
            quesParse = QuesParse.replace(m.group(), "");
        }
        return quesParse;
    }

    public void setQuesParse(String quesParse) {
        QuesParse = quesParse;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isDifficult() {
        return isDifficult;
    }

    public void setIsDifficult(boolean isDifficult) {
        this.isDifficult = isDifficult;
    }

    public String getVideoIds() {
        return videoIds;
    }

    public void setVideoIds(String videoIds) {
        this.videoIds = videoIds;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getParseVideoURL() {
        Pattern p = Pattern.compile("<video.*?src=\"([^\"]+)\"");
        Matcher m = p.matcher(QuesParse);
        while (m.find()) {
            parseVideoURL = m.group(1).trim();
        }
        return parseVideoURL;
    }

    public void setParseVideoURL(String parseVideoURL) {
        this.parseVideoURL = parseVideoURL;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isAnswerStateUpdate() {
        return isAnswerStateUpdate;
    }

    public void setAnswerStateUpdate(boolean answerStateUpdate) {
        isAnswerStateUpdate = answerStateUpdate;
    }

    public int getRedoGrade() {
        return redoGrade;
    }

    public void setRedoGrade(int redoGrade) {
        this.redoGrade = redoGrade;
    }
}
