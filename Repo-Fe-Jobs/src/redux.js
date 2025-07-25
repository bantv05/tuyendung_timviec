import rootReducer from './store/reducers/rootReducer'
import {applyMiddleware, createStore} from "redux";
import {thunk} from "redux-thunk";
import {configureStore} from "@reduxjs/toolkit";

export const reduxConfig = () => {
    const store = createStore(rootReducer, applyMiddleware(thunk));
  return store;
}
// export default reduxConfig;