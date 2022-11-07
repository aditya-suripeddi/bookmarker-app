package com.dev.bookmarker.domain;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
@RequiredArgsConstructor // instead of manually creating a constructor to initialize BookmarkRepository property
public class BookmarkService {

    private final BookmarkRepository repository;
    private final BookmarkMapper bookmarkMapper;


    @Transactional(readOnly = true) // tell hibernate that we are only reading data and not doing data manipulations
                                    // so that it can bring in some optimizations
    public BookmarksDTO getBookmarks(Integer page) {

        int pageNo = Math.max(page - 1, 0) ;


        // Paging configuration:   pageNo you wish to view,
        //                         10 elements per page,
        //                         desc sort on createdAt property
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");


        // bookmarkPage of type Page<BookMarkDTO> has additional metadata
        // such as number of pages, current page, totalNumberOfElements
        // isFirstPage, isLastPage which is used in BookmarksDTO
        Page<BookmarkDTO> bookmarkPage = repository.getBookmarks(pageable);

        return new BookmarksDTO(bookmarkPage);
    }

}