  import React, { useState, useEffect } from 'react';
  import { Link } from 'react-router-dom';
  import { getCategoryList } from '../services/api'; // Import hàm gọi API lấy danh sách category
  import Pagination from './Pagination';
  const CategoryList = ({ handleLogout }) => {
    const [categories, setCategories] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [categoriesPerPage] = useState(5); // Số lượng category trên mỗi trang

    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
      try {
        // Gọi API để lấy danh sách các category
        const data = await getCategoryList();
        setCategories(data); // Cập nhật danh sách category
      } catch (error) {
        console.error('Error fetching categories:', error);
        // Xử lý lỗi khi không thể lấy danh sách categories
      }
    };
    // Logic phân trang
    const indexOfLastCategory = currentPage * categoriesPerPage;
    const indexOfFirstCategory = indexOfLastCategory - categoriesPerPage;
    const currentCategories = categories.slice(indexOfFirstCategory, indexOfLastCategory);

    // Thay đổi trang
    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div>
        <h2>Danh sách category</h2>
        <ul>
            {currentCategories.map((category) => (
            <li key={category.id}>
                <Link to={`/category/${category.id}`}>{category.name}</Link>
            </li>
            ))}
        </ul>
        <Pagination
            categoriesPerPage={categoriesPerPage}
            totalCategories={categories.length}
            paginate={paginate}
        /> 
        </div>
    );
    };

    export default CategoryList;
