# ignite-clients
This dependency contain:
1. A simple **ignite clients** util connector based on properies file.
2. An useful abstraction of a simple logic for **object persistence**.

## Cluster clients
First, the class ClientProvider, on first load, search the file ClientProvider.property_filename in the  classloader resource path (getClassLoader().getResourceAsStream(...)), if not present, read the property system ClientProvider.systemProperty_property_filename.

The property file, can contain *n* cluster config file, or better to say, *n* cluster endpoits.
An example of the properies file can be the following:

"src/main/resources/igniteClient.properties"

    default.clusterTag=testnet
    default.uri=https://somesite.com/ignite-config-for-clients-testnet.xml
    
    1.clusterTag=mainnet
    1.uri=https://somesite.com/ignite-config-for-clients-mainnet.xml
    
    2.clusterTag=other
    2.uri=/path/to/ignite-config-for-clients-oher.xml

then, calling ClientProvider.instance().ignite("mainnet"), result an Ignite.class instace that represent the client node connection to the "ignite-config-for-clients-mainnet.xml" cluster.
Calling ClientProvider.instance().ignite(), tip to "testnet" cluster, that is, what is specified by "default.xxxxx"

 - ***clusterTag***: is, in fact, the name given to the cluster by the server nodes.
 - ***uri***: is the node clients ignite config file (spring.xml beans file). It can be a URL of a file on the filesystem.

## Object persistence

*Under development*
