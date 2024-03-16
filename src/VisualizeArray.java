import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class VisualizeArray {
    /*
     * 可视化热图
     */
    public VisualizeArray(String[][] dataArray, String[] columnNames, int max) {
        JFrame frame = new JFrame("Array to Table Visualizer");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // 设置单元格正方形大小
        int cellSize = 60;

        // 创建表格模型
        DefaultTableModel tableModel = new DefaultTableModel(dataArray, columnNames);
        JTable table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);

                // 设置行高和列宽相等，实现正方形格子效果
                setRowHeight(row, cellSize);
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(cellSize);
                tableColumn.setMaxWidth(cellSize);
                tableColumn.setMinWidth(cellSize);

                if (column != 0) {
                    // 取出数据值并转换为数值类型
                    float value = Float.parseFloat(dataArray[row][column]);
                    value = (int) 450 * value / max;//将value值映射到指定范围0~450
                    // 根据数据值设置单元格背景色
                    if (row == column - 1) {
                        component.setBackground(new Color(91, 49, 5));
                        component.setForeground(Color.lightGray);
                    } else if (value >= 0 && value < 10) {
                        component.setBackground(new Color(234, 149, 87));
                        component.setForeground(Color.black);
                    } else if (value >= 10 && value < 50) {
                        component.setBackground(new Color(250, 138, 72));
                        component.setForeground(Color.black);
                    } else if (value >= 50 && value < 100) {
                        component.setBackground(new Color(250, 97, 15));
                        component.setForeground(Color.black);
                    } else if (value >= 100 && value < 150) {
                        component.setBackground(new Color(248, 84, 25));
                        component.setForeground(Color.black);
                    } else if (value >= 150 && value < 200) {
                        component.setBackground(new Color(222, 43, 5));
                        component.setForeground(Color.black);
                    } else if (value >= 200 && value < 250) {
                        component.setBackground(new Color(217, 37, 6));
                        component.setForeground(Color.black);
                    } else if (value >= 250 && value < 300) {
                        component.setBackground(new Color(206, 15, 7));
                        component.setForeground(Color.black);
                    } else if (value >= 300 && value < 350) {
                        component.setBackground(new Color(157, 22, 3));
                        component.setForeground(Color.lightGray);
                    } else if (value >= 350 && value < 400) {
                        component.setBackground(new Color(129, 8, 8));
                        component.setForeground(Color.lightGray);
                    } else if (value >= 400) {
                        component.setBackground(new Color(117, 2, 2));
                        component.setForeground(Color.lightGray);
                    }
                } else {
                    component.setBackground(new Color(250, 249, 249));
                    component.setForeground(Color.black);
                }

                return component;
            }
        };

        table.setShowGrid(true); // 显示网格线
        table.setGridColor(new Color(253, 215, 206, 255)); // 设置网格线颜色
        table.setFont(new Font("FangSong", Font.PLAIN, 14)); // 设置字体


        // 设置单元格渲染器，自定义外观和配色
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 居中对齐单元格内容
//        cellRenderer.setBackground(new Color(255, 255, 255, 255)); // 设置背景颜色
        cellRenderer.setForeground(Color.black); // 设置文字颜色
        table.setDefaultRenderer(Object.class, cellRenderer);

        // 设置表格边框样式
        table.setBorder(BorderFactory.createLineBorder(new Color(246, 211, 203, 255)));

        // 设置表头渲染器，自定义表头外观
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(255, 255, 255, 255)); // 设置表头背景颜色
        header.setForeground(Color.black); // 设置表头文字颜色
        TableColumnModel columnModel = table.getColumnModel();
        header.setPreferredSize(new Dimension(cellSize, cellSize));
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn tableColumn = columnModel.getColumn(i);
            tableColumn.setHeaderRenderer(cellRenderer); // 设置表头渲染器
            tableColumn.setPreferredWidth(cellSize);
            tableColumn.setMaxWidth(cellSize);
            tableColumn.setMinWidth(cellSize);
        }

        // 创建滚动窗口，以便查看整个表格
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加表格到窗口
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 设置窗口属性
        frame.setSize((dataArray.length + 1) * cellSize + 30, (dataArray.length + 2) * cellSize);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}