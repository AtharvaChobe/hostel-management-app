import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();
    const [form, setForm] = useState({ email: "", password: "" });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await axios.post(`${import.meta.env.VITE_BACKEND_URL}/api/auth/login`, form);
            // console.log(res)
            localStorage.setItem("token", res.data.token);
            localStorage.setItem("name", res.data.name);
            alert("Login successful!");
            navigate("/home");
            window.location.reload();
        } catch (err) {
            alert(err);
        }
    };

    return (
        <div className="flex flex-col gap-4 w-1/3 mx-auto mt-10">
            <h1 className="title">Login</h1>
            <input
                type="email"
                name="email"
                value={form.email}
                onChange={handleChange}
                placeholder="Email"
                className="border p-2 rounded"
            />
            <input
                type="password"
                name="password"
                value={form.password}
                onChange={handleChange}
                placeholder="Password"
                className="border p-2 rounded"
            />
            <button className="btn" onClick={handleSubmit}>Login</button>
        </div>
    );
};

export default Login;
