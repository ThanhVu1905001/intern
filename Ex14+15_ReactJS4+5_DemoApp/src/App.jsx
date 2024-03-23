import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import CategoryDetailPage from './pages/CategoryDetailPage';
import CreateCategoryPage from './pages/CreateCategoryPage';
import Success from './components/Success';
import { AuthProvider, useAuth } from './utils/auth'; // Import useAuth từ context

function App() {
  const { loggedIn, handleLogout } = useAuth(); // Sử dụng hook useAuth để truy cập trạng thái đăng nhập và hàm handleLogout từ context

  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={loggedIn ? <Navigate to="/dashboard" /> : <Home />}
        />
        <Route
          path="/dashboard"
          element={loggedIn ? <Dashboard /> : <Navigate to="/" />}
        />
        <Route
          path="/category/:categoryId"
          element={loggedIn ? <CategoryDetailPage /> : <Navigate to="/" />}
        />
        <Route
          path="/create-category"
          element={loggedIn ? <CreateCategoryPage /> : <Navigate to="/" />}
        />
        <Route path="/success" element={<Success />} />
      </Routes>
    </Router>
  );
}

export default App;
