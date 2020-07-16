package com.yingbao.career.http.resultbean;

import java.util.List;

/**
 * @Description:
 * @Date: 2020/5/12 17:16
 * @Auther: wanyan
 */
public class CourseListResultBean {

    /**
     * code : 0
     * message :
     * result : {"current":0,"pages":0,"records":[{"courseCover":"","courseId":0,"courseName":"","courseStartTime":"","courseTeacherId":0,"courseTeacherIntroduction":"","courseTeacherName":"","gradeId":0,"specialId":0,"subjectId":0}],"searchCount":true,"size":0,"total":0}
     * success : true
     * timestamp : 0
     */

    private int code;
    private String message;
    private ResultBean result;
    private boolean success;
    private int timestamp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultBean {
        /**
         * current : 0
         * pages : 0
         * records : [{"courseCover":"","courseId":0,"courseName":"","courseStartTime":"","courseTeacherId":0,"courseTeacherIntroduction":"","courseTeacherName":"","gradeId":0,"specialId":0,"subjectId":0}]
         * searchCount : true
         * size : 0
         * total : 0
         */

        private int current;
        private int pages;
        private boolean searchCount;
        private int size;
        private int total;
        private List<RecordsBean> records;

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * courseCover :
             * courseId : 0
             * courseName :
             * courseStartTime :
             * courseTeacherId : 0
             * courseTeacherIntroduction :
             * courseTeacherName :
             * gradeId : 0
             * specialId : 0
             * subjectId : 0
             */

            private String courseCover;
            private int courseId;
            private String courseName;
            private String courseStartTime;
            private int courseTeacherId;
            private String courseTeacherIntroduction;
            private String courseTeacherName;
            private int gradeId;
            private int specialId;
            private int subjectId;

            public String getCourseCover() {
                return courseCover;
            }

            public void setCourseCover(String courseCover) {
                this.courseCover = courseCover;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getCourseName() {
                return courseName;
            }

            public void setCourseName(String courseName) {
                this.courseName = courseName;
            }

            public String getCourseStartTime() {
                return courseStartTime;
            }

            public void setCourseStartTime(String courseStartTime) {
                this.courseStartTime = courseStartTime;
            }

            public int getCourseTeacherId() {
                return courseTeacherId;
            }

            public void setCourseTeacherId(int courseTeacherId) {
                this.courseTeacherId = courseTeacherId;
            }

            public String getCourseTeacherIntroduction() {
                return courseTeacherIntroduction;
            }

            public void setCourseTeacherIntroduction(String courseTeacherIntroduction) {
                this.courseTeacherIntroduction = courseTeacherIntroduction;
            }

            public String getCourseTeacherName() {
                return courseTeacherName;
            }

            public void setCourseTeacherName(String courseTeacherName) {
                this.courseTeacherName = courseTeacherName;
            }

            public int getGradeId() {
                return gradeId;
            }

            public void setGradeId(int gradeId) {
                this.gradeId = gradeId;
            }

            public int getSpecialId() {
                return specialId;
            }

            public void setSpecialId(int specialId) {
                this.specialId = specialId;
            }

            public int getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(int subjectId) {
                this.subjectId = subjectId;
            }
        }
    }
}
