import React from 'react';
import CreateCategory from '../components/CreateCategory';
import { Link } from 'react-router-dom';
import { logout } from '../utils/auth'; // Import hàm đăng xuất

const CreateCategoryPage = ({ handleLogout }) => {
  return (
    <div>
      <h1>Tạo mới Category</h1>
      <button onClick={handleLogout}>Đăng xuất</button>
      <CreateCategory handleLogout={handleLogout} /> {/* Component CreateCategory */}
      <Link to="/dashboard">Quay lại danh sách</Link>

    </div>
  );
};

export default CreateCategoryPage;
