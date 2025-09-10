import React from 'react'
import { Link } from 'react-router-dom'
const Sidebar = () => {
  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.reload();
  }
  return (
    <div className='bg-primary h-[100vh] w-1/4 flex flex-col justify-between gap-9'>
      <div className='flex flex-col gap-3'>
        <h1 className='title mb-4'>{localStorage.getItem("name")}</h1>
        <Link className='hover:font-bold' to={"/home"} >Home</Link>
        <Link className='hover:font-bold' to={"/payments"} >Payments</Link>
        <Link className='hover:font-bold' to={"/hostelers"} >Hostelers</Link>
        <Link className='hover:font-bold' to={"/rooms"} >Rooms</Link>
      </div>
      <div>
        <button className={`${!localStorage.getItem("token") ? "hidden" : "btn"}`} onClick={handleLogout}>Logout</button>
      </div>
    </div>
  )
}

export default Sidebar