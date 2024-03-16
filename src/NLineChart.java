import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class NLineChart extends JFrame {
    private static String[] names;
    private static int[][] data;

    public NLineChart(String[] names, int[][] data) {//初始化
        NLineChart.names = names;
        NLineChart.data = data;
    }

    public void initUI() {
        int maxl = 0;
        for (int[] i : data) {
            for (int j : i) {
                if (maxl < j) {
                    maxl = j;//找到最大值
                }
            }
        }

        CategoryDataset dataset = createDataset(maxl);

        JFreeChart chart = ChartFactory.createLineChart(
                "小说人物篇幅跨度",
                "章节",
                "人物",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot mPlot = (CategoryPlot) chart.getPlot();
        mPlot.setBackgroundPaint(Color.WHITE);
        mPlot.setRangeGridlinePaint(Color.BLUE);// 背景底部横虚线
        mPlot.setOutlinePaint(Color.RED);// 边界线

        // 设置斜向X轴
        CategoryAxis domainAxis = mPlot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis.setMaximumCategoryLabelLines(1);

        // 显示数据点
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setBaseShapesVisible(true);  // 显示数据点
        mPlot.setRenderer(renderer);

        ChartFrame mChartFrame = new ChartFrame("人物篇幅跨度", chart);
        mChartFrame.pack();
        mChartFrame.setVisible(true);
//        mChartFrame.setBounds(1, 1, 800, 600);
    }

    public CategoryDataset createDataset(int maxl) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int nameIndex = 0; nameIndex < names.length; nameIndex++) {
            double a = ((double) (data[nameIndex][0]) / maxl) * 100;//映射0~100
            double b = ((double) data[nameIndex][1] / maxl) * 100;//映射到0~100
            for (int page = 0; page < 101; page+=2) {
                if (a <= page && b >= page) {
                    dataset.addValue(nameIndex + 1, names[nameIndex], String.valueOf(page));//添加进入数据集
                }
            }
        }
        return dataset;
    }
}

