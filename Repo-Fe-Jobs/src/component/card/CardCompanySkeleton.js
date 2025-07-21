import Skeleton from 'react-loading-skeleton'
import 'react-loading-skeleton/dist/skeleton.css'

export const CardCompanySkeleton = () => {
    return (
        <div className="rounded-[8px] border border-[#DEDEDE] p-4">
            <Skeleton height={190} className="mb-4"/>
            <div className="flex align-center justify-center px-15px">
                <Skeleton count={1} width={100} className="mb-2"/>
            </div>
            <div className="flex justify-between">
                <Skeleton width={80}/>
                <Skeleton width={100}/>
            </div>
        </div>
    )
}
