import React from 'react';
import Login from '../Login';
import { useAuth } from '../utils/auth';
import './Home.css';
const Home: React.FC = () => {
  const { login } = useAuth();

  const handleLogin = (token: string) => {
    // Thực hiện xác thực với token nhận được từ quá trình đăng nhập
    login(token);
  };

  return (
    <div className='home-container'>
      <h1 className='home-title'>Chào mừng đến với ứng dụng của chúng tôi!</h1>
      <Login handleLogin={handleLogin} />
    </div>
  );
};

export default Home;

