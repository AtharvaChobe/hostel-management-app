import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

const Hostelers = () => {
  const [List, setList] = useState([]);
  const [searchParam, setsearchParam] = useState("");
  const [filteredList, setFilteredList] = useState(List);
  useEffect(() => {
    const fetchAllHosteler = async () => {
      try {
        const res = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/api/hostelers`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
          }
        });
        if (!res.data) throw ("failed to load data")
        // console.log(res.data);
        setList(res.data);
      } catch (error) {
        alert(error);
      }
    }
    fetchAllHosteler();
  }, [])

  const handleDelete = async (id) => {
    const confirmed = confirm("confirm to delete");
    if (!confirmed) return;
    try {
      const res = await axios.delete(`${import.meta.env.VITE_BACKEND_URL}/api/hostelers/${id}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      });
      // console.log(res);
      if (res.status != 200) throw ("Failed to delete");
      setList(pre => pre.filter((h) => h.id != id));
      alert("Deleted Sucessfully")
    } catch (error) {
      alert(error)
    }
  }

  useEffect(() => {
    if (searchParam) {
      setFilteredList(List.filter(h => h.name.toLowerCase().includes(searchParam.toLowerCase())));
    } else {
      setFilteredList(List);
    }
  }, [searchParam, List])

  const handleExportData = async () => {
    try {
      const res = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/api/hostelers/export`, {
        responseType: "blob",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      })
      if (!res) throw new Error("no response from server");
      const blob = new Blob([res.data], {
        type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
      });
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", "hostelers.xlsx");
      document.body.appendChild(link);
      link.click();
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <div className='flex flex-col justify-center md:text-lg text-xs items-center gap-3'>
      <div className='flex gap-4 items-center justify-between w-full'>
        <input className='border px-3 py-1 rounded'
          type="text" value={searchParam}
          onChange={e => setsearchParam(e.target.value)}
          placeholder='Search Hosteler'
        />
        <div className='gap-3 flex'>
          <Link to={"/addHosteler"}>
            <button className='btn'>Add Hosteler</button>
          </Link>
          <button onClick={handleExportData} className="btn">
            Export Data
          </button>
        </div>
      </div>
      <table className="min-w-full border border-gray-200">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Contact</th>
            <th>Room Number</th>
          </tr>
        </thead>
        <tbody>
          {
            filteredList
              .sort((a, b) => a.roomNo - b.roomNo)
              .map((h) => {
                return (
                  <tr key={h.id} className='text-center'>
                    <td>{h.name}</td>
                    <td>{h.email}</td>
                    <td>{h.contact}</td>
                    <td>{h.roomNo}</td>
                    <td>
                      <button onClick={() => handleDelete(h.id)} className='bg-red-500 text-white px-2 py-1 rounded'>Delete</button>
                    </td>
                  </tr>
                )
              })
          }
        </tbody>
      </table>
    </div>
  )
}

export default Hostelers