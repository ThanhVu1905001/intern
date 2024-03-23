import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getCategoryById, updateCategory } from '../services/api';
import { useAuth } from '../utils/auth'; // Import useAuth from your authentication context

const CategoryDetail = () => {
  const [category, setCategory] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editedCategory, setEditedCategory] = useState(null);
  const { categoryId } = useParams();
  const { isAuthenticated } = useAuth(); // Access isAuthenticated from the authentication context

  useEffect(() => {
    loadCategory();
  }, []);

  useEffect(() => {
    if (category) {
      setEditedCategory({ ...category });
    }
  }, [category]);

  const loadCategory = async () => {
    try {
      const data = await getCategoryById(categoryId);
      setCategory(data);
    } catch (error) {
      console.error('Error fetching category:', error);
    }
  };

  const handleEdit = () => {
    // Check if user is authenticated before allowing edit
    if (isAuthenticated()) {
      setIsEditing(true);
    } else {
      // Handle scenario when user is not authenticated
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditedCategory({ ...editedCategory, [name]: value });
  };

  const handleUpdate = async () => {
    try {
      const accessToken = localStorage.getItem('accessToken');
      await updateCategory(categoryId, editedCategory, accessToken);
      setIsEditing(false);
      loadCategory();
    } catch (error) {
      console.error('Error updating category:', error);
    }
  };

  return (
    <div className="category-detail">
      <h2>Thông tin chi tiết category</h2>
      {category ? (
        <div className="category-form">
          {isEditing ? (
            <div>
              <input
                type="text"
                name="categoryCode"
                value={editedCategory.categoryCode}
                onChange={handleInputChange}
              />
              <input
                type="text"
                name="name"
                value={editedCategory.name}
                onChange={handleInputChange}
              />
              <textarea
                name="categoryDescription"
                value={editedCategory.categoryDescription}
                onChange={handleInputChange}
              />
              <button onClick={handleUpdate}>Cập nhật</button>
            </div>
          ) : (
            <div>
              <h3>Mã: {category.categoryCode}</h3>
              <h3>Tên: {category.name}</h3>
              <p>Mô tả: {category.categoryDescription}</p>
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
