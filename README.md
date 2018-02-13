# Metro Ingestion Assessment 

This assessment is used as a introductory course to the SMACK stack

## Prerequisites 
- It is mandatory to use Scala and it's ecosystem for this coding challenge.
- Only use SMACK Stack technology for the implementation. (https://mesosphere.com/blog/smack-stack-new-lamp-stack/)
- The ingested data has to be integrated in a dedicated domain model that you define.
- The resulting artifacts have to be deployable as Docker container.
- Data Source: http://api.metro.net/agencies/lametro/vehicles/
- Map projection approach: Bing Maps Tile System (https://msdn.microsoft.com/en- us/library/bb259689.aspx)
- The ingested data has to be buffered before enrichment and persistence.
- The API has to be available for the assessment team for testing.
- There has to be unit tests available for the specified user stories.
- Optional: Cluster each component with a valuable amount of nodes

## Use Cases
1. As a User I want to have data collected from the given data source into a Fast Data IoT stack and has to be available as hot and cold data.

2. As a User I want to request a list of available vehicles out of a web based service API.
    - Endpoint: **http://{host}:{port}/api/vehicles/list**
    - Request Type: **GET**
    - Responses:
        - **200** – List[Vehicle]
        - **204** – Empty response
        - **500** - Error case with error message
        - Content-Type: `application/json`

3. As a User I want to request the last position of a vehicle out of a web based service API.
    - Endpoint: **http://{host}:{port}/api/ vehicles/vehicle/{vehicleId}/lastPosition**
    - Request Type: **GET**
    - URL Parameter:
        -  Vehicle ID
    -  Responses:
        - **200** - Trajectory of last position
        - **404**
        - **500** - Error case with error message
        - Content-Type: `application/json`

4. As a User I want to request map tiles containing hot and aggregated data out of a web based service API.

    Following requests have to be available:

    - Request to determine which tiles are filled with aggregated data / vehicles:
        - Endpoint: **http://{host}:{port}/api/tiles/filled**
        - Request Type: **GET**
        - Responses:
            - **200** - List[unique tile identifier]
            - **204** – Empty response
            - **500** - Error case with error message
            - Content-Type: `application/json`
        
    - Request to query a specific tile and get the vehicles currently located in the tile area:
        - Endpoint: **http://{host}:{port}/api/tiles/tile/{tile_id}/availableVehicles**
        - Request Type: **GET**
        - URL Parameter:
            - Tile identifier
        - Responses:
            - **200** - List[Vehicles]
            - **204** – Empty response
            - **500** - Error case with error message
            - Content-Type: `application/json`

    - Request to query a specific bounding box of tiles to get the data from use case nr. 2:
        - Endpoint: **http://{host}:{port}/api/tiles/{tile_list}/vehicleCount**
        - Request Type: **GET**
        - Request Parameters:
            - tile_list (semicolon separated list of tiles) 
        - Responses:
            - **200** - Map[unique tile identifier, Int] 
            - **401** - Bad request
            - **500** - Error case with error message
            - Content-Type: `application/json`
 
 ## Technical Guidelines
- Conceptually your system needs to be broken down into the following main components:
    - Metro Data Loader / Fetcher
    - Metro Data Ingestion / Enrichment 
    - Metro API
    
    as well as any additional components that you feel are neccessary to allow for scaling and separation of logic. 
- Determine which queries you need to support and store data in such a way to facilitate this. 
- One of your components must be responsible for merging hot and cold data. This means that your system must have the ability to merge data from storage (cassandra) and kafka to provide the most recent data set. 
- Pay attention to the defined response types and ensure that your API conforms to this. 
- Your components should be able to be monitored in some way to ensure that they are running and provide feedback on what it is doing. You can implements this in anyway you like, ex. Logging / Health Checks.
