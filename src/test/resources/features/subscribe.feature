Feature: Проверка возможности подписки


  Scenario: Check subscription
    Given Открыта главная страница
    When Оформлена подписка
    Then Подписка успешна
