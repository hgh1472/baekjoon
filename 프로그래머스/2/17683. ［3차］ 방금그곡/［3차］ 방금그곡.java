import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int longestTime = 0;
        for (String musicInfo : musicinfos) {
            String[] info = musicInfo.split(",");
            int playTime = getPlayTime(info[0], info[1]);
            
            List<String> musicNotes = parseNotes(info[3]);
            
            List<String> hearNotes = parseNotes(m);
            
            if (isContain(hearNotes, musicNotes, playTime) && longestTime < playTime) {
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
    
    private List<String> parseNotes(String str) {
        List<String> notes = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            String note = String.valueOf(str.charAt(i));
            if (i + 1 < str.length() && str.charAt(i+1) == '#') {
                note = note + str.charAt(i+1);
                i++;
            }
            notes.add(String.valueOf(note));
        }
        return notes;
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
/**
m, info = "CDCDF", ["13:50,14:02,WORLD,CDCDCDF"]
*/
