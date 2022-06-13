import React from 'react';
import './App.css';
import {LoginPage, AdminPage, UserPage} from "../../pages";
import {Navigate, Route, Routes} from "react-router-dom";

function App() {
    return (
        <Routes>
            <Route path='/'  element={<LoginPage />}/>
            <Route path='/user' element={<UserPage />}/>
            <Route path='/admin' element={<AdminPage />}/>
            <Route path='*' element={<Navigate to={'/'} />} />
        </Routes>
    );
}

export default App;