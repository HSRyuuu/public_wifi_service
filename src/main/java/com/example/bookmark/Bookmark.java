package com.example.bookmark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Bookmark {
    private long id;
    private String bookmarkGroupName;//북마크그룹 이름
    private String wifiManageNumber;//wifi 관리번호
    private String wifiName;//wifi 이름
    private String dateTime;//생성 일시

    public Bookmark() {
    }
}
