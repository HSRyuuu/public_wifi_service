package com.example.bookmark_group;

import com.example.bookmark.BookmarkRepository;
import com.example.dto.BookmarkGroupDTO;

import java.util.List;

public class BookmarkGroupService {
    private static BookmarkGroupRepository bookmarkGroupRepository = new BookmarkGroupRepository();
    private static BookmarkRepository bookmarkRepository = new BookmarkRepository();

    public BookmarkGroup findById(long id) {
        return bookmarkGroupRepository.findById(id);
    }

    public List<BookmarkGroup> findAllGroupList() {
        return bookmarkGroupRepository.findAll();
    }

    public void addBookmarkGroup(BookmarkGroupDTO bookmarkGroupDTO) {
        bookmarkGroupRepository.save(bookmarkGroupDTO);
    }

    public void editBookmarkGroup(long id, BookmarkGroupDTO bookmarkGroupDTO) {
        String before = bookmarkGroupRepository.findById(id).getName();
        bookmarkGroupRepository.edit(id, bookmarkGroupDTO);
        bookmarkRepository.updateBookmarkGroupName(before, bookmarkGroupDTO.getName());
    }

    public void deleteBookmarkGroup(long id) {
        String name = bookmarkGroupRepository.findById(id).getName();
        bookmarkRepository.deleteByBookmarkGroupName(name);
        bookmarkGroupRepository.delete(id);
    }
}
