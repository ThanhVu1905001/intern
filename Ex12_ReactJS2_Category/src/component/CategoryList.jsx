import React from 'react';
import CategoryItem from './CategoryItem';

const CategoryList = ({ categories, showCategoryDetail }) => {
  return (
    <div>
      <h2>Category List</h2>
      <ul>
        {categories.map(category => (
          <CategoryItem
            key={category.id}
            category={category}
            showCategoryDetail={showCategoryDetail}
          />
        ))}
      </ul>
    </div>
  );
};

export default CategoryList;
