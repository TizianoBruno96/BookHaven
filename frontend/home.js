const API_URL = "http://localhost:8080/reader"; // Cambia con l'URL della tua API

// Funzione per creare un lettore
function createReader() {
    const username = document.getElementById('createUsername').value;
    const email = document.getElementById('createEmail').value;

    const requestBody = {
        username: username,
        email: email
    };

    fetch(`${API_URL}/create`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('message').textContent = "Lettore creato con successo!";
    })
    .catch(error => {
        document.getElementById('message').textContent = `Errore: ${error.message}`;
    });
}

// Funzione per aggiungere un amico
function addFriend() {
    const readerId = document.getElementById('readerId').value;
    const friendId = document.getElementById('friendId').value;

    fetch(`${API_URL}/addFriend?readerId=${readerId}&friendId=${friendId}`, {
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('message').textContent = "Amico aggiunto con successo!";
    })
    .catch(error => {
        document.getElementById('message').textContent = `Errore: ${error.message}`;
    });
}

// Funzione per elencare tutti i lettori
function listAllReaders() {
    fetch(`${API_URL}/listAll`)
    .then(response => response.json())
    .then(data => {
        const readerListDiv = document.getElementById('readerList');
        readerListDiv.innerHTML = '';  // Pulisce la lista precedente

        data.forEach(reader => {
            const readerDiv = document.createElement('div');
            readerDiv.classList.add('reader-item');
            readerDiv.innerHTML = `
                <h3>${reader.username}</h3>
                <p>Email: ${reader.email}</p>
            `;
            readerListDiv.appendChild(readerDiv);
        });
    })
    .catch(error => {
        document.getElementById('message').textContent = `Errore: ${error.message}`;
    });
}

// Funzione per cercare un lettore per ID
function findReaderById() {
    const id = document.getElementById('searchId').value;

    fetch(`${API_URL}/findById/${id}`)
    .then(response => response.json())
    .then(data => {
        if (data) {
            document.getElementById('message').textContent = `Lettore trovato: ${data.username}`;
        } else {
            document.getElementById('message').textContent = 'Lettore non trovato.';
        }
    })
    .catch(error => {
        document.getElementById('message').textContent = `Errore: ${error.message}`;
    });
}
