import React from 'react';
import Login from '../components/Login';
import { useAuth } from '../utils/auth'; // Sử dụng hook useAuth thay vì import hàm login từ utils/auth

const Home = () => {
  const { login } = useAuth(); // Sử dụng hook useAuth để truy cập hàm login từ context

  const handleLogin = () => {
    // Gọi hàm login từ context để đánh dấu người dùng đã đăng nhập
    login();
  };

  return (
    <div>
      <h1>Chào mừng đến với ứng dụng của chúng tôi!</h1>
      <Login handleLogin={handleLogin} />
    </div>
  );
};

export default Home;
