package com.example.history;

import com.example.dto.LocationDTO;

import java.util.List;

public class HistoryService {
    private static HistoryRepository historyRepository = new HistoryRepository();

    public List<History> getLatest20Histories(){
        return historyRepository.selectLatest20History();
    }

    public void saveHistory(LocationDTO loc){
        historyRepository.saveHistory(loc);
    }
    public void deleteHistoryById(String id){
        historyRepository.deleteById(Long.parseLong(id));
    }
}
