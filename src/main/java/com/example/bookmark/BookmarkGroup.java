package com.example.bookmark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookmarkGroup {
    private long id;
    private String name;//위도
    private int priority;//경도
    private String createDateTime;//생성 일시
    private String editDateTime;//수정 일시

    public BookmarkGroup() {
    }
}
