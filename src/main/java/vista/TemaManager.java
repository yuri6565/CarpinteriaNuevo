package vista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemaManager {
    private static final String ARCHIVO = "config.txt";
    private static TemaManager instance;
    private boolean oscuro = true;
    private List<Runnable> listeners = new ArrayList<>();

    private TemaManager() {
        oscuro = cargarTema();
    }

    public static TemaManager getInstance() {
        if (instance == null) {
            instance = new TemaManager();
        }
        return instance;
    }

    public void guardarTema(boolean oscuro) {
        this.oscuro = oscuro;
        try (FileWriter fw = new FileWriter(ARCHIVO)) {
            fw.write(oscuro ? "oscuro" : "blanco");
            notifyListeners();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean cargarTema() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea = br.readLine();
            return "oscuro".equalsIgnoreCase(linea);
        } catch (IOException e) {
            return true;
        }
    }

    public boolean isOscuro() {
        return oscuro;
    }

    public void addThemeChangeListener(Runnable listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (Runnable listener : listeners) {
            listener.run();
        }
    }
}