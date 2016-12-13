# Inicializador

start = 15;
finish = 21;
for i = start : finish
  text_length(i - start + 1) = 2^i;
  word_search_number(i - start + 1) = 2^i / 10;
end

# --- START DATA --- #

# Tiempos de construccion Suffix Tree (mseg)

construction_time(1) = 6; # 15
construction_time(2) = 11; # 16
construction_time(3) = 16; # 17
construction_time(4) = 19; # 18
construction_time(5) = 20; # 19
construction_time(6) = 28; # 20
construction_time(7) = 34; # 21

# Operaciones por fase

avg_ops_per_phase(1) = 0;
avg_ops_per_phase(2) = 0;
avg_ops_per_phase(3) = 0;
avg_ops_per_phase(4) = 0;
avg_ops_per_phase(5) = 0;
avg_ops_per_phase(6) = 0;
avg_ops_per_phase(7) = 0;

# Largo promedio patron

avg_pattern_length(1) = 1;
avg_pattern_length(2) = 2;
avg_pattern_length(3) = 3;
avg_pattern_length(4) = 4;
avg_pattern_length(5) = 5;
avg_pattern_length(6) = 6;
avg_pattern_length(7) = 7;

# Tiempos de busqueda (mseg)

search_time(1) = 0;
search_time(2) = 0;
search_time(3) = 0;
search_time(4) = 0;
search_time(5) = 0;
search_time(6) = 0;
search_time(7) = 0;

# --- END DATA --- #

# Plot para tiempo de construccion

figure(1);
hold on;
plot(log2(text_length), construction_time, "r");
legend("Tiempo de construccion");
grid on;
xlabel ("Numero de caracteres en texto (escala log)");
ylabel ("Tiempo de construccion (mseg)");
title ("Numero de caracteres vs Tiempo de construccion del Suffix Tree");
hold off;

# Plot para tiempo de construccion

figure(2);
hold on;
plot(text_length, avg_ops_per_phase, "b");
legend("Operaciones");
grid on;
xlabel ("Numero de caracteres en texto");
ylabel ("Promedio de operaciones");
title ("Numero de caracteres vs Operaciones por fase");
hold off;

# Plot para busqueda

figure(3);
hold on;
plot(word_search_number, search_time, "r");
plot(word_search_number, avg_pattern_length, "b");
legend("Tiempo de busqueda", "Promedio de largo del patron");
grid on;
xlabel ("Numero de palabras");
ylabel ("Tiempo de busqueda (mseg)");
title ("Numero de palabras vs Tiempo de busqueda");
hold off;
