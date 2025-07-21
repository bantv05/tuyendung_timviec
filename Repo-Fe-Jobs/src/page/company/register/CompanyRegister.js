import {path} from "../../../utils/path";
import {Link, useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import {useState} from "react";
import {registerCompany} from "../../../redux/apiRequest";

export const CompanyRegister = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [userName, setUsername] = useState("");
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handlerCompanyRegister = (e) => {
      e.preventDefault();
        const newUser = {
            userName: userName,
            email: email,
            password: password,
        }
        registerCompany(newUser, dispatch, navigate);
    }

    return (
        <>
            <div className="py-[60px]">
                <div className="container">
                    <div className="rounded-[8px] border border-[#DEDEDE] py-[50px] px-[20px] max-w-[602px] mx-auto">
                        <h1 className="font-[700] text-[20px] text-black mb-[20px] text-center">
                            Đăng ký (Nhà tuyển dụng)
                        </h1>
                        <form onSubmit={handlerCompanyRegister} action="" className="grid grid-cols-1 gap-x-[20px] gap-y-[15px]">
                            <div className="">
                                <label htmlFor="companyName" className="font-[500] text-[14px] text-black mb-[5px]">
                                    Tên công ty *
                                </label>
                                <input
                                    onChange={(e) => setUsername(e.target.value)}
                                    type="text"
                                    name=""
                                    id="companyName"
                                    className="w-full h-[46px] rounded-[4px] border border-[#DEDEDE] font-[500] text-[14px] text-black px-[20px]"
                                />
                            </div>
                            <div className="">
                                <label htmlFor="email" className="font-[500] text-[14px] text-black mb-[5px]">
                                    Email *
                                </label>
                                <input
                                    onChange={event => setEmail(event.target.value)}
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
                                <button className="w-full h-[48px] bg-[#0088FF] rounded-[4px] font-[700] text-[16px] text-white cursor-pointer">
                                    Đăng ký
                                </button>
                                <Link to={`/`}
                                      className="font-[500] text-[14px] text-black px-[20px] hover:text-[#0088FF] hover:opacity-100 cursor-pointer opacity-50">
                                    Quay lại
                                </Link>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}