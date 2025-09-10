import React, { useEffect, useState } from 'react'
import Chart from '../components/PieChart'
import axios from 'axios'
import { months } from '../utils/months';

const Home = () => {
  const [occupancy, setOccupancy] = useState(0);
  const [capacity, setCapacity] = useState(0);
  const [totalHostelers, settotalHostelers] = useState(0);
  const [totalPayments, settotalPayments] = useState(0);
  const [month, setMonth] = useState(new Date().getMonth() + 1);

  useEffect(() => {
    const getListOfRooms = async () => {
      const response = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/api/room`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        }
      });

      const rooms = response.data;
      let totalCapacity = 0;
      let totalOccupancy = 0;

      rooms.forEach(room => {
        totalCapacity += room.capacity;
        totalOccupancy += room.occupancy;
      });

      setCapacity(totalCapacity);
      setOccupancy(totalOccupancy);
    };

    getListOfRooms();
  }, []);

  useEffect(() => {
    const getTotalHostelers = async () => {
      try {
        const res = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/api/hostelers`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
          }
        })
        if (!res) throw "Failed to get hostelers";
        // console.log(res.data)
        settotalHostelers(res.data.length);
      } catch (error) {
        console.log(error)
      }
    }
    const monthsArray = [
      "January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
    ];
    const getTotalPayments = async () => {
      try {
        const res = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/api/hostelers/month/${monthsArray[new Date().getMonth()].toLowerCase()}`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
          }
        })
        if (!res) throw "Failed to get payments";
        // console.log(res.data)
        // console.log(monthsArray[new Date().getMonth()].toLowerCase())
        settotalPayments(res.data);
      } catch (error) {
        console.log(error)
      }
    }
    getTotalHostelers();
    getTotalPayments();
    // console.log(new Date().getMonth() + 1)
    // console.log(totalHostelers)
    // console.log(totalPayments)
  }, [])

  const handleExport = async () => {
    try {
      const res = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/api/payment/defaulter/${months[month-1].toLowerCase()}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        },
        responseType: "blob"
      })
      if (!res) throw new Error("no response from server");
      const blob = new Blob([res.data], {
        type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
      });
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", `defaulters_${months[month-1]}.xlsx`);
      document.body.appendChild(link);
      link.click();
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <div className='flex flex-col gap-4'>
      <div className='flex flex-row items-center justify-center gap-4'>
        <Chart prop1={capacity} prop2={capacity - occupancy} title1={"Capacity"} title2={"Vacancy"} />
        <Chart prop1={totalHostelers} prop2={totalHostelers - totalPayments} title1={"Total Hostelers"} title2={"Payment defaulters"} />
      </div>
      <div className='flex items-center gap-4'>
        <h2>
          Export payment defaulters :
        </h2>
        <select value={month} onChange={(e) => setMonth(e.target.value)} className="border rounded p-2">
          {months.map((m, index) => (
            <option key={index} value={index + 1}>
              {m}
            </option>
          ))}
        </select>
        <button onClick={handleExport} className="btn">
          Fetch
        </button>
      </div>
    </div>
  )
}

export default Home
