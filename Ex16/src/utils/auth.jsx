import React, { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

export const useAuth = () => {
  return useContext(AuthContext);
};

export const AuthProvider = ({ children }) => {
  const [loggedIn, setLoggedIn] = useState(false);

  const isAuthenticated = () => {
    // Kiểm tra và trả về trạng thái đăng nhập từ localStorage
    return localStorage.getItem('accessToken') !== null;
  };

  const login = (token) => {
    // Lưu token vào localStorage khi người dùng đăng nhập thành công
    localStorage.setItem('accessToken', token);
    setLoggedIn(true);
  };

  const logout = () => {
    // Xử lý đăng xuất: xóa token từ localStorage
    localStorage.removeItem('accessToken');
    setLoggedIn(false);
  };

  const contextValue = {
    loggedIn,
    isAuthenticated,
    login,
    logout,
  };

  return (
    <AuthContext.Provider value={contextValue}>
      {children}
    </AuthContext.Provider>
  );
};
