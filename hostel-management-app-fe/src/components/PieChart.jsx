import * as React from 'react';
import { PieChart } from '@mui/x-charts/PieChart';

export default function Chart({ prop1, prop2, title1, title2 }) {
    return (
        <div className='w-full border border-dotted py-5 rounded'>
            <PieChart
                colors={['#edede9', '#d6ccc2']}
                series={[
                    {
                        data: [
                            { id: 0, value: prop1, label: `${title1} : ${prop1}` },
                            { id: 1, value: prop2, label: `${title2} : ${prop2}` },
                        ],
                    },
                ]}
                width={200}
                height={200}
            />
        </div>
    );
}