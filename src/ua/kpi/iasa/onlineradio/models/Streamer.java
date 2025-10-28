package ua.kpi.iasa.onlineradio.models;

// import ua.kpi.iasa.onlineradio.models.Playlist;
// import ua.kpi.iasa.onlineradio.models.Track;

/**
 * Сервісний клас, що імітує процес трансляції музики (ефіру).
 * Керує активним плейлистом та поточним треком.
 */
public class Streamer {
    private Playlist activePlaylist;
    private Track currentTrack;
    private int currentTrackIndex = -1;

    /**
     * Встановлює плейлист, який буде грати в ефірі.
     * @param playlist Плейлист для трансляції.
     */
    public void setActivePlaylist(Playlist playlist) {
        this.activePlaylist = playlist;
        this.currentTrackIndex = -1; // Скидаємо індекс при зміні плейлиста
        this.currentTrack = null;
        System.out.println("Активний плейлист змінено на: " + playlist.getName());
    }

    /**
     * Починає або продовжує відтворення.
     */
    public void play() {
        if (activePlaylist == null || activePlaylist.getTracks().isEmpty()) {
            System.out.println("Помилка: плейлист не встановлено або він порожній.");
            return;
        }

        if (currentTrack == null) {
            nextTrack();
        } else {
            System.out.println("▶️ Відтворення продовжено: " + currentTrack.getArtist() + " - " + currentTrack.getTitle());
        }
    }

    /**
     * Перемикає на наступний трек у плейлисті.
     */
    public void nextTrack() {
        if (activePlaylist == null || activePlaylist.getTracks().isEmpty()) {
            System.out.println("Неможливо перемкнути трек: плейлист не активний.");
            return;
        }

        currentTrackIndex++;
        // Якщо дійшли до кінця плейлиста, починаємо з початку
        if (currentTrackIndex >= activePlaylist.getTracks().size()) {
            currentTrackIndex = 0;
        }

        currentTrack = activePlaylist.getTracks().get(currentTrackIndex);
        System.out.println("🎧 Зараз в ефірі: " + currentTrack.getArtist() + " - " + currentTrack.getTitle());
    }

    public Track getCurrentTrack() {
        return currentTrack;
    }
}
