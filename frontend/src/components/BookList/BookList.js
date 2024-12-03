import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import './BookList.css';

const BookList = () => {
    const [error, setError] = useState(null);
    const [books, setBooks] = useState([]);
    const [sortConfig, setSortConfig] = useState({key: '', direction: 'asc'});
    const [currentPage, setCurrentPage] = useState(1);
    const booksPerPage = 24;

    const navigate = useNavigate();

    const fetchBooks = async () => {
        try {
            const response = await fetch('http://localhost:8080/book/listAll');

            if (!response.ok) {
                throw new Error("Error in fetching books API");
            }

            const data = await response.json();

            setBooks(data);
            setError(null);
        } catch (err) {
            setBooks([]);
            setError(err.message);
        }
    };

    const handleSort = (columnKey) => {
        let direction = 'asc';
        if (sortConfig.key === columnKey && sortConfig.direction === 'asc') {
            direction = 'desc';
        }

        const sortedBooks = [...books].sort((a, b) => {
            if (a[columnKey] < b[columnKey]) return direction === 'asc' ? -1 : 1;
            if (a[columnKey] > b[columnKey]) return direction === 'asc' ? 1 : -1;
            return 0;
        });

        setBooks(sortedBooks);
        setSortConfig({key: columnKey, direction: direction});
    };

    const handleBookClick = (bookId) => {
        navigate(`/book/${bookId}`);
    };

    const handleNextPage = () => {
        if (currentPage < totalPages) {
            setCurrentPage(currentPage + 1);
        }
    };

    const handlePrevPage = () => {
        if (currentPage > 1) {
            setCurrentPage(currentPage - 1);
        }
    };

    useEffect(() => {
        fetchBooks();
    }, []);

    const formatDate = (date) => {
        const options = {day: '2-digit', month: '2-digit', year: 'numeric'};
        return new Date(date).toLocaleDateString('it-IT', options);
    };

    const totalPages = Math.ceil(books.length / booksPerPage);
    const currentBooks = books.slice((currentPage - 1) * booksPerPage, currentPage * booksPerPage);

    return (
        <div className="container">
            {error && <div className="error">Error: {error}</div>}

            {currentBooks.length > 0 && (
                <div>
                    <div className="book-cards-container">
                        {currentBooks.map((book) => (
                            <div
                                className="book-card"
                                key={book.id}
                                onClick={() => handleBookClick(book.id)}
                            >
                                <h3 className="book-card-title">{book.title}</h3>
                                <p className="book-card-author">{book.author}</p>
                                <p className="book-card-genre">{book.genre}</p>
                                <p className="book-card-year">{formatDate(book.publishedYear)}</p>
                            </div>
                        ))}
                    </div>

                    <div className="pagination">
                        <button
                            className="pagination-btn left"
                            onClick={handlePrevPage}
                            disabled={currentPage === 1}
                        >
                            &#8592;
                        </button>
                        <span>{currentPage} of {totalPages}</span>
                        <button
                            className="pagination-btn right"
                            onClick={handleNextPage}
                            disabled={currentPage === totalPages}
                        >
                            &#8594;
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default BookList;
