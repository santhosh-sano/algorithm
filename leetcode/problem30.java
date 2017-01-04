public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();
        if (s==null || words.length == 0){
            return result;
        }

        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (int i=0;i<words.length;i++){
            String w = words[i];
            if (!allWords.containsKey(w)){
                allWords.put(w, 1);
            }
            else{
                Integer count = allWords.get(w);
                count++;
                allWords.put(w, count);
            }
        }
        int wordLength = words[0].length();
        int length = words.length*wordLength;
        for (int i=0;i<=s.length()-length;i++){
            boolean b = findWords(s, i, (Map)allWords.clone(), wordLength);
            if (b){
                result.add(i);
            }
        }
        return result;
    }

    private boolean findWords(String s, int startIndex, Map<String, Integer> allWords, int wordLength){
        if (allWords.isEmpty()){
            return true;
        }
        String s1 = s.substring(startIndex, startIndex+wordLength);
        if (allWords.containsKey(s1)){
            int result = allWords.get(s1);
            result--;
            if (result == 0){
                allWords.remove(s1);
            }
            else{
                allWords.put(s1, result);
            }
            return findWords(s, startIndex+wordLength, allWords, wordLength);
        }
        return false;
    }
}

