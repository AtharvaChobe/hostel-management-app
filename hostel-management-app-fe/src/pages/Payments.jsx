import React from 'react'
import AddPayment from '../components/AddPayment'

const Payments = () => {
  return (
    <div className='flex flex-col items-center justify-center'>
      <div className='flex flex-col border p-6 rounded w-fit justify-center gap-5 items-center'>
        <h1 className="title">Add a payment</h1>
        <AddPayment />
      </div>
    </div>
  )
}

export default Payments