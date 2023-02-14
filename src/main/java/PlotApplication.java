import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;




/**
 * JavaFX application to plot elevations of a GPS track, for
 * the Advanced task of COMP1721 Coursework 1.
 *
 * @author YOUR NAME HERE
 */
public class PlotApplication extends Application {

    

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Distance");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Elevation");
        
        Parameters params = getParameters();
        List<String> paramsArray = params.getRaw();
        String filepath = paramsArray.get(0);

        LineChart<Number, Number> elevationChart = new LineChart<Number, Number>(xAxis, yAxis);
        elevationChart.setCreateSymbols(false);
        elevationChart.setTitle("Distance/Elevation Graph");
    
        XYChart.Series<Number , Number> plot = new XYChart.Series<Number, Number>();
        Track track = new Track();
        System.out.println(filepath);
        track.readFile(filepath);
        for(int i = 0; i < track.size(); i++){
            Point point = track.get(i);
            XYChart.Data<Number, Number> data = new XYChart.Data<Number, Number>(Point.greatCircleDistance(track.get(0), point), point.getElevation());
            plot.getData().add(data);
        }
        Scene scene = new Scene(elevationChart, 800, 600);
        elevationChart.getData().add(plot);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // OPTIONAL: Implement the elevation plotting application here

    public void run(String args[]){
    
        launch(args);
    }
}
