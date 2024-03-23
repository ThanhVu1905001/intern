
import React from 'react';
import Login from './component/Login.jsx';

const App = () => {
  const isLoggedIn = localStorage.getItem('loggedInUser');


  return (
    <>
      <Login />
    </>
  )
}

export default App
