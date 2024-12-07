
FROM quay.io/keycloak/keycloak:legacy

ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

COPY ./keycloak-config/realm-export.json /opt/keycloak/data/import/realm-export.json

CMD ["-Dkeycloak.http.port=8080"]

