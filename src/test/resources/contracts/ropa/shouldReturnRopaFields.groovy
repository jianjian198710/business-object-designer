import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return ropa fields"

    request {
        url "/businessObjects/4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33/fields"
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
                                        "uuid": "c479d16b-0b0f-4c4f-8af8-ed403b89afc3",
                                        "name": "Custom1", 
                                        "type": "String",
                                        "isMandatory": false,
                                        "isVisible": true,
                                        "isCustField": true,
                                        "isMultiInput": false,
                                        "isValueSet": true,
                                        "createdAt": 1516089558466,
                                        "creatorBy": "Jane Huang",
                                        "changedAt": 1516089558466,
                                        "changedBy": "Jane Huang",
                                        "businessObjectFieldTextList": [{
                                            "uuid": "3e61ca1b-7bdf-44a9-bb0d-1666488bd47d",
                                            "languageId": "en",
                                            "fieldShortDescription": "Custom Field 1",
                                            "description": "Custom Field 1"
                                        }]
                                    },
                                    {
                                        "uuid": "56e61124-ac3e-42b9-81d1-1775c6704867",
                                        "name": "Custom2", 
                                        "type": "Date",
                                        "isCustField": true,
                                        "isValueSet": false,
                                        "isMandatory": false,
                                        "isVisible": false,
                                        "createdAt": 1516089558466,
                                        "creatorBy": "Jane Huang",
                                        "changedAt": 1516089558466,
                                        "changedBy": "Jane Huang",
                                        "businessObjectFieldTextList": [{
                                            "uuid": "921a7e46-8ad6-4f77-ab7b-cbd372b7aa65",
                                            "languageId": "en",
                                            "fieldShortDescription": "Custom Field 2",
                                            "description": "Custom Field 2"
                                        }]
                                    }
                            ]
             '''
                            
        
    }
}