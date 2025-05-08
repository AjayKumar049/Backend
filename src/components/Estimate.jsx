import React from 'react'

const Estimate = () => {
  return (
    


    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Estimate</h1>

      <div className="overflow-x-auto">
        <table className="table-fixed border-collapse border border-gray-400 w-full min-w-[800px]">
          <thead>
            <tr className="bg-gray-100">
              <th className="border border-gray-400 px-4 py-2">Name</th>
              <th className="border border-gray-400 px-4 py-2">Unit Price</th>
              <th className="border border-gray-400 px-4 py-2">Discount</th>
              <th className="border border-gray-400 px-4 py-2">Tax</th>
              <th className="border border-gray-400 px-4 py-2">GST</th>
              <th className="border border-gray-400 px-4 py-2">Total Amount</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td className="border border-gray-400 px-4 py-2">Item A</td>
              <td className="border border-gray-400 px-4 py-2">₹500</td>
              <td className="border border-gray-400 px-4 py-2">10%</td>
              <td className="border border-gray-400 px-4 py-2">5%</td>
              <td className="border border-gray-400 px-4 py-2">18%</td>
              <td className="border border-gray-400 px-4 py-2">₹527</td>
            </tr>
            <tr>
              <td className="border border-gray-400 px-4 py-2">Item B</td>
              <td className="border border-gray-400 px-4 py-2">₹1000</td>
              <td className="border border-gray-400 px-4 py-2">0%</td>
              <td className="border border-gray-400 px-4 py-2">5%</td>
              <td className="border border-gray-400 px-4 py-2">18%</td>
              <td className="border border-gray-400 px-4 py-2">₹1230</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Estimate;
