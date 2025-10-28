package ua.kpi.iasa.onlineradio;

import ua.kpi.iasa.onlineradio.data.*;
import ua.kpi.iasa.onlineradio.models.*;
import ua.kpi.iasa.onlineradio.repositories.*;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Ініціалізація тестового середовища ---");

        // 1. Створюємо "чисті" репозиторії
        ITrackRepository trackRepository = new TrackRepository();
        IUserRepository userRepository = new UserRepository();
        IPlaylistRepository playlistRepository = new PlaylistRepository();

        // 2. Створюємо та зберігаємо тестові дані
        // Створюємо користувачів
        User admin = new Administrator(0, "admin", "hash_of_admin_pass");
        User listener = new User(0, "listener1", "hash_of_user_pass");
        userRepository.save(admin);
        userRepository.save(listener);
        System.out.println("Створено користувачів.");
        
        // Створюємо треки
        Track track1 = new Track(0, "Bohemian Rhapsody", "Queen", "/music/queen_bohemian.mp3");
        Track track2 = new Track(0, "Smells Like Teen Spirit", "Nirvana", "/music/nirvana_smells.mp3");
        trackRepository.save(track1);
        trackRepository.save(track2);
        System.out.println("Створено треки.");

        // Створюємо плейлист і наповнюємо його треками
        Playlist rockPlaylist = new Playlist(0, "Rock Classics");
        rockPlaylist.addTrack(track1); // Додаємо вже існуючі об'єкти
        rockPlaylist.addTrack(track2);
        playlistRepository.save(rockPlaylist);
        System.out.println("Створено плейлист.");
        
        System.out.println("\n--- Тестування репозиторіїв ---");
        
        System.out.println("\nВсі треки в бібліотеці:");
        trackRepository.findAll().forEach(System.out::println);

        System.out.println("\nВсі користувачі:");
        userRepository.findAll().forEach(System.out::println);

        System.out.println("\nПлейлист 'Rock Classics':");
        Optional<Playlist> foundPlaylist = playlistRepository.findById(1);
        foundPlaylist.ifPresent(p -> {
            System.out.println("Знайдено: " + p);
            System.out.println("Треки в ньому:");
            p.getTracks().forEach(t -> System.out.println("  - " + t.getTitle()));
        });
    }
}