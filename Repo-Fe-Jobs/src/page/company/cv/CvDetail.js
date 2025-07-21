import { Link } from "react-router-dom";
import {path} from "../../../utils/path";
export const CvDetail = () => {
    return (
        <>
            <div className="py-[60px]">
                <div className="container">
                    {/* Thông tin CV */}
                    <div className="border border-[#DEDEDE] rounded-[8px] p-[20px]">
                        <div className="flex flex-wr ap gap-[20px] items-center justify-between mb-[20px]">
                            <div className="font-[700] text-[20px]">
                                Thông tin CV
                            </div>
                            <Link to={`/${path.COMPANY_LIST_CV}`} className="font-[400] text-[14px] text-[#0088FF] underline" >
                                Quay lại danh sách
                            </Link>
                        </div>
                        <div className="font-[400] text-[16px] mb-[10px]">
                            Họ tên: <span className="font-[700]">Lê Văn A</span>
                        </div>
                        <div className="font-[400] text-[16px] mb-[10px]">
                            Email: <span className="font-[700]">levana@gmail.com</span>
                        </div>
                        <div className="font-[400] text-[16px] mb-[10px]">
                            Số điện thoại: <span className="font-[700]">0123456789</span>
                        </div>
                        <div className="font-[400] text-[16px] mb-[10px]">
                            File CV:
                        </div>
                        <div className="bg-[#D9D9D9] h-[736px]">
                            <iframe
                                src="https://res.cloudinary.com/dxhwpfxxn/image/upload/v1750249597/bdwguoqukqebm2mokjzk.pdf"
                                className="w-full h-full"
                            ></iframe>
                        </div>
                    </div>
                    {/* Hết Thông tin CV */}

                    {/* Thông tin công việc */}
                    <div className="border border-[#DEDEDE] rounded-[8px] p-[20px] mt-[20px]">
                        <div className="font-[700] text-[20px] mb-[20px]">
                            Thông tin công việc
                        </div>
                        <div className="font-[400] text-[16px] mb-[10px]">
                            Tên công việc: <span className="font-[700]">Frontend Engineer (ReactJS)</span>
                        </div>
                        <div className="font-[400] text-[16px] mb-[10px]">
                            Mức lương: <span className="font-[700]">1.000$ - 1.500$</span>
                        </div>
                        <div className="font-[400] text-[16px] mb-[10px]">
                            Cấp bậc: <span className="font-[700]">Fresher</span>
                        </div>
                        <div className="font-[400] text-[16px] mb-[10px]">
                            Hình thức làm việc: <span className="font-[700]">Tại văn phòng</span>
                        </div>
                        <div className="font-[400] text-[16px] mb-[10px]">
                            Công nghệ: <span className="font-[700]">HTML5, CSS3, Javascript, ReactJS</span>
                        </div>
                        <Link href="#" className="font-[400] text-[14px] text-[#0088FF] underline">
                            Xem chi tiết công việc
                        </Link>
                    </div>
                    {/* Hết Thông tin công việc */}
                </div>
            </div>
        </>
    )
}