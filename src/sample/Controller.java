package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import sample.Text_Similarity.Searcher;
import sample.Text_Similarity.Folder;
import sample.Text_Similarity.Text_Reader;
import sample.Text_Similarity.percent_of_similarity;

import java.io.File;
import java.util.Formatter;

public class Controller {
    @FXML private TextField folderpath;
    @FXML private TextField filepath;
    @FXML private TextArea res;

    private File file;
    private File sources;

    public void folderbt(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser=new DirectoryChooser();
        directoryChooser.setTitle("Select a folder");
        directoryChooser.setInitialDirectory(new File("\\C:"));
        sources=directoryChooser.showDialog(null);
        if(sources!=null)
            folderpath.setText(sources.getPath());
        else
            folderpath.setText("");
    }

    public void filebt(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Choose a file");
        fileChooser.setInitialDirectory(new File("\\C:"));
        file=fileChooser.showOpenDialog(null);
        if(file!=null)
            filepath.setText(file.getPath());
        else
            filepath.setText("");
    }

    public void check(ActionEvent actionEvent) {
        Text_Reader f=new Text_Reader(filepath.getText());
        Folder sources=new Folder(folderpath.getText());
        sources.Reader();

        Searcher manager=new Searcher(f);
        res.setText(manager.Search(sources.getList()));

//        temp+="_____________________________________________________________________________________________________________________________\n";
//        double p=percent_of_similarity.similarity(manager.getNum_of_similar_Words(),f.text.split(" ").length);
//        Formatter formatter = new Formatter();
//        formatter.format("%.2f", p);
//        temp+="percentage of similarity is "+ formatter.toString()+"%";
//        res.setText(temp);
    }
}
