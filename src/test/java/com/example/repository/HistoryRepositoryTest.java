package com.example.repository;

import com.example.dto.LocationDTO;
import com.example.history.History;
import com.example.history.HistoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

class HistoryRepositoryTest {

    static HistoryRepository historyRepository = new HistoryRepository();
    @Test
    void saveHistory() {
        LocationDTO loc = new LocationDTO("37.123", "126.678");
        historyRepository.saveHistory(loc);
    }

    @Test
    void select20History(){
        List<History> list = historyRepository.selectLatest20History();
        for(History h : list){
            System.out.println(h);
        }
    }
}