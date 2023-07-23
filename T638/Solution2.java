package T638;

import java.util.*;

// 记忆化搜索
public class Solution2 {
    //key是需求，即curNeeds；value是minPrice
    Map<List<Integer>, Integer> memo = new HashMap<List<Integer>, Integer>();

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        // 过滤不需要计算的大礼包，只保留需要计算的大礼包
        List<List<Integer>> filterSpecial = new ArrayList<List<Integer>>();
        for (List<Integer> sp : special) {
            int totalPrice = 0;
            for (int i = 0; i < n; ++i) {
                totalPrice += sp.get(i) * price.get(i);
            }
            if (totalPrice > sp.get(n)) {
                filterSpecial.add(sp);
            }
        }
        return dfs(price, needs, filterSpecial, n);
    }

    // 记忆化搜索 计算满足购物清单所需花费的最低价格
    public int dfs(List<Integer> price, List<Integer> curNeeds
            , List<List<Integer>> filterSpecial, int n) {
        //这里的判断起到剪枝的作用，如果之前出现过相同的需求，
        //那么之前所得到的最小价格minPrice即本次的最小价格minPrice，无需再次判断
        if (!memo.containsKey(curNeeds)) {
            int minPrice = 0;
            for (int i = 0; i < n; ++i) {
                // 不购买任何大礼包，原价购买当前需求全部商品的价钱
                minPrice += curNeeds.get(i) * price.get(i);
            }
            One:
            for (List<Integer> curSpecial : filterSpecial) {
                //当前礼包的总价
                int specialPrice = curSpecial.get(n);
                List<Integer> nxtNeeds = new ArrayList<Integer>();
                for (int i = 0; i < n; ++i) {
                    // 不能购买超出购物清单指定数量的物品，所以当前礼包不可用，直接开始判断下一礼包
                    if (curSpecial.get(i) > curNeeds.get(i)) continue One;
                    // 更新下一个礼包的curNeeds参数
                    nxtNeeds.add(curNeeds.get(i) - curSpecial.get(i));
                }
                // 大礼包可以购买
                minPrice = Math.min(minPrice,
                        dfs(price, nxtNeeds, filterSpecial, n) + specialPrice);
            }
            memo.put(curNeeds, minPrice);
        }
        //这里的curNeeds是最先开始的需求，所以在map中能获得答案，而非其它需求的minPrice
        return memo.get(curNeeds);
    }

    public static void main(String[] args) {
        List<Integer> price = new ArrayList<>();
        price.addAll(Arrays.asList(2, 5));

        List<List<Integer>> special = new ArrayList<>();
        List<Integer> s1 = new ArrayList<>();
        s1.addAll(Arrays.asList(3, 0, 5));
        special.add(s1);
        List<Integer> s2 = new ArrayList<>();
        s2.addAll(Arrays.asList(1, 2, 10));
        special.add(s2);

        List<Integer> needs = new ArrayList<>();
        needs.addAll(Arrays.asList(3, 2));

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.shoppingOffers(price, special, needs));
    }
}
