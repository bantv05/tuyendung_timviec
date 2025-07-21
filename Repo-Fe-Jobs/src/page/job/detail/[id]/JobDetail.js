import { Link, useParams } from "react-router-dom";
import { FaArrowRightLong, FaBriefcase, FaLocationDot, FaUser  } from "react-icons/fa6";
import {useEffect, useState} from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import {toast} from "react-toastify";

export const JobDetailPage = () => {
  const user = useSelector(state => state.auth?.login?.currentUser );
  const [job, setJob] = useState([]);
  const { id } = useParams();
  useEffect(() => {
    const fetchJobDetails = async () => {
      try {
        const res = await axios.get(`http://localhost:8080/api/v1/public/job-offer/${id}`, {
          headers: { Authorization: `Bearer ${user.token}` },
        });
        setJob(res.data);
      } catch (e) {
        toast.error("Không thể lấy chi tiết công việc.");
      }
    };
    fetchJobDetails();
  }, [id, user.token]);
  console.log(job);
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [cv, setCv] = useState(null);
  // const [data, setData] = useState({});
  const handlerApply = async (e) => {
    e.preventDefault();

    // Validate required fields
    if (!userName || !email || !phone || !cv) {
      toast.error("Please fill all required fields");
      return;
    }

    const formData = new FormData();
    formData.append('application', JSON.stringify({
      fullName: userName,
      email: email,
      phone: phone
    }));
    formData.append('cvFile', cv);

    try {
      const response = await axios.post(
          `http://localhost:8080/api/v1/apply/${id}/${user.id}`,
          formData,
          {
            headers: {
              'Content-Type': 'multipart/form-data',
              'Authorization': `Bearer ${user.token}`
            }
          }
      );

      toast.success("Application submitted successfully!");
    } catch (error) {
      console.error("Submission error:", {
        status: error.response?.status,
        data: error.response?.data,
        config: error.config
      });

      if (error.response?.status === 403) {
        toast.error("Access denied. Please check your permissions.");
      } else {
        toast.error("Failed to submit application. Please try again.");
      }
    }
  };


  return (
      <>
        {/* Job Details */}
        <div className="pt-[30px] pb-[60px]">
          <div className="container">
            {/* Wrap */}
            <div className="flex flex-wrap gap-[20px]">
              {/* Left */}
              <div className="lg:w-[65%] w-full">
                {/* Job Information */}
                <div className="rounded-[8px] border border-[#DEDEDE] p-[20px]">
                  <h1 className="font-[700] sm:text-[28px] text-[24px] text-[#121212] mb-[10px]">
                    {job?.jobTitle}
                  </h1>
                  <div className="font-[400] text-[16px] text-[#414042] mb-[10px]">
                    {job?.companyInformation?.companyName}
                  </div>
                  <div className="font-[700] text-[20px] text-[#0088FF] sm:mb-[20px] mb-[10px]">
                    {job?.minSalary}$ - {job?.maxSalary}$
                  </div>
                  <Link to="#" className="bg-[#0088FF] rounded-[4px] p-[14px] font-[700] text-[16px] text-white mb-[20px] block text-center">
                    Ứng tuyển
                  </Link>
                  <div className="grid grid-cols-3 sm:gap-[16px] gap-[8px] mb-[20px]">
                    <img
                        src={`${job?.companyInformation?.logo}`}
                        alt=""
                        className="w-full h-full object-cover"
                    />
                  </div>
                  <div className="flex items-center gap-[8px] mb-[10px] text-[14px]">
                    <FaUser  className="text-[16px]" /> {job?.level?.levelName}
                  </div>
                  <div className="flex items-center gap-[8px] mb-[10px] text-[14px]">
                    <FaBriefcase className="text-[16px]" /> {job?.workType?.workTypeName}
                  </div>
                  <div className="flex items-center gap-[8px] mb-[10px] text-[14px]">
                    <FaLocationDot className="text-[16px]" /> {job?.companyInformation?.city}
                  </div>
                  <div className="flex flex-wrap gap-[8px]">
                    {job?.technologies?.split(',').map((tech, index) => (
                        <div key={index} className="border border-[#DEDEDE] rounded-[20px] py-[6px] px-[16px] font-[400] text-[12px] text-[#414042]">
                          {tech.trim()}
                        </div>
                    ))}
                  </div>
                </div>
                {/* End Job Information */}

                {/* Detailed Description */}
                <div className="rounded-[8px] border border-[#DEDEDE] p-[20px] mt-[20px]">
                  {job?.description}
                </div>
                {/* End Detailed Description */}

                {/* Application Form */}
                <div className="rounded-[8px] border border-[#DEDEDE] p-[20px] mt-[20px]">
                  <h2 className="font-[700] text-[20px] text-black mb-[20px]">
                    Ứng tuyển ngay
                  </h2>
                  <form onSubmit={handlerApply} action="" className="flex flex-col gap-[15px]">
                    <div className="">
                      <label htmlFor="fullName" className="font-[500] text-[14px] text-black mb-[5px]">
                        Họ tên *
                      </label>
                      <input
                          onChange={event => setUserName(event.target.value)}
                          type="text"
                          id="fullName"
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
                          id="email"
                          className="w-full h-[46px] rounded-[4px] border border-[#DEDEDE] font-[500] text-[14px] text-black px-[20px]"
                      />
                    </div>
                    <div className="">
                      <label htmlFor="phone" className="font-[500] text-[14px] text-black mb-[5px]">
                        Số điện thoại *
                      </label>
                      <input
                          onChange={event => setPhone(event.target.value)}
                          type="text"
                          id="phone"
                          className="w-full h-[46px] rounded-[4px] border border-[#DEDEDE] font-[500] text-[14px] text-black px-[20px]"
                      />
                    </div>
                    <div className="">
                      <label htmlFor="fileCV" className="font-[500] text-[14px] text-black mb-[5px]">
                        File CV dạng PDF *
                      </label>
                      <input
                          onChange={event => setCv(event.target.value)}
                          type="file"
                          id="fileCV"
                          className="w-full h-[46px] rounded-[4px] border border-[#DEDEDE] font-[500] text-[14px] text-black px-[20px] file:py-[12px]"
                      />
                    </div>
                    <button className="w-full h-[48px] bg-[#0088FF] rounded-[4px] font-[700] text-[16px] text-white cursor-pointer">
                      Gửi CV ứng tuyển
                    </button>
                  </form>
                </div>
                {/* End Application Form */}
              </div>
              {/* Right */}
              <div className="flex-1">
                {/* Company Information */}
                <div className="border border-[#DEDEDE] rounded-[8px] p-[20px]">
                  <div className="flex gap-[12px]">
                    <div className="w-[100px] aspect-square rounded-[4px] truncate">
                      <img
                          src={`${job?.companyInformation?.logo}`}
                          alt=""
                          className="w-full h-full object-cover"
                      />
                    </div>
                    <div className="flex-1">
                      <div className="font-[700] text-[18px] text-[#121212] mb-[10px]">
                        {job?.companyInformation?.companyName}
                      </div>
                      <Link to="#" className="flex items-center gap-[8px] font-[400] text-[16px] text-[#0088FF]">
                        Xem công ty <FaArrowRightLong className="text-[16px]" />
                      </Link>
                    </div>
                  </div>
                  <div className="mt-[20px] flex flex-col gap-[10px]">
                    <div className="flex items-center justify-between gap-[10px]">
                      <div className="font-[400] text-[16px] text-[#A6A6A6]">
                        Mô hình công ty
                      </div>
                      <div className="font-[400] text-[16px] text-[#121212] text-right">
                        {job?.companyInformation?.companyModel}
                      </div>
                    </div>
                    <div className="flex items-center justify-between gap-[10px]">
                      <div className="font-[400] text-[16px] text-[#A6A6A6]">
                        Quy mô công ty
                      </div>
                      <div className="font-[400] text-[16px] text-[#121212] text-right">
                        {job?.companyInformation?.companySize}
                      </div>
                    </div>
                    <div className="flex items-center justify-between gap-[10px]">
                      <div className="font-[400] text-[16px] text-[#A6A6A6]">
                        Thời gian làm việc
                      </div>
                      <div className="font-[400] text-[16px] text-[#121212] text-right">
                        {job?.companyInformation?.workingHours}
                      </div>
                    </div>
                    <div className="flex items-center justify-between gap-[10px]">
                      <div className="font-[400] text-[16px] text-[#A6A6A6]">
                        Làm việc ngoài giờ
                      </div>
                      <div className="font-[400] text-[16px] text-[#121212] text-right">
                        {job?.companyInformation?.overtimePolicy}
                      </div>
                    </div>
                  </div>
                </div>
                {/* End Company Information */}
              </div>
            </div>
          </div>
        </div>
        {/* End Job Details */}
      </>
  );
};
