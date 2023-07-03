import axios, {AxiosResponse} from "axios"
import {BookmarksResponse} from "@/services/models";

const API_BASE_URL = "http://localhost:8080"

export const fetchBookmarks = async (page:number, query:string): Promise<BookmarksResponse> => {
    let url = `${API_BASE_URL}/api/bookmarks?page=${page}`
    if( query ){
      url += `&query=${query}`
    }
    const res = await axios.get<BookmarksResponse>(url)
    return res.data
}


// instead of declaring an AddBookmarkRequest interface with title and url attributes
// we create an adhoc type {title:string, url:string} as show below:
export const saveBookmark = async (bookmark:{title:string, url:string}) => {
    const res = await axios.post(`${API_BASE_URL}/api/bookmarks`, bookmark)
    return res.data
}