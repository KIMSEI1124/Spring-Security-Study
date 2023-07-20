package one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            getAnswer(i);
        }

        System.out.println(answer);
    }

    private static void getAnswer(final int id) throws IOException {
        int caseAnswer = 0;

        int n = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[n];
        List<Integer> list = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int i = 0; i < n; i++) {
            /* 방문 여부 */
            if (visited[i]) {
                continue;
            }
            visited[i] = true;

            /* 예외 처리 */
            int index = list.get(i) + i;
            if (index < 0 || index >= n) {
                continue;
            }

            /* 편지 검증 */
            int target = list.get(index) * -1;
            if (list.get(i) == target) {
                caseAnswer++;
                visited[index] = true;
            }
        }

        answer.append("#" + id + " " + caseAnswer + "\n");
    }
}