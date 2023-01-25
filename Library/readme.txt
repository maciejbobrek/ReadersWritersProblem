Jeśli chodzi o koncepcje programu to trochę ją zmieniłem. Mianowicie zamiast robić 2 klas Reader i Writer zrobiłem 1 klasę
Member, która łączy użytkowość tych klas. W konstruktorze podajemy jaki typ osoby chcielibyśmy stworzyć. Do stworzenia projektu użyłem
Semaphor, czyli bardzo pomocnej struktury pozwalającej na zarządzanie dostępem do zasobu.
Jedna służy jako 1 osobową pamięć służącą do zapamiętywania pierwszej osoby w kolejce a druga jako samą czytelnie.
Writer wchodzi do czytelni z permitem 5, i dlatego tylko wtedy gdy żaden czytelnik ani pisarz nie używają zasobu będzie on mógł wejść.