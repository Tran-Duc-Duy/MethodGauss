package com.duy.math;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        int size = 0;
        ArrayList<Double> arrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите: 1 - для ввода с консоли; 2 - для чтения файла");
        int num = scanner.nextInt();
        while (!(num == 1 || num == 2)) {
            System.out.println("Ошибка ввода!");
            System.out.println("Введите: 1 - для ввода с консоли; 2 - для чтения файла");
            num = scanner.nextInt();
        }
        switch (num) {
            case 1 -> {
                System.out.println("Укажите размерносить матрицы: ");
                size = scanner.nextInt();

                if (size == 1)
                    System.out.println("Размерность СЛАУ не может быть равна одному");
                else if (size == 2) {
                    System.out.println("Формат ввода: 'a11 a12 = b1'");
                    System.out.println("Введите коффициенты через пробел:");
                } else {
                    System.out.println("Формат ввода: 'a11 ... a1" + size + " = b1'");
                    System.out.println("Введите коффициенты через пробел:");
                }

                try {
                    String ch = "";
                    Pattern p = Pattern.compile("[A-Za-zА-Яа-я!#$@_+?-]");
                    scanner.nextLine();
                    for (int i = 0; i < size * size + size; i++) {
                        ch = scanner.nextLine();
                        ch = ch.replaceAll(",+", ".");
                        int dot = 0;
                        Matcher m = p.matcher(ch);
                        for (int j = 0; j < ch.length(); j++) {
                            if (ch.charAt(j) == '.') {
                                dot++;
                            }
                        }
                        if (dot > 1 || m.find()) {
                            System.out.println("Ошибка! Ещё раз");
                            i--;
                            continue;
                        }
                        Double db = Double.valueOf(ch);
                        System.out.println("получил -> " + db);
                        arrayList.add(db);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка ввода!  Проверьте, что дробные числа записаны через запятую");
                }
            }
            case 2 -> {
                try {
                    FileInputStream path = new FileInputStream("input.txt");
                    DataInputStream inFile = new DataInputStream(path);
                    BufferedReader br = new BufferedReader(new InputStreamReader(inFile));
                    String data;

                    while ((data = br.readLine()) != null) {
                        data = data.replaceAll(",", ".");
                        String[] tmp = data.split(" ");    //Split space
                        for (String s : tmp)
                            arrayList.add(Double.parseDouble(s));
                        size++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода!  Проверьте, что дробные числа записаны через точку");
                    System.exit(0);
                }
                System.out.println("Размерность матрицы: ");
                System.out.println(size);
                System.out.println();
            }
        }

        double[][] matrix = new double[size][size + 1];
        double[][] matrixCopy = new double[size][size + 1];
        int index = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size + 1; j++) {
                matrix[i][j] = matrixCopy[i][j] = arrayList.get(index);
                index++;
            }

        MethodGauss.setIndexMass(size);
        double[][] triangleMatrix = MethodGauss.getTriangleNew(matrix);
        if (triangleMatrix != null) {
            System.out.println("Получена треугольная матрица: ");
            MethodGauss.printMatrix(triangleMatrix);

            System.out.println("Определитель матрицы равен: ");
            double det = MethodGauss.getDeterminantOfMatrix(triangleMatrix);
            System.out.println(det);
            System.out.println();

            if (det != 0) {
                double[] x = MethodGauss.getResult(triangleMatrix);
                System.out.println("Найдены корни СЛАУ:");
                for (double v : x) System.out.printf("%.2f\t", (double) Math.round(v * 1000) / 1000);
                System.out.println();
                System.out.println();

                System.out.println("Вектор невязки: ");
                double[] dis = MethodGauss.getDiscrepancyNew(matrixCopy, x);
                for (double di : dis) System.out.printf("%.2f\t", (double) Math.round(di * 1000) / 1000);
                System.out.println();

            } else
                System.out.println("Система имеет бесконечное множество решений!");
        } else System.out.println("Ошибка в подсчете матрицы или система не имеет решений!");

    }
}