import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import Spinner from '../components/Spinner';

const Rooms = () => {
  // console.log(localStorage.getItem("token"))
  const [List, setList] = useState([]);
  const [searchParam, setsearchParam] = useState("");
  const [filteredList, setfilteredList] = useState(List);
  useEffect(() => {
    const getListOfRooms = async () => {
      const response = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/api/room`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        }
      })
      // console.log(response.data);
      setList(response.data);
    }
    getListOfRooms();
  }, [])

  const handleDelete = async (roomNo) => {
    const confirmed = confirm("Proceed to delete?");
    if (!confirmed) return;
    try {
      await axios.delete(`${import.meta.env.VITE_BACKEND_URL}/api/room/${roomNo}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      });
      alert("Room deleted successfully");

      setList((prevList) => prevList.filter((room) => room.roomNo !== roomNo));
    } catch (error) {
      console.error("Error deleting room:", error);
      alert("Failed to delete room");
    }
  };

  // useEffect(() => {
  //   const handleVacantRooms = async (roomNo) => {
  //     try {
  //       const res = await axios.get(`${import.meta.env.VITE_BACKEND_URL}/api/room/vacancy/${roomNo}`, {
  //         headers: {
  //           Authorization: `Bearer ${localStorage.getItem("token")}`
  //         }
  //       })
  //       if (!res) return "NA";
  //       console.log(res.data);
  //     } catch (error) {
  //       console.log(error)
  //     }
  //   }
  //   handleVacantRooms();
  // }, [])

  useEffect(() => {
    if (searchParam) {
      setfilteredList(List.filter(room => room.roomNo.includes(searchParam)))
    } else {
      setfilteredList(List)
    }
  }, [searchParam, List])

  if (!filteredList) return <Spinner />
  else return (
    <div className='flex flex-col justify-center items-center gap-3'>
      <Link to={"/addrooms"}>
        <button className='btn'>Add a room</button>
      </Link>
      <div>
        <input type="text" className='input-field' value={searchParam} onChange={e => setsearchParam(e.target.value)} placeholder='Search Room' />
      </div>
      <table className="min-w-full border border-gray-200 rounded-lg">
        <thead>
          <tr>
            <th>Room Number</th>
            <th>capacity</th>
            <th>Vacancy</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {
            filteredList.map((room) => {
              return (
                <tr key={room.roomId} className='text-center'>
                  <td>{room.roomNo}</td>
                  <td>{room.capacity}</td>
                  <td>{room.capacity - room.occupancy}</td>
                  <td>
                    <button onClick={() => handleDelete(room.roomNo)} className='bg-red-500 text-white px-2 py-1 rounded'>Delete</button>
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

export default Rooms