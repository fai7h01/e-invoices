FROM quay.io/keycloak/keycloak:legacy

# Set Keycloak admin username and password
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

# Copy the Keycloak realm export file
COPY ./keycloak-config/realm-export.json /opt/keycloak/data/import/realm-export.json

# Run Keycloak with the correct command
CMD ["-Dkeycloak.http.port=8080", "-Dkeycloak.import=/opt/keycloak/data/import/realm-export.json", "-Dkeycloak.import-check-skip=true"]
