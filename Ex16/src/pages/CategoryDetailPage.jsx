import React from 'react';
import { useParams, Link } from 'react-router-dom';
import CategoryDetail from '../components/CategoryDetail';
import { useAuth } from '../utils/auth'; // Import useAuth from your authentication context

const CategoryDetailPage = () => {
  const { id } = useParams();
  const { logout } = useAuth(); // Sử dụng hook useAuth để truy cập hàm logout từ context
  const handleLogout = () => {
    logout(); // Gọi hàm logout từ context khi người dùng đăng xuất
  };
  return (
    <div>
      <h1>Chi tiết Category</h1>
      <button onClick={handleLogout}>Đăng xuất</button>
      <CategoryDetail categoryId={id} /> {/* Component CategoryDetail */}
      <Link to="/dashboard">Quay lại danh sách</Link>
    </div>
  );
};

export default CategoryDetailPage;
