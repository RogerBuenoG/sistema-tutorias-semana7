# Análisis de cobertura

## Resultado observado
- Cobertura de líneas: consultar `target/site/jacoco/index.html` después de ejecutar `mvn clean test`.
- Cobertura de ramas: consultar el mismo reporte de JaCoCo.
- Clase analizada: `edu.uees.testing.service.ReservaService`.

## Huecos relevantes
1. La lógica de `calcularTotal` requiere cubrir cada alternativa de descuento y la validación de total negativo.
2. `confirmar()` requiere cubrir reserva nula, disponibilidad falsa y disponibilidad verdadera, incluyendo sus interacciones.

## Decisiones
- Se añadieron casos específicos para NORMAL, VIP, ESTUDIANTE y total negativo.
- Se añadió un caso de sensibilidad de mayúsculas para VIP.
- Se utilizaron dobles de prueba en `confirmar()`: `DisponibilidadClient` se controla mediante `when(...).thenReturn(...)`; `ReservaRepository` y `Notificador` se verifican con `verify(...)`.
- La cobertura alta no garantiza corrección porque una prueba puede ejecutar una línea sin comprobar el resultado de negocio o sin verificar escenarios límite e interacciones.
