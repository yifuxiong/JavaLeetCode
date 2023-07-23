package T165;

public class Solution {
    public int compareVersion(String version1, String version2) {
        int v1 = 0, v2 = 0;
        while (v1 < version1.length() || v2 < version2.length()) {
            int a = 0, b = 0;
            while (v1 < version1.length() && version1.charAt(v1) != '.'){
                a = a * 10 + version1.charAt(v1) - '0';
                v1++;
            }
            while (v2 < version2.length() && version2.charAt(v2) != '.'){
                b = b * 10 + version2.charAt(v2) - '0';
                v2++;
            }
            if (a > b){
                return 1;
            }else if (a < b){
                return -1;
            }
            // 遇到'.'跳过
            v1++;
            v2++;
        }
        return 0;
    }

    public static void main(String[] args) {
        String version1 = "1.01";  // 0
        String version2 = "1.001";

        String version1_2 = "1.0";  // 0
        String version2_2 = "1.0.0";

        String version1_3 = "0.1";  // -1
        String version2_3 = "1.1";

        String version1_4 = "1.0.1";  // 1
        String version2_4 = "1";

        String version1_5 = "7.5.2.4";  // -1
        String version2_5 = "7.5.3";

        Solution solut = new Solution();
        System.out.println(solut.compareVersion(version1_4, version2_4));
    }
}
