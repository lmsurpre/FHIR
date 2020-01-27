#
# (C) Copyright IBM Corp. 2019, 2020
#
# SPDX-License-Identifier: Apache-2.0
#

# Set the instance_memory and enable to 90 days trail license to avoid memory allocation failures.
#/opt/ibm/db2/V11.5/adm/db2licm -r db2dec && /opt/ibm/db2/V11.5/adm/db2licm -a /var/db2/db2trial.lic
echo "Adding the 90-day trial license..."
/opt/ibm/db2/V11.5/adm/db2licm -a /var/db2/db2trial.lic

echo "Configuring automatic memory managment..."
su - db2inst1 -c "db2 update dbm cfg using INSTANCE_MEMORY AUTOMATIC"

# Create the FHIR database and give fhirserver access
# Note Update transaction log file size (logfilsiz) to avoid
# Note 32K pagesize is required

# Create the database if it doesn't yet exist
echo "Creating FHIRDB..."
if [[ $(su - db2inst1 -c "db2 list db directory" | grep alias | grep FHIRDB | awk '{print $4}') == "FHIRDB" ]]; then
  echo "FHIRDB exists - skipping create"
else
  su - db2inst1 -c "db2 CREATE DB FHIRDB using codeset UTF-8 territory us PAGESIZE 32768"
fi

# To be properly idempotent, we always want to execute this, just in case it failed the first time
su - db2inst1 -c "db2 \"connect to fhirdb\" && db2 \"grant connect on database TO USER fhirserver\""
