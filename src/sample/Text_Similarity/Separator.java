package sample.Text_Similarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Separator {
    public List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void Separate(String text){
        List<String> res=new ArrayList<>(Arrays.asList(text.split("\\.")));
        for(int i=0;i<res.size();i++) {
            if(res.get(i).equals("")){
                res.remove(i);
                i--;
            }
            else {
                String temp = res.get(i).trim();
                res.remove(i);
                res.add(i, temp);
                if (res.get(i).contains("!")) {
                    String[] res2 = res.get(i).split("\\!");
                    res.remove(i);
                    boolean b=false;
                    for (int j = 0; j < res2.length; j++) {
                        res2[j] = res2[j].trim();
                        res.add(i, res2[j]);
                        i++;
                        b=true;
                    }
                    if (b==true)                                                                                                          //بار اخر که حلقه اجرا میشه یکی i اضافه میکنه که اضافیه
                        i--;
                }
            }
        }
        for(int i=0;i<res.size();i++) {
            if(res.get(i).contains("?")) {
                String[] res3 = res.get(i).split("\\?");
                if (res3.length != 0) {
                    res.remove(i);
                    boolean b=false;
                    for (int j = 0; j < res3.length; j++) {
                        res3[j]=res3[j].trim();
                        res.add(i, res3[j]);
                        i++;
                        b=true;
                    }
                    if (b==true)
                        i--;
                }
            }
        }
        setList(res);
    }
}
