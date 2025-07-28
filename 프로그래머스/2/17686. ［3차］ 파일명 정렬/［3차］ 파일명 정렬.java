import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        List<File> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            list.add(new File(file, i));
        }
        
        Collections.sort(list);
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            File file = list.get(i);
            answer[i] = file.org;
        }
        return answer;
    }
    
    class File implements Comparable<File> {
        String org;
        String head;
        int number;
        String tail;
        int order;
        
        File(String file, int order) {
            this.org = file;
            int idx = 0;
            while (idx < file.length()) {
                if ('0' <= file.charAt(idx) && file.charAt(idx) <='9') {
                    break;
                }
                idx++;
            }
            this.head = file.substring(0, idx);
            
            this.number = 0;
            while (idx < file.length()) {
                if (!('0' <= file.charAt(idx) && file.charAt(idx) <= '9')) {
                    break;
                }
                this.number *= 10;
                this.number += file.charAt(idx) - '0';
                idx++;
            }
            
            int tailIdx = idx;
            while (idx < file.length()) {
                idx++;
            }
            
            this.tail = file.substring(tailIdx, idx);
            this.order = order;
        }
        
        @Override
        public int compareTo(File f) {
            String h1 = this.head.toUpperCase();
            String h2 = f.head.toUpperCase();
            if (h1.compareTo(h2) != 0) {
                return h1.compareTo(h2);
            }
            
            if (this.number != f.number) {
                return this.number - f.number;
            }
            
            return this.order - f.order;
        }
        
    }
}

/**
HEAD = 숫자가 아닌 문자 -> 대소문자를 무시한 정렬
NUMBER = 오름차순 정렬
TAIL = 들어온 순서대로 유지
*/
