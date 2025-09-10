import React, { useEffect, useState } from "react";
import axios from "axios";
import { months } from "../utils/months";

const AddPayment = () => {
    const [month, setMonth] = useState(new Date().getMonth() + 1);
    const [rooms, setRooms] = useState([]);
    const [selectedRoom, setSelectedRoom] = useState("");
    const [hostelers, setHostelers] = useState([]);
    const [selectedHosteler, setSelectedHosteler] = useState("");
    const [amount, setAmount] = useState();
    const [addingPayment, setaddingPayment] = useState(false);

    useEffect(() => {
        const fetchRooms = async () => {
            try {
                const res = await axios.get("http://localhost:8080/api/room", {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("token")}`,
                    }
                })
                setRooms(res.data);
            } catch (err) {
                console.log(err)
                alert("Failed to load rooms");
            }
        };
        fetchRooms();
    }, []);

    useEffect(() => {
        if (selectedRoom) {
            const fetchHostelers = async () => {
                try {
                    const res = await axios.get(
                        `${import.meta.env.VITE_BACKEND_URL}/api/hostelers/room/${selectedRoom}`, {
                        headers: {
                            Authorization: `Bearer ${localStorage.getItem("token")}`
                        }
                    }
                    );
                    setHostelers(res.data);
                } catch (err) {
                    console.log(err)
                    alert("Failed to load hostelers");
                }
            };
            fetchHostelers();
        } else {
            setHostelers([]);
        }
    }, [selectedRoom]);
    const monthsArray = [
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ];
    const handleSubmit = async () => {
        setaddingPayment(true);
        try {
            const res = await axios.post(`${import.meta.env.VITE_BACKEND_URL}/api/hostelers/payments`, {
                hostelerId: selectedHosteler,
                month: monthsArray[month - 1].toLowerCase(),
                amount: amount,
                paymentDate: new Date()
            }, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            });
            // console.log(res)
            if (res) {
                alert("Payment recorded successfully!");
                setMonth(new Date().getMonth() + 1);
                setSelectedRoom("");
                setHostelers([]);
                setSelectedHosteler("");
                setAmount(0);
            }
        } catch (err) {
            // console.log(err.response.data.error)
            alert(err.response.data.error);
        }
        finally {
            setaddingPayment(false);
        }
    };


    return (
        <div className="flex w-full justify-center gap-4 items-center">
            <select value={month} onChange={(e) => setMonth(e.target.value)} className="border rounded p-2">
                {months.map((m, index) => (
                    <option key={index} value={index + 1}>
                        {m}
                    </option>
                ))}
            </select>

            <select
                className="border rounded p-2"
                value={selectedRoom}
                onChange={(e) => setSelectedRoom(e.target.value)}
            >
                <option value="">-- Select Room --</option>
                {rooms.map((r) => (
                    <option key={r.id} value={r.roomNo}>
                        Room {r.roomNo}
                    </option>
                ))}
            </select>

            <select
                className="border rounded p-2"
                value={selectedHosteler}
                onChange={(e) => setSelectedHosteler(e.target.value)}
                disabled={!selectedRoom}
            >
                <option value="">-- Select Hosteler --</option>
                {hostelers.map((h) => (
                    <option key={h.id} value={h.id}>
                        {h.name}
                    </option>
                ))}
            </select>
            <input type="number" placeholder="amount" value={amount} onChange={e => setAmount(e.target.value)} className="border rounded p-2" />
            <button
                className="btn"
                onClick={handleSubmit}
                disabled={!selectedRoom || !selectedHosteler}
            >
                {addingPayment ? "...processing" : "Submit"}
            </button>
        </div>
    );
};

export default AddPayment;