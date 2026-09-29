# Pull Request — Ae6

## Objetivo
Proteger las reglas críticas del módulo de reservas mediante una suite JUnit 5 reproducible, dobles de prueba y evidencia de cobertura JaCoCo.

## Cambios realizados
- Se ampliaron las pruebas de `ReservaService` a 12 casos.
- Se cubrieron escenarios normales, límites, inválidos y excepciones.
- Se incorporaron Stub/Mock con Mockito para `confirmar()`.
- Se documentaron matriz, cobertura, Git y autorrevisión.

## Casos de prueba
- Cancelación: 5 horas, 2 horas y 1 hora.
- Descuentos: NORMAL, VIP, ESTUDIANTE, VIP en minúsculas y total negativo.
- Confirmación: disponible, no disponible, reserva nula y orden de interacciones.

## Cómo verificar
```bash
mvn clean test
```

## Cobertura
Ejecutar la suite genera el reporte en `target/site/jacoco/index.html`. El directorio `target/` es generado y no debe versionarse.

## Limitaciones
El proyecto base no incluye una integración real de disponibilidad, persistencia ni notificación; por ello se controlan mediante interfaces y Mockito.

## Autorrevisión
- [x] Compila
- [x] Pruebas en verde
- [x] Sin archivos accidentales
- [x] Commits descriptivos
- [x] Documentación actualizada

## Uso de IA
Se utilizó ChatGPT como apoyo para estructurar la matriz, proponer escenarios de prueba, revisar la redacción técnica y preparar la documentación. La ejecución y validación del proyecto se realizó sobre el código base proporcionado.

### Evidencia final

La entrega debe incluir matriz, suite, cobertura, Git, PR, autorrevisión y declaración de IA.
