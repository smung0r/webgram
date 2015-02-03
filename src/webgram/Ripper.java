/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webgram;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author hannes.schulze01
 */
public class Ripper {
    
    public List ripMovies(String url){
        Document doc;
        List<Movie> movies = new ArrayList<>();
        try {
            doc = Jsoup.connect(url).get();
            Elements dates = doc.getElementsByClass("datumzeile");
            for (Element date : dates) {
                String sDate = date.text();
                Pattern p = Pattern.compile("(.*), ([0-9]{1,2}).([0-9]{1,2}).([0-9]{2,4})");
                Matcher m = p.matcher(sDate);
                Date d = null;
                if(m.find()){
//                    System.out.println(m.group(0));
                    System.out.println(m.group(1));
                    System.out.println(m.group(2));
                    System.out.println(m.group(3));
                    System.out.println(m.group(4));
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.MONTH, Integer.valueOf(m.group(3)));
                    cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(m.group(2)));
                    cal.set(Calendar.YEAR, Integer.valueOf(m.group(4)));
                    
//                    d = new Date(Integer.valueOf(m.group(4)), Integer.valueOf(m.group(3)), Integer.valueOf(m.group(2)));
                    d = cal.getTime();
                    System.out.println(cal.getTime());
                    System.out.println(d.getDay() + "." + d.getMonth() +"." + d.getYear());
                    System.out.println("dom"+cal.get(Calendar.DAY_OF_MONTH));
                    System.out.println(d);
                }
//                System.out.println(date.nextElementSibling().html());
                  movies.addAll(this.getMovies(date.nextElementSibling(), d));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Ripper.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(movies.size());
        return movies;
    }
    
    public List getMovies(Element el, Date date){
        List<Movie> movies = new ArrayList<>();
        for (Element elt : el.select(".tagestabelle tr")) {
            Movie movie = new Movie();
            String title = elt.select("td>a .filmtitel").text();
            String image = elt.select("img").attr("src");
            movie.setImageUri(image);
            Pattern p = Pattern.compile("([0-9]{2}):([0-9]{2}): (.*)");
            Matcher m = p.matcher(title);
            if(m.find()){
                movie.setName(m.group(3));
//                date.setHours(Integer.valueOf(m.group(1)));
//                date.setMinutes(Integer.valueOf(m.group(2)));
            }
            movie.setDescription(elt.select("td> .filminhalt").text());
            
            movie.setDate(date);
//            System.out.println(movie);
            movies.add(movie);
        }
        return movies;
//        System.out.println("##########");
    }
}
