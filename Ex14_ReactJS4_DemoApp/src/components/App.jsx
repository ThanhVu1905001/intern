import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import CategoryDetailPage from './pages/CategoryDetailPage';
import CreateCategoryPage from './pages/CreateCategoryPage';
import { isAuthenticated, logout } from './utils/auth';
import Success from './components/Success';

function App() {
  const [loggedIn, setLoggedIn] = useState(isAuthenticated()); // Kiểm tra trạng thái đăng nhập

  const handleLogout = () => {
    logout();
    setLoggedIn(false);
  };

  return (
    <Router>
      <Routes>
        <Route path="/" element={loggedIn ? <Navigate to="/dashboard" /> : <Home setLoggedIn={setLoggedIn} />} />
        <Route path="/dashboard" element={loggedIn ? <Dashboard handleLogout={handleLogout} /> : <Navigate to="/" />} />
        <Route path="/category/:categoryId" element={loggedIn ? <CategoryDetailPage handleLogout={handleLogout} /> : <Navigate to="/" />} />
        <Route path="/create-category" element={loggedIn ? <CreateCategoryPage handleLogout={handleLogout} /> : <Navigate to="/" />} />
        <Route path="/success" element={<Success />} />
      </Routes>
    </Router>
  );
}

export default App;

