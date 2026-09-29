# Matriz de casos Ae6

| ID | Regla | Escenario | Entrada | Esperado | Tipo | Riesgo |
|---|---|---|---|---|---|---|
| CP-01 | puedeCancelar | Normal | 5 horas | true | Normal | Permitir cancelación válida |
| CP-02 | puedeCancelar | Límite válido | 2 horas | true | Límite | Evitar rechazo del mínimo permitido |
| CP-03 | puedeCancelar | Límite inválido | 1 hora | false | Límite | Evitar cancelación fuera de plazo |
| CP-04 | calcularTotal | NORMAL | NORMAL, 100 | 100 | Normal | Cobro incorrecto del caso base |
| CP-05 | calcularTotal | VIP | VIP, 100 | 85 | Alternativo | No aplicar descuento VIP |
| CP-06 | calcularTotal | ESTUDIANTE | ESTUDIANTE, 100 | 90 | Alternativo | No aplicar descuento estudiantil |
| CP-07 | calcularTotal | Tipo VIP en minúsculas | vip, 100 | 85 | Alternativo | Sensibilidad a mayúsculas |
| CP-08 | calcularTotal | Total negativo | NORMAL, -1 | IllegalArgumentException | Inválido | Aceptar importes inválidos |
| CP-09 | confirmar | Disponible | R-001, disponible=true | CONFIRMADA + guardar + notificar | Normal | No completar el flujo de confirmación |
| CP-10 | confirmar | No disponible | R-002, disponible=false | IllegalStateException; PENDIENTE | Alternativo | Confirmar una reserva no disponible |
| CP-11 | confirmar | Reserva nula | null | IllegalArgumentException | Inválido | Procesar una reserva inexistente |
| CP-12 | confirmar | Orden de interacciones | R-003, disponible=true | disponibilidad → guardar → notificar | Interacción | Ejecutar dependencias en orden incorrecto |
