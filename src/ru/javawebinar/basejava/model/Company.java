package ru.javawebinar.basejava.model;

import java.util.*;

public class Company {
    private final String name;
    private final Map<String, String> stages;
    private final String content;

    public Company(String name, String content, List<String> periods, List<String> positions) {
        this.name = name;
        this.stages = new LinkedHashMap<>();
        this.content = content;
        setStages(periods, positions);
    }

    public void setStages(List<String> periods, List<String> positions) {
        for (int i = 0; i < periods.size(); i++) {
            stages.put(periods.get(i), positions.get(i));
        }
    }
    
    @Override
    public String toString() {
        System.out.println(name);
        Iterator<Map.Entry<String, String>> iterator = stages.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String period = entry.getKey();
            String position = entry.getValue();
            System.out.print(period + "   ");
            System.out.println(position);
        }
        return content + "\n";
    }
}
