package com.example.history;

import com.example.dto.LocationDTO;

import java.util.List;

public class HistoryService {
    private static HistoryRepository historyRepository = new HistoryRepository();

    public List<History> findLatest20Histories() {
        return historyRepository.findAll();
    }

    public void saveHistory(LocationDTO loc) {
        historyRepository.saveHistory(loc);
    }

    public void deleteHistoryById(String id) {
        historyRepository.deleteHistory(Long.parseLong(id));
    }
}
