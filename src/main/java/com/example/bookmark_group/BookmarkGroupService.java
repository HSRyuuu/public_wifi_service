package com.example.bookmark_group;

import com.example.dto.BookmarkGroupDTO;

import java.util.List;

public class BookmarkGroupService {
    private static BookmarkGroupRepository bookmarkGroupRepository = new BookmarkGroupRepository();

    public BookmarkGroup findById(long id){
        return bookmarkGroupRepository.findById(id);
    }
    public List<BookmarkGroup> findAllGroupList(){
        return bookmarkGroupRepository.findAll();
    }
    public void addBookmarkGroup(BookmarkGroupDTO bookmarkGroupDTO){
        bookmarkGroupRepository.addBookmarkGroup(bookmarkGroupDTO);
    }
    public void editBookmarkGroup(long id, BookmarkGroupDTO bookmarkGroupDTO){
        bookmarkGroupRepository.edit(id, bookmarkGroupDTO);
    }
    public void deleteBookmarkGroup(long id){
        bookmarkGroupRepository.delete(id);
    }
}
