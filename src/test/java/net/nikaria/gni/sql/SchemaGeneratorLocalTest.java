package net.nikaria.gni.sql;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.AnnotatedTypeScanner;

import javax.persistence.Entity;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.Set;

public class SchemaGeneratorLocalTest {

    private Path newFile = Paths.get("target/sql/flyway", "V1.00.000__baseline_db_create.sql");

    @Test
    public void generateDatabaseSchema() {
        MetadataSources metadataSources = createMetadataSources();

        Set<Class<?>> entityClasses = this.findallEntityClasses();

        entityClasses.forEach(metadataSources::addAnnotatedClass);

//        MetadataImplementor metadata = (MetadataImplementor)metadataSources.getMetadataBuilder().build();

        this.writeSchemaToFile(this.newFile, metadataSources);
    }

    private void writeSchemaToFile(Path newFile, MetadataSources metadataSources) {
        MetadataImplementor metadata = (MetadataImplementor) metadataSources.buildMetadata();

        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setHaltOnError(true);
        schemaExport.setFormat(true);
        schemaExport.setDelimiter(";");

        schemaExport.setOutputFile(newFile.toFile().getAbsolutePath());
        schemaExport.create(EnumSet.of(TargetType.SCRIPT), metadata);
    }

    private MetadataSources createMetadataSources() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.hbm2ddl.auto", "create")
                .applySetting("hibernate.connection.url", "jdbc:h2:mem:db1;DB_CLOSE_DELAY=-1")
                .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .applySetting("hibernate.id.new_generator_mapping", "true").build();

        return new MetadataSources(standardRegistry);
    }

    private Set<Class<?>> findallEntityClasses() {
        AnnotatedTypeScanner annotatedTypeScanner = new AnnotatedTypeScanner(true, Entity.class);
        return annotatedTypeScanner.findTypes("net.nikaria.gni");
    }
}
