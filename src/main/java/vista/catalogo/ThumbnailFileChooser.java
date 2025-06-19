package vista.catalogo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileView;

public class ThumbnailFileChooser extends JFileChooser {

    /** All preview icons will be this width and height */
    private static final int ICON_SIZE = 60;

    /** This blank icon will be used while previews are loading */
    private static final Image LOADING_IMAGE = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);

    /** Edit this to determine what file types will be previewed. */
    private final Pattern imageFilePattern = Pattern.compile(".+?\\.(png|jpe?g|gif|tiff?)$", Pattern.CASE_INSENSITIVE);

    /** Use a weak hash map to cache images until the next garbage collection (saves memory) */
    private final Map<File, ImageIcon> imageCache = new WeakHashMap<>();

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        ThumbnailFileChooser chooser = new ThumbnailFileChooser();
        chooser.showOpenDialog(null);
        System.exit(1);
    }

    public ThumbnailFileChooser() {
        super();
        // Set file selection mode to files only
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Add a file filter for images
        setFileFilter(new ImageFileFilter());
        // Apply the custom thumbnail view
        setFileView(new ThumbnailView());
    }



    {
  
    }

    private class ThumbnailView extends FileView {
        /** This thread pool is where the thumbnail icon loaders run */
        private final ExecutorService executor = Executors.newCachedThreadPool();

        public Icon getIcon(File file) {
            if (!imageFilePattern.matcher(file.getName()).matches()) {
                return null;
            }

            synchronized (imageCache) {
                ImageIcon icon = imageCache.get(file);

                if (icon == null) {
                    // Create a new icon with the default image
                    icon = new ImageIcon(LOADING_IMAGE);
                    imageCache.put(file, icon);
                    executor.submit(new ThumbnailIconLoader(icon, file));
                }
                return icon;
            }
        }
    }

    private class ThumbnailIconLoader implements Runnable {
        private final ImageIcon icon;
        private final File file;

        public ThumbnailIconLoader(ImageIcon i, File f) {
            icon = i;
            file = f;
        }

        public void run() {
            System.out.println("Loading image: " + file);
            ImageIcon newIcon = new ImageIcon(file.getAbsolutePath());
            Image img = newIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
            icon.setImage(img);
            SwingUtilities.invokeLater(new Runnable() {public void run() {repaint();}});
        }
    }

    private class ImageFileFilter extends FileFilter {
        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return false; // Exclude directories
            }
            return imageFilePattern.matcher(file.getName()).matches();
        }

        @Override
        public String getDescription() {
            return "Image Files (*.png, *.jpg, *.jpeg,*.tiff)";
        }
    }
}