package com.dev.bookmarker.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 *   BookmarkDTO is the data transfer object corresponding
 *   to Bookmark
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkDTO {

    private Long id;
    private String title;
    private String url;
    private Instant createdAt;

}