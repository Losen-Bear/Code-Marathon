# 075 赌神

来源：[比赛网页](http://10.68.41.189/contest/7/problem/075)，2026-10-15读取。

### Description

你手中有一把牌，需要重新排列这些牌，分成若干组，使每一组的牌数都是groupSize，并且由groupSize张连续的牌组成。
给你一个整数数组hand，其中hand[i]是写在第i张牌上的数值。如果可以重新排列，返回true；否则返回false。

### Input

第一行输入hand[i]，用英文逗号分隔；第二行输入groupSize。
1≤hand.length≤10，0≤hand[i]≤10^6，1≤groupSize≤hand.length。

### Output

输出true或false。

### Sample Input 1

```text
1,2,3,6,2,3,4,7,8
3
```

### Sample Output 1

```text
true
解释：你手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
```

### Sample Input 2

```text
1,2,3,4,5
4
```

### Sample Output 2

```text
false
解释：你手中的牌无法被重新排列成几个大小为 4 的组。
```
