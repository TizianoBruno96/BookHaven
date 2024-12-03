import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Importa useNavigate
import './BookList.css';

const BookList = () => {
    const [bookDebugInfo, setBookDebugInfo] = useState(null);
    const [error, setError] = useState(null);
    const [books, setBooks] = useState([]);
    const [sortConfig, setSortConfig] = useState({ key: '', direction: 'asc' });

    const navigate = useNavigate(); // Hook per la navigazione

    const fetchBooks = async () => {
        try {
            const response = await fetch('http://localhost:8080/book/listAll');
            const debugData = {
                status: response.status,
                statusText: response.statusText,
                headers: Object.fromEntries(response.headers.entries()),
            };

            if (!response.ok) {
                setBookDebugInfo(debugData);
                throw new Error("Error in fetching books API");
            }

            const data = await response.json();
            debugData.body = JSON.stringify(data, null, 2);

            setBookDebugInfo(debugData);
            setBooks(data);
            setError(null);
        } catch (err) {
            setBookDebugInfo(null);
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
        setSortConfig({ key: columnKey, direction: direction });
    };

    const handleBookClick = (bookId) => {
        navigate(`/book/${bookId}`); // Naviga alla pagina del dettaglio del libro
    };

    const getSortArrow = (columnKey) => {
        if (sortConfig.key === columnKey) {
            return sortConfig.direction === 'asc' ? '↑' : '↓';
        }
        return '';
    };

    return (
        <div className="container">
            <div className="booklist-header">
                <button onClick={fetchBooks}>Load Books</button>
            </div>

            {bookDebugInfo && (
                <div>
                    <h2>Debug</h2>
                    <pre>{JSON.stringify(bookDebugInfo, null, 2)}</pre>
                </div>
            )}

            {error && <div className="error">Error: {error}</div>}

            {books.length > 0 && (
                <div>
                    <table>
                        <thead>
                        <tr>
                            <th onClick={() => handleSort('title')}>
                                Title {getSortArrow('title')}
                            </th>
                            <th onClick={() => handleSort('author')}>
                                Author {getSortArrow('author')}
                            </th>
                            <th onClick={() => handleSort('pages')}>
                                Pages {getSortArrow('pages')}
                            </th>
                            <th onClick={() => handleSort('genre')}>
                                Genre {getSortArrow('genre')}
                            </th>
                            <th onClick={() => handleSort('publishedYear')}>
                                Year {getSortArrow('publishedYear')}
                            </th>
                            <th>Description</th>
                            <th>URL</th>
                        </tr>
                        </thead>
                        <tbody>
                        {books.map((book) => (
                            <tr key={book.id} onClick={() => handleBookClick(book.id)}>
                                <td>{book.title}</td>
                                <td>{book.author}</td>
                                <td>{book.pages}</td>
                                <td>{book.genre}</td>
                                <td>{new Date(book.publishedYear).toLocaleDateString()}</td>
                                <td>{book.description}</td>
                                <td>
                                    <a href={book.url} target="_blank" rel="noopener noreferrer">
                                        Link
                                    </a>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default BookList;
