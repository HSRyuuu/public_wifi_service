package com.example.bookmark_group;

import com.example.bookmark_group.BookmarkGroup;
import com.example.bookmark_group.BookmarkGroupRepository;
import com.example.dto.BookmarkGroupDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;


class BookmarkGroupRepositoryTest {
    static BookmarkGroupRepository repository = new BookmarkGroupRepository();

    @Test
    void addBookmarkGroup() {
        BookmarkGroupDTO dto = new BookmarkGroupDTO("이름2",2);
        repository.save(dto);
    }

    @Test
    void edit() {
        BookmarkGroupDTO dto = new BookmarkGroupDTO("이름1 수정",3);
        repository.edit(1, dto);
    }

    @Test
    void findAll() {
        List<BookmarkGroup> list = repository.findAll();
        for (BookmarkGroup bg : list){
            System.out.println(bg);
        }
    }
}