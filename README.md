# Automatizacion WASI

## Información
El proyecto de automatización está realizado en Kotlin haciendo uso del framework Selenium.
Gradle es la herramienta de automatización de la construcción del código utilizada en este caso.

### Escenarios
Todos los escenarios automatizados se encuentran en la clase <b>Escenarios</b>. [Ir a Escenarios].
- Abrir sesión fallidamente
- Abrir y cerrar sesión exitosamente
- Cambiar password exitosamente
- Cambiar password fallidamente

### Casos
Los casos se encuentran bajo las clases <b>ConfiguracionesUsuarioTests</b> y <b>LoginLogoutTests</b>. [Ir a Casos]

### Reporte de ejecución
Para obtener el reporte completo de la ejecución se puede ejecutar la tarea <b>allureReport</b> de Gradle
```sh
./gradle allureReport
```
Posteriormente se genera un reporte HTML con el detalle completo de la ejecución.

### Snapshots
Cada ejecución se realiza con un identificador único y los screenshots relacionados a las ejecuciones se encuentran bajo la carpeta <b>/test-result/reports/</b>.
#### Ejemplo:
```sh
cd test-result/reports/6cc69119-23fa-4de9-a0a3-a4ae16583f1c/realizarLogin/
```

[Ir a Escenarios]: <https://github.com/davidcadena3/testAutomation/blob/fe22dd21d52f13cbee6e3cc347a6b8c41d11cd66/src/test/kotlin/com/test/automation/wasi/scenarios/Escenarios.kt>
[Ir a Casos]: <https://github.com/davidcadena3/testAutomation/blob/fe22dd21d52f13cbee6e3cc347a6b8c41d11cd66/src/test/kotlin/com/test/automation/wasi/cases>

