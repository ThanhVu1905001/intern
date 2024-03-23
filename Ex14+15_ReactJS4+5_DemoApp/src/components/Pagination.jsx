import React from 'react';
import './Pagination.css';

const Pagination = ({ categoriesPerPage, totalCategories, paginate }) => {
  const pageNumbers = [];

  for (let i = 1; i <= Math.ceil(totalCategories / categoriesPerPage); i++) {
    pageNumbers.push(i);
  }

  return (
    <ul>
      {pageNumbers.map((number) => (
        <li key={number}>
          <button onClick={() => paginate(number)}>{number}</button>
        </li>
      ))}
    </ul>
  );
};

export default Pagination;
