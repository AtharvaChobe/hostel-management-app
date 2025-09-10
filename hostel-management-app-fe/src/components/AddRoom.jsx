import axios from 'axios';
import React, { useState } from 'react'

const AddRoom = () => {
  const [formData, setformData] = useState({
    "roomNo": "",
    "capacity": ""
  });
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post(`${import.meta.env.VITE_BACKEND_URL}/api/room`, formData, {
        headers:{
          Authorization:`Bearer ${localStorage.getItem("token")}`
        }
      });
      // console.log(res.status);
      if (res.status !== 200) throw (res.error)
      alert("room added")
      setformData({
        "roomNo": "",
        "capacity": ""
      })
    } catch (error) {
      console.log(error);
      alert("room number already exists")
    }
  }
  const handleChange = (e) => setformData({ ...formData, [e.target.name]: e.target.value });

  return (
    <div className='flex items-center justify-center gap-5 flex-col w-full'>
      <h2 className='primary'>Add a room</h2>
      <form className='frm' action="">
        <input className='input-field' name='roomNo' value={formData.roomNo} onChange={handleChange} placeholder='Room Number' type="text" />
        <input className='input-field' name='capacity' value={formData.capacity} onChange={handleChange} placeholder='capacity' type="text" />
        <button className='btn' onClick={handleSubmit}>Submit</button>
      </form>
    </div>
  )
}

export default AddRoom