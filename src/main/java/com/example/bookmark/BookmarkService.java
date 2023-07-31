package com.example.bookmark;

import com.example.dto.WifiDTO;
import com.example.wifi.Wifi;
import com.example.wifi.WifiService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookmarkService {
    static BookmarkRepository bookmarkRepository = new BookmarkRepository();
    static WifiService wifiService = new WifiService();

    public Bookmark findById(long id) {
        return bookmarkRepository.findById(id);
    }

    public List<Bookmark> findAll() {
        return bookmarkRepository.findAll();
    }

    public void save(String key, String bookmarkGroupName) {
        WifiDTO wifiDTO = wifiService.findById(key);
        Bookmark bookmark = new Bookmark();
        bookmark.setBookmarkGroupName(bookmarkGroupName);
        bookmark.setWifiManageNumber(key);
        bookmark.setWifiName(wifiDTO.getName());
        bookmark.setDateTime(getDateTimeNow());
        bookmarkRepository.save(bookmark);
    }

    public void delete(long id) {
        bookmarkRepository.delete(id);
    }

    private String getDateTimeNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")).toString();
    }

}
