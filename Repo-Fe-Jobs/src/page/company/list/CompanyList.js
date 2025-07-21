import {Title} from "../../../component/title/Title";
// import {useState, useEffect} from "react";
import {Pagination} from "../../../component/pagenation/Pagination";
// import {CardCompanySkeleton} from "../../../component/card/CardCompanySkeleton";
import {CardCompanyItem} from "../../../component/card/CardCompanyItem";

export function CompanyListPage() {
    return (
        <>
            <div className="py-[60px]">
                <div className="container">
                    <Title text="Danh sách công ty" />

                    {/* Wrap */}
                    <div className="grid lg:grid-cols-3 grid-cols-2 sm:gap-x-[20px] gap-x-[10px] gap-y-[20px]">
                        {/* Item */}
                        <CardCompanyItem />
                    </div>

                    {/* Phân trang */}
                    <Pagination />
                </div>
            </div>
        </>
    )
}