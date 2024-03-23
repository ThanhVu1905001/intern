import React from 'react';
import './Pagination.css';

interface PaginationProps {
  categoriesPerPage: number;
  totalCategories: number;
  paginate: (number: number) => void;
}

const Pagination: React.FC<PaginationProps> = ({
  categoriesPerPage,
  totalCategories,
  paginate,
}) => {
  const pageNumbers: number[] = [];

  for (let i = 1; i <= Math.ceil(totalCategories / categoriesPerPage); i++) {
    pageNumbers.push(i);
  }

  return (
    <ul className="pagination">
      {pageNumbers.map((number) => (
        <li key={number}>
          <button onClick={() => paginate(number)}>{number}</button>
        </li>
      ))}
    </ul>
  );
};

export default Pagination;
