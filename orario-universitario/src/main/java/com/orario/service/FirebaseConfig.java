package com.orario.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.io.InputStream;

public class FirebaseConfig {
    private static Firestore db;

    public static synchronized void initialize() {
        if (FirebaseApp.getApps().isEmpty()) {
            try {
                InputStream serviceAccount =
                    FirebaseConfig.class.getClassLoader()
                        .getResourceAsStream("firebase-credentials.json");

                if (serviceAccount == null) {
                    throw new RuntimeException("firebase-credentials.json non trovato!");
                }

                FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("orario-iti-icd-1e58b")
                    .build();

                FirebaseApp.initializeApp(options);
                System.out.println("Firebase inizializzato!");
            } catch (IOException e) {
                throw new RuntimeException("Errore Firebase: " + e.getMessage(), e);
            }
        }
        db = FirestoreClient.getFirestore();
    }

    public static Firestore getDb() {
        if (db == null) initialize();
        return db;
    }
}