import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Home from "./pages/Home";
import Payments from "./pages/Payments";
import Hostelers from "./pages/Hostelers";
import DashboardLayout from "./components/Dashboard";
import Rooms from "./pages/Rooms";
import AddRoom from "./components/AddRoom";
import AddHosteler from "./pages/AddHosteler";
import Register from "./pages/Register";
import Login from "./pages/Login";
import {isTokenValid} from "./utils/IsValidToken";

export default function App() {
  const token = localStorage.getItem("token");
  const isValid = isTokenValid(token);
  // console.log(isValid)
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route
          path="/"
          element={isValid ? <DashboardLayout /> : <Navigate to="/login" replace />}
        >
          <Route index element={<Home />} />
          <Route path="home" element={<Home />} />
          <Route path="payments" element={<Payments />} />
          <Route path="hostelers" element={<Hostelers />} />
          <Route path="rooms" element={<Rooms />} />
          <Route path="addrooms" element={<AddRoom />} />
          <Route path="addHosteler" element={<AddHosteler />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}