package com.example.bookmark_group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BookmarkGroup {
    private long id;
    private String name;//북마크 이름
    private int priority;//순서
    private String createDateTime;//생성 일시
    private String editDateTime;//수정 일시

    public BookmarkGroup() {
    }
}
