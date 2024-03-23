import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { AuthProvider } from './utils/auth'; // Import AuthProvider từ context

ReactDOM.createRoot(document.getElementById('root')).render(
      <AuthProvider> {/* Bao bọc toàn bộ ứng dụng với AuthProvider */}
        <App />
      </AuthProvider>
)
