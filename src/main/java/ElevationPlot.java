import javafx.stage.Stage;

/**
 * Launcher for application to plot elevations of a GPS track, needed
 * for the Advanced task of COMP1721 Coursework 1.
 *
 * @author Nick Efford
 */
public class ElevationPlot {
  public static void main(String[] args) {
    PlotApplication pa = new PlotApplication();
    try {
      pa.run(args);
    } catch (Exception e) {
      
      e.printStackTrace();
    }
  }
}
