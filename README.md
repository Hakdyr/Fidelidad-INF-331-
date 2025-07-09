# Fidelidad-INF-331-
Propietario: Joaquín Aguilera (201930003-2)
## Estructura/Diagrama

```yaml
Cliente
 ├── id : String
 ├── nombre : String
 ├── correo : String
 ├── puntos : int
 ├── nivel : Nivel
 └── compras : List<Compra>

Compra
 ├── idCompra : String
 ├── idCliente : String
 ├── monto : double
 └── fecha : LocalDate

Nivel (enum)
 ├── BRONCE
 ├── PLATA
 ├── ORO
 └── PLATINO
```
## Test unitarios JUnit 5

### Ciclo 1: Agregar cliente
- Rojo: se crea test agregarClienteValido_debeCrearClienteConBronceY0Puntos().

- Verde: se implementa ClienteService.agregarCliente().

- Azúl: se centraliza validación de correo en entidad Cliente

### Ciclo 2: Validar correo

- Rojo: test que valida correo inválido lanza excepción.

- Verde: se añade validación en constructor de Cliente.

- Azúl : mejorar mensaje de error, encapsular setters.

### Ciclo 3: Actualizar cliente

- Rojo: test para actualizar nombre y correo.

- Verde: se implementa ClienteService.actualizarCliente().

- Azúl: validación de correo en setter, limpieza.

### Ciclo 4: Registrar compra y sumar puntos base

- Rojo: test verifica puntos base y acumulación.

- Verde: se implementa CompraService.registrarCompra().

- Azúl: separar lógica de bonus y actualización de nivel.

### Ciclo 5: Bonus por tercera compra
- Rojo: test verifica +10 pts en tercera compra del día.

- Verde: conteo de compras por fecha y suma de bonus.

- Azúl: organizar lógica en métodos más pequeños.

