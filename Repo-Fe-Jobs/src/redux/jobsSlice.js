import { createSlice } from "@reduxjs/toolkit";

export const jobsSlice = createSlice({
    name: "jobs",
    initialState: {
        jobs: {
            allJobs: null,
            isFetching: false,
            error: false,
        },
        jobDetail: {
            detail: null,
            isFetching: false,
            error: false,
        },
    },
    reducers: {
        jobsStart: (state) => {
            state.jobs.isFetching = true;
        },
        jobsSuccess: (state, action) => {
            state.jobs.isFetching = false;
            state.jobs.allJobs = action.payload;
            state.jobs.error = false;
        },
        jobsFailed: (state) => {
            state.jobs.isFetching = false;
            state.jobs.error = true;
        },
        jobDetailStart: (state) => {
            state.jobDetail.isFetching = true;
        },
        jobDetailSuccess: (state, action) => {
            state.jobDetail.detail = action.payload;
            state.jobDetail.isFetching = false;
        },
        jobDetailFailed: (state) => {
            state.jobDetail.isFetching = false;
            state.jobDetail.error = true;
        },
        searchJobsSuccess: (state, action) => {
            state.jobs.allJobs = {
                content: action.payload,
            };
            state.jobs.isFetching = false;
            state.jobs.error = false;
        },
    },
});

export const {
    jobsStart,
    jobsSuccess,
    jobsFailed,
    jobDetailStart,
    jobDetailSuccess,
    jobDetailFailed,
    searchJobsSuccess,
} = jobsSlice.actions;

export default jobsSlice.reducer;
