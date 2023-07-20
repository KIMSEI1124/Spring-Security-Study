package two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder answer = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            getAnswer(i);
        }

        System.out.println(answer);
    }

    private static void getAnswer(final int id) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());   // 좌표 D에 있는 발사 장치
        int s = 0;                                  // 좌표 0에 있는 발사 장치

        Map<Integer, Integer> tools = getTools(n);
        boolean[] visited = new boolean[d + 1];

        int sTime = 0;  // s 폭죽의 지연 시간
        int dTime = 0;  // d 폭죽의 지연 시간

        while (s < d) {
            /* 해당 위치에 지연 장치가 있는지 확인 */
            if (tools.containsKey(d) && !visited[d]) {
                dTime = tools.get(d);
                visited[d] = true;
            }
            if (tools.containsKey(s) && !visited[s]) {
                sTime = tools.get(s);
                visited[s] = true;
            }

            /* 폭죽이 지연 장치에 없을 때 */
            if (sTime == 0) {
                s++;
            }
            if (dTime == 0) {
                d--;
            }

            /* 폭죽이 지연 장치에 있을 때 */
            if (sTime > 0) {
                sTime--;
            }
            if (dTime > 0) {
                dTime--;
            }
        }

        answer.append("#" + id + " " + s + "\n");
    }

    private static Map<Integer, Integer> getTools(final int n) throws IOException {
        Map<Integer, Integer> tools = new HashMap<>();
//        while (n-- > 0) {
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            tools.put(index, time);
        }
        return tools;
    }
}