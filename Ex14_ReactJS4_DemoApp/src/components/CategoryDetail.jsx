import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getCategoryById, updateCategory } from '../services/api'; // Import hàm gọi API lấy và cập nhật thông tin category

const CategoryDetail = ({ handleLogout }) => {
  const [category, setCategory] = useState(null);
  const [isEditing, setIsEditing] = useState(false); // Trạng thái chỉnh sửa
  const { categoryId } = useParams();

  useEffect(() => {
    loadCategory();
  }, []);

  const loadCategory = async () => {
    try {
      const data = await getCategoryById(categoryId);
      setCategory(data);
    } catch (error) {
      console.error('Error fetching category:', error);
    }
  };

  const handleEdit = () => {
    setIsEditing(true); // Kích hoạt chế độ chỉnh sửa khi nhấn vào "Chỉnh sửa"
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCategory({ ...category, [name]: value });
  };

  const handleUpdate = async () => {
    try {
      const accessToken = localStorage.getItem('accessToken');
      await updateCategory(categoryId, category, accessToken);
      setIsEditing(false); // Kết thúc chế độ chỉnh sửa sau khi cập nhật thành công
      // Reload thông tin category sau khi cập nhật
      loadCategory();
    } catch (error) {
      console.error('Error updating category:', error);
      // Xử lý lỗi khi cập nhật không thành công
    }
  };

  return (
    <div>
      <h2>Thông tin chi tiết category</h2>
      {category ? (
        <div>
          {isEditing ? (
            <div>
              <input
                type="text"
                name="categoryCode"
                value={category.categoryCode}
                onChange={handleInputChange}
              />
              <input
                type="text"
                name="name"
                value={category.name}
                onChange={handleInputChange}
              />
              <textarea
                name="categoryDescription"
                value={category.categoryDescription}
                onChange={handleInputChange}
              />
              {/* Thêm nút cập nhật */}
              <button onClick={handleUpdate}>Cập nhật</button>
            </div>
          ) : (
            <div>
              <h3>Mã: {category.categoryCode}</h3>
              <h3>Tên: {category.name}</h3>
              <p>Mô tả: {category.categoryDescription}</p>
              {/* Thêm nút Chỉnh sửa */}
              <button onClick={handleEdit}>Chỉnh sửa</button>
            </div>
          )}
        </div>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default CategoryDetail;
