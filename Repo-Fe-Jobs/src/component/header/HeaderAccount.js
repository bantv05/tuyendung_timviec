import {Link} from "react-router-dom";
import {path} from "../../utils/path";
import {useSelector} from "react-redux";

export const HeaderAccount = () => {
  const user = useSelector(state => state.auth?.login?.currentUser);
  return (
    <>
      <div className="font-[600] sm:text-[16px] text-[12px] text-white inline-flex gap-x-[5px] relative group/sub-1">
        {/* Chưa đăng nhập */}
        {/* <Link to="#" className="">
          Đăng Nhập
        </Link>
        <span className="">/</span>
        <Link to="#" className="">
          Đăng Ký
        </Link> */}

        {/* Đã đăng nhập */}
        <Link to={path.USER_PROFILE} className="">
          {user?.infoUser?.username || "LG Elect..."}
        </Link>
        <ul className="bg-[#000065] lg:absolute relative lg:top-[100%] top-[0] right-0 lg:w-[200px] w-full rounded-[4px] hidden group-hover/sub-1:block">
          <li className="flex items-center justify-between py-[10px] px-[16px] rounded-[4px] hover:bg-[#000096] group/sub-2 flex-wrap">
            <Link to={path.USER_LOGIN} className="font-[600] text-[16px] text-white">
              Đăng nhập (ứng viên)
            </Link>
          </li>
          <li className="flex items-center justify-between py-[10px] px-[16px] rounded-[4px] hover:bg-[#000096] group/sub-2 flex-wrap">
            <Link to={path.COMPANY_DETAIL} className="font-[600] text-[16px] text-white">
              Thông tin công ty
            </Link>
          </li>
          <li className="flex items-center justify-between py-[10px] px-[16px] rounded-[4px] hover:bg-[#000096] group/sub-2 flex-wrap">
            <Link to={path.COMPANY} className="font-[600] text-[16px] text-white">
              Quản lý công việc
            </Link>
          </li>
          <li className="flex items-center justify-between py-[10px] px-[16px] rounded-[4px] hover:bg-[#000096] group/sub-2 flex-wrap">
            <Link to={path.COMPANY_LIST_CV} className="font-[600] text-[16px] text-white">
              Quản lý CV
            </Link>
          </li>
          <li className="flex items-center justify-between py-[10px] px-[16px] rounded-[4px] hover:bg-[#000096] group/sub-2 flex-wrap">
            <Link to="#" className="font-[600] text-[16px] text-white">
              Đăng xuất
            </Link>
          </li>
        </ul>
      </div>
    </>
  )
}