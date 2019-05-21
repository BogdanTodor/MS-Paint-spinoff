import java.io.File;
import javax.swing.filechooser.FileFilter;

/** Used to set the filter for the file format type in scope and to accept the select in the save/open menu.*/
public class FilterFileType extends FileFilter {
    /** The file format to filter by.*/
    private String extension;
    /** The description of the filetype chosen*/
    private String description;

    /** Sets the file format and description for the save file and open file menu.
     * @param extension The chosen file format type to filter by.
     * @param description A description for the extension.*/
    public FilterFileType(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }

    /** Accepts the file selection if in the directory.
     * @param file The name of the file.
     * @return The file name with the new file extension.*/
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        return file.getName().endsWith(extension);
    }

    /** @return The description of the file extension.*/
    public String getDescription() {
        return description + String.format(" (*%s)", extension);
    }
}