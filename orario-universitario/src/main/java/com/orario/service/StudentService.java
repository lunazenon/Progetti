package com.orario.service;

import com.google.cloud.firestore.*;
import com.orario.model.Student;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class StudentService {

    private static final String COLLECTION = "students";

    public static boolean register(String username, String password, int year)
            throws ExecutionException, InterruptedException {
        Firestore db = FirebaseConfig.getDb();
        DocumentReference ref = db.collection(COLLECTION).document(username);
        DocumentSnapshot snap = ref.get().get();
        if (snap.exists()) return false;

        Map<String, Object> data = new HashMap<>();
        data.put("password", password);
        data.put("enrolledYear", year);
        data.put("selectedLessons", new ArrayList<>());
        ref.set(data).get();
        return true;
    }

    public static Student login(String username, String password)
            throws ExecutionException, InterruptedException {
        Firestore db = FirebaseConfig.getDb();
        DocumentSnapshot snap = db.collection(COLLECTION)
                                  .document(username).get().get();
        if (!snap.exists()) return null;
        if (!password.equals(snap.getString("password"))) return null;

        int year = Objects.requireNonNull(snap.getLong("enrolledYear")).intValue();
        Student s = new Student(username, year);

        List<String> ids = (List<String>) snap.get("selectedLessons");
        if (ids != null) s.setSelectedIds(ids);
        return s;
    }

    public static void saveSelectedLessons(Student student)
            throws ExecutionException, InterruptedException {
        Firestore db = FirebaseConfig.getDb();
        Map<String, Object> update = new HashMap<>();
        update.put("selectedLessons", new ArrayList<>(student.getSelectedIds()));
        db.collection(COLLECTION)
          .document(student.getUsername())
          .update(update).get();
    }
}