import React from 'react';
import { ChevronDownIcon } from '@heroicons/react/16/solid';

const Estimate = () => {
  return (
    <div className="p-4 max-w-8xl mx-auto space-y-6">
      <h1 className="text-2xl font-bold">Estimate</h1>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {/* Customer Name */}
        <div>
          <label htmlFor="customerName" className="block text-sm font-medium text-gray-700">
            Customer Name
          </label>
          <input
            type="text"
            id="customerName"
            name="customerName"
            placeholder="Enter customer name"
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
          />
        </div>

        {/* Estimate Date */}
        <div>
          <label htmlFor="estimateDate" className="block text-sm font-medium text-gray-700">
            Estimated Date
          </label>
          <input
            type="date"
            id="estimateDate"
            name="estimateDate"
            className="mt-1 block w-full rounded-none border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
          />
        </div>

        {/* Expiry Date */}
        <div>
          <label htmlFor="expiryDate" className="block text-sm font-medium text-gray-700">
            Expiry Date
          </label>
          <input
            type="date"
            id="expiryDate"
            name="expiryDate"
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
          />
        </div>

        {/* Terms and Conditions */}
        <div>
          <label htmlFor="terms" className="block text-sm font-medium text-gray-700">
            Terms and Conditions
          </label>
          <textarea
            id="terms"
            name="terms"
            rows="4"
            placeholder="Enter terms and conditions here..."
            className="mt-1 block w-full rounded-md border border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
          ></textarea>
        </div>
      </div>

      {/* Table Section */}
      <div className="overflow-x-auto">
        <h1>Item Table</h1>
        <table className="min-w-[800px] w-full table-auto border-collapse border border-gray-400">
          <thead className="bg-gray-100">
            <tr>
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
              <td className="border border-gray-400 px-4 py-2">500</td>
              <td className="border border-gray-400 px-4 py-2">10%</td>
              <td className="border border-gray-400 px-4 py-2">5%</td>
              <td className="border border-gray-400 px-4 py-2">18%</td>
              <td className="border border-gray-400 px-4 py-2">527</td>
            </tr>
            <tr>
              <td className="border border-gray-400 px-4 py-2">Item B</td>
              <td className="border border-gray-400 px-4 py-2">1000</td>
              <td className="border border-gray-400 px-4 py-2">0%</td>
              <td className="border border-gray-400 px-4 py-2">5%</td>
              <td className="border border-gray-400 px-4 py-2">18%</td>
              <td className="border border-gray-400 px-4 py-2">1230</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Estimate;
