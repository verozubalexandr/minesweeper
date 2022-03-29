package com.company;

public class Main {
    public static void main(String[] args) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        int rows = 20;
        int cols = 20;
        int cellNumber = 0;
        String cellNumberSymbol = " ";
        boolean[][] field = fillArray(rows, cols);





        //печатаем поле с проверкой на расположение мин
        for (int k = 0; k < cols * 3.4; k++) {
            System.out.print(ANSI_WHITE + "█" + ANSI_RESET);
        }
        System.out.println();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j]) {
                    System.out.print(ANSI_WHITE + "▌" + ANSI_RESET + "⛳" + ANSI_WHITE + "▐" + ANSI_RESET);
                } else {
                    cellNumber = bombCounter(field, i, j);
                    if (cellNumber == 1)
                        cellNumberSymbol = (ANSI_BLUE + "①" + ANSI_RESET);
                    if (cellNumber == 2)
                        cellNumberSymbol = (ANSI_GREEN + "②" + ANSI_RESET);
                    if (cellNumber == 3)
                        cellNumberSymbol = (ANSI_RED + "③" + ANSI_RESET);
                    if (cellNumber == 4)
                        cellNumberSymbol = (ANSI_CYAN + "④" + ANSI_RESET);
                    if (cellNumber == 5)
                        cellNumberSymbol = (ANSI_PURPLE + "⑤" + ANSI_RESET);
                    if (cellNumber == 6)
                        cellNumberSymbol = (ANSI_YELLOW +"⑥" + ANSI_RESET);
                    if (cellNumber == 7)
                        cellNumberSymbol = (ANSI_WHITE + "⑦" + ANSI_RESET);
                    if (cellNumber == 0)
                        cellNumberSymbol = "⓿";
                    System.out.print(ANSI_WHITE + "▌" + ANSI_RESET + cellNumberSymbol + ANSI_WHITE + "▐" + ANSI_RESET);
                }
                if (j + 1 == field.length) {
                    System.out.print("\n");
                    for (int k = 0; k < cols * 3.4; k++) {
                        System.out.print(ANSI_WHITE + "█" + ANSI_RESET);
                    }
                }
            }
            System.out.println();
        }

    }





    // рассчитываем значения в ячейках
    public static int bombCounter(boolean[][] field, int i, int j) {
        int countBomb = 0;
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        boolean leftTopCorner = false;
        boolean rightTopCorner = false;
        boolean rightDownCorner = false;
        boolean leftDownCorner = false;

        //ищем, в каких ячейках вокруг искомой boolean == true, а в каких false
        if (i - 1 >= 0) up = field[i - 1][j];
        if (j - 1 >= 0) left = field[i][j - 1];
        if (j + 1 < field[i].length) right = field[i][j + 1];
        if (i + 1 < field.length) down = field[i + 1][j];
        if (i - 1 >= 0 && j + 1 < field[0].length) rightTopCorner = field[i - 1][j + 1];
        if (i - 1 >= 0 && j - 1 >= 0) leftTopCorner = field[i - 1][j - 1];
        if (i + 1 < field.length && j - 1 >= 0) leftDownCorner = field[i + 1][j - 1];
        if (i + 1 < field.length && j + 1 < field[0].length) rightDownCorner = field[i + 1][j + 1];

        //через тернарный оператор меняем boolean на int (таким образом через переменные наличия мин по разные стороны
        // от печатаемой ячейки мы определяем значение в ней
        countBomb += right ? 1 : 0;
        countBomb += left ? 1 : 0;
        countBomb += down ? 1 : 0;
        countBomb += up ? 1 : 0;
        countBomb += leftTopCorner ? 1 : 0;
        countBomb += leftDownCorner ? 1 : 0;
        countBomb += rightTopCorner ? 1 : 0;
        countBomb += rightDownCorner ? 1 : 0;

        return countBomb;
    }





    //"расставляем" мины на поле при помощи генератора случайных чисел
    public static boolean[][] fillArray(int rows, int cols) {
        boolean[][] field = new boolean[rows][cols];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = (int) (Math.random() * 9) == 3;
            }
        }
        return field;
    }
}

