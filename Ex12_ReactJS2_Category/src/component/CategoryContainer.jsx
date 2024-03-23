import React, { useState } from 'react';
import CategoryList from './CategoryList';
import CategoryDetail from './CategoryDetail';

const CategoryContainer = () => {
  const [selectedCategory, setSelectedCategory] = useState(null);
  const categories = [
    { id: 1, name: 'Category 1',categoryCode:'cd1', description: 'Description for Category 1' },
    { id: 2, name: 'Category 2',categoryCode:'cd1', description: 'Description for Category 2' },
    // Thêm các category khác vào đây
  ];

  const showCategoryDetail = (categoryId) => {
    const selected = categories.find(category => category.id === categoryId);
    setSelectedCategory(selected);
  };

  const goBack = () => {
    setSelectedCategory(null);
  };

  return (
    <div>
      {selectedCategory ? (
        <CategoryDetail category={selectedCategory} goBack={goBack} />
      ) : (
        <CategoryList categories={categories} showCategoryDetail={showCategoryDetail} />
      )}
    </div>
  );
};

export default CategoryContainer;
