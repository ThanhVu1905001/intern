import React, { useState, useEffect, ChangeEvent } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getCategoryById, updateCategory } from './service/api';
import { useAuth } from './utils/auth';

interface Category {
  categoryCode: string;
  name: string;
  categoryDescription: string;
}

interface Props {
  categoryIdProp: string; // Thêm prop categoryIdProp vào định nghĩa của component
}

const CategoryDetail: React.FC<Props> = ({ categoryIdProp }) => {
  const [category, setCategory] = useState<Category | null>(null);
  const [isEditing, setIsEditing] = useState<boolean>(false);
  const [editedCategory, setEditedCategory] = useState<Category | null>(null);
  const { categoryId } = useParams<{ categoryId: string }>();
  const { isAuthenticated } = useAuth();

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
      const data: Category = await getCategoryById(categoryId!);
      setCategory(data);
    } catch (error) {
      console.error('Error fetching category:', error);
    }
  };

  const handleEdit = () => {
    if (isAuthenticated()) {
      setIsEditing(true);
    } else {
      // Handle scenario when user is not authenticated
    }
  };

  const handleInputChange = (e: ChangeEvent<HTMLInputElement> | ChangeEvent<HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    if (editedCategory) {
      setEditedCategory({ ...editedCategory, [name]: value });
    }
  };

  const handleUpdate = async () => {
    try {
      const accessToken = localStorage.getItem('accessToken');
      if (accessToken && editedCategory) {
        await updateCategory(categoryId!, editedCategory, accessToken);
        setIsEditing(false);
        loadCategory();
      }
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
                value={editedCategory?.categoryCode || ''}
                onChange={handleInputChange}
              />
              <input
                type="text"
                name="name"
                value={editedCategory?.name || ''}
                onChange={handleInputChange}
              />
              <textarea
                name="categoryDescription"
                value={editedCategory?.categoryDescription || ''}
                onChange={handleInputChange}
              ></textarea>
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
