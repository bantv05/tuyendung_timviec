import {combineReducers, configureStore} from '@reduxjs/toolkit';
import authReducer from './authSlice'; // Import reducer thay vì authSlice (slice bao gồm cả actions và reducer)
import jobsReducer from './jobsSlice';
import { persistStore, persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";

const persistConfig = {
    key: "root",
    storage,
};
const userReducer = combineReducers({
    auth: authReducer,
    jobs: jobsReducer
})
const persistedReducer = persistReducer(persistConfig, userReducer);

export const store = configureStore({
    auth: authReducer,
    reducer: persistedReducer,
    devTools: process.env.NODE_ENV !== "production",
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware({
            serializableCheck: {
                ignoredActions: ["persist/PERSIST", "persist/REHYDRATE"],
            },
        }),
});

export const persistor = persistStore(store);

