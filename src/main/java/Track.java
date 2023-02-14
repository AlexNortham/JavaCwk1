
/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author YOUR NAME
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;

public class Track {
    private ArrayList<Point> pointList;

    // TODO: Create a stub for the constructor
    public Track(String filename) {
        pointList = new ArrayList<>();
        try {
            readFile(filename);
        } catch (IOException e) {

        }
    }

    public Track() {
        pointList = new ArrayList<>();
    }

    // TODO: Create a stub for readFile()
    public void readFile(String filename) throws IOException {
        this.pointList = new ArrayList<Point>();
        ZonedDateTime time = null;
        double longitude = 0;
        double latitude = 0;
        double elevation = 0;
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String fileLine = scanner.nextLine();
            String[] lineArray = fileLine.split(",");
            if (lineArray.length == 4) {
                try {
                    time = ZonedDateTime.parse(lineArray[0]);
                    longitude = Double.parseDouble(lineArray[1]);
                    latitude = Double.parseDouble(lineArray[2]);
                    elevation = Double.parseDouble(lineArray[3]);

                    Point point = new Point(time, longitude, latitude, elevation);
                    this.pointList.add(point);
                } catch (Exception e) {
                    throw new GPSException("Parse error");
                }
            } else {
                throw new GPSException("not enough arguments");
            }
        }

    }

    // TODO: Create a stub for add()
    public void add(Point point) {
        pointList.add(point);
    }

    // TODO: Create a stub for get()
    public Point get(int index) {
        if (index >= 0 && index < pointList.size()) {
            return pointList.get(index);
        } else {
            throw new GPSException("Invalid index");
        }

    }

    // TODO: Create a stub for size()
    public int size() {
        return pointList.size();
    }

    // TODO: Create a stub for lowestPoint()
    public Point lowestPoint() {

        Point lowest = new Point(null, 0, 0, Double.POSITIVE_INFINITY);

        if (pointList.size() == 0) {

            throw new GPSException("Track is empty");
        }

        for (Point point : pointList) {

            if (point.getElevation() < lowest.getElevation()) {
                lowest = point;

            }
        }
        return lowest;

    }

    // TODO: Create a stub for highestPoint()
    public Point highestPoint() {

        Point highest = new Point(null, 0, 0, 0);

        if (pointList.size() == 0) {

            throw new GPSException("Track is empty");
        }

        for (Point point : pointList) {

            if (point.getElevation() > highest.getElevation()) {
                highest = point;

            }
        }
        return highest;

    }

    // TODO: Create a stub for totalDistance()
    public double totalDistance() {
        if (pointList.size() < 2) {
            throw new GPSException("Track is too small");
        }
        double total = 0;
        // return Point.greatCircleDistance(pointList.get(0),
        // pointList.get(pointList.size() - 1));
        for (int i = 1; i < pointList.size(); i++) {
            total += Point.greatCircleDistance(pointList.get(i - 1), pointList.get(i));

        }
        return total;

    }

    // TODO: Create a stub for averageSpeed()

    public double averageSpeed() {
        if (pointList.size() < 2) {
            throw new GPSException("Track is too small");
        }
        double totalTime = ChronoUnit.SECONDS.between(pointList.get(0).getTime(),
                pointList.get(pointList.size() - 1).getTime());
        double avgSpeed = totalDistance() / totalTime;
        return avgSpeed;
    }

    public void writeKML(String filename) {
        File file = new File(filename);
        file.delete();
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n<Document>\n<Style id=\"yellowLineGreenPoly\">\n<LineStyle>\n<color>7f00ffff</color>\n<width>4</width>\n</LineStyle>\n<PolyStyle>\n<color>7f00ff00</color>\n</PolyStyle>\n</Style>\n<Placemark>\n<LineString>\n<extrude>1</extrude>\n<tessellate>1</tessellate>\n<altitudeMode>absolute</altitudeMode>\n<coordinates>");
            for (int i = 0; i < size(); i++) {
                fw.write(pointList.get(i).getLongitude() + "," + pointList.get(i).getLatitude() + ","
                        + pointList.get(i).getElevation() + "\n");
            }
            fw.write("</coordinates>\n</LineString>\n</Placemark>\n</Document>\n</kml>");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
