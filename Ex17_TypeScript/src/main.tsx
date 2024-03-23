
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.css';
import { AuthProvider } from './components/utils/auth';

let root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);

root.render(
  <AuthProvider>
    <App />
  </AuthProvider>
);
