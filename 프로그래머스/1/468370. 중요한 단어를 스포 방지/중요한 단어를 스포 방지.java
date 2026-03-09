import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int wordCount = 0;
        int[] idx = new int[message.length()];
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) != ' ') {
                idx[i] = wordCount;
            }
            else {
                wordCount++;
                idx[i] = -1;
            }
        }
        
        String[] words = message.split(" ");
        boolean[] isSpoil = new boolean[words.length];
        Set<String> spoils = new HashSet<>();
        Set<String> nonSpoils = new HashSet<>();
        for (int[] range : spoiler_ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                if (idx[i] == -1) {
                    continue;
                }
                isSpoil[idx[i]] = true;
            }
        }
        for (int i = 0; i < words.length; i++) {
            if (isSpoil[i]) {
                spoils.add(words[i]);
            }
            else {
                nonSpoils.add(words[i]);
            }
        }
        
        int answer = 0;
        for (String spoil : spoils) {
            if (nonSpoils.contains(spoil)) {
                continue;
            }
            answer++;
        }
        return answer;
    }
}