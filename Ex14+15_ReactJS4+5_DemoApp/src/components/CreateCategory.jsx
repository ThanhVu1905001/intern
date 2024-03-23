import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { createNewCategory } from '../services/api';
import { useAuth } from '../utils/auth'; // Sử dụng hook useAuth thay vì import hàm xác thực từ utils/auth
import './CreateCategory.css';

const CreateCategory = () => {
  const { isAuthenticated } = useAuth(); // Sử dụng hook useAuth để truy cập hàm isAuthenticated từ context
  const [categoryCode, setCategoryCode] = useState('');
  const [name, setName] = useState('');
  const [categoryDescription, setCategoryDescription] = useState('');
  const navigate = useNavigate();
  const [creationSuccess, setCreationSuccess] = useState(false);

  const handleCreate = async () => {
    try {
      console.log('Is Authenticated:', isAuthenticated());
      if (!isAuthenticated()) {
        // Xử lý logic khi chưa đăng nhập
        // Redirect hoặc hiển thị thông báo lỗi
        return;
      }

      const accessToken = localStorage.getItem('accessToken'); // Lấy accessToken từ localStorage sau khi đăng nhập
      console.log('Access Token:', accessToken);
      await createNewCategory({
        categoryCode,
        name,
        categoryDescription,
      }, accessToken); // Truyền accessToken vào hàm createNewCategory
      
      setCreationSuccess(true);
      console.log('Creation Success:', creationSuccess);
    } catch (error) {
      console.error('Error creating category:', error);
    }
  };

  useEffect(() => {
    if (creationSuccess) {
      console.log('Navigating...');
      navigate('/success'); // Thay đổi '/success' thành đường chuyển hướng khi tạo mới thành công
    }
  }, [creationSuccess, navigate]);

  // Phần JSX và các thành phần giao diện khác của component CreateCategory

  return (
    <div>
      <h2>Tạo mới category</h2>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleCreate();
        }}
      >
        <div>
          <label>Mã category:</label>
          <input
            type="text"
            value={categoryCode}
            onChange={(e) => setCategoryCode(e.target.value)}
          />
        </div>
        <div>
          <label>Tên category:</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div>
          <label>Mô tả:</label>
          <textarea
            value={categoryDescription}
            onChange={(e) => setCategoryDescription(e.target.value)}
          ></textarea>
        </div>
        <button type="submit">Tạo mới</button>
      </form>
    </div>
  );
};

export default CreateCategory;
