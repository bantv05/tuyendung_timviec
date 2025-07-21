import {Link, useNavigate} from "react-router-dom";
import {path} from "../../../utils/path";
import {useState} from "react";
import {loginCompany} from "../../../redux/apiRequest";
import {useDispatch} from "react-redux";

export const CompanyLogin = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handlerLoginCompany = (e) => {
        e.preventDefault();
        const newUser = {
            email: email,
            password: password,
        }
        loginCompany(newUser, dispatch, navigate);
    }
    return (
        <>
            <div className="py-[60px]">
                <div className="container">
                    <div className="rounded-[8px] border border-[#DEDEDE] py-[50px] px-[20px] max-w-[602px] mx-auto">
                        <h1 className="font-[700] text-[20px] text-black mb-[20px] text-center">
                            Đăng nhập (Nhà tuyển dụng)
                        </h1>
                        <form onSubmit={handlerLoginCompany} action="" className="grid grid-cols-1 gap-x-[20px] gap-y-[15px]">
                            <div className="">
                                <label htmlFor="email" className="font-[500] text-[14px] text-black mb-[5px]">
                                    Email *
                                </label>
                                <input
                                    onChange={e => setEmail(e.target.value)}
                                    type="email"
                                    name=""
                                    id="email"
                                    className="w-full h-[46px] rounded-[4px] border border-[#DEDEDE] font-[500] text-[14px] text-black px-[20px]"
                                />
                            </div>
                            <div className="">
                                <label htmlFor="password" className="font-[500] text-[14px] text-black mb-[5px]">
                                    Mật khẩu *
                                </label>
                                <input
                                    onChange={event => setPassword(event.target.value)}
                                    type="password"
                                    name=""
                                    id="password"
                                    className="w-full h-[46px] rounded-[4px] border border-[#DEDEDE] font-[500] text-[14px] text-black px-[20px]"
                                />
                            </div>
                            <div className="flex flex-wrap gap-[15px]">
                                <button
                                    className="w-full h-[48px] bg-[#0088FF] rounded-[4px] font-[700] text-[16px] text-white cursor-pointer">
                                    Đăng nhập
                                </button>
                                <Link to={`/${path.COMPANY_REGISTER}`}
                                    className="font-[500] text-[14px] text-black px-[20px] hover:text-[#0088FF] cursor-pointer">
                                    Đăng ký tuyển dụng
                                </Link>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}