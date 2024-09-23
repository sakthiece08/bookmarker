'use client'
import {BookmarksResponse} from "../../../services/models";
import {fetchBookmarks} from "../../../services/api";
import {useSearchParams} from "next/navigation";
import Bookmarks from "../../../components/Bookmarks";
import SearchForm from "../../../components/SearchForm";

interface HomeProps {
    bookmarks: BookmarksResponse
}
const Home = async () => {
    const resp = await getData();
    return (
        <div>
            <SearchForm/>
            <Bookmarks bookmarks={resp.bookmarks}/>
        </div>


    )
}

const getData = async () => {
    const searchParams = useSearchParams()
    let page  = searchParams.get('page');
    let query  = searchParams.get('query');
    console.log('PageNo in url: ' + page + 'query: ' + query)
    if(page <= 0) {
        page = 1;
    }
    if(!query){
        query = "";
    }
    const bookmarks = await fetchBookmarks(parseInt(String(page)), String(query));
    return {
        bookmarks

    }
}

export default Home;