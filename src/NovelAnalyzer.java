//import javafx.application.Application;
//import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.*;

import static java.util.Collections.max;
import static java.util.Collections.min;
//import static javafx.application.Application.launch;


public class NovelAnalyzer {
    /*
     * 小说分析器，分为两个部分
     *  1.初始化小说函数
     *  2.小说分析函数
     */
    static String filePath;
    String namesInput;
    static String[] names;
    static String[][] otherNames;
    static int[][] relations;

    static JTextArea textArea;

    NovelAnalyzer(JTextArea textArea, GUI G) throws FileNotFoundException {

        // 创建一个文件选择器对话框
        JFileChooser fileChooser = new JFileChooser();

        // 添加一个按钮来触发文件选择对话框
        JButton button = new JButton("Browse");
        String filePath1 = null;
        fileChooser.setCurrentDirectory(new File("C:\\Users\\99250\\Desktop\\Novel"));//预设路径
        int returnValue = fileChooser.showOpenDialog(null);


        // 处理用户选择的文件
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePath1 = selectedFile.getAbsolutePath();//获取实际路径
            textArea.append("\n  Selected file path: " + filePath1 + "\n");
        }
        File Novefile = null;
        if (filePath1 != null) {
            Novefile = new File(filePath1);
        }
        Scanner fileScanner_info = new Scanner(Novefile);//扫描器
        for (int line_index = 0; fileScanner_info.hasNextLine(); line_index++) {
            String line = fileScanner_info.nextLine();
            if (line_index == 0) {//第0行读取小说路径
                filePath = line;
                textArea.append("    Novel Path: " + filePath + "\n");
                textArea.append("=====================================================|\n");
            } else if (line_index == 1) {   //第一行读取人名
                this.namesInput = line;
                names = namesInput.split("，");
                otherNames = new String[names.length + 1][0];
                textArea.append("\n");
                textArea.append("---------------------------+\n");
                textArea.append("   读取人名为：         |\n");
                textArea.append("---------------------------+\n");
                for (String j : names) {
                    textArea.append(j + " ");
                }
                textArea.append("\n");
                textArea.append("---------------------------+\n");
                textArea.append("   别名分别为：         |\n");
                textArea.append("---------------------------+\n");
            } else {//其他行读取别名
                String otherName = line;
                otherNames[line_index - 2] = otherName.split("，");
                for (String otherNames : otherNames[line_index - 2]) {
                    textArea.append("  Name" + String.valueOf(line_index - 2) + ": " + otherNames + " ");
                }
                textArea.append("\n");
            }
        }
    }

    public static void Analyze(JTextArea textArea) throws FileNotFoundException {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            int data[][] = new int[names.length][3];
            // 功能1：画出每个人在小说中出现位置的可视化图并排序每人出现的次数
            Map<String, Integer> countMap = new HashMap<>();//建立跨度哈希map
            Map<String, List<Integer>> positionMap = new HashMap<>();//建立位置哈希map
            int index = 0;
            int Novel_length = 0;
            while (fileScanner.hasNextLine()) {//扫描器还能读取下一行则继续循环
                String line = fileScanner.nextLine();
                Novel_length++;
                for (int otherName = 0; otherName < otherNames.length; otherName++) {
                    for (int j = 0; j < otherNames[otherName].length; j++) {
                        if (line.contains(otherNames[otherName][j])) {
                            line = line.replaceAll(otherNames[otherName][j], names[otherName]);//将找出的别名替换成小说人物名
                        }
                    }
                }
                for (String name : names) {
                    if (line.contains(name)) {
                        int times = line.split(name).length;//按照小说人物名分割
                        if (!line.endsWith(name)) {//如果结尾出现人物名，则出现次数减一
                            times--;
                        }
                        countMap.put(name, countMap.getOrDefault(name, 0) + times);//次数加进哈希表
                        if (!positionMap.containsKey(name)) {//如果这个名字没有出现过就初始化
                            positionMap.put(name, new ArrayList<>());
                        }
                        positionMap.get(name).add(index);//位置加进哈希表

                    }
                }
                index++;
            }
            for(int i = 0;i<names.length;i++){
                data[i][0] = min(positionMap.get(names[i]));
                data[i][1]=max(positionMap.get(names[i]));
            }
            createScatterPlot(names, positionMap);//画出散点图
            // 绘制柱状图，显示人名出现次数
            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();

            // Assuming countMap is a Map<String, Integer> containing name-count pairs
            List<Map.Entry<String, Integer>> sortedEntries = countMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();//出现次数排序

            for (Map.Entry<String, Integer> entry : sortedEntries) {//添加到dataset1
                String name = entry.getKey();
                int count = entry.getValue();
                dataset1.addValue(count, "出现次数", name);
            }

            JFreeChart chart1 = ChartFactory.createBarChart3D("人名出现次数", "人名",
                    "出现次数", dataset1, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot plot1 = (CategoryPlot) chart1.getPlot();
            BarRenderer3D renderer1 = new BarRenderer3D();
            plot1.setRenderer(renderer1);


            ChartFrame frame1 = new ChartFrame("人名出现次数统计", chart1);
            frame1.pack();
            frame1.setVisible(true);

            // 功能2：统计每个人在小说中出现的篇幅跨度并排序
            Map<String, Integer> spanMap = new HashMap<>();
            for (String name : positionMap.keySet()) {
                // 获取该人名对应的位置列表
                List<Integer> positions = positionMap.get(name);
                // 计算人名的篇幅跨度，即最后一个位置减去第一个位置
                int span = positions.get(positions.size() - 1) - positions.get(0);
                // 将人名和篇幅跨度添加到spanMap中
                spanMap.put(name, span);

            }

            // 绘制柱状图，显示人名篇幅跨度和位置百分比
            DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

            List<Map.Entry<String, Integer>> sortedEntries1 = spanMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue()).toList();

            for (Map.Entry<String, Integer> entry : sortedEntries1) {
                String name = entry.getKey();
                int span = entry.getValue();
                // Add the span data to the dataset
                dataset2.addValue(span, "篇幅跨度", name);
            }

            JFreeChart chart2 = ChartFactory.createBarChart3D("人名篇幅跨度", "人名",
                    "篇幅跨度", dataset2, PlotOrientation.VERTICAL, false, true, false);
            CategoryAxis domainAxis = getCategoryAxis(chart2);
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 旋转标签以提高可见性
            domainAxis.setTickLabelPaint(Color.BLACK); // 设置刻度标签颜色

            ChartFrame frame2 = new ChartFrame("人名篇幅跨度统计", chart2);
            frame2.pack();
            frame2.setVisible(true);

            NLineChart lineChart1 = new NLineChart(names,data);//线图，展示百分比
            lineChart1.initUI();

            // 功能3：统计人物关系紧密程度
            int[][] relationMatrix = new int[names.length][names.length];
            fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                for (int i = 0; i < otherNames.length; i++) {
                    for (int j = 0; j < otherNames[i].length; j++) {
                        if (line.contains(otherNames[i][j])) {
                            line = line.replaceAll(otherNames[i][j], names[i]);//将别名替换掉
                        }
                    }
                }
                for (int name = 0; name < names.length; name++) {//两层循环找ij都出现在同一行的
                    for (int nextName = name + 1; nextName < names.length; nextName++) {
                        if (line.contains(names[name]) && line.contains(names[nextName])) {//包含这个名字和其他一个名字
                            relationMatrix[name][nextName]++;//对应relationMatrix增加一个
                        }
                    }
                }
            }
            relations = relationMatrix;

            String[][] relationMatrix_S = new String[names.length][names.length + 1];
            String[] nameN = new String[names.length + 1];
            for (int index_n = 0; index_n < names.length; index_n++) {
                relationMatrix_S[index_n][0] = names[index_n];
                nameN[index_n + 1] = names[index_n];

                for (int index_m = 1; index_m < names.length + 1; index_m++) {
                    for (int index_k = 0; index_k < names.length; index_k++) {
                        relationMatrix_S[index_k][index_m] = String.valueOf(relationMatrix[index_k][index_m - 1]);//转换数据
                        //relationMatrix_S[index_m-1][index_k] = String.valueOf(relationMatrix[index_m - 1][index_k]);
                    }
                }
            }

            // 输入一个人名，列出该人和其他九人关系的紧密程度并排名
            String inputName = JOptionPane.showInputDialog("请输入一个人名：");
            int indexInput = Arrays.asList(names).indexOf(inputName);// 获取输入人名在数组 names 中的索
            PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());// 创建一个优先级队列（最大堆），用于存储与输入人名关系密切程度的值
            for (int name = 0; name < names.length; name++) {
                if (name != indexInput) {//不是输入人名则
                    queue.offer(relationMatrix[indexInput][name]);// 将与输入人名关系密切程度的值添加到优先级队列中
                }
            }
            textArea.append("  与" + inputName + "关系紧密程度排名：\n");
//            System.out.println("与" + inputName + "关系紧密程度排名：");
            int rank = 1;
            while (!queue.isEmpty()) {
                int value = queue.poll();
                for (int name = 0; name < names.length; name++) {
                    if (name != indexInput && relationMatrix[indexInput][name] == value) {
                        textArea.append(rank + ". " + names[name] + "\n");
                        queue.remove(value);// 从队列中移除已显示的值，避免重复处理
                        rank++;
                    }
                }
            }

            Stack<Integer> relationScoreStack = new Stack<Integer>();
            Stack<Integer> coordinateX_Stack = new Stack<Integer>();
            Stack<Integer> coordinateY_Stack = new Stack<Integer>();
            for (int nameI = 0; nameI < names.length; nameI++) {
                for (int nameJ = 0; nameJ < names.length; nameJ++) {
                    relationScoreStack.push(relationMatrix[nameI][nameJ]);//将relation数据输入stack
                    coordinateX_Stack.push(nameI);
                    coordinateY_Stack.push(nameJ);
                }
            }
            Stack<table> sortRes = sort.sortStk(relationScoreStack, coordinateX_Stack, coordinateY_Stack);
            int max = 0;
            int[] nameParents = new int[names.length];
            for (int name = 0; name < names.length; name++) {
                nameParents[name] = name;
            }
            Set<Map.Entry<String, String>> dqueue = new HashSet<>();
            Union_Set unionSet = new Union_Set(names, nameParents, dqueue);
            String C1 = JOptionPane.showInputDialog("请输入C值");
            int C = Integer.parseInt(C1);
            int index_c = 0;
            textArea.append("  筛选出联系较深的笛卡尔积: \n");
            while (!sortRes.isEmpty()) {
                table T = sortRes.pop();
                int i = T.elementB;
                int j = T.elementC;
                textArea.append("  <" + names[i] + "," + names[j] + ">   ");
                if (index_c < C) unionSet.Union(i, j);//排名在5以前就Union一下
                if (index_c == 1) {
                    max = relationMatrix[i][j];//找到max，后面映射
                }
                index_c++;
                if (index_c % 5 == 0) {
                    textArea.append("\n");
                }
            }
            int finalMax = max;
            SwingUtilities.invokeLater(() -> new VisualizeArray(relationMatrix_S, nameN, finalMax));//建立热图
            List<List<String>> Unionset = unionSet.printnames(textArea);

            fileScanner.close();

        } catch (
                IOException e) {
            e.printStackTrace();
        }


    }

    private static void createScatterPlot(String[] names, Map<String, List<Integer>> positionMap) {//建立散点图
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (int j = 0;j<names.length;j++ ) {//遍历所有名字
            String name = names[j];
            XYSeries series = new XYSeries(name);

            List<Integer> positions = positionMap.get(name);//为每一个名字创建一个List来储存位置
            if (positions != null) {
                for (int i = 0; i < positions.size(); i++) {
                    series.add(j+1, positions.get(i)); // 为每个位置添加一个点
                }
            }

            dataset.addSeries(series);
        }

        JFreeChart chart = ChartFactory.createScatterPlot(
                "人物出现的位置",
                "人物",
                "章节",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );//画图

        XYPlot plot = chart.getXYPlot();
        XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);
        plot.setRenderer(renderer);

        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartFrame frame = new ChartFrame("人物在章节中出现的位置", chart);
        frame.pack();
        frame.setVisible(true);
    }

    private static CategoryAxis getCategoryAxis(JFreeChart chart2) {
        CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
        BarRenderer3D renderer2 = new BarRenderer3D();

        // 设置柱状图颜色为蓝色
        renderer2.setSeriesPaint(0, new Color(104, 106, 253));

        // plot 的外观
        plot2.setRenderer(renderer2);
        plot2.setBackgroundPaint(Color.WHITE); // 设置 plot 背景颜色
        plot2.setDomainGridlinePaint(Color.BLACK); // 设置网格线颜色

        // domain 轴（X 轴）的外观
        CategoryAxis domainAxis = plot2.getDomainAxis();
        return domainAxis;
    }
}