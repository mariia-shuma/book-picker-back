import java.sql.SQLException;
import java.util.ArrayList;


public class BookPicker {
    static String json = "{aaa:[{\"genre\":\"Classics\\n      \\n        \\n          4,044 users\"},{\"genre\":\"Fiction\\n      " +
            "\\n        \\n          3,215 users\"},{\"genre\":\"Literature\\n      \\n        \\n          " +
            "737 users\"},{\"genre\":\"European Literature >\\n        Irish Literature\\n      \\n        \\n          " +
            "487 users\"},{\"genre\":\"Cultural >\\n        Ireland\\n      \\n        \\n          428 users\"}," +
            "{\"genre\":\"Novels\\n      \\n        \\n          412 users\"},{\"genre\":\"Literature >\\n        " +
            "0th Century\\n      \\n        \\n          251 users\"},{\"genre\":\"Literary Fiction\\n      \\n        " +
            "\\n          174 users\"},{\"genre\":\"Academic >\\n        School\\n      \\n        \\n          " +
            "172 users\"},{\"genre\":\"Classics >\\n        Classic Literature\\n      \\n        \\n          " +
            "128 users\"}]}";
//    public static Array getArrayFromJson(String json){
////    }

    public static double getPercentOfGenres(int...numbers){
        ArrayList<Integer> integers = new ArrayList<Integer>();
        //integers.add(numbers);


        int suma = 0;
        for (int i : numbers) {
            suma = suma + i;
        }

        return 1;
    }





    public static void main(String[] args) throws SQLException  {
//        JSONArray arr = new JSONObject(json).getJSONArray("aaa");
//        for( int i = 0; i < arr.length(); i++){
//
//            JSONObject item = arr.getJSONObject(i);
//            String genre = item.getString("genre");
//        }


    GoodReadImporter gr = new GoodReadImporter();
//        gr.importData("G:\\www\\goodreads\\csv\\goodreads1-10.csv");
//        gr.importData("G:\\www\\goodreads\\csv\\goodreads11-20.csv");
        gr.importData("G:\\www\\goodreads\\csv\\goodreads21-30.csv");
//        gr.importData("G:\\www\\goodreads\\csv\\goodreads31-40.csv");
//        gr.importData("G:\\www\\goodreads\\csv\\goodreads41-50.csv");
//        gr.importData("G:\\www\\goodreads\\csv\\goodreads51-60.csv");
//        gr.importData("G:\\www\\goodreads\\csv\\goodreads61-70.csv");
//        gr.importData("G:\\www\\goodreads\\csv\\goodreads71-80.csv");
//        gr.importData("G:\\www\\goodreads\\csv\\goodreads81-90.csv");
//        gr.importData("G:\\www\\goodreads\\csv\\goodreads91-100.csv");




    }
}