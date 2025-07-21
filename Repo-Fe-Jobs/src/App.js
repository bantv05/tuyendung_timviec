import './App.css';
import {Route, Routes} from "react-router-dom";
import {path} from './utils/path';
import {CompanyLogin} from "./page/company/login/CompanyLogin";
import {UserLogin} from "./page/user/login/UserLogin";
import {Public} from "./Public";
// import {CompanyListPage} from "./page/company/list/CompanyList";
import SearchPage from "./page/search/SearchPage";
import {CompanyRegister} from "./page/company/register/CompanyRegister";
import {CvList} from "./page/company/cv/CvList";
import {CompanyDetail} from "./page/company/detail/CompanyDetail";
import {CompanyListPage} from "./page/company/list/CompanyList";
import {CompanyManageJobList} from "./page/company/job/list/CompanyManageJobList";
import NotFound from "./utils/NotFound";
import {UserRegister} from "./page/user/register/UserRegister";
import {ToastContainer} from "react-toastify";
import {CvDetail} from "./page/company/cv/CvDetail";
import {CompanyManageJobCreate} from "./page/company/job/create/CompanyManageJobCreate";
import {CompanyManageProfile} from "./page/company/profile/CompanyManageProfile";
import {UserManageCVListPage} from "./page/user/cv/list/UserManageCVList";
import {UserManageProfile} from "./page/user/profile/UserManageProfile";
import {JobDetailPage} from "./page/job/detail/[id]/JobDetail";
function App() {
  return (
    <div className="App">
      <Routes>
          <Route path={path.PUBLIC} element={<Public />}>
              <Route path={path.HOME} element={<SearchPage />} />
                  <Route path={path.COMPANY_LOGIN} element={<CompanyLogin />} />
                  <Route path={path.COMPANY_REGISTER} element={<CompanyRegister />} />
                  <Route path={path.COMPANY_LIST_CV} element={<CvList />} />
                  <Route path={path.COMPANY_DETAIL} element={<CompanyDetail />} />
                  <Route path={path.COMPANY} element={<CompanyManageJobList />} />
                  <Route path={path.PUBLIC_JOB_DETAIL} element={<JobDetailPage />} />
                  <Route path={path.COMPANY_LIST} element={<CompanyListPage />} />
                  <Route path={path.USER_LOGIN} element={<UserLogin/>} />
                  <Route path={path.USER_REGISTER} element={<UserRegister/>} />
                  <Route path={path.COMPANY_CV} element={<CvDetail/>} />
                  <Route path={path.COMPANY_CREATE_JOB} element={<CompanyManageJobCreate/>} />
                  <Route path={path.COMPANY_PROFILE} element={<CompanyManageProfile/>} />
                  <Route path={path.USER_CV_LIST_APPLY} element={<UserManageCVListPage/>} />
                  <Route path={path.USER_PROFILE} element={<UserManageProfile/>} />
                    <Route path='*' element={<NotFound/>}/>
          </Route>
      </Routes>
        <ToastContainer />
    </div>
  );
}

export default App;
