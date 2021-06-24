Feature: Проверка информации


  Scenario: Check info
    Given Открыта страница FAQ
    When Отображается текст
    Then Текст корректный
