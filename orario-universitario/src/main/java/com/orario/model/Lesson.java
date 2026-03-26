package com.orario.model;

import java.io.Serializable;

public class Lesson implements Serializable {
    private final String subject, day, timeStart, timeEnd, semester, course;
    private final int year;

    public Lesson(String subject, String day, String timeStart, String timeEnd,
                  int year, String semester, String course) {
        this.subject = subject; this.day = day;
        this.timeStart = timeStart; this.timeEnd = timeEnd;
        this.year = year; this.semester = semester; this.course = course;
    }

    public String getSubject()   { return subject; }
    public String getDay()       { return day; }
    public String getTimeStart() { return timeStart; }
    public String getTimeEnd()   { return timeEnd; }
    public String getSemester()  { return semester; }
    public String getCourse()    { return course; }
    public int    getYear()      { return year; }

    public String getId() {
        return (subject + "_" + day + "_" + timeStart)
            .replaceAll("[^a-zA-Z0-9_]", "_").toLowerCase();
    }
}