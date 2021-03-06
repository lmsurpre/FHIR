# IBM FHIR Server - fhir-persistence-schema

Builds and manages the multi-tenant FHIR R4 RDBMS schema (Db2). Includes Derby support for use in tests.

This module is built into two different jar files. The default jar is included with the IBM FHIR Server web application and is used for bootstrapping Apache Derby databases (if configured). There is also an executable command line interface (cli) version of this jar that packages this module with all of its dependencies.

The executable command line interface (cli) version of this module can be downloaded from the project [Releases tab](https://github.com/IBM/FHIR/releases).

## Getting started
### Creating the database

To create the Db2 database and database user, use the following commands:

``` shell
useradd fhirserver
su - db2inst1 -c "db2 CREATE DB FHIRDB using codeset UTF-8 territory us PAGESIZE 32768"
su - db2inst1 -c "db2 \"connect to fhirdb\" && db2 \"grant connect on database TO USER fhirserver\""
```

### Printing the schema

To print the schema DDL for review, execute `com.ibm.fhir.schema.app.SchemaPrinter`:

``` shell
java -cp ./fhir-persistence-schema-${VERSION}-cli.jar com.ibm.fhir.schema.app.SchemaPrinter [--to-file]
```

Note: Replace `${VERSION}` with the version of the jar you're using or use the wildcard `*` to match any version.

With `--to-file` it outputs to `./schema.sql`, `./grants.sql`, and `./stored-procedures.sql`; otherwise to System.out.

### Connection properties

The `fhir-persistence-schema` tool uses a properties file for database connection information.

|Property|Description|
|--------|-----------|
|db.host | The database server hostname|
|db.port | The database server port|
|db.database | The name of the database|
|user | A username with connect and admin permissions on the target database|
|password | The user password for connecting to the database|

A sample properties file can be found at https://github.com/IBM/FHIR/blob/master/fhir-persistence-schema/db2.properties

Alternatively, properties may be passed via the command line interface `--prop` flag (`--prop <propname>=<propvalue>`).

## Execute the fhir-persistence-schema command line interface (CLI)

To deploy and manage the schema on a target database, the `fhir-persistence-schema` project includes a Main class that can parallelize the schema updates.

``` shell
java -jar ./fhir-persistence-schema-${VERSION}-cli.jar [OPTIONS]
```

Note: Replace `${VERSION}` with the version of the jar you're using or use the wildcard `*` to match any version.

The following sections include common values for `OPTIONS`.

### Drop tables from FHIR_ADMIN and FHIRDATA

```
--prop-file db2.properties
--schema-name FHIRDATA
--drop-schema
--drop-admin
--confirm-drop
```

### Create new schema

```
--prop-file db2.properties
--schema-name FHIRDATA
--create-schemas
```

### Deploy new schema

```
--prop-file db2.properties
--schema-name FHIRDATA
--update-schema
```

### Grant privileges to data access user

```
--prop-file db2.properties
--schema-name FHIRDATA
--grant-to FHIRSERVER
```

### Add a new tenant (e.g. default)

Don't forget to copy the tenant-key secret generated by --allocate-tenant. This key must be added to the datasource configuration to authorize the FHIR server to access this tenant.

```
--prop-file db2.properties
--schema-name FHIRDATA
--allocate-tenant default
```

Note: for tenant names other than `default`, the server must determine the tenant id to use for each request.
By default, we get the tenant id from the `X-FHIR-TENANT-ID` header, but to trust this value requires a well-planned approach to security.
Once the server has determined the tenant id for a given request, it uses this to look up the tenantKey and the two are
used in conjunction to create or retrieve data for this tenant.
For more information on multi-tenancy, see section [4.9 Multi-tenancy of the IBM FHIR Server Users Guide](https://ibm.github.io/FHIR/guides/FHIRServerUsersGuide#49-multi-tenancy).

### Configure tenant-key (example)

Edit `wlp/usr/servers/fhir-server/config/default/fhir-server-config.json` and add the tenant-key captured from the add-tenant step above:

```
                "default": {
                    "tenantKey": "<the-base64-tenant-key>",
                    "type": "db2",
                    "connectionProperties": {
                        "serverName": "<db2-host-name>",
                        "portNumber": 50001,
                        "databaseName": "BLUDB",
                        "apiKey": "<your-db2-api-key>",
                        "securityMechanism": 15,
                        "pluginName": "IBMIAMauth",
                        "currentSchema": "FHIRDATA",
                        "driverType": 4,
                        "sslConnection": true,
                        "sslTrustStoreLocation": "resources/security/dbTruststore.jks",
                        "sslTrustStorePassword": "<your-ssl-truststore-password>"
                    }
                }
```


### Test a tenant

```
--prop-file db2.properties
--schema-name FHIRDATA
--test-tenant default
--tenant-key "<the-base64-tenant-key>"
```

### Update the stored procedures for FHIRDATA (and not FHIR_ADMIN)

```
--prop-file db2.properties
--schema-name FHIRDATA
--update-proc
```

## Alternative: manually apply the schema

To manually apply the DDL to a Db2 instance:

1 - Print the schema to files by executing the SchemaPrinter:

*Linux/Mac*  

```
VERSION=4.0.1
java -jar ./fhir-persistence-schema-${VERSION}-cli.jar --to-file
```

*Windows*

```
set VERSION=4.0.1
java -jar ./fhir-persistence-schema-%VERSION%-cli.jar --to-file
```

Note: the jar file is stored locally in `fhir-persistence-schema/target` or in the Artifactory repository for this project.

2 - Connect to your instance and execute each of the following:
    - schema.sql:  `db2 -tvf schema.sql`
    - grants.sql:  `db2 -tvf grants.sql`
    - stored-procedures.sql:  `db2 -td@ -vf stored-procedures.sql`


FHIR® is the registered trademark of HL7 and is used with the permission of HL7.
