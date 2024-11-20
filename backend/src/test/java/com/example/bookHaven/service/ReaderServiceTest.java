package com.example.bookHaven.service;

import com.example.bookHaven.entity.Reader;
import com.example.bookHaven.repository.ReaderRepository;
import com.example.bookHaven.service.implementation.ReaderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReaderServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @InjectMocks
    private ReaderService readerService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_shouldCreateNewReader() {
        when(readerRepository.existsByUsername("testUser")).thenReturn(false);

        Reader reader = new Reader();
        reader.setUsername("testUser");

        boolean result = readerService.create(reader);

        verify(readerRepository, times(1)).save(reader);
        assertFalse(result);
    }


    @Test
    void create_shouldNotCreateExistingReader() {
        Reader reader = new Reader();
        reader.setUsername("existingUser");

        when(readerRepository.existsByUsername("existingUser")).thenReturn(true);

        boolean result = readerService.create(reader);

        verify(readerRepository, never()).save(any());
        assertFalse(result);
    }

    @Test
    void update_shouldUpdateExistingReader() {
        Reader reader = new Reader();
        reader.setUsername("testUser");

        when(readerRepository.existsByUsername("testUser")).thenReturn(true);
        when(readerRepository.findByUsername("testUser")).thenReturn(Optional.of(reader));

        boolean result = readerService.update(reader);

        verify(readerRepository, times(1)).save(reader);
        assertTrue(result);
    }

    @Test
    void update_shouldNotUpdateNonExistingReader() {
        Reader reader = new Reader();
        reader.setUsername("nonExistingUser");

        when(readerRepository.existsByUsername("nonExistingUser")).thenReturn(false);

        boolean result = readerService.update(reader);

        verify(readerRepository, never()).save(any());
        assertFalse(result);
    }

    @Test
    void findById_shouldReturnReader() {
        Reader reader = new Reader();
        reader.setId("1");

        when(readerRepository.findById("1")).thenReturn(Optional.of(reader));

        Reader result = readerService.findById("1");

        assertEquals(reader, result);
    }

    @Test
    void findById_shouldReturnNullIfNotFound() {
        when(readerRepository.findById("1")).thenReturn(Optional.empty());

        Reader result = readerService.findById("1");

        assertNull(result);
    }

    @Test
    void deleteById_shouldDeleteReader() {
        when(readerRepository.existsById("1")).thenReturn(true).thenReturn(false);

        boolean result = readerService.deleteById("1");

        verify(readerRepository, times(1)).deleteById("1");
        assertTrue(result);
    }

    @Test
    void deleteById_shouldReturnFalseIfReaderDoesNotExist() {
        when(readerRepository.existsById("1")).thenReturn(false);

        boolean result = readerService.deleteById("1");

        verify(readerRepository, never()).deleteById("1");
        assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("provideDataForExistsMethods")
    void existMethods_shouldReturnCorrectResults(String input, boolean expected, boolean isUsername) {
        if (isUsername) {
            when(readerRepository.existsByUsername(input)).thenReturn(expected);
            assertEquals(expected, readerService.existByUsername(input));
        } else {
            when(readerRepository.existsByEmail(input)).thenReturn(expected);
            assertEquals(expected, readerService.existByEmail(input));
        }
    }

    private static Stream<Arguments> provideDataForExistsMethods() {
        return Stream.of(
                Arguments.of("testUser", true, true),
                Arguments.of("testUser", false, true),
                Arguments.of("testEmail@test.com", true, false),
                Arguments.of("testEmail@test.com", false, false)
        );
    }

    @Test
    void listAll_shouldReturnAllReaders() {
        List<Reader> readers = new ArrayList<>();
        Reader reader1 = new Reader();
        reader1.setUsername("reader1");
        Reader reader2 = new Reader();
        reader2.setUsername("reader2");
        readers.add(reader1);
        readers.add(reader2);

        when(readerRepository.findAll()).thenReturn(readers);

        List<Reader> result = readerService.listAll();

        assertEquals(2, result.size());
        assertEquals(readers, result);
    }

    @Test
    void count_shouldReturnReaderCount() {
        when(readerRepository.count()).thenReturn(5L);

        long result = readerService.count();

        assertEquals(5L, result);
    }

    @Test
    void checkUserPassword_shouldReturnTrueForValidCredentials() {
        when(readerRepository.existsByUsernameAndPassword("testUser", "password123")).thenReturn(true);

        boolean result = readerService.checkUserPassword("testUser", "password123");

        assertTrue(result);
    }

    @Test
    void checkUserPassword_shouldReturnFalseForInvalidCredentials() {
        when(readerRepository.existsByUsernameAndPassword("testUser", "wrongPassword")).thenReturn(false);

        boolean result = readerService.checkUserPassword("testUser", "wrongPassword");

        assertFalse(result);
    }

    @Test
    void addFriend_shouldAddFriendSuccessfully() {
        String readerId = "1";
        String friendId = "2";

        Reader reader = new Reader();
        reader.setId(readerId);
        reader.setFriends(new ArrayList<>());

        Reader friend = new Reader();
        friend.setId(friendId);

        when(readerRepository.findById(readerId)).thenReturn(Optional.of(reader));
        when(readerRepository.findById(friendId)).thenReturn(Optional.of(friend));

        // Act
        readerService.addFriend(readerId, friendId);

        // Assert
        verify(readerRepository, times(1)).findById(readerId);
        verify(readerRepository, times(1)).findById(friendId);
        verify(readerRepository, times(1)).save(reader);

        assert reader.getFriends().contains(friend);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidFriendshipScenarios")
    void addFriend_shouldHandleInvalidScenarios(String readerId, String friendId, Optional<Reader> reader, Optional<Reader> friend) {
        // Arrange
        when(readerRepository.findById(readerId)).thenReturn(reader);
        when(readerRepository.findById(friendId)).thenReturn(friend);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> readerService.addFriend(readerId, friendId));

        verify(readerRepository, times(1)).findById(readerId);
        verify(readerRepository, times(1)).findById(friendId);
        verify(readerRepository, never()).save(any());
    }

    private static Stream<Arguments> provideInvalidFriendshipScenarios() {
        return Stream.of(
                Arguments.of("1", "2", Optional.empty(), Optional.of(new Reader())),
                Arguments.of("1", "2", Optional.of(new Reader()), Optional.empty()),
                Arguments.of("1", "2", Optional.empty(), Optional.empty())
        );
    }
}
