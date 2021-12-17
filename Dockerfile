ARG JAVA_VERSION

FROM amazoncorretto:${JAVA_VERSION} as javabuilder
RUN mkdir /build/
WORKDIR /build

# Provide sources
COPY java .
RUN ./mvnw package



FROM golang:1.16 as gobuilder
WORKDIR /
COPY go.* ./
RUN go mod download
COPY main.go .
COPY --from=javabuilder /build/target/evilfactory-1.0-SNAPSHOT.jar .
COPY --from=javabuilder /build/target/classes/EvilFactory.class .
RUN CGO_ENABLED=0 GOOS=linux go build -a -tags app -o app -ldflags '-w' .


FROM scratch
COPY --from=gobuilder /app /app
ENTRYPOINT ["/app"]
EXPOSE 3000
EXPOSE 1389
