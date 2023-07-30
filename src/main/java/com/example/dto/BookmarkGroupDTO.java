package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookmarkGroupDTO {
    private String name;//북마크 이름
    private int priority;//순서
}
