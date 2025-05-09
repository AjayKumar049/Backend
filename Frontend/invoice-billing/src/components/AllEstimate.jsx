import React from 'react';

const AllEstimate = () => {
  return (
    <div className="p-4 max-w-7xl mx-auto">
      {/* Header Row */}
      <div className="flex flex-col md:flex-row justify-between items-start md:items-center mb-4 space-y-2 md:space-y-0">
        <h1 className="text-2xl font-bold">AllEstimate</h1>

        <div className="flex space-x-2">
          <button className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 text-sm">New</button>
          <input
            type="text"
            placeholder="Search..."
            className="px-3 py-2 border border-gray-300 rounded text-sm focus:outline-none focus:ring focus:border-blue-500"
          />
        </div>
      </div>

      {/* Table */}
      <div className="overflow-x-auto">
        <table className="min-w-full table-auto border-collapse border border-gray-300">
          <thead className="bg-gray-100">
            <tr>
              <th className="border border-gray-300 px-4 py-2 text-left text-sm">Estimate No</th>
              <th className="border border-gray-300 px-4 py-2 text-left text-sm">Customer Name</th>
              <th className="border border-gray-300 px-4 py-2 text-left text-sm">Estimate Date</th>
              <th className="border border-gray-300 px-4 py-2 text-left text-sm">Expiry Date</th>
              <th className="border border-gray-300 px-4 py-2 text-left text-sm">Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td className="border border-gray-300 px-4 py-2 text-sm">EST-001</td>
              <td className="border border-gray-300 px-4 py-2 text-sm">John Doe</td>
              <td className="border border-gray-300 px-4 py-2 text-sm">2025-05-09</td>
              <td className="border border-gray-300 px-4 py-2 text-sm">2025-05-20</td>
              <td className="border border-gray-300 px-4 py-2 text-sm space-x-2">
                <button className="bg-yellow-500 text-white px-3 py-1 rounded text-xs hover:bg-yellow-600">Edit</button>
                <button className="bg-red-600 text-white px-3 py-1 rounded text-xs hover:bg-red-700">Delete</button>
              </td>
            </tr>
            {/* Add more rows as needed */}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AllEstimate;
