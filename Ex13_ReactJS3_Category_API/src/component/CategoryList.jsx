// CategoryList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CategoryItem from './CategoryItem';
import CategoryDetail from './CategoryDetail';
import Pagination from './Pagination'; // Component hỗ trợ phân trang

const CategoryList = () => {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage] = useState(5); // Số lượng item trên mỗi trang

  useEffect(() => {
    axios.get('http://localhost:8089/api/categories/')
        .then(response => {
        setCategories(response.data);
        console.log(response.data);
        })
        .catch(error => {
        console.error(error);
        });
    }, []);

  // Xử lý chọn category để hiển thị chi tiết
  const handleCategoryClick = category => {
    setSelectedCategory(category);
  };

  const goBack = () => {
    setSelectedCategory(null);
  };

  // Logic phân trang
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = categories.slice(indexOfFirstItem, indexOfLastItem);

  // Chuyển trang
  const paginate = pageNumber => setCurrentPage(pageNumber);

  return (
    <div>
      <h1>Category List</h1>
      <div>
        {currentItems.map(category => (
          <CategoryItem
            key={category.id}
            category={category}
            handleClick={handleCategoryClick}
          />
        ))}
      </div>
      <Pagination
        itemsPerPage={itemsPerPage}
        totalItems={categories.length}
        paginate={paginate}
      />
      {selectedCategory && (
        <CategoryDetail category={selectedCategory} goBack={goBack}/>
      )}
    </div>
  );
};

export default CategoryList;
