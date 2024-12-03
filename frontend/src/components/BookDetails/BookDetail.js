import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import './BookDetail.css';

const BookDetail = () => {
    const {id} = useParams();
    const [book, setBook] = useState(null);
    const [bookCards, setBookCards] = useState([]);
    const [currentCardIndex, setCurrentCardIndex] = useState(0);
    const [error, setError] = useState(null);
    const navigate = useNavigate();  // Crea l'istanza del navigatore

    const formatDate = (date) => {
        const d = new Date(date);
        const day = String(d.getDate()).padStart(2, '0');
        const month = String(d.getMonth() + 1).padStart(2, '0');
        const year = d.getFullYear();
        return `${day}/${month}/${year}`;
    };

    const fetchBookDetails = async () => {
        try {
            const response = await fetch(`http://localhost:8080/book/findById/${id}`);
            if (!response.ok) {
                throw new Error(`Error in fetching book details: ${response.status}`);
            }
            const data = await response.json();
            setBook(data);
        } catch (err) {
            setError(err.message);
        }
    };

    const fetchBookCards = async () => {
        try {
            const response = await fetch(`http://localhost:8080/bookCard/findByBook/${id}`);
            if (!response.ok) {
                throw new Error(`Error in fetching book cards: ${response.status}`);
            }
            const data = await response.json();
            setBookCards(data);
        } catch (err) {
            setError(err.message);
        }
    };

    useEffect(() => {
        const fetchData = async () => {
            fetchBookDetails();
            fetchBookCards();
        };
        fetchData();
    }, [id]);

    const handlePrevCard = () => {
        setCurrentCardIndex((prevIndex) =>
            prevIndex === 0 ? bookCards.length - 1 : prevIndex - 1
        );
    };

    const handleNextCard = () => {
        setCurrentCardIndex((prevIndex) =>
            prevIndex === bookCards.length - 1 ? 0 : prevIndex + 1
        );
    };

    if (error) {
        return <div className="error">Error: {error}</div>;
    }

    if (!book) {
        return <div>Loading book details...</div>;
    }

    return (
        <div className="book-detail-container">
            <header className="book-detail-header">
                <button onClick={() => navigate('/')} className="btn btn-secondary mb-3">
                    Back to Home
                </button>
                {/* Pulsante Back */}
                <h2>{book.title}</h2>
                <p><strong>Author:</strong> {book.author}</p>
                <p><strong>Genre:</strong> {book.genre}</p>
                <p><strong>Pages:</strong> {book.pages}</p>
                <p><strong>Published Date:</strong> {formatDate(book.publishedYear)}</p>
            </header>

            {bookCards.length > 0 && (
                <div className="book-card-section">
                    <h3>Book Cards</h3>
                    <div className="book-card-slider">
                        <button className="prev-button" onClick={handlePrevCard}>
                            &lt;
                        </button>
                        <div className="book-card">
                            <h4>{bookCards[currentCardIndex].title}</h4>
                            <p>{bookCards[currentCardIndex].body}</p>
                            {bookCards[currentCardIndex].image && (
                                <img
                                    src={bookCards[currentCardIndex].image}
                                    alt="Book card"
                                />
                            )}
                        </div>
                        <button className="next-button" onClick={handleNextCard}>
                            &gt;
                        </button>
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
