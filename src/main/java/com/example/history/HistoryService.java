package com.example.history;

import com.example.dto.LocationDTO;

import java.util.List;

public class HistoryService {
    private static HistoryRepository historyRepository = new HistoryRepository();

    public List<History> findLatest20Histories() {
        return historyRepository.findAll();
    }

    public void saveHistory(LocationDTO loc) {
        historyRepository.save(loc);
    }

    public void deleteHistoryById(String id) {
        historyRepository.deleteById(Long.parseLong(id));
    }
}
