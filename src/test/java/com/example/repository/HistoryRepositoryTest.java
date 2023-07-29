package com.example.repository;

import com.example.dto.LocationDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryRepositoryTest {

    static HistoryRepository historyRepository = new HistoryRepository();
    @Test
    void saveHistory() {
        //given
        LocationDTO loc = new LocationDTO("37.123", "126.678");
        historyRepository.saveHistory(loc);
    }
}