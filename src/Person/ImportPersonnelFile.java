package Person;

import Utilities.OracleConnection;
import Utilities.ShowAlertDialogue;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportPersonnelFile {
    Path to;

    public boolean addPerson(String sql) {
        String fileName = getFileDirectory();

        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, fileName);
            ps.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
  //      removeFile();
        return false;
    }

    private void removeFile() {
        try {
            Files.delete(to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileDirectory() {
        String fileName = null;
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            fileName = file.getName();
            copyFileToDiffDirectory(fileName, file);
        }

        return fileName;
    }

    private void copyFileToDiffDirectory(String fileName, File file) {
        Path from = Paths.get(file.toURI());
        to = Paths.get("D:\\csv\\" + fileName);
        try {
            Files.copy(from, to);
        } catch (IOException e) {
            e.printStackTrace();
            new ShowAlertDialogue().infoBox("The selected file is already added\nPlease choose another file", null, "Add a File");
        }
    }

    private void configureFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle("Add Employee");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All file", "*.*"),
                new FileChooser.ExtensionFilter("TEXT", "*.txt"),
                new FileChooser.ExtensionFilter("CSV", "*.csv")
        );
    }
}
