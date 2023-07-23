package T649;

public class Solution {
    public String predictPartyVictory(String senate) {
        String ret = "";
        while (senate.contains("D") && senate.contains("R")){
            senate = vote(senate);
        }
        if (senate.contains("D"))
            ret = "Dire";
        else ret = "Radiant";
        return ret;
    }

    public static String vote(String senate){
        for (int i = 0; i < senate.length(); i++){
            String head = senate.substring(0, i);
            String tail = senate.substring(i);
            // 判断当前字符
            if (senate.charAt(i) == 'R'){
                // 优先淘汰当前索引之后的第一个地方阵营的议员
                if (tail.contains("D")){
                    tail = tail.replaceFirst("D", "_");
                    senate = head.concat(tail);
                }else{  // 否则把当前索引前面的敌方议员淘汰
                    head = head.replaceFirst("D", "_");
                    senate = head.concat(tail);
                }
            }else if(senate.charAt(i) == 'D'){
                // 优先淘汰当前索引之后的第一个地方阵营的议员
                if (tail.contains("R")){
                    tail = tail.replaceFirst("R", "_");
                    senate = head.concat(tail);
                }else{  // 否则把当前索引前面的敌方议员淘汰
                    head = head.replaceFirst("R", "_");
                    senate = head.concat(tail);
                }
            }else if (senate.charAt(i) == '_'){
                continue;
            }
        }
        return senate;
    }

    public static void main(String[] args){
        String senate = "RD";
        String senate2 = "RDD";
        String senate3 = "DDRRR";
        String senate4 = "DRRDRDRDRDDRDRDR";

        Solution solut = new Solution();
        System.out.println(solut.predictPartyVictory(senate3));
    }
}
