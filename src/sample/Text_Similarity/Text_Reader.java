package sample.Text_Similarity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Text_Reader {
    public String file_name;
    public List<String> sentences_list;
    public String text;

    public Text_Reader(String file_name) {
        setFile_name(file_name);
        this.sentences_list=new ArrayList<>();
        read();
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public List<String> getSentences_list() {
        return sentences_list;
    }

    public void setSentences_list(List<String> sentences_list) {
        this.sentences_list = sentences_list;
    }

    public void read(){
        String text="";
        String line =null;
        try {
            FileReader fr = new FileReader(file_name);

            BufferedReader br = new BufferedReader(fr);

            while( (line = br.readLine() ) != null ) {
                text+=" "+line;
            }

            br.close();

            Separator seperator=new Separator();

            seperator.Separate(text);
            this.sentences_list=seperator.getList();
            this.text=text;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
