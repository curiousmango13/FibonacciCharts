/**
 * Program Fibonacci Charts implements the Fibonacci function in both a recursive and iterative fashion 
 * and compares runtime efficiency for each.
 * Results are represented as line charts with time (in nanoseconds) on the Y axis, and input on the X axis.
 * 
 * @author Julia Smith
 * @date   2020/10/11
 */

package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	/**
	 * Integer representing number of elements in sequence required.
	 */
	int limit = 50;

	/**
	 * First number in the sequence when using recursion.
	 */
	static int num;

	/**
	 * First number in the sequence when using iterative approach.
	 */
	int previousNum = 0;

	/**
	 * Second number in sequence when using iterative approach.
	 */
	int nextNum = 1;

	/**
	 * startTime marks the beginning of calculation using recursion.
	 */
	/**
	 * startTime2 marks the beginning of calculation using recursion.
	 */
	long startTime, startTime2;

	/**
	 * eachNumTime marks the time to reach each new number using recursion.
	 */
	/**
	 * eachNumTime2 marks the time to reach each new number using iteration.
	 */
	long eachNumTime, eachNumTime2;

	/**
	 * endTime marks the time when recursion is completed.
	 */
	/**
	 * endTime2 marks the time when iteration is completed.
	 */
	static long endTime, endTime2;

	/**
	 * time marks the time it took recursion to completed.
	 */
	/**
	 * time2 marks the time it took iteration is completed.
	 */
	static long time, time2;

	/**
	 * This is the method which sets up XY-axis line chart to display output of
	 * recursion vs. iteration .
	 * 
	 * @param args unused.
	 * @return nothing.
	 */
	@Override
	public void start(Stage stage) {

		// setting Stage title
		stage.setTitle("Fibonacci Sequence");

		// creating X and Y-axis
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Input");

		// range of the chart - x-axis
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(0);
		xAxis.setUpperBound(50);
		xAxis.setTickUnit(1);

		// range of the chart - y-axis
		yAxis.setLabel("Time in nanoseconds");
		yAxis.setAutoRanging(true);
		yAxis.setLowerBound(500000000);
		yAxis.setUpperBound(1902127300);
		yAxis.setTickUnit(25000000);
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		lineChart.setTitle("Fibonacci function: recursive vs.iterative approaches");

		// creating series to display output of recursion
		XYChart.Series recursive = new XYChart.Series();
		recursive.setName("Recursive");

// Recursive approach

		System.out.print("Fibonacci Sequence of " + limit + " numbers: ");

		// marking the beginning of the count
		long startTime = System.nanoTime();

		// loop to run recursion as many times as required by limit variable
		for (int i = 0; i < limit; i++) {
			System.out.print(fibonacciRecursion(i) + " ");

			// it took this long to reach next number
			long eachNumTime = System.nanoTime();

			// adding data to the chart
			// recursive.getData().add(new XYChart.Data(fibonacciRecursion(i),
			// eachNumTime));
			recursive.getData().add(new XYChart.Data(i, eachNumTime));

		}
		// this is the time when recursion is complete
		long endTime = System.nanoTime();

		// this long it took to run recursion
		long time = endTime - startTime;

		System.out.println();

		System.out.println("It took " + time + " nanoseconds using recursion");

		// creating series to display output of iteration
		XYChart.Series iterative = new XYChart.Series();
		iterative.setName("Iterative");

		// iterative approach

		System.out.println();

		System.out.print("Fibonacci Sequence of " + limit + " numbers: ");

		// loop to implement iterative approach and time when it began to run
		long startTime2 = System.nanoTime();
		for (int i = 1; i <= limit; ++i) {
			System.out.print(previousNum + " ");

			int sum = previousNum + nextNum;
			previousNum = nextNum;
			nextNum = sum;

			// time it took to reach each new number
			long eachNumTime2 = System.nanoTime();
			// iterative.getData().add(new XYChart.Data(previousNum, eachNumTime2));
			// adding data to the chart
			iterative.getData().add(new XYChart.Data(i, eachNumTime2));

		}
		// time when iteration is complete
		long endTime2 = System.nanoTime();

		// it took this long to complete iteration
		long time2 = endTime2 - startTime2;

		System.out.println();

		System.out.println("It took " + time2 + " nanoseconds using iteration");

		// creating the scene and adding series to the line chart
		Scene scene = new Scene(lineChart, 800, 600);
		lineChart.getData().addAll(recursive, iterative);

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This is the main method which launches GUI .
	 * 
	 * @param args unused.
	 * @return nothing.
	 */

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This is the method which calculates Fibonacci numbers using recursion.
	 * 
	 * @param Integer, initialized as first number to use in recursion.
	 * @return integer.
	 */
	public static int fibonacciRecursion(int num) {
		if (num == 0) {
			return 0;
		}
		if (num == 1 || num == 2) {
			return 1;
		}
		return fibonacciRecursion(num - 2) + fibonacciRecursion(num - 1);
	}
}
