import java.util.stream.IntStream;

/**
 * Написать скрипт для расчета корреляции Пирсона между
 * двумя случайными величинами (двумя массивами).
 * <p>
 * P.S. Получилась помесь (бульдога с носорогом :) ) функциональной и процедурной парадигм.
 */

public class Main {
    public static void main(String[] args) {
        int[] array1 = {3, 5, 8, 9, 12};
        int[] array2 = {12, 9, 8, 5, 3};
        try {
            System.out.printf("%.3f%n", pirson(array1, array2));
        } catch (ArrayLengthsEqualException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Вычисление корреляции Пирсона
     *
     * @param arr1 первый массив
     * @param arr2 второй массив
     * @return корреляция массивов
     * @throws ArrayLengthsEqualException исключение при массивах с разным размером
     */
    public static double pirson(int[] arr1, int[] arr2) throws ArrayLengthsEqualException {
        // Выбрасываем исключение если разные длинны массивов
        if (!(arr1.length == arr2.length)) {
            throw new ArrayLengthsEqualException("Массивы должны иметь иметь одинаковый размер!");
        }
        int arraysLength = arr1.length;
        double sum1 = java.util.Arrays.stream(arr1, 0, arraysLength).sum();

        double sum2 = java.util.Arrays.stream(arr2, 0, arraysLength).sum();

        double sum1Squared = IntStream.range(0, arraysLength)
                .mapToDouble(i -> arr1[i] * arr1[i])
                .sum();

        double sum2Squared = IntStream.range(0, arraysLength)
                .mapToDouble(i -> arr2[i] * arr2[i])
                .sum();

        double productSum = IntStream.range(0, arraysLength)
                .mapToDouble(i -> arr1[i] * arr2[i])
                .sum();

        double numerator = arraysLength * productSum - sum1 * sum2;

        double denominator = Math.sqrt((arraysLength * sum1Squared - sum1 * sum1) * (arraysLength * sum2Squared - sum2 * sum2));

        if (denominator == 0) {
            // Обработка случая деления на ноль
            return 0.0;
        }

        return numerator / denominator;
    }


}

/**
 * Класс исключения разных длин массивов
 */
class ArrayLengthsEqualException extends Exception {
    public ArrayLengthsEqualException(String message) {
        super(message);
    }
}