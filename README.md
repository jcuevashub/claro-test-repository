
# Clean Architecture en Android con Kotlin

Este repositorio contiene una aplicación Android de ejemplo implementada utilizando los principios de Clean Architecture en Kotlin.
## Características

- **Clean Architecture**: Este proyecto está organizado siguiendo los principios de Clean Architecture, que separa el código en diferentes capas con responsabilidades claras, haciendo que la base de código sea más fácil de entender, probar y mantener.
- **MVVM**: Se utiliza el patrón Model-View-ViewModel (MVVM), que facilita una separación del desarrollo de la interfaz gráfica de usuario del desarrollo de la lógica de negocio o back-end.
- **Inyección de Dependencias (Hilt)**: Se utiliza Hilt para la inyección de dependencias, lo que ayuda a proporcionar las dependencias requeridas a las clases, haciendo que el código sea más modular, mantenible y testeable.
- **Componente de Navegación**: El Componente de Navegación de Android se utiliza para navegar entre diferentes pantallas (fragmentos) de la aplicación.
- **Retrofit / API REST**: Retrofit se utiliza para hacer solicitudes de red a una API REST.
- **Data / View Binding**: Se utilizan Data Binding y View Binding para vincular componentes de UI en los layouts a fuentes de datos y evitar código repetitivo.
- **Corrutinas**: Se utilizan Corrutinas de Kotlin para gestionar hilos en segundo plano con código simplificado y reducir la necesidad de callbacks.

## Estructura del Proyecto

El proyecto se divide en las siguientes capas principales:

- **Capa de Presentación**: Esta capa contiene el código de UI. Actividades, fragmentos, view models y adaptadores forman parte de esta capa. El patrón MVVM se utiliza en esta capa.
- **Capa de Dominio**: Esta capa contiene toda la lógica de negocio. Los casos de uso (interactores) y los modelos de dominio forman parte de esta capa.
- **Capa de Datos**: Esta capa contiene el código para la recuperación de datos de varias fuentes de datos como red, base de datos, etc. Repositorios, fuentes de datos y modelos de datos forman parte de esta capa.

```
app
├─ di
├─ data
│  ├─ remote
│  ├─ repository
│  ├─ model
├─ domain
│  ├─ usecase
│  ├─ model
├─ presentation
│  ├─ view
│  │  ├─ adapter
│  │  ├─ fragment
│  │  ├─ activity
│  ├─ viewmodel
├─ util
```

## Librerías Utilizadas

- **Hilt**: Para la inyección de dependencias.
- **Retrofit**: Para hacer solicitudes de red.
- **Componente de Navegación**: Para la navegación entre fragmentos.
- **Corrutinas**: Para operaciones en segundo plano.
- **Data Binding**: Para vincular componentes de UI en los layouts a fuentes de datos.
- **View Binding**: Para vincular vistas.

## Cómo Construir

1. Clona el repositorio:

```
git clone https://github.com/jcuevashub/claro-test-repository
```

2. Abre el proyecto en Android Studio.

3. Construye y ejecuta la aplicación en un emulador o un dispositivo físico.

## Contribuir

¡Las contribuciones siempre son bienvenidas! No dudes en abrir un problema o enviar una solicitud de pull con tus cambios o mejoras.

## Licencia

Este proyecto es de código abierto y está disponible bajo la [Licencia MIT](LICENSE).
