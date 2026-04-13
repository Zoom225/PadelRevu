# ─────────────────────────────────────────────
# ÉTAPE 1 : Build du JAR avec Maven
# ─────────────────────────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copier les fichiers Maven en premier (cache des dépendances)
COPY backend/mvnw .
COPY backend/.mvn .mvn
COPY backend/pom.xml .

# Télécharger les dépendances sans compiler le code source
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copier le code source et compiler
COPY backend/src ./src
RUN ./mvnw clean package -DskipTests -B

# ─────────────────────────────────────────────
# ÉTAPE 2 : Image finale légère pour l'exécution
# ─────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copier uniquement le JAR généré depuis l'étape builder
COPY --from=builder /app/target/*.jar app.jar

# Port exposé par Spring Boot
EXPOSE 8080

# Variables d'environnement par défaut (surchargeables au runtime)
ENV SPRING_PROFILES_ACTIVE=prod \
    JAVA_OPTS="-Xms256m -Xmx512m"

# Lancement de l'application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

