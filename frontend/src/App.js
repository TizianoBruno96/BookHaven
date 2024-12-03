import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import BookList from './BookList';
import BookDetail from './BookDetail'; // Nuovo componente per la pagina di dettaglio
import './App.css';

function App() {
    return (
        <Router>
            <div className="App">
                <header className="App-header">
                    <h1>BookHaven</h1>
                    <p className="developer-info">Developed by Tiziano Bruno</p>
                </header>

                <Routes>
                    <Route path="/" element={<BookList />} />
                    <Route path="/book/:id" element={<BookDetail />} /> {/* Aggiungi la rotta per il dettaglio del libro */}
                </Routes>

                <footer className="App-footer">
                    <p>© 2024 - BookHaven</p>
                </footer>
            </div>
        </Router>
    );
}

export default App;
