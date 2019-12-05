# [RULP](https://github.com/wouterken/rulp) - Ruby Linear Programming

Autor: Bartosz Białoskórski I6E2S1

### Opis
Rulp jest DSL'em do Ruby, który pozwala na generowanie opisów problemów
programowania liniowego lub całkowitoliczbowego w formacie LP.
Wygenerowane opisy mogą być następnie wykonane przez jeden z tooliktów takich jak
Cbc, Scip, fscip(ug) bądź GLPK, a zwrócone przez toolkit wyniki są parsowane przez Rulp.
Jest to tak na prawdę api do komunikacji z powyższymi toolkitami z poziomu Ruby.
Dzięki temu, że jest to DSL to API rulp jest bardzo proste i zwięzłe.
Wybrany toolkit może być określony jawnie w kodzie lub przy pomocy zmiennej środowiskowej.
Dokumentacja jest niestety praktycznie nieistniejąca co jest wspólnym motywem bibliotek programowania liniowego w Ruby. :) 

### Uruchomienie przykładu:
Aby korzystać z rulp należy mieć już zainstalowany jeden z wyżej wymienionych toolkitów.
Ja użyłem [GLPK](https://www.gnu.org/software/glpk/), który zainstalowałem przy pomocy packet managera  - homebrew : `homebrew install glpk`
Jeśli korzysta Pan z Debian Package: `sudo apt-get install glpk`.

Konkretnie *ten* toolkit będzie potrzebny do uruchomienia przykładu, gdyż wybór solvera określiłem jawnie w kodzie.

Następnie należy sklonować repozytorium rulp, które można znaleźć w hyperlinku w tytule tego dokumentu do folderu wat-minwd/i6e1-2s1/rulp/rulp.

Przykład można uruchomić z terminala komendą `ruby example.rb`.
