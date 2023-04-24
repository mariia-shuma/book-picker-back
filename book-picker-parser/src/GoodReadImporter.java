import com.mysql.cj.jdbc.Driver;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodReadImporter {



    protected String getDescriptionOutOfTwo (String description1, String description2){
        int description1Length = description1.length();
        int description2Length = description2.length();
        String biggerDescription = (description1Length > description2Length) ? description1 : description2;
        return biggerDescription;
    }


    protected  void getGenreAndNumber (){
//        somehow count the percentage for the genres
    }

    protected List<GenreFullInfo> getGenreAndNumber (String json){
        JSONArray arr = new JSONObject("{aaa:" + json + "}").getJSONArray("aaa");
        int sum = 0;
        ArrayList<GenreFullInfo>  result = new ArrayList<GenreFullInfo>();
        for( int i = 0; i < arr.length(); i++) {

            JSONObject item = arr.getJSONObject(i);
            String genre = item.getString("genre");
            genre = genre.replaceAll("n\\s+", " ");
            genre = genre.replaceAll("\\s+"," ");
            String[] onlyGenres = genre.split("\\s(?=[0-9,]+ users$)|\\s(?=users$)");
            onlyGenres[1] = onlyGenres[1].replaceAll("\\D", "");
            int finalNumbers = Integer.parseInt(onlyGenres[1]);
            sum = sum + finalNumbers;

            result.add(new GenreFullInfo(onlyGenres[0], finalNumbers,0));
        }

        for (int i = 0; i < result.size(); i++){
            GenreFullInfo curr =  result.get(i);
            curr.overallPercentage=curr.votersNumbers*100/(double)sum;
        }


        return result;

    }

    public void importData (String filePath) throws SQLException {

        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverAction da = new JDBConnection();
        DriverManager.registerDriver(driver,da);
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookpicker", "root","");
//        Statement stmt = con.createStatement();
        Statement stmt = con.prepareStatement("INSERT INTO books (full_name, author, cover, short_summary, rating_overall, rating_people_voted, reviews_amount) values (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        Statement stmtG = con.prepareStatement("INSERT INTO genres (`name`) values (?)", Statement.RETURN_GENERATED_KEYS);
        Statement stmtGB = con.prepareStatement("INSERT INTO book_genred (book_id, genre_id, num_users_voted, overall_percentage) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        Statement selectStatement = con.prepareStatement("SELECT * FROM `genres` WHERE `name` = ?");

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] lineInArray;
            reader.readNext();
            while ((lineInArray = reader.readNext()) != null) {
                Integer bookId = null;
                ((PreparedStatement) stmt).setString(1, lineInArray[4]);
                ((PreparedStatement) stmt).setString(2, lineInArray[5]);
                ((PreparedStatement) stmt).setString(3, lineInArray[6]);
                ((PreparedStatement) stmt).setString(4, this.getDescriptionOutOfTwo(lineInArray[11],lineInArray[12]));
                ((PreparedStatement) stmt).setString(5, lineInArray[7]);
                ((PreparedStatement) stmt).setString(6, lineInArray[8]);
                ((PreparedStatement) stmt).setString(7, lineInArray[9]);
                ((PreparedStatement) stmt).execute();
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        bookId = generatedKeys.getInt(1) ;
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

//if(true) break;


                List<GenreFullInfo> genres  = this.getGenreAndNumber(lineInArray[10]);
                for (int i = 0; i < genres.size(); i++){
                    Integer genreId = null;
                    ((PreparedStatement) selectStatement).setString(1, genres.get(i).genreName);
                    try {
                        ResultSet result = ((PreparedStatement) selectStatement).executeQuery();
                        result.next();
                        genreId = result.getInt(1);
                    } catch (Exception e){
                        ((PreparedStatement) stmtG).setString(1, genres.get(i).genreName);
                        ((PreparedStatement) stmtG).execute();
                        try (ResultSet generatedKeys = stmtG.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                genreId = generatedKeys.getInt(1);
                            }
                        }
                    }

                    if(genreId > 0){
                        ((PreparedStatement) stmtGB).setInt(1, bookId);
                        ((PreparedStatement) stmtGB).setInt(2, genreId);
                        ((PreparedStatement) stmtGB).setInt(3, genres.get(i).votersNumbers);
                        ((PreparedStatement) stmtGB).setDouble(4, genres.get(i).overallPercentage);
                        ((PreparedStatement) stmtGB).execute();
                    }

                }

                //stmt.execute("INSERT INTO book (full_name) VALUES ('"+ lineInArray[4] +"')");
//                System.out.println(lineInArray[4] + "/" + lineInArray[5]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

}
