import React, { useState } from "react";
import { Outlet } from "react-router-dom";
import Sidebar from "./Sidebar";
import { IoMdMenu } from "react-icons/io";

export default function DashboardLayout() {
  const [open, setOpen] = useState(false);

  return (
    <div className="flex h-screen">
      <button
        onClick={() => setOpen(!open)}
        className="p-2 md:hidden fixed top-4 left-4 z-50 bg-gray-200 rounded"
      >
        <IoMdMenu size={24} />
      </button>

      <div
        className={`
          fixed inset-y-0 left-0 w-2/3 sm:w-1/2 md:static md:w-1/4 bg-primary z-40
          transform ${open ? "translate-x-0" : "-translate-x-full"}
          transition-transform duration-300 md:translate-x-0
        `}
      >
        <Sidebar closeMenu={() => setOpen(false)} />
      </div>

      {open && (
        <div
          className="fixed inset-0 bg-black bg-opacity-40 md:hidden"
          onClick={() => setOpen(false)}
        />
      )}

      <div className="flex-1 p-6 bg-gray-50 overflow-y-auto">
        <Outlet />
      </div>
    </div>
  );
}