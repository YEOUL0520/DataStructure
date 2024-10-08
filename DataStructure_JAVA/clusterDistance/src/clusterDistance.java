/* 22112144_류효정
 *
 * 파일명: "clusterDistance.java"
 * 프로그램의 목적 및 기본 기능:
 *   - A 집단의 좌표들과 B 집단의 좌표들 간 최단 거리를 출력하는 프로그램
 * 프로그램 작성자: 류효정 (2024년 10월 06일)
 * 최종 수정 및 보완: 류효정 (2024년 10월 06일)
 * =================================================================================================
 * 프로그램 수정/보완 이력
 * =================================================================================================
 * 프로그램 수정/보완작업자        일자                수정/보완 내용
 * 류효정                    2024.10.06         파일 입력 및 출력 완료
 * =================================================================================================
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Point {
    int x, y;

    Point(int a, int b) {
        x = a;
        y = b;
    }
}

public class clusterDistance {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the file name (ex. data.txt): ");
        String fName = scanner.nextLine();

        // 파일 경로 수정 (src 폴더 내부에 file 삽입해서 사용함, src 내부에 삽입하는 경우가 아니라면 해당 부분 삭제)
        String fPath = "src/" + fName;

        Point[] A = null;
        Point[] B = null;

        // 데이터 파일 읽기
        try (Scanner fScanner = new Scanner(new File(fPath))) {
            int n = fScanner.nextInt(); //첫번째 줄은 A점 개수
            A = new Point[n];
            for (int i = 0; i < n; i++) //다음줄부터 순서대로 i번째 원소의 x값, y값
            {
                A[i] = new Point(fScanner.nextInt(), fScanner.nextInt());
            }
            int m = fScanner.nextInt(); //첫번째 줄은 B점 개수
            B = new Point[m];
            for (int j = 0; j < m; j++) //다음줄부터 순서대로 i번째 원소의 x값, y값
            {
                B[j] = new Point(fScanner.nextInt(), fScanner.nextInt());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Can't find such file");
            return;
        }

        //단순한 방법 (n*m 전부 수행)
        long startTime = System.currentTimeMillis();
        clusterMinD(A, B);
        long endTime = System.currentTimeMillis();
        System.out.printf("실행시간 : %.3f초%n", (endTime - startTime) / 1000.0);

        //똑똑한 방법
        startTime = System.currentTimeMillis();
        clusterMinDImproved(A, B);
        endTime = System.currentTimeMillis();
        System.out.printf("실행시간 : %.3f초%n", (endTime - startTime) / 1000.0);
    }

    //단순한 방법
    private static void clusterMinD(Point[] A, Point[] B) {
        //우선 최소값을 정수 범위 내 최대값으로 선언해 비교 연산 이루어지도록 함
        int minDistance = Integer.MAX_VALUE;

        //최소값 담아줄 Point 선언
        Point minA = new Point(0,0);
        Point minB = new Point(0,0);

        for (int i = 0; i < A.length; i++) {
            Point a = A[i];
            for (int j = 0; j < B.length; j++) {
                Point b = B[j];
                int distance = (b.x - a.x) + Math.abs(b.y - a.y);
                if (distance < minDistance) {
                    minDistance = distance;

                    minA = a;
                    minB = b;
                }
            }
        }

        System.out.println("단순한 방법 : 최단 거리 = " + minDistance + ", i = (" + minA.x + ", " + minA.y + "), p = (" + minB.x + ", " + minB.y + ")");
    }

    //똑똑한 방법
    private static void clusterMinDImproved(Point[] A, Point[] B) {
        //우선 최소값을 정수 범위 내 최대값으로 선언해 비교 연산 이루어지도록 함
        int minDistance = Integer.MAX_VALUE;

        //최소값 담아줄 Point 선언
        Point minA = new Point(0,0);
        Point minB = new Point(0,0);

        //y값 기준으로 B 정렬
        java.util.Arrays.sort(B, (p1, p2) -> Integer.compare(p1.y, p2.y));

        for (int i = 0; i < A.length; i++) {
            Point a = A[i];
            for (int j = 0; j < B.length; j++) {
                Point b = B[j];
                if (b.x < minB.x + minDistance) //y의 x값이 유효한 범위를 벗어날 경우 다음 b값과 비교
                {
                    int distance = (b.x - a.x) + Math.abs(b.y - a.y);
                    if (distance < minDistance) {
                        minDistance = distance;
                        minA = a;
                        minB = b;
                    }
                    //유효한 범위를 벗어나는 경우 다음 a값과 비교
                    if (b.y > a.y + minDistance)
                        break;
                }
            }
        }
        System.out.println("똑똑한 방법 : 최단 거리 = " + minDistance + ", i = (" + minA.x + ", " + minA.y + "), p = (" + minB.x + ", " + minB.y + ")");
    }

}
