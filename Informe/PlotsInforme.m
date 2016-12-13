## Inicializador

start = 15;
#finish = 25;
finish = 21;
for i = start : finish
  text_length(i - start + 1) = 2^i;
  word_search_number(i - start + 1) = 2^i / 10;
end

## --- START DATA --- ##

## Tiempos de construccion Suffix Tree (mseg)

#construction_time = [15,61,109,226,575,1034,34,34,34,34,34]; # 15..25
construction_time = [15,61,109,226,575,1034,2219]; # 15..21

## Operaciones por fase

#avg_ops_per_phase = [0,0,0,0,0,0,0,0,0,0,0];
avg_ops_per_phase = [0,0,0,0,0,0,0];

## Largo promedio patron

#avg_pattern_length = [1,2,3,4,5,6,7,8,9,10,11];
avg_pattern_length = [1,2,3,4,5,6,7];

## Tiempos de busqueda (mseg)

#search_time = [11,10,9,8,7,6,5,4,3,2,1];
search_time = [7,6,5,4,3,2,1];

## --- END DATA --- ##

## Plot para tiempo de construccion

figure(1);
hold on;
plot(log2(text_length), log2(construction_time), "r");
legend("Tiempo de construccion");
grid on;
xlabel ("Numero de caracteres en texto");
ylabel ("Tiempo de construccion (mseg)");
title ("Numero de caracteres vs Tiempo de construccion del Suffix Tree (Escala logaritmica");
print -dpng fig1.png;
hold off;

## Plot para tiempo de construccion

figure(2);
hold on;
plot(text_length, avg_ops_per_phase, "b");
legend("Operaciones");
grid on;
xlabel ("Numero de caracteres en texto");
ylabel ("Promedio de operaciones");
title ("Numero de caracteres vs Operaciones por fase");
print -dpng fig2.png;
hold off;

## Plot para busqueda

figure(3);
hold on;
plot(word_search_number, search_time, "r");
plot(word_search_number, avg_pattern_length, "b");
legend("Tiempo de busqueda", "Promedio de largo del patron");
grid on;
xlabel ("Numero de palabras");
ylabel ("Tiempo de busqueda (mseg)");
title ("Numero de palabras vs Tiempo de busqueda");
print -dpng fig3.png;
hold off;
