import React from 'react';
import CreateCategory from '../components/CreateCategory';
import { Link } from 'react-router-dom';
import { useAuth } from '../utils/auth'; // Sử dụng hook useAuth thay vì import hàm logout từ utils/auth

const CreateCategoryPage = () => {
  const { logout } = useAuth(); // Sử dụng hook useAuth để truy cập hàm logout từ context

  const handleLogout = () => {
    logout(); // Gọi hàm logout từ context khi người dùng đăng xuất
  };
  return (
    <div>
      <h1>Tạo mới Category</h1>
      <button onClick={handleLogout}>Đăng xuất</button>
      <CreateCategory/> {/* Component CreateCategory */}
      <Link to="/dashboard">Quay lại danh sách</Link>

    </div>
  );
};

export default CreateCategoryPage;
