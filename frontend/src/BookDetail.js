import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './BookDetail.css';

const BookDetail = () => {
    const { id } = useParams();
    const [book, setBook] = useState(null);
    const [bookCards, setBookCards] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const fetchBookDetails = async () => {
        try {
            const response = await fetch(`http://localhost:8080/book/${id}`);
            const data = await response.json();

            console.log(data);

            if (!response.ok) {
                throw new Error("Error in fetching book details");
            }

            setBook(data);
        } catch (err) {
            setError(err.message);
        }
    };

    const fetchBookCards = async () => {
        try {
            const response = await fetch(`http://localhost:8080/bookCard/findByBook/${id}`);
            const data = await response.json();

            console.log(data);

            if (!response.ok) {
                throw new Error("Error in fetching book cards");
            }

            setBookCards(data);
        } catch (err) {
            setError(err.message);
        }
    };

    useEffect(() => {
        fetchBookDetails();
        fetchBookCards();
    }, [fetchBookCards, fetchBookDetails, id]);

    if (error) {
        return <div className="error">Error: {error}</div>;
    }

    if (!book) {
        return <div>Loading book details...</div>;
    }

    return (
        <div className="book-detail-container">
            <header className="book-detail-header">
                <button onClick={() => navigate('/')}>Back to List</button>
                <h2>{book.title}</h2>
                <p><strong>Author:</strong> {book.author}</p>
                <p><strong>Genre:</strong> {book.genre}</p>
                <p><strong>Pages:</strong> {book.pages}</p>
                <p><strong>Published Year:</strong> {book.publishedYear}</p>
            </header>

            {bookCards.length > 0 && (
                <div className="book-card-section">
                    <h3>Book Card</h3>
                    <div className="book-card">
                        <h4>{bookCards[0].title}</h4> {}
                        <p>{bookCards[0].body}</p> {}
                        {bookCards[0].image && <img src={bookCards[0].image} alt="Book card" />}
                    </div>
                </div>
            )}

            <footer className="book-detail-footer">
                <p>© 2024 - BookHaven</p>
            </footer>
        </div>
    );
};

export default BookDetail;
