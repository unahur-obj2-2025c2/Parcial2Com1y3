# 2do Parcial de Objetos II


> 💡 _Este parcial busca evaluar la correcta implementación de los patrones que aplican a esta solución, además del diseño orientado a objetos, la cohesión entre clases y la capacidad de extender comportamientos sin modificar el código existente._

## Consideraciones Iniciales

- El código entregado debe compilar obligatoriamente. Un parcial entregado cuyo código no compila queda desaprobado automáticamente.

- Se realizará un control exhaustivo, incluyendo distintas herramientas de análisis estático de código para identificar posibles copias entre las soluciones entregadas.

- La solución debe aplicar patrones de diseño apropiados para la problemática planteada. **Ojo** Pueden aparecer patrones vistos en la primera parte de la materia.

- El código entregado debe tener los test suficientes que garantice el correcto funcionamiento de la solucion que propone el alumno. Se espera que se completen los 4 test que se detallan con el setup propuesto en el enunciado. El coverage debe ser de al menos un 75%.
  
- No se aceptan entregas fuera de plazo ni que no estén correctamente subidas al repositorio del classroom de la materia.

- Duración del Parcial: 3 hs.

# 🚦 Sistema de Control de Tráfico Inteligente

## 🌍 Contexto

Una única **Central de Tránsito** que debemos modelar coordina una red de **cruces viales inteligentes** distribuidos por toda la ciudad.  
Su misión es mantener el flujo vehicular lo más fluido posible, detectando eventos de tráfico y notificando a los cruces para que ajusten sus semáforos y rutas alternativas.

Cada cruce vial decide cómo calcular su **nivel de congestión** según su comportamiento actual.  
Este comportamiento puede cambiar dinámicamente según las necesidades del momento.

---

## 📢 Reportar Evento de Tráfico

Cada evento tiene las siguientes características:

- **Tipo:** descripción del evento (por ejemplo: “accidente”, “obras”, “congestión”)
- **Gravedad:** valor numérico del 1 al 10 (sino lanza una excepción con el mensaje **"Nivel de gravedad incorrecto"**.)
  - (1 = leve, 10 = colapso total)
- **Grave:** se considera grave si la gravedad es mayor o igual a 8

### Funcionalidades de la Central de Tránsito

#### 🚨 Registro de eventos

Cuando se reporta un evento (tipo y gravedad), la central:

1. Crea el evento indicando el tipo y gravedad recibidos.
2. Guarda el evento en un registro de eventos, donde se almacena:
   - El evento en sí.
   - y los cruces a notificar que serán notificados
3. Notifica el evento a **todos los cruces registrados**.

#### 🏗️ Gestión de cruces

- Puede **agregar o quitar** cruces viales en tiempo real.

#### 📊 Cantidad de notificaciones

- Devuelve el total de notificaciones enviadas (suma de todas las realizadas por cada evento registrado).

---

## 🚦 Cruce Vial Inteligente

Cada cruce:

- Tiene un **nombre fijo** (por ejemplo: “Cruce Av. Mitre y Belgrano”).
- Registra todas las **alertas o eventos** recibidos.
- Calcula su **nivel de congestión** según su **estrategia actual**.
- Cada cruce puede adoptar una nueva estrategia de cálculo de congestión. Inicialmente, todos los cruces usan el comportamiento **Congestión Crítica**

---

## 🔄 Estrategias de Congestión

### 🚨 Congestión Crítica

- El nivel de congestión es igual a la gravedad del último evento recibido, pero si dicho evento es grave, la congestión pasa automáticamente a 10.

### 📊 Congestión Promedio

- Calcula la congestión como el **promedio de las gravedades** de todos los eventos recibidos.  
  Ejemplo: si recibió eventos con gravedades `[3, 5, 2]`, el nivel de congestión será `(3 + 5 + 2) / 3 = 3.33`.

### ⏱️ Congestión Acumulativa

- Suma las gravedades de los **eventos graves** recibidos.  
  Ejemplo: si hubo eventos graves con gravedades 8 y 9, la congestión será `8 + 9 = 17`.

---

## 🧪 Tests (Casos de Prueba)

### 🧩 Set Up

- Recuperar la instancia de **Central de Tránsito** y guardarla en una variable.
- Limpiar todos todos lo cruces y eventos registrados.
- Crear 2 cruces (`c1`, `c2`) con comportamiento por defecto (**Congestión Crítica**).
- Agregar los cruces a la central a la única central.

---

### ✅ Test #1 - Notificaciones y Congestión Crítica

**dadoElSetUp_alAgregarEventos_SeVerificaCorrectamenteLasNotificacionesYCongestion**

Dado el setup inicial:

- Se agregan 2 eventos:
  - "obras" con gravedad **6**
  - "accidente" con gravedad **8**

Se verifica que:

- Cada cruce haya recibido ambos eventos.
- El nivel de congestión de cada cruce sea **10**, por ser el último evento grave.

---

### ✅ Test #2 - Cambio de Estrategia

**dadoElSetUp_alCambiarEstrategiaYAgregarEventos_SeVerificaCorrectamenteLasNotificacionesYCongestion**

Dado el setup inicial:

- Cambiar el comportamiento de `c1` a **Congestión Promedio**.
- Se agregan 2 eventos:
  - "obras" con gravedad **6**
  - "accidente" con gravedad **8**

Se verifica que:

- Ambos cruces recibieron ambos eventos.
- El nivel de congestión de `c1` es **7**.
- El nivel de congestión de `c2` es **10**.

---

### ✅ Test #3 - Agregar y Quitar Cruces

**dadoElSetUp_alAgregarEventosQuitarUnCruceYAgregarNuevoEvento_SeVerificaCorrectamenteLasNotificacionesYCongestion**

Dado el setup inicial:

- Agregar 2 eventos: “obras” (6) y “accidente” (8).
- Quitar el cruce `c1` de los registrados.
- Agregar un nuevo evento: “congestión” (7).

Se verifica que:

- `c1` tiene 2 eventos y congestión **10**.
- `c2` tiene 3 eventos y congestión **7**.
- La cantidad total de notificaciones enviadas es **5**.

---

### ❌ Test #4 - Excepción

Si se intenta reportar un evento con gravedad **0**, **11** o **negativa**, el sistema debe lanzar una excepción con el mensaje:  
**"Nivel de gravedad incorrecto"**.

---

## 🎯 Bonus / Punto 10

Se propone una crear una **Congestión Inercial**, que mantiene un estado interno representando el nivel de **congestión actual** del cruce.
Cada nuevo evento de tránsito actualiza ese nivel de la siguiente forma:

- Si la gravedad del nuevo evento es mayor que la **congestión actual**, el nivel de congestión acumenta aumenta hasta ese nuevo valor.
- Si la gravedad es menor, la congestión actual disminuye solo en 1 punto, simulando que el cruce tarda en descongestionarse completamente.

De esta manera, el sistema conserva cierta “memoria” de la congestión reciente, reflejando que el tránsito no se normaliza de inmediato después de un evento grave.


