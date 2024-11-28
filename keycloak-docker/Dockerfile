
FROM quay.io/keycloak/keycloak:legacy

ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

COPY realm-export.json /opt/keycloak/data/import/realm-export.json

CMD ["-Dkeycloak.migration.action=import", \
     "-Dkeycloak.migration.provider=singleFile", \
     "-Dkeycloak.migration.file=/opt/keycloak/data/import/realm-export.json", \
     "-Dkeycloak.http.port=8080"]

