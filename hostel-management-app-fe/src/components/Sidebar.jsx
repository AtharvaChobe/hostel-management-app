import React from "react";
import { Link } from "react-router-dom";

const Sidebar = ({ closeMenu }) => {
  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.reload();
  };

  return (
    <div className="h-full flex flex-col justify-between p-4">
      <div className="flex flex-col gap-3">
        <h1 className="title mb-4">{localStorage.getItem("name")}</h1>
        <Link onClick={closeMenu} className="hover:font-bold" to="/home">
          Home
        </Link>
        <Link onClick={closeMenu} className="hover:font-bold" to="/payments">
          Payments
        </Link>
        <Link onClick={closeMenu} className="hover:font-bold" to="/hostelers">
          Hostelers
        </Link>
        <Link onClick={closeMenu} className="hover:font-bold" to="/rooms">
          Rooms
        </Link>
      </div>
      <div>
        <button
          className={`${!localStorage.getItem("token") ? "hidden" : "btn"
            }`}
          onClick={handleLogout}
        >
          Logout
        </button>
      </div>
    </div>
  );
};

export default Sidebar;