import React from 'react';
import { Link } from 'react-router-dom';
import CategoryList from '../components/CategoryList';
import { useAuth } from '../utils/auth'; // Sử dụng hook useAuth thay vì import hàm logout từ utils/auth

const Dashboard = () => {
  const { logout } = useAuth(); // Sử dụng hook useAuth để truy cập hàm logout từ context

  const handleLogout = () => {
    logout(); // Gọi hàm logout từ context khi người dùng đăng xuất
  };

  return (
    <div>
      <h1>Dashboard</h1>
      <button onClick={handleLogout}>Đăng xuất</button>
      <CategoryList />
      <Link to="/create-category">Tạo mới category</Link>
    </div>
  );
};

export default Dashboard;
