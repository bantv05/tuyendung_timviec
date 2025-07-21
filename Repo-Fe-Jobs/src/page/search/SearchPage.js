import { Pagination } from "../../component/pagenation/Pagination";
import { CardJobItem } from "../../component/card/CardJobItem";
import { Section1 } from "../../component/section/Section1";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { allJobs } from "../../redux/apiRequest";
import axios from "axios";
import {searchJobsSuccess} from "../../redux/jobsSlice";

export default function SearchPage() {
  const user = useSelector(state => state.auth?.login?.currentUser);
  const jobs = useSelector(state => state.jobs?.jobs?.allJobs?.content || []);
  const dispatch = useDispatch();

  const [searchTerm, setSearchTerm] = useState("");
  const [selectedCity, setSelectedCity] = useState("");

  useEffect(() => {
    if (user?.token) {
      allJobs(user.token, dispatch);
    }
  }, [user, dispatch]);

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!user?.token) return;

    try {
      const response = await axios.post(
          "http://localhost:8080/api/v1/job-offers/search",
          {
            jobTitle: searchTerm,
            cities: selectedCity ? [selectedCity] : [],
          },
          {
            headers: {
              Authorization: `Bearer ${user.token}`,
            },
          }
      );
      dispatch(searchJobsSuccess(response.data));
    } catch (error) {
      console.error("Error fetching jobs:", error);
    }
  };

  return (
      <>
        <Section1
            data={jobs}
            searchTerm={searchTerm}
            setSearchTerm={setSearchTerm}
            selectedCity={selectedCity}
            setSelectedCity={setSelectedCity}
            handleSearch={handleSearch}
        />

        <div className="py-[60px]">
          <div className="container">
            <h2 className="font-[700] text-[28px] text-[#121212] mb-[30px]">
              {jobs.length} việc làm <span className="text-[#0088FF]">{searchTerm}</span>
            </h2>

            <div className="grid lg:grid-cols-3 sm:grid-cols-2 grid-cols-1 sm:gap-x-[20px] gap-x-[10px] gap-y-[20px]">
              {jobs.map((job) => (
                  <CardJobItem key={job.idJobOffer} data={job} />
              ))}
            </div>

            <Pagination />
          </div>
        </div>
      </>
  );
}
