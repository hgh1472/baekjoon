import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int longestTime = 0;
        for (String musicInfo : musicinfos) {
            String[] info = musicInfo.split(",");
            int playTime = getPlayTime(info[0], info[1]);
            
            String music = parseNotes(info[3]);
            if (playTime < music.length()) {
                music = music.substring(0, playTime);
            }
            else {
                int length = music.length();
                String add = music.substring(0, playTime % length);
                music = music.repeat(playTime / music.length()).concat(add);
            }
            
            String hear = parseNotes(m);
 
            if (music.contains(hear) && longestTime < playTime) {
                answer = info[2];
                longestTime = playTime;
            }
        }
        return answer;
    }
    
    private int getPlayTime(String start, String end) {
        String[] time1 = start.split(":");
        String[] time2 = end.split(":");
        
        int result = Integer.parseInt(time2[1]) - Integer.parseInt(time1[1]);
        result += (Integer.parseInt(time2[0]) - Integer.parseInt(time1[0])) * 60;
        return result;
    }
    
    String parseNotes(String str) {
        str = str.replaceAll("C#", "c");
        str = str.replaceAll("D#", "d");
        str = str.replaceAll("F#", "f");
        str = str.replaceAll("G#", "g");
        str = str.replaceAll("A#", "a");
        str = str.replaceAll("E#", "e");
        str = str.replaceAll("B#", "b");
        return str;
    }
    
    private boolean isContain(List<String> hearNotes, List<String> musicNotes, int playTime) {
        int length = playTime;
        for (int i = 0; i < length; i++) {
            int idx = i % musicNotes.size();
            if (hearNotes.get(0).equals(musicNotes.get(idx))) {
                int count = 1;
                for (count = 1; count < hearNotes.size(); count++) {
                    if (i + count >= length) {
                        break;
                    }
                    int tmpIdx = (i + count) % musicNotes.size();
                    if (!hearNotes.get(count).equals(musicNotes.get(tmpIdx))) {
                        break;
                    }
                }
                if (count == hearNotes.size()) {
                    return true;
                }
            }
        }
        return false;
    }
}
