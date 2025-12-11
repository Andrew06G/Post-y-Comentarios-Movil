# Posts & Comments (Android · Kotlin)

Aplicación Android para listar publicaciones, buscarlas por título o ID, ver detalle y gestionar comentarios locales con persistencia offline. Arquitectura limpia (Clean Architecture), MVVM, Hilt, Retrofit/Moshi, Room y Navigation Component.

## ✨ Características
- Lista de posts desde JSONPlaceholder.
- Búsqueda en tiempo real por título o ID.
- Detalle del post con acceso a comentarios.
- Creación y visualización de comentarios por post.
- Persistencia local (Room) y recarga tras reinicio.
- Navegación con Safe Args.
- Manejo de estados: loading, éxito, error, vacío.

## 🧩 Arquitectura (Clean + MVVM)
- **Presentación**: Fragments/Activities + ViewModel + LiveData, adapters para RecyclerView.
- **Dominio**: Casos de uso y modelos de dominio.
- **Datos**: Repositorios, DAOs Room, entidades, mapeadores, fuentes remotas (Retrofit/Moshi).
- **DI**: Hilt para proveer dependencias por módulo.

## 🛠️ Tech Stack
- **Kotlin**, **Coroutines**
- **Clean Architecture + MVVM**
- **Hilt** (DI)
- **Retrofit + Moshi** (API)
- **Room** (persistencia local)
- **Navigation Component + Safe Args**
- **LiveData / ViewModel**
- **RecyclerView + Adapters**

## 🗺️ Flujo de la app
1) Lista de posts (desde DB local; si vacío, se sincroniza con API).  
2) Búsqueda en tiempo real (título o ID).  
3) Tap en post → Detalle.  
4) Botón “Ver comentarios” → Lista de comentarios.  
5) Crear comentario → se guarda en Room y se refresca la lista.  

## 📸 Screenshots
- Lista principal: `./screenshots/PrincipalListPost.jpg`
- Búsqueda por nombre: `./screenshots/SearchByName.jpg`
- Búsqueda por ID: `./screenshots/SearchById.jpg`
- Detalle de post: `./screenshots/PostInformation.jpg`
- Sección de comentarios: `./screenshots/CommentsSection.jpg`

## 🚀 Build
Requisitos: JDK 11+, Android Studio Giraffe o superior.
1. Clonar repo.
2. Abrir en Android Studio.
3. Sin configuraciones extra: usa Gradle Wrapper y dependencias declaradas.
4. Ejecutar en dispositivo/emulador (minSdk 24, targetSdk 34).

## 🧩 Inyección de dependencias (Hilt)
- `DatabaseModule`: Room `AppDatabase`, `PostDao`, `CommentDao`.
- `NetworkModule`: Retrofit/Moshi, `JSONPlaceholderService`.
- `RepositoryModule`: bindings `PostsRepository`, `CommentsRepository`.
- `UseCaseModule`: `GetPostsUseCase`, `SearchPostsUseCase`, `GetPostByIdUseCase`, `GetCommentsUseCase`, `InsertCommentUseCase`.
- `PostsApp`: `@HiltAndroidApp` inicializa Hilt.

## 📁 Estructura de carpetas (resumen)
```
app/
 └─ src/main/java/com/example/postscommentsapp/
    ├─ data/
    │  ├─ local/ (db, dao, entity)
    │  ├─ remote/ (api, dto)
    │  ├─ mapper/
    │  └─ repository/
    ├─ domain/
    │  ├─ model/
    │  ├─ repository/
    │  └─ usecase/
    ├─ presentation/
    │  ├─ postlist/ (Fragment, VM, Adapter)
    │  ├─ postdetail/
    │  ├─ comments/
    │  └─ viewmodel/ (compartidos)
    └─ di/
 └─ res/ (layout, navigation, values, drawables)
```

## 🌐 API usada
- Base URL: `https://jsonplaceholder.typicode.com/`
- Endpoint: `GET /posts` (lista de posts)

## 🔍 Lógica de búsqueda
- EditText con texto libre.
- Si la entrada es un número puro → se filtra por ID en la lista cargada.
- Si es texto → `SearchPostsUseCase` filtra por título (case-insensitive) sobre datos locales.
- Al limpiar el texto → se recarga la lista completa.

## 🗄️ Room (persistencia)
- Entidades: `PostEntity`, `CommentEntity`.
- DAOs: `PostDao` (getAll, getById, insert), `CommentDao` (por postId, insert).
- `AppDatabase` versión 1, `fallbackToDestructiveMigration()` para dev.
- Comentarios se guardan solo localmente y se muestran ordenados (fecha desc).

## 🧭 Navegación (Safe Args)
- `nav_graph.xml` define destinos: lista de posts → detalle → comentarios.
- Safe Args genera direcciones tipadas para pasar `postId`.

## 💬 Comentarios
- En Detalle: botón “Ver comentarios”.
- En Comentarios: lista en RecyclerView; input + botón para crear.
- Se inserta en Room y se refresca al instante via ViewModel.

## 🔮 Futuras mejoras
- DiffUtil/ListAdapter para mayor eficiencia en listas.
- Cache/refresh policy para posts (stale-while-revalidate).
- Manejo de estados más rico (empty view / retry).
- Theming con Material 3 y dark mode.
- Tests unitarios e instrumentados.
- Soporte KMP (iOS/Desktop) como deseable.

## 📄 Licencia
MIT License. Ver `LICENSE`.

