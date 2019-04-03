# calculator_CFT
Калькулятор для отбора на Focus Start ЦФТ

## Overview
Калькулятор поддерживает основные арифметические операции: сложение, вычитание, умножение и деление. Также поддерживаются дробные числа.

Пользовательский интерфейс калькулятора имеет три аттрибута:
  - Поле ввода выражения;
  - Поле вывода результата;
  - Кнопка "=". Она вычисляет текущее выражение в поле ввода;
  - Кнопка "Clear All". Она очищает поле ввода и вывода результата

## Packages and classes:
  - factory — пакет, содержащий методы и классы для всех вычислений. В реализации помог шаболн проектирования FactoryMethod;
    - operators — пакет, содержащий все классы, отвечающие за конкретную операцию;
  - gui — пакет, содержащий класс GUI и Main;
  
## Классы:
| Класс | Назначение |
| ------ | ------ |
| GUI | отвечает за графический интерфейс, реализованный с помощью Swing |
| Main | класс, запускающий приложение |
| Calculator | Отвечает за обработку строки в обратной польской записи и последующие вычисления |
| PolishNotator | Отвечает за перевод строки в обратную польскую запись для последующих вычислений |
| FactoryMain | Класс, обрабатывающий классы операций, исполняющих интерфейс Operation |
| Plus, Minus, Divide, Multiply | Классы, отвечающие за арифметические операции +, -, /, * соответственно и реализующие метод calculate|

Интерфейс Operator — функциональный интерфейс, содержащий метод calculate, который выполняет некоторую арифметическую операцию.

### Проект был реализован в IntelliJ IDEA Community 2018.3. Java 11
