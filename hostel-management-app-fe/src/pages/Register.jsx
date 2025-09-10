import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Register = () => {
    const [form, setForm] = useState({
        ownerName: "",
        hostelName: "",
        address: "",
        email: "",
        password: ""
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async () => {
        try {
            await axios.post(`${import.meta.env.VITE_BACKEND_URL}/api/auth/register`, form);
            alert("Registered successfully!");
            navigate("/login")
        } catch (err) {
            alert(err);
        }
    };

    return (
        <div className="flex flex-col gap-4 w-1/3 mx-auto mt-10">
            <h1 className="title">Register</h1>
            {Object.keys(form).map((key) => (
                <input
                    key={key}
                    type={key === "password" ? "password" : "text"}
                    name={key}
                    value={form[key]}
                    onChange={handleChange}
                    placeholder={key}
                    className="border p-2 rounded"
                />
            ))}
            <button className="btn" onClick={handleSubmit}>Register</button>
        </div>
    );
};

export default Register;