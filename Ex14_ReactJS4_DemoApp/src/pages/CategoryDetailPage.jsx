import React from 'react';
import { useParams, Link } from 'react-router-dom';
import CategoryDetail from '../components/CategoryDetail';
import { logout } from '../utils/auth'; // Import hàm đăng xuất

const CategoryDetailPage = ({ handleLogout }) => {
  const { id } = useParams();

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
