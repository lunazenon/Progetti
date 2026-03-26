package com.orario.service;

import com.orario.model.Lesson;
import java.util.*;
import java.util.stream.Collectors;

public class LessonRepository {
    private static final List<Lesson> ALL = new ArrayList<>();

    static {
        // ── 1° SEMESTRE ──────────────────────────────────────────────────
        // 3° Anno ICD (foto 4)
        a("Interazione Uomo-Macchina","Lunedì","09:00","13:10",3,"1","ICD");
        a("Programmazione per il Web","Martedì","09:00","14:00",3,"1","ICD");
        a("Tecnologie Informatiche per la Didattica","Mercoledì","09:50","13:05",3,"1","ICD");
        a("Sicurezza Informatica","Giovedì","09:00","13:10",3,"1","ICD");
        a("Reti di Calcolatori e Com. Digitale","Venerdì","09:00","14:00",3,"1","ICD");
        // 2° Anno ICD (foto 5)
        a("Algoritmi e Strutture Dati","Lunedì","09:00","13:10",2,"1","ICD");
        a("Algoritmi e Strutture Dati","Martedì","09:00","11:30",2,"1","ICD");
        a("Progettazione e Prod. Multimediale","Martedì","11:30","15:40",2,"1","ICD");
        a("Fondamenti dell'Informatica","Mercoledì","09:50","13:05",2,"1","ICD");
        a("Statistica Matematica","Giovedì","09:00","13:10",2,"1","ICD");
        a("Progettazione e Prod. Multimediale","Venerdì","09:00","11:30",2,"1","ICD");
        // 1° Anno ITI (foto 6)
        a("Architettura degli Elaboratori","Lunedì","09:00","13:10",1,"1","ITI");
        a("Architettura degli Elaboratori","Martedì","08:30","11:50",1,"1","ITI");
        a("Fondamenti di Matematica","Martedì","11:50","15:10",1,"1","ITI");
        a("Fondamenti di Matematica","Mercoledì","09:50","13:10",1,"1","ITI");
        a("Programmazione","Giovedì","08:30","13:30",1,"1","ITI");
        a("Programmazione","Venerdì","08:30","12:40",1,"1","ITI");

        // ── 2° SEMESTRE ──────────────────────────────────────────────────
        // 3° Anno ICD (foto 1)
        a("Diritto dell'Informatica","Martedì","09:20","13:30",3,"2","ICD");
        a("Sistemi Intelligenti per la Com. Digitale","Lunedì","12:00","16:10",3,"2","ICD");
        // 2° Anno ICD (foto 2)
        a("Basi di Dati","Martedì","09:20","15:10",2,"2","ICD");
        a("Calcolo Numerico","Giovedì","09:15","14:15",2,"2","ICD");
        a("Metodi di Osservazione","Venerdì","08:30","13:30",2,"2","ICD");
        a("Ingegneria del Software","Mercoledì","13:30","16:50",2,"2","ICD");
        a("Ingegneria del Software","Giovedì","14:20","16:50",2,"2","ICD");
        // 1° Anno ITI (foto 3)
        a("Laboratorio di Informatica","Giovedì","09:20","14:20",1,"2","ITI");
        a("Lingua Inglese","Venerdì","09:20","14:20",1,"2","ITI");
    }

    private static void a(String s, String d, String ts, String te,
                           int y, String sem, String c) {
        ALL.add(new Lesson(s, d, ts, te, y, sem, c));
    }

    public static List<Lesson> getFiltered(int maxYear, String semester) {
        return ALL.stream()
            .filter(l -> l.getSemester().equals(semester))
            .filter(l -> maxYear == 0 || l.getYear() <= maxYear)
            .collect(Collectors.toList());
    }

    public static Lesson findById(String id) {
        return ALL.stream()
            .filter(l -> l.getId().equals(id))
            .findFirst().orElse(null);
    }
}