import React from 'react';
import { Link } from 'react-router-dom';

const Success: React.FC = () => {
  return (
    <div>
      <h2>Tạo mới thành công!</h2>
      <p>Thông báo thành công khi tạo mới category.</p>
      <Link to="/dashboard">
        <button>Quay lại Trang danh sách</button>
      </Link>
    </div>
  );
};

export default Success;
