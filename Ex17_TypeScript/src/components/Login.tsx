import React, { useState } from 'react';
import { useAuth } from './utils/auth';
import axios from 'axios';

interface LoginProps {
  handleLogin: (token: string) => void;
}

const Login: React.FC<LoginProps> = ({ handleLogin }) => {
  const { login } = useAuth();
  const [username, setUsername] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [error, setError] = useState<string>('');

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
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
        login(response.data.accessToken);
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
            className='login-section'
          />
        </div>
        <div>
          <label>Mật khẩu:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className='login-section'
          />
        </div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <button type="submit">Đăng nhập</button>
      </form>
    </div>
  );
};

export default Login;
