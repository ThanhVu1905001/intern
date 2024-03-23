// CategoryDetail.js
import React from 'react';

const CategoryDetail = ({ category }) => {
  return (
    <div>
      <h2>Category Details</h2>
      <p>Name: {category.name}</p>
      {/* Hiển thị thông tin chi tiết khác của category */}
    </div>
  );
};

export default CategoryDetail;
