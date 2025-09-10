import React from 'react'

const Spinner = () => {
    return (
        <div>
            <TailSpin
                visible={true}
                height="80"
                width="80"
                color="#d6ccc2"
                ariaLabel="tail-spin-loading"
                radius="1"
                wrapperStyle={{}}
                wrapperClass=""
            />
        </div>
    )
}

export default Spinner