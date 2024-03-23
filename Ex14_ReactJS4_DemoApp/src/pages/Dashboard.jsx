import React from 'react';
import { Link } from 'react-router-dom';
import CategoryList from '../components/CategoryList';
import { logout } from '../utils/auth'; // Import hàm đăng xuất

const Dashboard = ({ handleLogout }) => {
  return (
    <div>
      <h1>Dashboard</h1>
      <button onClick={handleLogout}>Đăng xuất</button>
      <CategoryList handleLogout={handleLogout} /> {/* Component CategoryList */}
      <Link to="/create-category">Tạo mới category</Link>
    </div>
  );
};

export default Dashboard;
