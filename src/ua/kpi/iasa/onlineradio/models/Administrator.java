package ua.kpi.iasa.onlineradio.models;

public class Administrator extends User {

    public Administrator(int id, String username, String passwordHash) {
        // Викликаємо конструктор батьківського класу User
        super(id, username, passwordHash);
    }

    public void manageStation(RadioStation station) {
        System.out.println("Адміністратор " + getUsername() + " керує станцією " + station.getName());
        // Тут могла б бути логіка виклику методів для управління плейлистами,
        // бібліотекою треків, налаштуваннями трансляції тощо.
    }

    @Override
    public String toString() {
        return "Administrator{" +
               "id=" + getId() +
               ", username='" + getUsername() + '\'' +
               '}';
    }
}