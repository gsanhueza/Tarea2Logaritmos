## Inicializador

start = 15;
finish = 25;

for i = start : finish
  text_length(i - start + 1) = 2^i;
  word_search_number(i - start + 1) = 2^i / 10;
end

## --- START DATA --- ##

## Tiempos de construccion Suffix Tree (mseg)

construction_time = [5, 3, 3, 6, 11, 13, 21, 44, 93, 167, 329]; # 15..25

## Largo promedio patron

avg_pattern_length = [5.36, 5.31, 5.52, 5.39, 5.35, 4.76, 4.50, 4.43, 4.37, 4.42, 4.47];

## Tiempos de busqueda (mseg)

search_time = [2671.75, 1109.40, 259.94, 296.97, 254.40, 268.31, 246.81, 245.39, 254.88, 253.84, 261.40];

## --- END DATA --- ##

## Plot para tiempo de construccion

figure(1);
hold on;
plot(text_length, construction_time, "r");
legend("Tiempo de construccion (mseg)");
grid on;
xlabel ("Numero de caracteres en texto");
ylabel ("Tiempo de construccion (mseg)");
title ("Numero de caracteres vs Tiempo de construccion del Suffix Tree");
print -dpng fig1.png;
hold off;

## Plot para tiempo de construccion

figure(2);
hold on;
plot(word_search_number, avg_pattern_length, "b");
legend("Largo promedio");
grid on;
xlabel ("Numero de sufijos buscados");
ylabel ("Largo promedio del sufijo");
title ("Numero de sufijos buscadas vs Largo promedio del sufijo");
print -dpng fig2.png;
hold off;

## Plot para busqueda

figure(3);
hold on;
plot(log2(word_search_number), search_time, "r");
legend("Tiempo de busqueda (nano-segundos)");
grid on;
xlabel ("Numero de sufijos buscados (escala logaritmica)");
ylabel ("Tiempo de busqueda (nano-segundos)");
title ("log(Numero de palabras) vs Tiempo de busqueda");
print -dpng fig3.png;
hold off;
