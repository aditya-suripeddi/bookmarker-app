import React from "react"
import exp from "constants";
import {BookmarksResponse} from "@/services/models";
import Bookmark from "@/components/Bookmark";

interface BookmarksProps {
    bookmarks: BookmarksResponse
}

const Bookmarks: React.FC<BookmarksProps> = ({bookmarks}) => {
    return (
        <div>
            {bookmarks.data.map(bookmark => <Bookmark key={bookmark.id} bookmark={bookmark} />)}
        </div>
    )
}

export default Bookmarks;