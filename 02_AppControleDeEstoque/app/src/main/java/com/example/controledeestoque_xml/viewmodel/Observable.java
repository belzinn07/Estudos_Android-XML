package com.example.controledeestoque_xml.viewmodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class Observable {
    private Map<String, Set<Consumer<Object>>> listeners = new HashMap<>();

    public void addListener(String propertyName, Consumer<Object> listener) {
        listeners.computeIfAbsent(propertyName, k -> new HashSet<>()).add(listener);
    }

    public void removeListener(String propertyName, Consumer<Object> listener) {
        if (listeners.containsKey(propertyName)) {
            listeners.get(propertyName).remove(listener);
        }
    }

    protected void notifyListeners(String propertyName, Object newValue) {
        if (listeners.containsKey(propertyName)) {
            for (Consumer<Object> listener : listeners.get(propertyName)) {
                listener.accept(newValue);
            }
        }
    }
}


