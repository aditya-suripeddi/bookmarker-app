import React from "react"
import exp from "constants";
import {BookmarksResponse} from "@/services/models";
import Bookmark from "@/components/Bookmark";
import Pagination from "@/components/Pagination";

interface BookmarksProps {
    bookmarks: BookmarksResponse
}

const Bookmarks: React.FC<BookmarksProps> = ({bookmarks}) => {
    return (
        <div>
            <Pagination bookmarks={bookmarks}/>
            {bookmarks.data.map(bookmark => <Bookmark key={bookmark.id} bookmark={bookmark} />)}
        </div>
    )
}

export default Bookmarks;