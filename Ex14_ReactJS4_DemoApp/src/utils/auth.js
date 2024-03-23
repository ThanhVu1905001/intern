// auth.js

const isAuthenticated = () => {
  // Kiểm tra và trả về trạng thái đăng nhập từ localStorage
  return localStorage.getItem('accessToken') !== null;
};

const login = (token) => {
  // Lưu token vào localStorage khi người dùng đăng nhập thành công
  localStorage.setItem('accessToken', token);
};

const logout = () => {
  // Xử lý đăng xuất: xóa token từ localStorage
  localStorage.removeItem('accessToken');
};

export { isAuthenticated, login, logout };
