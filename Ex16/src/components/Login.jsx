import React, { useState } from 'react';
import { useAuth } from '../utils/auth';
import axios from 'axios';

const Login = () => {
  const { login } = useAuth();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios({
        url: 'http://localhost:8089/api/auth/login',
        method: 'POST',
        data: {
          username: username,
          password: password,
        },
      });

      if (response.status === 200) {
        login(response.data.accessToken); // Sử dụng hàm login từ context
      } else {
        setError('Tên đăng nhập hoặc mật khẩu không đúng');
      }
    } catch (error) {
      setError('Đã xảy ra lỗi khi đăng nhập');
      console.error('Login error:', error);
    }
  };

  return (
    <div>
      <h2>Đăng nhập</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Tên đăng nhập:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            style={{
              width: '30%',
              padding: '8px',
              marginTop: '5px',
              border: '1px solid #ccc',
              borderRadius: '4px',
              fontSize: '16px',
              /* Thêm các thuộc tính CSS tùy chỉnh khác theo nhu cầu của bạn */
            }}
          />
        </div>
        <div>
          <label>Mật khẩu:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            style={{
              width: '30%',
              padding: '8px',
              marginTop: '5px',
              border: '1px solid #ccc',
              borderRadius: '4px',
              fontSize: '16px',
            }}
          />
        </div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <button type="submit">Đăng nhập</button>
      </form>
    </div>
  );
};

export default Login;
