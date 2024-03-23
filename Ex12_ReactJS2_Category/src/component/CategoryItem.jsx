import React from 'react';

const CategoryItem = ({ category, showCategoryDetail }) => {
  return (
    <li onClick={() => showCategoryDetail(category.id)}>
      {category.name}
    </li>
  );
};

export default CategoryItem;
