# 码拉松比赛题目 1-10

---

# 001 光轨城市：信号优化计划

## Description

在未来的星际都市“光轨城”（Luminous Rail City），城市交通依赖于一条贯穿全城的量子光轨。光轨由一系列光脉冲信号控制，每个信号位是 0 或 1，表示光子的两种偏振态。

* 连续的相同信号（如 000 或 111）会引发共振干扰，可能导致轨道失稳。
* 干扰强度与最长连续相同信号段的长度成正比。
* 城市安全局规定：最长连续相同信号段不得超过 k 位，否则系统将自动熔断。

为了提升运力，工程师可以使用量子翻转器（最多 numOps 次），将任意一位的信号从 0 改为 1，或从 1 改为 0。

你的任务是：最小化最长连续相同信号段的长度，确保城市交通最稳定。

给定一个长度为 n 的二进制字符串 s，表示当前的光轨信号序列。
你可以最多执行 numOps 次翻转操作（改变任意一位）。

目标是：

最小化 s 的最长相同子字符串的长度，相同子字符串是指子字符串中的所有字符都相同。
返回执行所有操作后可获得的最小长度。

示例：

输入：

`000001`

`1`

输出：

`2`

解释：

将第 3 位（索引 2）从 '0' 翻转为 '1'，信号变为 "001001"。

最长连续相同段为 "00"（长度 2），达成最优。

## Input

第一行：字符串 s，由 '0' 和 '1' 组成，表示原始信号。

第二行：整数 numOps，表示可用的量子翻转次数。

## Output

一个整数，表示操作后最长连续相同信号段的最小可能长度。

### Sample Input 1

```text
000001
1
```

### Sample Output 1

```text
2
```

### Sample Input 2

```text
0000
2
```

### Sample Output 2

```text
1
```

---

# 002 极限削木游戏

## Description

有一段长度为正整数的木头，将以下的操持续到木头长变为2或更小。

操作：选择两个位置将木头截成长度为正整数三段，丢弃其中最长的一段和最短的一段。

当长度为n的木头进行此操作时，令f(n)为该操作可进行的最多次数。

给出正整数x，求使f(n)=x的最大整数n。

## Input

输入来自标准输入，格式如下：

```text
X
```

## Output

f (N) =X 打印N的值

### Sample Input 1

```text
1
```

### Sample Output 1

```text
6
```

### Sample Input 2

```text
2
```

### Sample Output 2

```text
14
```

---

# 003解压缩编码问题

## Description

在未来的某一天，人类成功接收到了来自宇宙深处的信号。然而，这些信号并不是直接可读的信息，而是经过了一种特殊的压缩方式编码。科学家们发现，这种编码方式使用了**重复压缩**，规则如下：

* 一个子串 x 反复出现 D 次，会被压缩为 `[DX]`（其中 D 是 1 到 99 之间的整数）。
* 例如，字符串 ABABAB 可能被表示为 `[3AB]`，或者进一步嵌套为 `[2[3A]B]`。
* 这种编码方式支持**多重嵌套**，如 `[2[2[2AB]]]`，表示 ABABABABABABABAB。

你的任务是**解码**这些压缩字符串，得到原始的完整信息。

* 50% 的数据：解压后的字符串长度在 1000 以内，最多嵌套 3 层。
* 100% 的数据：解压后的字符串长度在 20000 以内，最多嵌套 10 层。
* 输入字符串仅包含**大写字母、数字 `[` `]`**，且保证格式合法。

## Input

输入一行，一个字符串 s，表示经过压缩的信号。

## Output

输出一行，一个字符串，表示解压后的完整信息。

### Sample Input 1

```text
X[3Y]
```

### Sample Output 1

```text
XYYY
```

### Sample Input 2

```text
A[2BC[3D]]
```

### Sample Output 2

```text
ABCDDBCDCDD
```

---

# 004 PUA/小源

## Description

老师对小源说我很看好你，于是他给了小源一个有序整型数组arr和一个整数num，让小源找出该数组中所有**子集元素之和等于num**的子集，并按字典序输出。

* 0 ≤ arr.length ≤ 50
* 0 ≤ arr[i] ≤ 1000
* 0 ≤ num ≤ 1000

## Input

输入

第一行是数组元素个数

第二行是数组arr元素，中间用空格隔开

第三行是num

## Output

每行输出最后不含空格

最后一行不换行

元素之间空格隔开

### Sample Input 1

```text
5
1 2 3 4 5
5
```

### Sample Output 1

```text
1 4
2 3
5
```

### Sample Input 2

```text
6
1 2 2 3 4 5
5
```

### Sample Output 2

```text
1 2 2
1 4
2 3
5
```

### Sample Input 3

```text
3
2 3 4
1
```

### Sample Output 3

```text
解释：无符合条件的子集，输出为空
```

### Hint

```cpp
int main()
{
    int n,num;
    vector<int> arr;
    cin >> n;
    arr.resize(n);
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }
    cin >> num;
    vector<vector<int>> res = solution(arr,num);
    for (int i = 0; i < res.size(); i++)
    {
        for (int j = 0; j < res[i].size(); j++)
        {
            cout << res[i][j];
            if (j != res[i].size() - 1)
            {
                cout << " ";
            }
        }
        if (i != res.size() - 1)
        {
            cout << endl;
        }
    }
    return 0;
}
```

---

# 005 泡泡堂

## Description

有一种叫做泡泡堂的游戏，玩家可以在游戏地图中放置炸弹击杀敌人

游戏地图可以用一个二维矩阵表示，二维矩阵的每个元素为一个字符，该字符由3种地图元素构成，分别为敌人'E'，障碍'B'，空格'S'

玩家只能在空格处放置炸弹，炸弹可以炸到同一行同一列的敌人，但炸弹的威力不能穿过障碍

现在玩家只有一枚炸弹，需要找到玩家能够击杀的最大敌人数目

![图片](image/005.jpg)

## Input

输入一个二维整数数组，数组长度大于等于1

## Output

输出一个整数

### Sample Input 1

```text
输入：{{'E', 'E', 'S', 'S', 'S'},
      {'S', 'S', 'E', 'B', 'S'},
      {'S', 'E', 'B', 'S', 'E'},
      {'S', 'S', 'B', 'S', 'S'},
      {'S', 'E', 'S', 'B', 'E'}}
```

### Sample Output 1

```text
输出：4
```

---

### Hint

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        char[][] matrix = parseStringTo2DArray(input);
        System.out.println(maxKilledEnemies(matrix));
    }

    public static char[][] parseStringTo2DArray(String input) {
        // 去除首尾多余的 {{ 和 }}
        input = input.trim().replaceAll("^\\{\\{|\\}\\}$", "");
        // 分割成行
        String[] rows = input.split("\\},\\{");
        List<char[]> matrixList = new ArrayList<>();
        for (String row : rows) {
            // 清理每行的多余符号并分割成字符
            String[] chars = row.trim().replaceAll("[{}']", "").split(",");
            char[] charRow = new char[chars.length];
            for (int i = 0; i < chars.length; i++) {
                charRow[i] = chars[i].trim().charAt(0);
            }
            matrixList.add(charRow);
        }
        // 将列表转换为二维数组
        int rowCount = matrixList.size();
        int colCount = matrixList.get(0).length; // 假设所有行有相同的列数
        char[][] matrix = new char[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            matrix[i] = matrixList.get(i);
        }
        return matrix;
    }

    public static int maxKilledEnemies(char[][] grid) {
        //to do
    }
}
```

---

# 006 猫和鱼

## Description

有两只猫和 n 条不同类型的鱼，每条鱼都只能被其中一只猫吃掉。

下标为 i 处的鱼被吃掉的得分为：

* 如果第一只猫吃掉，则得分为 reward1[i]。
* 如果第二只猫吃掉，则得分为 reward2[i]。

给你一个正整数数组 reward1，一个正整数数组 reward2，和一个非负整数 k。

请你返回第一只猫恰好吃掉 k 条鱼的情况下，两个猫所得的最大得分为多少。

## Input

输入两个数组和k

第一个数组为第一只猫吃掉对应的鱼所得分，第二个数组为第二只猫吃掉对应的鱼所得分

k为第一只猫恰好吃掉鱼的个数

## Output

输出第一只猫恰好吃掉 k 条鱼的情况下，最大得分为多少。

### Sample Input 1

```text
1 1 3 4
4 4 1 1
2
```

### Sample Output 1

```text
15
```

### Sample Input 2

```text
1 1
1 1
2
```

### Sample Output 2

```text
2
```

---

# 007 能量核心

## Description

考古学家在探索一座古老遗迹时，发现了一块奇异的能量矩阵。矩阵由大小为 n × m 的网格组成，其中的数字 1 到 9 代表活跃的能量核心，而数字 0 代表废弃区域。

考古学家发现，相邻的活跃能量核心（即**上下左右相邻**）会形成一个完整的能量簇。现在，他们需要计算遗迹中共有多少个独立的能量簇。

请你编写一个程序，帮助他们计算能量簇的总数。

1≤n,m≤100。

## Input

* 第一行包含两个整数 n 和 m，表示矩阵的行数和列数。
* 接下来的 n 行，每行包含一个长度为 m 的字符串，仅由字符 0 到 9 组成，表示能量矩阵。

## Output

* 输出一个整数，表示遗迹中独立的能量簇数量。

### Sample Input 1

```text
4 10
0234500067
1034560500
2045600671
0000000089
```

### Sample Output 1

```text
4
```

### Sample Input 2

```text
5 5
12345
60009
00123
45600
78901
```

### Sample Output 2

```text
2
```

---

以下是图片内容的 Markdown 版本：

# 008 反转加密

## Description

在现代网络安全中，密码学研究不仅关注加密算法的强度，也关注数字的各种特性。例如，反转数字的规律性在一些系统中被用来构造难以猜测的密钥。通过这种方式生成的密钥或验证码通常能增加安全性，防止暴力破解。

举个例子，在某些自动验证码系统中，系统可能会要求用户输入一个数字和它的反转之和，以确保输入的不仅仅是简单的数字，而是某种符合规则的组合。现在有一个大于等于 0 的整数 n，请你编写程序计算它是否可以由一个数 m（m 是一个大于等于 0 的整数）与它的反转数相加得到。例如 443，存在一个数 271，使得 271 + 172 = 443。

## Input

输入包含一个整数 n。

## Output

输出为 True 或 False。

### Sample Input 1

```text
443
```

### Sample Output 1

```text
True
```

### Sample Input 2

```text
63
```

### Sample Output 2

```text
False
```

### Hint

```cpp
#include <iostream>
using namespace std;

bool isExist(int num) {

}

int main()
{
    int num;
    cin >> num;
    if(isExist(num)){
        cout << "True" << endl;
    }
    else{
        cout << "False" << endl;
    }
    return 0;
}
```

---

# 009 进攻火星

## Description

在未来的探索时代，我们计划对火星进行一次大胆的进攻。火星的殖民地布局仿佛一棵古老的满二叉树，唯一的登陆点被称为“根”。除了这个起点之外，每个殖民地都通过一条唯一的通道与其“父”殖民地相连。火星的防御系统非常先进，如果两个直接相连的殖民地在同一时间被同时进攻，整个火星的警报系统将被激活。

我们的挑战是在不触发任何警报的情况下，找到一条路径，使得我们能够从火星上获取尽可能多的宝贵资源。这不仅是一场速度与技巧的较量，更是一场智力的比拼。我们必须在复杂的殖民地网络中，精心规划每一步行动，以确保在不被发现的同时，获取最大的收益。

## Input

按照层次遍历输入每个殖民地（即节点）node的值，值为正整数，代表该地的资源数量，每个值以英文逗号分隔。

总节点数在 [1, 10000] 范围内

0 ≤ node.val≤ 10000

## Output

输出最大的收益

### Sample Input 1

```text
3,2,3,0,3,0,1
```

### Sample Output 1

```text
7
解释：7 = 3(第一层)+0+3+0+1(第三层)
```

### Sample Input 2

```text
3,4,5,1,3,0,1
```

### Sample Output 2

```text
9
解释：9 = 4+5(第二层)
```

### Hint

```java
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    String[] values = input.split(",");
    TreeNode root = createTreeNode(values);
    int[] res = action(root);
    System.out.print(Math.max(res[0], res[1]));
}
```

---

# 010 原神启动

## Description

你吃饱了，因此你开始玩原神。但众所周知，玩原神是要吃甜甜花酿鸡的。

你在游戏中大战可莉，残血了。你被可莉打掉了 \(m(m \leq 5, 000)\) 点血，你需要吃食物来回满血。

你提前做好了 \(n(n \leq 1, 000)\) 种食物，第 \(i\) 种食物做了 \(a_i(a_i \leq 1024)\) 个，第 \(i\) 种食物每个可以帮你回 \(h_i(h_i \leq 5, 000)\) 点血。

你大战可莉，此时非常危险，因此你必须回满你的血量。

但你又非常节俭，你希望溢出的血量尽可能少。具体来说，如果你吃一个甜甜花酿鸡就能回满血，那你不会选择吃一个蒙德土豆饼的。

求你回满血时至少要溢出的血量。

## Input

第一行输入两个正整数 \(n, m\)，代表一共有多少种食物和你需要回多少点血才能满血。

接下来 \(n\) 行，每行两个正整数 \(a_i, h_i\)，分别表示第 \(i\) 种食物有多少个和每个第 \(i\) 种食物可以回多少点血。

测试用例保证输入都是正整数。

## Output

输出一个整数，表示你回满血时至少要溢出多少点血。如果你无论如何都回不满血，请输出"op"。

### Sample Input 1

```text
1 14
5 14
```

### Sample Output 1

```text
0
```

### Sample Input 2

```text
1 145
1 4
```

### Sample Output 2

```text
op
```

### Sample Input 3

```text
1 14
51 4
```

### Sample Output 3

```text
2
```
