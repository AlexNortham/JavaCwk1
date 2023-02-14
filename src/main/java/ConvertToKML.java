import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Program to general a KML file from GPS track data stored in a file,
 * for the Advanced task of COMP1721 Coursework 1.
 *
 * @author YOUR NAME HERE
 */
public class ConvertToKML {
  public static void main(String[] args){
    Track track = new Track();
    try {
      track.readFile(args[0]);
      track.writeKML(args[1]);
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
}
