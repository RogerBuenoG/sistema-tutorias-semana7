# UEES UCOM0310 — Semana 7 — Ae6

## Suite de pruebas, cobertura y Pull Request documentado

Proyecto individual de la asignatura **Diseño de Software – UCOM0310**, Semana 7.

### Requisitos
- Java 21
- Maven 3.9+
- Git

### Ejecución
```bash
mvn clean test
```

La ejecución genera el reporte JaCoCo en:
`target/site/jacoco/index.html`

### Suite implementada
La clase `ReservaServiceTest` contiene 12 casos JUnit 5 y Mockito que cubren:
- Cancelación normal y límites.
- Descuentos NORMAL, VIP y ESTUDIANTE.
- Validación de total negativo.
- Confirmación disponible, no disponible y reserva nula.
- Verificación de interacciones y orden de dependencias.

### Evidencia y trazabilidad
- `docs/01_MATRIZ_CASOS_PLANTILLA.md`: matriz de 12 casos.
- `docs/02_ANALISIS_COBERTURA_PLANTILLA.md`: interpretación de cobertura.
- `docs/03_PULL_REQUEST_PLANTILLA.md`: contenido sugerido para el PR.

### Flujo Git sugerido
```bash
git switch -c ae6/suite-pruebas
git add .
git commit -m "test: agregar casos de cancelacion"
git commit -m "test: cubrir descuentos y errores"
git commit -m "test: agregar confirmacion con mocks"
git commit -m "docs: analizar cobertura jacoco"
git push -u origin ae6/suite-pruebas
```

Después, crear un Pull Request desde `ae6/suite-pruebas` hacia `main` usando la plantilla de `docs/03_PULL_REQUEST_PLANTILLA.md`.
