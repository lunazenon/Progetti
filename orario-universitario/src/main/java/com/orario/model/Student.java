package com.orario.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class Student implements Serializable {
    private final String username;
    private final int enrolledYear;
    private final Set<String> selectedIds = new LinkedHashSet<>();

    public Student(String username, int enrolledYear) {
        this.username = username;
        this.enrolledYear = enrolledYear;
    }

    public String getUsername()     { return username; }
    public int    getEnrolledYear() { return enrolledYear; }

    public boolean canAccessYear(int y) {
        return enrolledYear == 0 || y <= enrolledYear;
    }

    public void toggleLesson(String id) {
        if (!selectedIds.remove(id)) selectedIds.add(id);
    }

    public boolean isSelected(String id) { return selectedIds.contains(id); }
    public Set<String> getSelectedIds()  { return selectedIds; }

    public void setSelectedIds(Collection<String> ids) {
        selectedIds.clear();
        selectedIds.addAll(ids);
    }
}