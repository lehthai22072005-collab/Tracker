FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# 👉 THÊM DÒNG NÀY
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/*.jar"]
