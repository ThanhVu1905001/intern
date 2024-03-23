import React from 'react';
import CreateCategory from '../components/CreateCategory';
import { Link } from 'react-router-dom';
import { useAuth } from '../utils/auth'; // Sử dụng hook useAuth thay vì import hàm logout từ utils/auth
import './CreateCategoryPage.css';
const CreateCategoryPage = () => {
  const { logout } = useAuth(); // Sử dụng hook useAuth để truy cập hàm logout từ context

  const handleLogout = () => {
    logout(); // Gọi hàm logout từ context khi người dùng đăng xuất
  };
  return (
    <div className="create-category-container">
      <h1 className="create-category-heading">Tạo mới Category</h1>
      <button onClick={handleLogout} className="logout-button">Đăng xuất</button>
      <CreateCategory/> {/* Component CreateCategory */}
      <Link to="/dashboard" className="back-link">Quay lại danh sách</Link>

    </div>
  );
};

export default CreateCategoryPage;
