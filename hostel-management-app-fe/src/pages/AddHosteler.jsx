import axios from 'axios';
import React, { useState } from 'react'

const AddHosteler = () => {
  const [formData, setformData] = useState({
    "name": "",
    "email": "",
    "contact": "",
    "roomNo": ""
  });
  const handleChange = (e) => {
    setformData({ ...formData, [e.target.name]: e.target.value });
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (!formData.contact || !formData.email || !formData.name || !formData.roomNo) throw ("All field are required");
      const res = await axios.post(`${import.meta.env.VITE_BACKEND_URL}/api/hostelers`, formData, {
        headers:{
          Authorization:`Bearer ${localStorage.getItem("token")}`
        }
      });
      // console.log(res);
      if (res.status != 200) throw (res.error);
      alert("Hosteler added");
      setformData({
        "name": "",
        "email": "",
        "contact": "",
        "roomNo": ""
      })
    } catch (error) {
      // console.log(error.response)
      if (error.response) {
        alert(error.response.data);
        console.error("Error:", error.response.data);
      } else {
        alert("Something went wrong!");
        console.error("Error:", error);
      }
    }
  }
  return (
    <div className='flex items-center justify-center'>
      <form action="" className='frm'>
        <input name='name' value={formData.name} onChange={handleChange} className='input-field' type="text" placeholder='Name' />
        <input name='email' value={formData.email} onChange={handleChange} className='input-field' type="email" placeholder='Email' />
        <input name='contact' value={formData.contact} onChange={handleChange} className='input-field' type="text" placeholder='Phone Number' />
        <input name='roomNo' value={formData.roomNo} onChange={handleChange} className='input-field' type="number" placeholder='Room Number' />
        <button className="btn" onClick={handleSubmit}>Submit</button>
      </form>
    </div>
  )
}

export default AddHosteler