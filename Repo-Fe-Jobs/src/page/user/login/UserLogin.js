import {Link, useNavigate} from "react-router-dom";
import {path} from "../../../utils/path";
import {useState} from "react";
import {loginUser} from "../../../redux/apiRequest";
import {useDispatch} from "react-redux";

export const UserLogin = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const dispatch = useDispatch();
    const navigate = useNavigate();
    function handleLogin(e) {
        e.preventDefault();
        const newUser = {
            email: username,
            password: password,
        }
        loginUser(newUser, dispatch, navigate)
        console.log(newUser);
    }

    return (
        <>
            <div className="py-[60px]">
                <div className="container">
                    <div className="rounded-[8px] border border-[#DEDEDE] py-[50px] px-[20px] max-w-[602px] mx-auto">
                        <h1 className="font-[700] text-[20px] text-black mb-[20px] text-center">
                            Đăng nhập (Ứng viên)
                        </h1>
                        <form onSubmit={handleLogin} action="" className="grid grid-cols-1 gap-x-[20px] gap-y-[15px]">
                            <div className="">
                                <label htmlFor="email" className="font-[500] text-[14px] text-black mb-[5px]">
                                    Email *
                                </label>
                                <input
                                    onChange={e => setUsername(e.target.value)}
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
                                    onChange={e => setPassword(e.target.value)}
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
                                <Link to={`/${path.USER_REGISTER}`} className="font-[500] text-[14px] text-black px-[20px] hover:text-[#0088FF] cursor-pointer">
                                    Đăng ký ứng viên
                                </Link>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}