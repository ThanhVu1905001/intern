import React from 'react';
import { useParams, Link } from 'react-router-dom';
import CategoryDetail from '../CategoryDetail';
import { useAuth } from '../utils/auth'; // Import useAuth from your authentication context
import './CategoryDetailPage.css';
const CategoryDetailPage: React.FC = () => {
  const { id } = useParams<{ id: string }>(); // Định kiểu cho id từ useParams
  const { logout } = useAuth(); // Sử dụng hook useAuth để truy cập hàm logout từ context
  
  const handleLogout = () => {
    logout(); // Gọi hàm logout từ context khi người dùng đăng xuất
  };
  
  return (
    <div className="category-detail-container">
      <h1 className="category-detail-heading">Chi tiết Category</h1>
      <button onClick={handleLogout} className="logout-button">Đăng xuất</button>
      <CategoryDetail categoryIdProp={id!} /> {/* Component CategoryDetail */}
      <Link to="/dashboard" className="back-link">Quay lại danh sách</Link>
    </div>
  );
};

export default CategoryDetailPage;
