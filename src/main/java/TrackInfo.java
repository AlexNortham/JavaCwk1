import java.io.IOException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author YOUR NAME HERE
 */
public class TrackInfo {
  public static void main(String[] args) {
    if(args.length == 0){
      System.out.println("File name must be provided");
      System.exit(0);
    }
    Track track = new Track();
    try {
      track.readFile(args[0]);

      System.out.println(track.size() + " points in track");
      System.out.println("Lowest point is " + track.lowestPoint().toString());
      System.out.println("Highest point is " + track.highestPoint().toString());
      System.out.println(String.format("Total distance = %.3f m", track.totalDistance()));
      System.out.println(String.format("Average Speed = %.3f m/s", track.averageSpeed()));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
