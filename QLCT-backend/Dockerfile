# =========================
# BUILD STAGE
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy source code
COPY . .

# (1) Xóa cache Maven để tránh lỗi compiler
RUN rm -rf ~/.m2/repository

# (2) Clean + force update dependencies
# (3) Fix lỗi compiler plugin bằng cách update deps
RUN mvn clean package -U -DskipTests

# =========================
# RUN STAGE
# =========================
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy jar từ build stage
COPY --from=build /app/target/*.jar app.jar

# Run app
CMD ["java", "-jar", "app.jar"]
