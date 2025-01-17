# Практичне завдання 4

## Завдання 1

### Короткий опис проекту
Цей проєкт реалізує програму для асинхронного генерування, модифікації та обробки одновимірного масиву. Основні етапи виконання:
- **Генерація масиву:** Створюється асинхронний одновимірний масив із 10 випадкових цілих чисел у межах від 1 до 90. Час виконання цієї операції виводиться на екран.

- **Модифікація масиву:**
Додавання 10 до кожного елемента масиву, виконане асинхронно.
Ділення кожного елемента на 2, що перетворює масив у дробовий. Час, витрачений на кожну з операцій, також виводиться.
- **Вивід даних:** Усі проміжні результати та фінальний модифікований масив виводяться на екран із відповідними інформаційними повідомленнями.
- **Підрахунок часу:** Для кожної асинхронної задачі та загального виконання програми вимірюється час виконання, який також демонструється.

Ця програма демонструє використання класу **CompletableFuture** для асинхронних операцій, що дозволяє виконувати обробку в паралельному потоці.

## Завдання 2

### Короткий опис проекту

Програма асинхронно виконує наступні дії:
- Генерує початкову послідовність із 20 випадкових дійсних чисел у межах від 1 до 100. Ця послідовність виводиться на екран із відповідним інформаційним повідомленням.
- Обчислює добуток різниць між сусідніми елементами послідовності, тобто (a2−a1)⋅(a3−a2)⋅...⋅(an−an−1).
- Виводить результат обчислення з пояснювальним повідомленням.
- Підраховує та виводить загальний час виконання всіх асинхронних задач.

Кожен етап виконується асинхронно для демонстрації роботи з багатопоточністю. У кінці використовується пауза для забезпечення завершення всіх асинхронних операцій.