import React from 'react';
import { Link } from 'react-router-dom';
import CategoryList from '../components/CategoryList';
import { useAuth } from '../utils/auth'; // Sử dụng hook useAuth thay vì import hàm logout từ utils/auth
import './Dashboard.css';
const Dashboard = () => {
  const { logout } = useAuth(); // Sử dụng hook useAuth để truy cập hàm logout từ context

  const handleLogout = () => {
    logout(); // Gọi hàm logout từ context khi người dùng đăng xuất
  };

  return (
    <div className="dashboard-container">
      <h1 className="dashboard-heading">Dashboard</h1>
      <button onClick={handleLogout} className="logout-button">Đăng xuất</button>
      <CategoryList className="category-list"/>
      <Link to="/create-category" className="dashboard-link">Tạo mới category</Link>
    </div>
  );
};

export default Dashboard;
