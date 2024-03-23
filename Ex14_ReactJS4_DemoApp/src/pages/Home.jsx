import React from 'react';
import { login } from '../utils/auth'; // Import hàm xác thực
import Login from '../components/Login';

const Home = ({ setLoggedIn }) => {
  const handleLogin = () => {
    // Xử lý đăng nhập - ví dụ: gọi hàm login để đánh dấu người dùng đã đăng nhập
    login();
    setLoggedIn(true);
  };

  return (
    <div>
      <h1>Chào mừng đến với ứng dụng của chúng tôi!</h1>
      <Login handleLogin={handleLogin} setLoggedIn={setLoggedIn} />
    </div>
  );
};

export default Home;
