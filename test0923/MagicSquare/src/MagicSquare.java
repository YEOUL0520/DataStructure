public class MagicSquare {
    public static void magic(int n) {
        int[][] square;

        if (n%2==0){
            System.out.println("Error: Please input an odd number.");
            return;
        }
        square = new int[n][n];
        square[0][n/2] = 1;
        int x = 0, y = n/2;

        for (int key =2; key <= n*n; key++){
            int i = (x - 1<0)?n-1:x-1;
            int j = (y-1<0)?n-1:y-1;
            if(square[i][j]>0)
                x = (x+1)%n;
            else{
                x = i;
                y = j;
            }
            square[x][y] = key;
        }

        System.out.printf("크기가 %d인 마방진: \n", n);
        for(int i = 0; i<n; i++){
            for(int j = 0; j <n; j++)
                System.out.printf(" %3d", square[i][j]);
            System.out.println();
        }
    }

    public static void main (String[] args){
        MagicSquare driver = new MagicSquare();
        int magicSquareSize = 5;
        magic(magicSquareSize);
    }
}
