import { IoIosSearch } from "react-icons/io";
import { Link } from "react-router-dom";

export const Section1 = ({ data, searchTerm, setSearchTerm, selectedCity, setSelectedCity, handleSearch }) => {
  return (
      <>
        <div className="bg-[#000065] py-[60px]">
          <div className="container">
            <h1 className="font-[700] text-[28px] text-white mb-[30px] text-center">
              {data?.length || 0} Việc làm IT cho Developer &quot;Chất&quot;
            </h1>
            <form onSubmit={handleSearch} className="flex gap-x-[15px] gap-y-[12px] md:flex-nowrap flex-wrap mb-[30px]">
              <select
                  value={selectedCity}
                  onChange={(e) => setSelectedCity(e.target.value)}
                  className="md:w-[240px] w-full h-[56px] rounded-[4px] bg-white font-[500] text-[16px] text-[#121212] px-[20px]"
              >
                <option value="">Tất cả thành phố</option>
                <option value="Hà Nội">Hà Nội</option>
                <option value="Đà Nẵng">Đà Nẵng</option>
                <option value="Hồ Chí Minh">Hồ Chí Minh</option>
              </select>
              <input
                  type="text"
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  className="md:flex-1 w-full h-[56px] rounded-[4px] bg-white font-[500] text-[16px] text-[#121212] px-[20px]"
                  placeholder="Nhập từ khoá..."
              />
              <button type="submit" className="md:w-[240px] w-full h-[56px] rounded-[4px] bg-[#0088FF] font-[500] text-[16px] text-white px-[20px] flex items-center justify-center gap-x-[10px] cursor-pointer">
                <IoIosSearch className="text-[26px]" /> Tìm Kiếm
              </button>
            </form>
            <div className="flex flex-wrap items-center gap-x-[12px] gap-y-[15px]">
              <div className="font-[500] text-[16px] text-[#DEDEDE]">
                Mọi người đang tìm kiếm:
              </div>
              <div className="flex flex-wrap gap-[10px]">
                <Link to={"#"} className="border-[#414042] border-[1px] bg-[#121212] hover:bg-[#414042] rounded-[20px] px-[22px] py-[8px] font-[500] text-[16px] text-[#DEDEDE] hover:text-white">
                  ReactJS
                </Link>
                <Link to={"#"} className="border-[#414042] border-[1px] bg-[#121212] hover:bg-[#414042] rounded-[20px] px-[22px] py-[8px] font-[500] text-[16px] text-[#DEDEDE] hover:text-white">
                  Javascript
                </Link>
                <Link to={"#"} className="border-[#414042] border-[1px] bg-[#121212] hover:bg-[#414042] rounded-[20px] px-[22px] py-[8px] font-[500] text-[16px] text-[#DEDEDE] hover:text-white">
                  NodeJS
                </Link>
              </div>
            </div>
          </div>
        </div>
      </>
  );
}
