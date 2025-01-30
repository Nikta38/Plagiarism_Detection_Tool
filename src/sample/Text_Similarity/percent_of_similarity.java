package sample.Text_Similarity;

public class percent_of_similarity {
    private percent_of_similarity(){}

    public static double similarity(int similar, int source){
        double res=(((double) similar)/source)*100;
        return res;
    }
}
