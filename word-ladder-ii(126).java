// T = O(n*26^l) - l as 单词长度; n as wordList length
// S = O(n+k*m) - k= #of paths; m as ave path length
class Solution {
    Set<String> dict = null;
    Map<String, List<String>> map = new HashMap<>();
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) return new ArrayList<>();

        bfs(beginWord, endWord);
        for(String key : map.keySet()) {
            System.out.print(key+"   ");
            System.out.println(map.get(key));
        }
        List<String> list = new ArrayList<>(); 
        list.add(beginWord);
        dfs(list, beginWord, endWord);
        return res;
    }

    private void dfs(List<String> list, String curWord, String endWord) {
        if(curWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            return;
        }
        if(map.get(curWord) == null) return;
        for(String next : map.get(curWord)) {
            list.add(next);
            dfs(list, next, endWord); 
            list.remove(list.size() - 1);
        } 
    }

    private void bfs(String beginWord, String endWord) {
        Set<String> startSet = new HashSet<>(); startSet.add(beginWord);
        boolean found = false;

        while(!startSet.isEmpty() && !found) {
            Set<String> nextLevel = new HashSet<>();
            dict.removeAll(startSet);
            // for each word in current level wordSet
            for(String word : startSet) {
                for(int i = 0; i < word.length(); i++) {
                    char[] wc = word.toCharArray();
                    for(char c = 'a'; c <= 'z'; c++) {
                        wc[i] = c; String newWord = new String(wc);
                        // if newWord exists in dict (valid)
                        if(dict.contains(newWord)) {
                            // once newWord equals endWord, finish this level
                            if(newWord.equals(endWord)) found = true;
                            else nextLevel.add(newWord);
                            // build a relation
                            if(map.get(word) == null) map.put(word, new ArrayList<>());
                            map.get(word).add(newWord); 
                        }
                    }
                }
            }
            startSet = nextLevel;
        }
    }
}

// T = O(n*26^(l/2)) - l as 单词长度; n as wordList length
// S = O(n+k*m) - k= #of paths; m as ave path length
class Solution {
    Set<String> dict = null;
    Map<String, List<String>> map = new HashMap<>();
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        dict = new HashSet<>(wordList);
        if(!dict.contains(endWord)) return new ArrayList<>();
        bfs(beginWord, endWord);

        List<String> list = new ArrayList<>(); list.add(beginWord);
        dfs(list, beginWord, endWord);
        return res;
    }

    private void dfs(List<String> list, String curWord, String endWord) {
        if(curWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            return;
        }
        if(map.get(curWord) == null) return;
        for(String word : map.get(curWord)) {
            list.add(word);
            dfs(list, word, endWord);
            list.remove(list.size()-1);
        }
    }

    private void bfs(String beginWord, String endWord) {
        Set<String> beginSet = new HashSet<>(); beginSet.add(beginWord);
        Set<String> endSet = new HashSet<>(); endSet.add(endWord);
        boolean found = false, reversed = false;
        while(!beginSet.isEmpty() && !found) {
            dict.removeAll(beginSet);
            Set<String> nextLevel = new HashSet<>();
            for(String word : beginSet) {
                for(int i = 0; i < word.length(); i++) {
                    char[] sc = word.toCharArray();
                    for(char c = 'a'; c <= 'z'; c++) {
                        sc[i] = c;
                        String newWord = new String(sc);
                        if(dict.contains(newWord)) {
                            if(endSet.contains(newWord)) found = true;
                            else nextLevel.add(newWord);

                            String key = reversed ? newWord : word;
                            String val = reversed ? word : newWord;
                            if(map.get(key) == null) map.put(key, new ArrayList<>());
                            map.get(key).add(val);
                        }
                    }
                }
            }

            if(nextLevel.size() < endSet.size()) beginSet = nextLevel;
            else {
                beginSet = endSet;
                endSet = nextLevel;
                reversed = !reversed;
            }
        }
    } 
}