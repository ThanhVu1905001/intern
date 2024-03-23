import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { createNewCategory } from './service/api';
import { useAuth } from './utils/auth';
import './CreateCategory.css';

const CreateCategory: React.FC = () => {
  const { isAuthenticated } = useAuth();
  const [categoryCode, setCategoryCode] = useState<string>('');
  const [name, setName] = useState<string>('');
  const [categoryDescription, setCategoryDescription] = useState<string>('');
  const navigate = useNavigate();
  const [creationSuccess, setCreationSuccess] = useState<boolean>(false);

  const handleCreate = async () => {
    try {
      console.log('Is Authenticated:', isAuthenticated());
      if (!isAuthenticated()) {
        // Handle logic when not authenticated
        // Redirect or show error message
        return;
      }

      const accessToken = localStorage.getItem('accessToken');
      console.log('Access Token:', accessToken);
      await createNewCategory({
        categoryCode,
        name,
        categoryDescription,
      }, accessToken || ''); // Replace '||' with proper default value if needed
      
      setCreationSuccess(true);
      console.log('Creation Success:', creationSuccess);
    } catch (error) {
      console.error('Error creating category:', error);
    }
  };

  useEffect(() => {
    if (creationSuccess) {
      console.log('Navigating...');
      navigate('/success'); // Replace '/success' with the correct redirect path after successful creation
    }
  }, [creationSuccess, navigate]);

  return (
    <div>
      <h2>Tạo mới category</h2>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleCreate();
        }}
      >
        {/* Input fields and other UI components */}
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
