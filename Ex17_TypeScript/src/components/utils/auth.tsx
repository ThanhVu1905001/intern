import { createContext, useContext, useState, ReactNode } from 'react';

interface AuthContextProps {
  loggedIn: boolean;
  isAuthenticated: () => boolean;
  login: (token: string) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextProps>({
  loggedIn: false,
  isAuthenticated: () => false,
  login: (token: string) => {},
  logout: () => {},
});

export const useAuth = (): AuthContextProps => {
  return useContext(AuthContext);
};

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider = ({ children }: AuthProviderProps): JSX.Element => {
  const [loggedIn, setLoggedIn] = useState<boolean>(() => {
    return localStorage.getItem('accessToken') !== null;
  });

  const isAuthenticated = (): boolean => {
    const accessToken = localStorage.getItem('accessToken');
    return !!accessToken && accessToken !== 'undefined'; // Kiểm tra tính hợp lệ của accessToken
  };  

  const login = (token: string): void => {
    // Lưu token vào localStorage khi người dùng đăng nhập thành công
    localStorage.setItem('accessToken', token);
    setLoggedIn(true);
  };

  const logout = (): void => {
    // Xử lý đăng xuất: xóa token từ localStorage
    localStorage.removeItem('accessToken');
    setLoggedIn(false);
  };

  const contextValue: AuthContextProps = {
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
