# Despliegue en Render — Sistema de Nómina

Guía paso a paso para publicar la aplicación web en [Render](https://render.com) como **Web Service**.

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

### Comandos de verificación local (Windows)

```powershell
.\mvnw.cmd test
.\mvnw.cmd clean package
.\mvnw.cmd spring-boot:run
```

Abrir: **http://localhost:8080/**

---

## 2. Subir el proyecto a GitHub

1. Inicializa Git si aún no lo has hecho:

```powershell
git init
git add .
git commit -m "Preparar proyecto para despliegue en Render"
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
4. Revisa la configuración y confirma el despliegue.

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
| **Runtime** | `Native` |
| **Plan** | `Free` |
| **Build Command** | `chmod +x mvnw && ./mvnw clean package -DskipTests` |
| **Start Command** | `java -jar target/sistema-nomina-0.0.1-SNAPSHOT.jar` |

5. En **Environment Variables**, agrega:

| Key | Value |
|---|---|
| `JAVA_VERSION` | `21` |

> Render asigna automáticamente la variable `PORT`. La aplicación la usa mediante `server.port=${PORT:8080}`.

6. Haz clic en **Create Web Service**.

---

## 4. Proceso de build en Render

Durante el despliegue, Render ejecutará:

1. `chmod +x mvnw` — Habilita el Maven Wrapper en Linux.
2. `./mvnw clean package -DskipTests` — Compila el proyecto, genera el JAR y compila SASS a CSS.
3. `java -jar target/sistema-nomina-0.0.1-SNAPSHOT.jar` — Inicia la aplicación web.

El primer despliegue puede tardar varios minutos porque Maven descarga dependencias y Node.js (para compilar SASS).

---

## 5. Verificar el despliegue

Cuando Render muestre **Live**, abre la URL pública generada, por ejemplo:

```
https://sistema-nomina.onrender.com/
```

### Rutas web disponibles

| Ruta | Descripción |
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

### El build falla por Java

- Verifica que `JAVA_VERSION=21` esté configurada en Render.
- Confirma que `pom.xml` usa `<java.version>21</java.version>`.

### La aplicación no responde

- Revisa los logs en el dashboard de Render.
- Confirma que `application.properties` contiene `server.port=${PORT:8080}`.

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
- **No se requiere Docker** para este despliegue.
- Cada nuevo `git push` a `main` puede disparar un redespliegue automático si está habilitado.

---

## 8. Comandos útiles antes del push

```powershell
.\mvnw.cmd test
.\mvnw.cmd clean package
git status
git add .
git commit -m "Preparar despliegue en Render"
git push origin main
```
