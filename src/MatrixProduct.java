import java.io.*;

public class MatrixProduct {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[][][] MatrixArray;
        try {
            System.out.println("幾つの行列を掛け合わせますか？");
            int pcs = Integer.parseInt(reader.readLine());
            MatrixArray = new int[pcs][][];
            for (int i = 0; i < pcs; i++) {
                int x = 0;
                if (i != 0) {
                    x = MatrixArray[i - 1][0].length;
                }
                System.out.println((i + 1) + "つ目の行列を定義：");
                MatrixArray[i] = Decide(x);
            }
            for (int i = 0; i < pcs; i++) {
                System.out.println((i + 1) + "つ目の行列：");
                for (int l = 0; l < MatrixArray[i].length; l++) {
                    System.out.print("[");
                    for (int j = 0; j < MatrixArray[i][0].length; j++) {
                        if (j == MatrixArray[i][0].length - 1) {
                            System.out.println(MatrixArray[i][l][j] + "]");
                        } else {
                            System.out.print(MatrixArray[i][l][j] + "　");
                        }
                    }
                }
                System.out.println("\n");
            }
            int[][] Ans = Calc(MatrixArray);
            System.out.println("計算結果：");
            for (int l = 0; l < Ans.length; l++) {
                System.out.print("[");
                for (int j = 0; j < Ans[0].length; j++) {
                    if (j == Ans[0].length - 1) {
                        System.out.println(Ans[l][j] + "]");
                    } else {
                        System.out.print(Ans[l][j] + "　");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.err.println("入力が正しくありません。");
        }
    }

    static int[][] Decide(int beforeW) {
        int Matrix[][];
        int length;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (beforeW == 0) {
                System.out.println("行列の縦の大きさを入力してください。");
                length = Integer.parseInt(reader.readLine());
            } else {
                length = beforeW;
            }
            System.out.println("行列の横の大きさを入力してください。");
            int width = Integer.parseInt(reader.readLine());
            Matrix = new int[length][width];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.println((i + 1) + "行" + (j + 1) + "列" + "の数を入力してください");
                    Matrix[i][j] = Integer.parseInt(reader.readLine());
                }
            }
            return Matrix;
        } catch (IOException e) {
            System.out.println(e);
            return Decide(beforeW);
        } catch (NumberFormatException e) {
            System.err.println("入力が正しくありません。");
            return Decide(beforeW);
        }
    }

    static int[][] Calc(int[][][] MatrixArray) {
        int Matrix[][];
        int sum;
        for (int l = 0; l < MatrixArray.length - 1; l++) {
            Matrix = new int[MatrixArray[l].length][MatrixArray[l + 1][0].length];
            for (int k = 0; k < Matrix.length; k++) {
                for (int i = 0; i < Matrix[0].length; i++) {
                    sum = 0;
                    for (int j = 0; j < MatrixArray[l][0].length; j++) {
                        sum = sum+(MatrixArray[l][k][j] * MatrixArray[l + 1][j][i]);
                    }
                    Matrix[k][i] = sum;
                }
            }
            MatrixArray[l + 1] = Matrix;
        }
        return MatrixArray[MatrixArray.length - 1];
    }
}
