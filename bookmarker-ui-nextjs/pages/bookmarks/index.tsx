import {GetServerSideProps, NextPage} from "next";
import React from "react";
import {BookmarksResponse} from "@/services/models";
import {fetchBookmarks} from "@/services/api";
import Bookmarks from "@/components/Bookmarks";

// https://www.digitalocean.com/community/tutorials/react-typescript-with-react#functional-components

interface HomeProps {
    bookmarks:BookmarksResponse
}
const Home: NextPage<HomeProps> = (props) => {
    return (
        <div>
            <h1>Welcome to Bookmarker</h1>
            <Bookmarks bookmarks={props.bookmarks}/>
        </div>
    )
}

export const getServerSideProps:GetServerSideProps = async(context) => {

    const {page = 1} = context.query
    const bookmarks = await fetchBookmarks(parseInt(String(page)))

    return {
        props : {
            bookmarks
        }//will be passed to page component as props
    }
}
export default Home;