import axios from "axios";
import {loginFailed, loginStart, loginSuccess, registerFailed, registerStart, registerSuccess} from "./authSlice";
import {toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {path} from "../utils/path";
import {jobDetailFailed, jobDetailStart, jobDetailSuccess, jobsFailed, jobsStart, jobsSuccess} from "./jobsSlice";

export const loginUser = async (user, dispatch, navigate) => {
  dispatch(loginStart());
  try {
    const res = await axios.post("http://localhost:8080/api/v1/login/user", user);
    if (res.data?.token) {
      dispatch(loginSuccess(res.data));
      navigate("/");
      toast.success('Đăng nhập thành công!')
    } else {
      dispatch(loginFailed());
      toast.error("Email hoặc password đã tồn tại!");
    }
  } catch (error) {
    dispatch(loginFailed());
    toast.error("Email hoặc password đã tồn tại!");
  }
};

export const registerUser = async (user, dispatch, navigate) => {
  dispatch(registerStart());
  try {
    await axios.post("http://localhost:8080/api/v1/user/register", user
    );
    dispatch(registerSuccess());
    toast.success("Đăng ký thành công!");
    navigate(`/${path.USER_LOGIN}`);
  } catch (error) {
    dispatch(registerFailed());
    toast.error("Email hoặc mật khẩu không hợp lệ!");
  }
};


export const loginCompany = async (user, dispatch, navigate) => {
  dispatch(loginStart());
  try {
    const res = await axios.post(process.env.REACT_APP_POST_COMPANY_LOGIN, user);
    if (res.data?.token) {
      dispatch(loginSuccess(res.data));
      navigate("/");
      toast.success('Đăng nhập thành công!')
    } else {
      dispatch(loginFailed());
      toast.error("Email hoặc password không hợp lệ!");
    }
  } catch (error) {
    dispatch(loginFailed());
    toast.error("Email hoặc password không hợp lệ!");
  }
};

export const registerCompany = async (user, dispatch, navigate) => {
  dispatch(registerStart());
  try {
    // const res = await axios.post(`${process.env.REACT_APP_POST_USER_REGISTER}`, user);
    await axios.post(process.env.REACT_APP_POST_COMPANY_REGISTER, user);
    toast.success('Đăng ký thành công!')
    dispatch(registerSuccess());
    navigate(`/${path.COMPANY_LOGIN}`);
  } catch (error) {
    dispatch(registerFailed());
    toast.error("Email hoặc password đã tồn tại!");
  }
};

export const allJobs = async (accessToken, dispatch) => {
  dispatch(jobsStart());

  try {
    const res = await axios.get("http://localhost:8080/api/v1/public/job-offer", {
      headers: {
        Authorization: `Bearer ${accessToken}`, // nếu BE cần token
      },
    });

    dispatch(jobsSuccess(res.data));
    console.log("All jobs fetched:", res.data);

  } catch (e) {
    dispatch(jobsFailed());

    if (e.response) {
      console.error("Lỗi từ server:", e.response.data);
      toast.error("Không thể lấy danh sách công việc.");
    } else if (e.request) {
      console.error("Không nhận được phản hồi từ server.");
      toast.error("Không thể kết nối tới máy chủ.");
    } else {
      console.error("Lỗi không xác định:", e.message);
      toast.error("Đã xảy ra lỗi không xác định.");
    }
  }
};

export const jobsDetail = async (accessToken, dispatch, id) => {
  dispatch(jobDetailStart());
  try {
    const res = await axios.get(`http://localhost:8080/api/v1/public/job-offer/${id}`, {
      headers: { Authorization: `Bearer ${accessToken}` },
    });
    dispatch(jobDetailSuccess(res.data));
  } catch (e) {
    dispatch(jobDetailFailed());
    toast.error("Không thể lấy chi tiết công việc.");
  }
};

