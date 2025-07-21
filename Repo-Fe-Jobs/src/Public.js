import {Outlet} from "react-router-dom"
import {Footer} from "./component/footer/Footer";
import {Header} from "./component/header/Header";
export const Public = () => {
    return (
        <>
            <Header/>
            <Outlet />
            <Footer/>
        </>
    )
}