// CategoryItem.js
import React from 'react';

const CategoryItem = ({ category, handleClick }) => {
  return (
    <div onClick={() => handleClick(category)}>
      <h3>{category.name}</h3>
      {/* Hiển thị thông tin khác của category */}
    </div>
  );
};

export default CategoryItem;
