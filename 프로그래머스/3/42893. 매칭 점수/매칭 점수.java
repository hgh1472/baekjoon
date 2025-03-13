import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String word, String[] pages) {
        int answer = 0;

        Map<String, Page> pageMap = new HashMap<>();
        word = word.toLowerCase();

        for (int i = 0; i < pages.length; i++) {
            String page = pages[i];
            page = page.toLowerCase();
            String url = "";
            int score = 0;
            Matcher matcher = Pattern.compile("(<meta property=\"og:url\" content=\"https://(\\S*)\")").matcher(page);
            while (matcher.find()) {
                url = matcher.group(2).trim();
            }

            matcher = Pattern.compile("(?<=[^a-zA-Z])("+word+")[^a-zA-Z]").matcher(page);
            while (matcher.find()) {
                score++;
            }
            Page p = new Page(i, score, url);

            matcher = Pattern.compile("<a href=\"(\\S*)//(\\S*)\"").matcher(page);
            while (matcher.find()) {
                String outLink = matcher.group(2).trim();
                p.addOutLink(outLink);
            }

            pageMap.put(p.url, p);
        }

        for (String url : pageMap.keySet()) {
            Page p = pageMap.get(url);
            for (String u : p.outLinks) {
                if (pageMap.containsKey(u)) {
                    pageMap.get(u).addMatchingScore((double) p.basicScore / p.outLinks.size());
                }
            }
        }

        double max = 0;
        for (String url : pageMap.keySet()) {
            if (max < pageMap.get(url).matchingScore) {
                max = pageMap.get(url).matchingScore;
                answer = pageMap.get(url).index;
            }
        }

        return answer;
    }

    class Page {
        int index;
        int basicScore;
        String url;
        double matchingScore;
        List<String> outLinks = new ArrayList<>();

        Page(int index, int basicScore, String url) {
            this.index = index;
            this.basicScore = basicScore;
            this.url = url;
            this.matchingScore = basicScore;
        }

        void addOutLink(String url) {
            this.outLinks.add(url);
        }

        void addMatchingScore(double score) {
            this.matchingScore += score;
        }
    }
}