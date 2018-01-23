import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return field value helps"

    request {
        url "/businessObjects/4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33/fields/Custom1/options"
        method GET()
    }

    response {
        status 200
        headers {
            contentType applicationJson()
        }
        body '''
                        [
                                    {
                                        "uuid": "f4ca61a2-c008-4e66-b026-4174466235e5",
                                        "fieldId": "A7F21EBC-3F4E-4767-85B6-F6C1AE15F152",
                                        "languageId": "en",
                                        "value": "Value1", 
                                        "description": "value 1 description"
                                    },
                                    {
                                        "uuid": "32e67c28-f80c-4264-afb2-8358a7602ea4",
                                        "fieldId": "0a18a56f-1ef2-4348-a48a-3df66bd625a4",
                                        "languageId": "en",
                                        "value": "Value2", 
                                        "description": "value 2 description"
                                    }
                            ]
             '''
                            
        
    }
}