package sample.Text_Similarity;

import java.io.File;
import java.util.ArrayList;

public class Folder {
    String path;
    File list[];

    public Folder(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File[] getList() {
        return list;
    }

    public void setList(File[] list) {
        this.list = list;
    }

    public void Reader(){
        File folder = new File(getPath());
        File[] listOfFiles = folder.listFiles();
        setList(listOfFiles);
    }
}
