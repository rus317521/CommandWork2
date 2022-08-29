import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] prices = {40, 80, 70, 110, 300};
        String[] products = {"Хлеб", "Молоко", "Сахар", "Яблоки", "Чай"};
        int[] shopCart = new int[products.length]; // записали в какой ячейке сколько штук лежит
        int sumProducts = 0; // итоговая сумма покупок

        while (true) {
            System.out.println("Введите номер продукта и его количество или наберите end для выхода:");
            for (int i = 0; i < products.length; i++) {
                System.out.println(i + 1 + "." + " " + products[i] + " " + prices[i] + " " + "руб/шт");
            }
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }

            String[] count = input.split(" ");

            int productNumber; // номер выбранного продукта
            int productCount; // количество продукта

            try {
                productNumber = Integer.parseInt(count[0]) - 1; // определили номер продукта
                productCount = Integer.parseInt(count[1]); // определили количество штук продукта
            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА!");
                System.out.println("Введите два числа!");
                System.out.println(" ");
                continue;
            }

            if (count.length != 2) {
                System.out.println("Введено не 2 значения!");
                System.out.println(" ");
                continue;
            }

            if (productNumber < 0 || productNumber > products.length) {
                System.out.println("Введён неверный номер продукта!");
                System.out.println("Укажите верный номер продукта!");
                System.out.println(" ");
                continue;
            }

            if (productCount < 0) { //допустим уменьшаем товар
                if (shopCart[productNumber] + productCount < 0) { //если уменьшаем на больше чем в корзине
                    shopCart[productNumber] = 0; //тогда сразу 0 товара
                } else {
                    shopCart[productNumber] += productCount; //в противном случае уменьшаем на Х штук
                }
            } else if (productCount == 0) { //если сразу 0 написали
                shopCart[productNumber] = 0; //позиция удаляется
            }
            //хрен знает как красивый текст написать, почему-то при уменьшении 1 товара
            //программа сообщила об уменьшении того товара, число штук которого я выбрал
            //поэтому голый код

            shopCart[productNumber] += productCount; // посчитали итоговое количество штук конкретного продукта
            int sum = productCount * prices[productNumber]; // посчитали цену конкретной позиции
            sumProducts += sum; // посчитали общую сумму покупок
        }

        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {
            if (shopCart[i] != 0) {
                System.out.println(products[i] + " " + shopCart[i] + " шт, " + prices[i] + " руб/шт, " + (shopCart[i] * prices[i]) + " рублей в сумме");
            }
        }
        System.out.println("Итого " + sumProducts + " руб");
    }
}