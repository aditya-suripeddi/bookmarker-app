import React from "react";
import {BookmarksResponse} from "@/services/models";

interface PaginationProps {
    bookmarks: BookmarksResponse
}

const Pagination: React.FC<PaginationProps> = ({bookmarks}) => {

    const path = "/bookmarks"
    const firstPage  = {pathname: path, query : { page: 1} }
    const previousPage = {pathname:path, query: {page: bookmarks.currentPage - 1}}
    const nextPage = {pathname:path, query: {page: bookmarks.currentPage + 1}}
    const lastPage = {pathname:path, query: {page: bookmarks.totalPages}}

    return (
        <div>
            <nav aria-label="Page navigation">
                <ul className="pagination justify-content-center">

                </ul>
            </nav>
        </div>
    )
}

export default Pagination;