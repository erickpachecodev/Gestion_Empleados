# Despliegue en Render — Sistema de Nómina

Guía paso a paso para publicar la aplicación web en [Render](https://render.com) como **Web Service** usando **Docker**.

---

## Requisitos previos

- Cuenta en Render.
- Cuenta en GitHub.
- Proyecto funcionando en local.
- Repositorio Git con el código del proyecto.

---

## 1. Verificar configuración del proyecto

Antes de desplegar, confirma que el proyecto contiene:

| Elemento | Valor esperado |
|---|---|
| Java | **21** (`pom.xml`) |
| Spring Boot | **4.1.0** |
| Puerto dinámico | `server.port=${PORT:8080}` en `application.properties` |
| JAR ejecutable | `target/sistema-nomina-0.0.1-SNAPSHOT.jar` |
| Maven Wrapper | `mvnw`, `mvnw.cmd`, carpeta `.mvn/` |
| Dockerfile | `./Dockerfile` en la raíz del proyecto |
| Configuración Render | `render.yaml` con `runtime: docker` |

Render usa **Docker** para aplicaciones Java/Spring Boot. El `Dockerfile` incluido construye la imagen con Maven Wrapper y Java 21. Render no requiere `buildCommand` ni `startCommand` manual: construye la imagen desde GitHub y ejecuta el `ENTRYPOINT` definido en el Dockerfile.

### Comandos de verificación local (Windows)

```powershell
.\mvnw.cmd test
.\mvnw.cmd clean package
.\mvnw.cmd spring-boot:run
```

Abrir: **http://localhost:8080/**

### Comandos opcionales con Docker (si tienes Docker Desktop)

```powershell
docker build -t sistema-nomina .
docker run --rm -p 8080:8080 -e PORT=8080 sistema-nomina
```

---

## 2. Subir el proyecto a GitHub

1. Inicializa Git si aún no lo has hecho:

```powershell
git init
git add .
git commit -m "Preparar proyecto para despliegue en Render con Docker"
```

2. Crea un repositorio en GitHub (por ejemplo: `sistema-nomina`).
3. Conecta y sube la rama principal:

```powershell
git remote add origin https://github.com/TU_USUARIO/sistema-nomina.git
git branch -M main
git push -u origin main
```

---

## 3. Crear el Web Service en Render

### Opción A: Usar `render.yaml` (recomendado)

1. En Render, ve a **Dashboard** → **New** → **Blueprint**.
2. Conecta tu repositorio de GitHub.
3. Render detectará el archivo `render.yaml` en la raíz.
4. Verifica que el servicio use **Docker** (`runtime: docker`).
5. Revisa la configuración y confirma el despliegue.

### Opción B: Configuración manual desde el dashboard

1. En Render, ve a **Dashboard** → **New** → **Web Service**.
2. Conecta tu repositorio de GitHub.
3. Selecciona el repositorio `sistema-nomina`.
4. Configura los siguientes valores:

| Campo | Valor |
|---|---|
| **Name** | `sistema-nomina` |
| **Region** | La más cercana a tu ubicación |
| **Branch** | `main` |
| **Runtime** | `Docker` |
| **Plan** | `Free` |
| **Dockerfile Path** | `./Dockerfile` |
| **Docker Context** | `.` |
| **Health Check Path** | `/` |

> No configures `buildCommand` ni `startCommand`. Render construye la imagen automáticamente desde el `Dockerfile` y ejecuta el JAR con el `ENTRYPOINT` definido ahí.

> Render asigna automáticamente la variable `PORT`. La aplicación la usa mediante `server.port=${PORT:8080}`.

5. Haz clic en **Create Web Service**.

---

## 4. Proceso de build en Render

Durante el despliegue, Render:

1. Clona el repositorio desde GitHub.
2. Construye la imagen Docker usando `./Dockerfile`.
3. En la etapa de build, ejecuta `./mvnw clean package -DskipTests` (compila el proyecto, genera el JAR y compila SASS a CSS).
4. Copia el JAR a una imagen final liviana con Java 21 JRE.
5. Inicia la aplicación con `java -jar app.jar`.

El primer despliegue puede tardar varios minutos porque Maven descarga dependencias y Node.js (para compilar SASS).

---

## 5. Verificar el despliegue

Cuando Render muestre **Live**, abre la URL pública generada, por ejemplo:

```
https://sistema-nomina.onrender.com/
```

La ruta de health check configurada es **`/`**.

### Rutas web disponibles

| Rota | Descripción |
|---|---|
| `/` | Dashboard con resumen y accesos rápidos |
| `/empleados` | Todos los empleados |
| `/empleados/apellidos` | Ordenados por primer apellido |
| `/empleados/salarios/asc` | Salario neto ascendente |
| `/empleados/salarios/desc` | Salario neto descendente |
| `/empleados/roles` | Cantidad por rol |
| `/empleados/detalles-salarios` | Detalle salarial completo |

---

## 6. Solución de problemas comunes

### El build de Docker falla

- Revisa los logs de build en el dashboard de Render.
- Verifica que `mvnw`, `.mvn/`, `pom.xml` y `src/` estén en el repositorio.
- Confirma que `.dockerignore` no excluye archivos necesarios para compilar.

### La aplicación no responde

- Revisa los logs en el dashboard de Render.
- Confirma que `application.properties` contiene `server.port=${PORT:8080}`.
- Verifica que el health check apunte a `/`.

### Los estilos no cargan

- El build debe completar la fase SASS (`frontend-maven-plugin`).
- Revisa que `package.json` y `src/main/sass/styles.scss` estén en el repositorio.

### El servicio Free se apaga por inactividad

- En el plan gratuito, Render puede suspender el servicio tras un periodo sin tráfico.
- La primera petición después de la suspensión puede tardar ~30–60 segundos en responder.

---

## 7. Notas importantes

- **Solo se despliega la interfaz web.** La consola (`MainConsola`) no se ejecuta en Render.
- **No hay base de datos.** Los datos viven en memoria y se reinician en cada despliegue.
- **El despliegue usa Docker.** Render construye la imagen desde el `Dockerfile` incluido en el repositorio.
- Cada nuevo `git push` a `main` puede disparar un redespliegue automático si está habilitado.

---

## 8. Comandos útiles antes del push

```powershell
.\mvnw.cmd test
.\mvnw.cmd clean package
git status
git add .
git commit -m "Configurar despliegue en Render con Docker"
git push origin main
```
