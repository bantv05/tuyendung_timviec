import { FaBars } from "react-icons/fa6";
import { HeaderMenu } from "./HeaderMenu";
import { useState } from "react";
import { HeaderAccount } from "./HeaderAccount";
import {Link} from "react-router-dom";

export const Header = () => {
  const [showMenu, setShowMenu] = useState(false);

  const handleShowMenu = () => {
    setShowMenu(!showMenu);
  }

  return (
    <>
      <header className="bg-[#000071] py-[15px]">
        <div className="container">
          <div className="flex justify-between items-center">
            {/* Logo */}
            <Link to="/" className="font-[800] sm:text-[28px] text-[20px] text-white lg:flex-none flex-1">
              SDC ITJobs
            </Link>
            {/* Menu */}
            <HeaderMenu showMenu={showMenu} />
            {/* Account */}
            <HeaderAccount />
            {/* Button Menu Mobile */}
            <button className="lg:hidden ml-[12px]" onClick={handleShowMenu}>
              <FaBars className="text-[20px] text-white" />
            </button>
          </div>
        </div>
      </header>
    </>
  )
}