package com.vimukti.dashboard.client.ui.controls.charts;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart.PieOptions;

/**
 * this class method creats a Hole on pie chart
 * 
 * @author Prabhakar
 * 
 */
public class DonutChartOptions extends PieOptions {
	public static DonutChartOptions create() {
		return JavaScriptObject.createObject().cast();
	}

	protected DonutChartOptions() {

	}

	/**
	 * 
	 * this method creates javaScript var <a href=
	 * "https://developers.google.com/chart/interactive/docs/gallery/piechart?csw=1#donut"
	 * > Donut Options Reference</a>. To create donut chart call this method on
	 * PieChart object by passing double value(Hole size).
	 * 
	 * @param pieHole
	 *            The pieHole option should be set to a number between 0 and 1.
	 *            Values equal to or greater than 1 will be ignored. a value of
	 *            0 will completely shut your piehole.. Numbers between 0.4 and
	 *            0.6 will look best on most charts.
	 */
	public final native void setPieHole(double pieHole) /*-{
		this.pieHole = pieHole;
	}-*/;

}
