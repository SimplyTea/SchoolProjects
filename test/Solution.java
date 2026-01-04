import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Solution {

    private Map<Integer, List<String>> NumToLetterMap = new HashMap<>();

    private List<String> GetLetters(int lastindex, int index, List<String> list) {

    }

    public List<String> letterCombinations(String digits) {
        int num = digits.length();
        List<String> answer = new List<String>();
        if (num == 0) {
            return answer;
        }

        List<String> two = {"a","b","c"};
        List<String> three = {"d","e","f"};
        List<String> four = {"g","h","i"};
        List<String> five = {"j","k","l"};
        List<String> six = {"m","n","o"};
        List<String> seven = {"p","q","r","s"};
        List<String> eight  = {"t","u","v"};
        List<String> nine = {"w","x","y","z"};
        

        NumToLetterMap.putIfAbsent(2, one);
        NumToLetterMap.putIfAbsent(3, one);
        NumToLetterMap.putIfAbsent(4, one);
        NumToLetterMap.putIfAbsent(5, one);
        NumToLetterMap.putIfAbsent(6, one);
        NumToLetterMap.putIfAbsent(7, one);
        NumToLetterMap.putIfAbsent(8, one);
        NumToLetterMap.putIfAbsent(9, one);

        return GetLetters(num, num, nine);
    }

}