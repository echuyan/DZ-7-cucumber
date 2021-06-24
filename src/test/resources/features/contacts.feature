Feature: Проверка адреса


  Scenario: Check address
    Given Открыта страница Контакты
    When Отображается адрес
    Then Адрес корректный
