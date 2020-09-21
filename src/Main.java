import java.util.Scanner;

public class Main {

    private final static Scanner input = new Scanner(System.in);
    private static double[][] firstMatrix;
    private static double[][] secondMatrix;
    private static int userChoice;
    private static int multiplicationNumber;

    public static void main(String[] args) {
        matrixMainMenu();
    }

    private static void matrixMainMenu() {
        while (true) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            userChoice = input.nextInt();
            switch (userChoice) {
                case 1:
                    firstMatrix = fillMatrix();
                    secondMatrix = fillMatrix();
                    System.out.println("The addition result is:");
                    printMatrix(addMatrix(firstMatrix, secondMatrix, firstMatrix.length, firstMatrix[0].length));
                    break;
                case 2:
                    firstMatrix = fillMatrix();
                    System.out.print("Enter multiplication number: ");
                    multiplicationNumber = input.nextInt();
                    System.out.println("The multiplication result is:");
                    printMatrix(multiplyMatrixByConstant(firstMatrix, firstMatrix.length, firstMatrix[0].length, multiplicationNumber));
                    break;
                case 3:
                    firstMatrix = fillMatrix();
                    secondMatrix = fillMatrix();
                    System.out.println("The multiplication result is:");
                    printMatrix(multiplyMatrices(firstMatrix, secondMatrix));
                    break;
                case 4:
                    transposeMatrixSubMenu();
                case 5:
                    firstMatrix = fillMatrix();
                    System.out.println("The result is:");
                    System.out.println(calculateDeterminant(firstMatrix, firstMatrix.length));
                    System.out.println();
                    break;
                case 6:
                    firstMatrix = fillMatrix();
                    System.out.println("The result is:");
                    printMatrix(inverseMatrix(firstMatrix, firstMatrix.length, firstMatrix[0].length));
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Sorry. Wrong option provided");
            }
        }
    }

    private static void transposeMatrixSubMenu() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
        userChoice = input.nextInt();
        firstMatrix = fillMatrix();
        System.out.println("The result is: ");
        switch (userChoice) {
            case 1:
                printMatrix(transposeMainDiagonal(firstMatrix));
                System.out.println();
                matrixMainMenu();
                break;
            case 2:
                printMatrix(transposeSideDiagonal(firstMatrix, firstMatrix.length, firstMatrix[0].length));
                System.out.println();
                matrixMainMenu();
                break;
            case 3:
                printMatrix(transposeVerticalLine(firstMatrix, firstMatrix.length, firstMatrix[0].length));
                System.out.println();
                matrixMainMenu();
                break;
            case 4:
                printMatrix(transposeHorizontalLine(firstMatrix, firstMatrix.length, firstMatrix[0].length));
                System.out.println();
                matrixMainMenu();
                break;
            default:
                System.out.println("Sorry. Wrong option provided");
                matrixMainMenu();
                break;
        }
    }

    private static double[][] addMatrix(double[][] matrixOne, double[][] matrixTwo, int rowLength, int columnLength) {
        double[][] additionMatrix = new double[rowLength][columnLength];
        if (matrixOne.length != matrixTwo.length && matrixOne[0].length != matrixTwo[0].length) {
            System.out.println();
            System.out.println("ERROR");
        } else {
            System.out.println();
            for (int row = 0; row < rowLength; ++row) {
                for (int column = 0; column < columnLength; ++column) {
                    additionMatrix[row][column] = matrixOne[row][column] + matrixTwo[row][column];
                }
            }
        }
        return additionMatrix;
    }

    private static double[][] multiplyMatrixByConstant(double[][] matrix, int rowLength, int columnLength, int multiplicationNumber) {
        double[][] multiplicationMatrix = new double[rowLength][columnLength];
        for (int row = 0; row < rowLength; ++row) {
            for (int column = 0; column < columnLength; ++column) {
                multiplicationMatrix[row][column] = matrix[row][column] * multiplicationNumber;
            }
        }
        return multiplicationMatrix;
    }

    private static double[][] multiplyMatrices(double[][] matrixOne, double[][] matrixTwo) {
        double[][] multiplyingMatrices = new double[matrixOne.length][matrixTwo[0].length];
        for (int row = 0; row < multiplyingMatrices.length; row++) {
            for (int column = 0; column < multiplyingMatrices[row].length; column++) {
                multiplyingMatrices[row][column] = multiplyMatricesCell(matrixOne, matrixTwo, row, column);
            }
        }
        return multiplyingMatrices;
    }

    private static double multiplyMatricesCell(double[][] matrixOne, double[][] matrixTwo, int row, int column) {
        double cell = 0;
        for (int i = 0; i < matrixTwo.length; i++) {
            cell += matrixOne[row][i] * matrixTwo[i][column];
        }
        return cell;
    }

    private static double[][] transposeMainDiagonal(double[][] matrix) {
        double[][] transposedMatrix = new double[matrix[0].length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            for(int column = 0; column < matrix[0].length; column++) {
                transposedMatrix[column][row] = matrix[row][column];
            }
        }
        return transposedMatrix;
    }

    private static double[][] transposeSideDiagonal(double[][] matrix, int rowLength, int columnLength) {
        for (int row = 0; row < (rowLength - 1); row++) {
            for (int column = 0; column < (columnLength - 1) - row; column++) {
                double tmp = matrix[row][column];
                matrix[row][column] = matrix[(columnLength - 1) - column][(columnLength - 1) - row];
                matrix[(columnLength - 1) - column][(columnLength - 1) - row] = tmp;
            }
        }
        return matrix;
    }

    private static double[][] transposeVerticalLine(double[][] matrix, int rowLength, int columnLength) {
        for (int row = 0; row < rowLength; ++row) {
            for (int column = 0; column < columnLength / 2; ++column) {
                double tmp = matrix[row][column];
                matrix[row][column] = matrix[row][columnLength - column - 1];
                matrix[row][columnLength - column - 1] = tmp;
            }
        }
        return matrix;
    }

    private static double[][] transposeHorizontalLine(double[][] matrix, int rowLength, int columnLength) {
        for (int row = 0; row < rowLength / 2; ++row) {
            for (int column = 0; column < columnLength; ++column) {
                double tmp = matrix[row][column];
                matrix[row][column] = matrix[rowLength - row -1][column];
                matrix[rowLength - row - 1][column] = tmp;
            }
        }
        return matrix;
    }

    private static double calculateDeterminant(double[][] matrix, int rowsOfFirstMatrix) {
        double determinant = 0;
        int columnsOfFirstMatrix = matrix[0].length;
        if (rowsOfFirstMatrix == 1) {
            return matrix[0][0];
        }
        double[][] temp = new double[rowsOfFirstMatrix][columnsOfFirstMatrix];
        int sign = 1;
        for (int row = 0; row < rowsOfFirstMatrix; row++) {
            getCofactor(matrix, temp, 0, row, rowsOfFirstMatrix);
            determinant += sign * matrix[0][row]
                    * calculateDeterminant(temp, rowsOfFirstMatrix - 1);
            sign = -sign;
        }
        return determinant;
    }

    private static void getCofactor(double[][] matrix, double[][] temp, int oldRow, int oldColumn, int rowsOfFirstMatrix) {
        int i = 0, j = 0;
        for (int row = 0; row < rowsOfFirstMatrix; row++) {
            for (int column = 0; column < rowsOfFirstMatrix; column++) {
                if (row != oldRow && column != oldColumn) {
                    temp[i][j++] = matrix[row][column];
                    if (j == rowsOfFirstMatrix - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    private static double[][] inverseMatrix(double[][] matrix, int rowLength, int columnLength) {
        double determinantNumber = calculateDeterminant(matrix, rowLength);
        double[][] temporaryInverseMatrix = new double[rowLength][columnLength];
        double[][] temp = new double[rowLength][columnLength];

        for (int row = 0; row < rowLength; row++) {
            for (int column = 0; column < rowLength; column++) {
                getCofactor(matrix, temp, row, column, rowLength);
                temporaryInverseMatrix[row][column] = Math.pow(-1, row + column)
                        * calculateDeterminant(temp, rowLength - 1);
            }
        }

        double[][] finalInverseMatrix = new double[rowLength][columnLength];

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                finalInverseMatrix[i][j] = temporaryInverseMatrix[j][i] / determinantNumber;
            }
        }
        return finalInverseMatrix;
    }

    private static double[][] fillMatrix() {
        System.out.print("Enter matrix size: ");
        int rowLength = input.nextInt();
        int columnLength = input.nextInt();
        double[][] matrix = new double[rowLength][columnLength];
        System.out.println("Enter matrix:");
        for (int row = 0; row < rowLength; row++) {
            for (int column = 0; column < columnLength; column++) {
                matrix[row][column] = input.nextDouble();
            }
        }
        return matrix;
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double cell : row) {
                System.out.printf("%.2f ", cell);
            }
            System.out.println();
        }
    }
}