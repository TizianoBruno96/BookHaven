import React from 'react';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import BookList from './components/BookList/BookList';
import BookDetail from './components/BookDetails/BookDetail';
import SignInPage from './components/SignIn/SignInPage';
import ProfilePage from './components/Profile/ProfilePage';
import Footer from './components/Home/Footer/Footer';
import './App.css';

function App() {
    const user = JSON.parse(localStorage.getItem('user'));

    return (
        <Router>
            <div className="App">
                <header className="App-header">
                    <h1>Welcome to BookHaven</h1>
                    <p className="site-description">
                        Discover your next great read with BookHaven. Explore books, reviews, and more.
                    </p>
                    <div className="auth-section">
                        {user ? (
                            <Link to="/profile" className="user-profile-link">{user.username}</Link>
                        ) : (
                            <Link to="/signin" className="sign-in-button">Sign In</Link>
                        )}
                    </div>
                </header>

                <div className="App-main">
                    <Routes>
                        <Route path="/" element={<BookList/>}/>
                        <Route path="/book/:id" element={<BookDetail/>}/>
                        <Route path="/signin" element={<SignInPage/>}/>
                        <Route path="/profile" element={<ProfilePage/>}/>
                    </Routes>
                </div>

                <Footer/>
            </div>
        </Router>
    );
}

export default App;
