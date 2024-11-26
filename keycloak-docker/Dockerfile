# Use the official Keycloak image
FROM quay.io/keycloak/keycloak:legacy

# Set environment variables for Keycloak admin credentials
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

# Copy the realm export file into the container
COPY ../keycloak-config/realm-export.json /opt/keycloak/data/import/realm-export.json

# Configure Keycloak to automatically import the realm on startup
CMD ["-Dkeycloak.migration.action=import", \
     "-Dkeycloak.migration.provider=singleFile", \
     "-Dkeycloak.migration.file=/opt/keycloak/data/import/realm-export.json", \
     "-Dkeycloak.http.port=8080"]