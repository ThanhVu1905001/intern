import React, { useState } from 'react';
import Dashboard from './Dashboard';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [loggedIn, setLoggedIn] = useState(false);


    const handleLogin = () => {
        if (username === 'user' && password === 'pass') {
            setLoggedIn(true);
        } else {
            setError('Username/password không đúng');
        }
    };

    return (
        <>
            {loggedIn ? (
                <Dashboard username={username} />
            ) : (
                <form className='login'>
                    <div className='login-wrapper'>
                        <h2 className='login-title'>Login Form</h2>
                        <div className='justify-center'>
                            <input className='login-input' type="text" required placeholder='Username' value={username} onChange={(e) => setUsername(e.target.value)} />
                        </div>
                        <div className="justify-center">
                            <input className='login-input' type="password" required placeholder='Password' value={password} onChange={(e) => setPassword(e.target.value)} />
                        </div>
                        <div className='justify-center'>
                            <button type='submit' className='login-btn' onClick={handleLogin}>Login</button>
                        </div>
                        {error && 
                            <div className='justify-center login-error-message'>
                                {error}
                            </div>
                        }
                    </div>
                </form>
            )}
        </>
    );
};

export default Login;
