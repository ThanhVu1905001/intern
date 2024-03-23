import React from 'react';

const CategoryDetail = ({ category, goBack }) => {
  return (
    <div>
      <button onClick={goBack}>Back to Category List</button>
      <h2>Category Detail</h2>
      <p>ID: {category.id}</p>
      <p>Name: {category.name}</p>
      <p>Category Code: {category.categoryCode}</p>
      <p>Description: {category.description}</p>
    </div>
  );
};

export default CategoryDetail;
