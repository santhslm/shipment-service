# Generated with Orion Generator - v1.1.5
# shipment-service
======================

<<A brief discription about the service >>

Service Version
V1

Service Url:
•	

List of operations:
•


Dependencies
------------
This project depends on following projects.

##Projects
- orion-gateway: https://github.comcast.com/Orion/orion-gateway.git
- deployment-manifests: https://github.comcast.com/Orion/deployment-manifests.git
- orion-properties: https://github.comcast.com:Orion/orion-properties.git

##Authorization/Authentication

### OAuth token is required to pass security
Provide HTTP header to your REST request:
Header name: "Authorization"
Header value: "Bearer {access_token}"

To get the access_token for Dev, QA and INT use Ping Federate Playground INT environment: https://websec-int.cable.comcast.com/OAuthPlayground/case4-client-credentials.jsp.
- Populate client id (for example: orion_dev) and client secret (for example: c02af9ff99dc45019fcba878fc90cb10)
- Provide scope value "orion:orion-dev" or customer specific scope like "orion:amdocs"
- Press Request token button
- Copy access_token value

Ping Federate Auth server for STG and PROD environments:
https://websec-stg.cable.comcast.com/OAuthPlayground/case4-client-credentials.jsp
https://websec.cable.comcast.com/OAuthPlayground/case4-client-credentials.jsp    (Production environment)

### WIKI
-  
