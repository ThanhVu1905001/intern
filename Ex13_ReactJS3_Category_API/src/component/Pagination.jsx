// Pagination.js
import React from 'react';

const Pagination = ({ itemsPerPage, totalItems, paginate }) => {
  const pageNumbers = [];

  for (let i = 1; i <= Math.ceil(totalItems / itemsPerPage); i++) {
    pageNumbers.push(i);
  }

  return (
    <nav>
      <ul style={paginationStyle}>
        {pageNumbers.map(number => (
          <li key={number} style={paginationItemStyle}>
            <a onClick={() => paginate(number)} href="!#" style={paginationLinkStyle}>
              {number}
            </a>
          </li>
        ))}
      </ul>
    </nav>
  );
};

// CSS styles
const paginationStyle = {
  display: 'flex',
  listStyle: 'none',
  padding: 0,
  justifyContent: 'center',
  marginTop: '20px',
};

const paginationItemStyle = {
  margin: '0 5px',
};

const paginationLinkStyle = {
  color: '#333',
  textDecoration: 'none',
  padding: '8px 12px',
  border: '1px solid #ccc',
  borderRadius: '4px',
  transition: 'background-color 0.3s ease',
};

const paginationLinkHoverStyle = {
  backgroundColor: '#f0f0f0',
};

export default Pagination;

