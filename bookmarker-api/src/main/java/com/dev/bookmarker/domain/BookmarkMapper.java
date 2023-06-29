package com.dev.bookmarker.domain;


import org.springframework.stereotype.Component;

/**
 *   BookmarkMapper maps bookmark domain model to
 *   its corresponding data transfer object
 */
@Component
public class BookmarkMapper {

    /**
     *
     * @param  bookmark domain model
     * @return dto corresponding to the domain model bookmark
     */
    public BookmarkDTO toDTO(Bookmark bookmark) {

        BookmarkDTO dto = new BookmarkDTO();
        dto.setId(bookmark.getId());
        dto.setTitle(bookmark.getTitle());
        dto.setUrl(bookmark.getUrl());
        dto.setCreatedAt(bookmark.getCreatedAt());
        return dto;

    }

}