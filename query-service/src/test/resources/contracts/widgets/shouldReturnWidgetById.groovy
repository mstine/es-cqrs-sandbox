package contracts.widgets

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/widgets/1'
    }
    response {
        status 200
        body(
                [id: '1', name: 'Larry']
        )
        headers {
            contentType(applicationJson())
        }
    }
}