# Search_txt
novel analyser
**题**：**用Java编程开发“小说人物统计”程序，主要考核字符串处理、IO操作等。**

自行下载自己最喜欢的小说1部。存储为文本文档。要求长篇小说，20万字以上。

事先指定其中10个重要人物，考虑他们的姓名、别名等等一系列因素。

1.  画出每个人在小说中出现位置的可视化图（图可以自行设计）。排序每人出现的次数，并在界面上用柱状图显示。

2.  统计每个人在小说中出现的篇幅跨度（第一次出现距最后一次出现的篇幅）并排序，并在界面上用柱状图显示，针对每个人物，柱状图中列出该人篇幅跨度起始点到终点位于全文的百分比哪个位置。

3.  如果两人在较短的一段文字（如500字范围，具体可自定）中出现，我们认为两人有联系。有联系的次数越多，关系越紧密。自行设计统计标准，对于10个人物：

<!-- -->

1.  用表格列出10个人物相互关系紧密程度。并对关系紧密程度进行排序，列在界面上。

2.  输入一个人名，列出该人和其他九人关系的紧密程度并排名。

3.  如果多人多次在一段文字中出现，我们认为多人组成小团队。自行设计算法，统计出10个人物中，哪些人可能是小团队？

看与实际是否符合？如果不太符合，说明可能的原因。（与分数无关）

二、操作环境
------------

  **硬件环境**

---------------- ----------------------------------

  **设备**         联想拯救者电脑Y7000p
  **设备处理器**   Gen intel(R) i7-11800H @ 2.30GHz
  **机带RAM**      16.0GB

  **软件环境**

-------------- ------------------------------

  **操作系统**   Windows11 家庭版
  **编程软件**   IntellJ IDEA 2023.2.5 旗舰版
  **Java版本**   Java 17

三、实验内容
------------

![image](https://github.com/user-attachments/assets/f9425a68-0027-4018-bb99-185e0b4cb40d)


如上图是项目代码结构，其中GUI为主界面模块，NovelAnalyzer是小说分析器的逻辑模块，这两个模块为整个项目的核心模块。下面对各个模块分别描述：

-   Analyzer：此模块为主界面按钮控件的监听函数，用于启动小说分析器并开始分析，详细介绍见GUI模块。

-   FileTreePrint：打印文件树模块，显示文件结构

    -   **功能概述：**

        -   **初始化文件树：** 通过构造函数
            **FileTreePrint(JTextArea J)**
            进行文件树的初始化，指定了根目录的路径。构造函数会创建一个根节点
            **FileTreeBean**，并调用 **getChildrenFile**
            方法递归获取树形结构的所有节点。

        -   **获取文件树：** 使用 **getChildrenFile**
            方法递归获取指定目录下的所有文件和子目录，并构建成树形结构。该方法返回一个
            **FileTreeBean**
            对象，包含了当前目录或文件的名称、级别（深度）、路径以及子节点列表。

        -   **打印文件树：** 使用 **FileTreeBean** 中的 **print**
            方法，实现树形结构的打印。通过递归地调用 **print**
            方法，每次打印一层文件或目录，形成树形结构的展示。

        -   **封装文件对象：** 使用 **FileTreeBean**
            内部类，封装了文件的名称、级别、路径以及子节点列表。该类还包含
            **addChild** 方法，用于添加子节点。

        -   **打印时输出到 JTextArea：** 打印时将结果输出到
            **JTextArea** 对象，实现了文件树的可视化展示。

    -   **方法概述：**

        -   **构造函数 FileTreePrint(JTextArea J)：**
            初始化文件树的根节点，并调用 **getChildrenFile**
            方法构建文件树。

        -   **getChildrenFile(FileTreeBean fileTreeBean)：**
            递归获取指定目录下的所有文件和子目录，并构建成树形结构。返回一个
            **FileTreeBean** 对象。

        -   **print()：** 递归打印文件树。通过 **appendString**
            方法构建打印字符串。

        -   **print(Pattern pattern, boolean include)：**
            递归打印文件树，根据正则表达式过滤打印结果。

        -   **appendString()：**
            构建打印字符串，包括缩进和文件或目录名称。通过 **JTextArea**
            对象的 **append** 方法输出到界面。

-   GUI：
    这个类为核心界面模块，设定了主界面上的各种控件和对应的功能方法。

    -   **功能概述：**

        -   **GUI界面：** 通过继承 **JFrame**
            类，创建了一个简单的GUI窗口，包含文本区域、按钮、滚动条等组件，提供用户与程序交互的界面。

        -   **文件树显示：** 使用 **FileTreePrint**
            类来生成并显示文件树。用户点击按钮后，程序会扫描文件系统，将文件树显示在左侧的文本区域
            **textArea1** 中。

        -   **小说参数设置：** 使用 **Start**
            类实例化，提供了一个按钮（"Input Your
            Data"）用于用户输入小说参数。点击按钮后，可能会触发小说参数的初始化操作。

        -   **小说分析：** 使用 **Analyzer**
            类实例化，提供了一个按钮（"Start
            Analyzing"）用于触发小说分析操作。点击按钮后，可能会触发小说分析的操作，结果将会显示在右侧的文本区域
            **textArea** 中。

        -   **Dark Theme切换：** 提供了一个复选框
            **transparencyCheckBox**，用于切换Dark
            Theme。当复选框选中时，界面可能切换到暗色主题。

    -   **方法概述：**

        -   **构造函数 GUI()：**
            初始化GUI界面，设置窗口标题、大小、位置，添加各种组件，注册事件监听器。

        -   **validateImagePath(String path)：**
            验证图片路径的合法性，判断给定路径的文件是否存在。

        -   **errorfunc()：**
            错误处理方法，弹出一个警告对话框，提示用户输入数据不能为空。

-   lineChart：线图模块，绘制线图来展示每个人物在人物出现百分比

    -   **功能概述：**

        -   **生成折线图：** 根据给定的小说人物名字数组 **names**
            和对应的篇幅数据二维数组
            **data**，生成表示篇幅跨度的折线图。

        -   **展示折线图：** 将生成的折线图显示在 Swing 窗口中。

    -   **方法概述：**

        -   **NLineChart 构造函数：**

            -   参数：小说人物名字数组 **names**
                和对应的篇幅数据二维数组 **data**。

            -   实现：初始化类的成员变量。

        -   **initUI 方法：**

            -   实现：初始化图表的UI，设置图表的标题、X轴名称、Y轴名称等基本属性。通过调用
                **createDataset**
                方法生成数据集，并创建折线图。设置图表的样式，包括背景色、网格线颜色、线条形状等。最后，通过
                **ChartFrame** 显示图表。

        -   **createDataset 方法：**

            -   参数：最大篇幅 **maxl**，用于将篇幅映射到0\~100的范围。

            -   返回值：**CategoryDataset**，表示折线图的数据集。

            -   实现：遍历人物数据，将人物的篇幅映射到0\~100的篇幅值。

-   NovelAnalyzer：小说分析器核心模块

> 这个类主要实现了小说分析的功能。

-   **功能概述：**

    -   **小说初始化功能：**
        通过文件选择器，用户可以选择一个小说文件。读取小说文件的第一行获取小说路径，读取第二行获取人名（可以有别名）。将人名和别名信息展示在文本区域中。

    -   **小说分析功能：**
        分析小说中人物的出现位置、出现次数、篇幅跨度、人物关系等，并将结果以可视化图表的形式展示在图形界面中。同时，根据用户输入的人物名，展示其与其他人物关系的紧密程度排名。

    -   **绘制图表：** 使用 JFreeChart
        库绘制了多种图表，包括散点图、柱状图、线图等。通过这些图表，直观地展示了小说中人物的出现位置、出现次数、篇幅跨度等信息。

    -   **人物关系分析：**
        通过计算人物在小说中的出现位置、出现次数、篇幅跨度，以及人物之间的关系紧密程度，形成了多个图表展示。

-   **方法概述：**

    -   构造函数 **NovelAnalyzer(JTextArea textArea, GUI G)：**接受一个
        JTextArea对象和GUI
        对象作为参数，负责小说的初始化。通过文件选择器选择小说文件，读取人名和别名信息，展示在界面上。

    -   **Analyze(JTextArea textArea)：** 接受一个 JTextArea
        对象作为参数，负责对小说进行分析。分析包括计算人物出现次数、篇幅跨度、人物关系等，以及绘制多种图表。还包括输入一个人名，输出该人名与其他人物的关系的紧密程度排名。

    -   **createScatterPlot(String\[\] names, Map&lt;String,
        List&lt;Integer&gt;&gt; positionMap)：**
        绘制散点图，展示人物在小说中出现的位置。

    -   **getCategoryAxis(JFreeChart chart2)：**
        获取柱状图的分类轴，用于设置柱状图的外观。

<!-- -->

-   Sort：这个类实现了一个基于栈的排序算法，主要用于比较人物的相关程度。以下是这个类的主要功能和方法：

    -   **功能概述：**

        -   **栈排序：** 使用栈进行排序，根据人物的相关程度来进行排序。

        -   **比较人物相关程度：** 通过比较元素的某一属性（这里是
            **elementA**）来判断人物的相关程度。

        -   **返回排序结果：** 返回排序后的结果，这里是将排序结果存储在
            **Stack&lt;table&gt;** 中返回。

    -   **方法概述：**

        -   **sortStk 方法：**

            -   参数：三个栈
                **s**、**s1**、**s2**，分别表示待排序的元素的三个属性。

            -   返回值：一个
                **Stack&lt;table&gt;**，表示排序后的结果，其中 **table**
                是自定义的存储元素的数据结构。

            -   实现：使用两个栈，一个是待排序的栈
                **s**，另外一个是辅助栈
                **res**。通过比较待排序栈的元素与辅助栈顶元素的大小，将较小的元素放入辅助栈。最终，辅助栈中的元素就是按照相关程度递增排序的。

    -   **内部类 table：**

        -   用于存储三个属性的元素。

        -   包含三个整型属性 **elementA**、**elementB**、**elementC**。

-   Start：启动模块，初始化小说info文件路径并读取小说路径，小说人物名及别名，详细介绍见GUI模块。

-   Table：自定义三元组数据结构，详细介绍同上。

-   Union\_Set：并查集模块，将筛选出来关系较大的笛卡尔积合并为Union，主要用于对人物进行聚合。

    -   **功能概述：**

        -   **并查集：**
            用于对一组元素进行分组和查询，判断两个元素是否属于同一组。

        -   **人物聚合：**
            将具有关联关系的人物进行聚合，形成不相交的集合。

        -   **Union 操作：** 通过 **Union**
            方法将两个元素所在的集合合并，实现人物的聚合。

        -   **查找祖先：** 通过 **find**
            方法查找一个元素所在集合的祖先。

        -   **输出聚合结果：** 通过 **printnames**
            方法将聚合结果输出到指定的 **JTextArea**。

    -   **方法概述：**

        -   **构造函数 Union\_Set(String\[\] names, int\[\]
            namesParents, Set&lt;Map.Entry&lt;String, String&gt;&gt;
            Dqueue)：** 初始化并查集，接收人物名称数组
            **names**、人物所在集合的父节点数组 **namesParents**
            以及一组关联关系的数据 **Dqueue**。

        -   **find 方法：** 查找并返回元素所在集合的祖先。

        -   **Union 方法：**
            将两个元素所在的集合合并，通过路径压缩优化。

        -   **printnames 方法：** 输出聚合后的结果，返回一个
            **List**，其中每个元素都是一个聚合后的集合。

    -   **内部类 Unit：**

        -   这个类用于辅助实现并查集的功能，表示一个并查集中的单元（Unit）。

        -   **Unit** 包含一个祖先的索引 **i**
            和一个以该祖先为根的人物索引列表 **child**。

        -   **adds** 方法用于向 **child** 中添加人物索引。

-   Unit：用来支持并查集打印Union结果的自定义数据结构模块，详细介绍同上。

-   VisualArray：这个类主要用于可视化热图，将一个二维数组以表格的形式展示在窗口中。以下是这个类的主要功能和方法：

    -   **功能概述：**

        -   **可视化热图：** 通过构造函数
            **VisualizeArray(String\[\]\[\] dataArray, String\[\]
            columnNames, int max)**
            实现对一个二维数组的可视化。这个数组表示了某种关联关系，通过不同颜色的单元格来反映数据的大小。

        -   **表格显示：** 使用 **JTable**
            组件来创建表格，通过调整表格的单元格颜色、字体、大小等来呈现热图效果。

        -   **单元格颜色映射：**
            根据数组中的数据值映射不同的颜色，以突显数据之间的差异。颜色的映射规则通过一系列的条件判断来实现。

        -   **表格自定义：**
            设置表格的单元格大小、边框样式、网格线等，以使得热图更具可读性。

    -   **方法概述：**

<!-- -->

-   **构造函数 VisualizeArray(String\[\]\[\] dataArray, String\[\]
    columnNames, int max)：** 初始化热图，接收一个二维数组
    **dataArray**，列名数组 **columnNames** 以及热图中的最大值 **max**。

-   **prepareRenderer 方法：** 重写了 **JTable** 的 **prepareRenderer**
    方法，用于自定义单元格的渲染器，包括单元格的背景颜色、前景
    颜色等。在此方法中，还进行了单元格的大小调整，以保证表格单元格呈正方形。

四、实验结果
------------

首先解压压缩包，调整文件路径和数据文件中
小说实际路径(位于小说名\_info.txt文件)后，点击运行后出现主界面。

![image](https://github.com/user-attachments/assets/4f7687a2-1f46-4b72-9a02-521ce104cc30)

主界面由左右两块文本框组成和下半部分三个控制按钮组成(其中单选框能够改变界面颜色，但不稳定，可能出现颜色修改混乱的情况)。左边显示文件数，默认路径为桌面的Novel文件夹路径。右边的文本框显示小说分析中的文本结果。

点击“input Your Data”按钮后弹出文件选择窗口：

![image](https://github.com/user-attachments/assets/aee6aa17-908f-49f6-a8a0-1a1981ed4510)


在文件选择窗口中选中“西游记\_info.txt”文件，点击打开。接下来系统导入选中的txt文件，读取其中的信息，包括小说实际路径，人物姓名和别名，并显示在页面上。西游记info文件以及选取后显示效果如下：

![image](https://github.com/user-attachments/assets/d067d422-4efe-489d-9d3b-e3c2c76ae0f1)

![image](https://github.com/user-attachments/assets/01dd3355-ff6a-4d35-b29a-300799e870a4)


接下来点击“Start
Analyzing”按钮，会生成四张图标，同时弹出两个输入框。输入框中输入要作为基准人物的姓名，以及聚合参数C的数值(一般输入5)。

![image](https://github.com/user-attachments/assets/c9b604ff-cff7-4d33-8bc0-61bced86db86)




输入C值后系统自动开始分析，得出结果后分析完成。接下来查看可视化的图像，共产生五幅图，其中题目第一问的图表如下：

![image](https://github.com/user-attachments/assets/efcb5e10-75b3-4138-878f-dc1e20e7bbb6)


![image](https://github.com/user-attachments/assets/952d5286-d209-417f-9056-b4f2f3b7c533)


第一张图是小说中人物在文本中每行中出现的位置，通过散点图的方式呈现。第二张图是小说人名出现次数，每个人物的出现次数在排序后通过柱状图显示。

第二问的图表如下：

![image](https://github.com/user-attachments/assets/9fe3ddf0-0c4f-4e0d-8214-76d6f321de15)


![image](https://github.com/user-attachments/assets/9effcd33-d0fa-46ef-b2a9-3d9105132351)


上面两张图分别是小说人物篇幅跨度百分比图和人名篇幅跨度排序图，其中第一张图蜘蛛精没有画出，是由于蜘蛛精在文中出现比例太小(&lt;2%)，因此没有显示(选择2%是为了更加方便的看横坐标轴，如果选择1%则会出现坐标重叠的问题)。第二张图通过排序能够发现，蜘蛛精的篇幅跨度远远小于其他人物。

第三题要求的图表如下：

![image](https://github.com/user-attachments/assets/3e99df12-77b8-482a-b736-ede542ab57ba)


这张图是人物关系紧密程度的可视化热图，方格中的数值越大表明关系越紧密。能够发现悟空与唐僧的关系最为紧密，其次是悟空与八戒的关系，沙僧则在文中与其他三人关系相对较远，其余人物的关系远近程度也能在图中看出。

接下来查看主界面上对小说人物的聚合结果。

![image](https://github.com/user-attachments/assets/d3def72c-3f64-4be9-9e68-b2492665788c)


这张图是其他人与输入的基准人物关系远近的排序结果和笛卡尔积结果，C值决定着系统取所有笛卡尔积中的前几项来进行小团体聚合。

![image](https://github.com/user-attachments/assets/eab73a22-0b18-4410-a2c1-36221de311b2)


这张图显示了聚合结果，可见悟空，八戒，唐僧，沙僧四人关系最为紧密，组成小团体，其他人的关系较远，都没有形成小团体。

至此小说分析完成！
